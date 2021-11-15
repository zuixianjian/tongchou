package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.dao.ClientInfoDao;
import cn.exrick.xboot.your.entity.ClientInfo;
import cn.exrick.xboot.your.service.ClientInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.exrick.xboot.core.common.vo.SearchVo;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import java.util.List;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.lang.reflect.Field;

/**
 * 客户信息维护接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class ClientInfoServiceImpl implements ClientInfoService {

    @Autowired
    private ClientInfoDao clientInfoDao;

    @Override
    public ClientInfoDao getRepository() {
        return clientInfoDao;
    }

    @Override
    public Page<ClientInfo> findByCondition(ClientInfo clientInfo, SearchVo searchVo, Pageable pageable) {

        return clientInfoDao.findAll(new Specification<ClientInfo>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<ClientInfo> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                // TODO 请根据实际需求修改你的搜索条件
                Path<String> nameField = root.get("name");
                Path<String> clientTypeField = root.get("clientType");
                Path<String> idNoField = root.get("idNo");
                Path<String> telephoneField = root.get("telephone");
                Path<Date> createTimeField = root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                // 客户姓名模糊搜素
                if(StrUtil.isNotBlank(clientInfo.getName())){
                    list.add(cb.like(nameField,'%'+clientInfo.getName()+'%'));
                }
                // 客户性质模糊搜素
                if(StrUtil.isNotBlank(clientInfo.getClientType())){
                    list.add(cb.like(clientTypeField,'%'+clientInfo.getClientType()+'%'));
                }
                // 证件号码模糊搜素
                if(StrUtil.isNotBlank(clientInfo.getIdNo())){
                    list.add(cb.equal(idNoField,clientInfo.getIdNo()));
                }
                // 手机号码模糊搜素
                if(StrUtil.isNotBlank(clientInfo.getTelephone())){
                    list.add(cb.equal(telephoneField,clientInfo.getTelephone()));
                }

                // 创建时间
                if (searchVo!=null){
                    if(StrUtil.isNotBlank(searchVo.getStartDate())&&StrUtil.isNotBlank(searchVo.getEndDate())){
                        Date start = DateUtil.parse(searchVo.getStartDate());
                        Date end = DateUtil.parse(searchVo.getEndDate());
                        list.add(cb.between(createTimeField, start, DateUtil.endOfDay(end)));
                    }
                }


                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);
    }

}