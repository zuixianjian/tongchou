package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.dao.HelperDamageDao;
import cn.exrick.xboot.your.entity.HelperDamage;
import cn.exrick.xboot.your.service.HelperDamageService;
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
 * 互助单损失主表接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class HelperDamageServiceImpl implements HelperDamageService {

    @Autowired
    private HelperDamageDao helperDamageDao;

    @Override
    public HelperDamageDao getRepository() {
        return helperDamageDao;
    }

    @Override
    public Page<HelperDamage> findByCondition(HelperDamage helperDamage, SearchVo searchVo, Pageable pageable) {

        return helperDamageDao.findAll(new Specification<HelperDamage>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<HelperDamage> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                // TODO 请根据实际需求修改你的搜索条件
                Path<String> helperOrderIdField = root.get("helperOrderId");
                Path<String> helperDamageClassifyIdField = root.get("helperDamageClassifyId");
                Path<String> carNoField = root.get("carNo");
                Path<String> idNoField = root.get("idNo");
                Path<Date> createTimeField = root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                // 互助单id模糊搜素
                if(StrUtil.isNotBlank(helperDamage.getHelperOrderId())){
                    list.add(cb.like(helperOrderIdField,'%'+helperDamage.getHelperOrderId()+'%'));
                }
                // 互助单损失主表分类id模糊搜素
                if(StrUtil.isNotBlank(helperDamage.getHelperDamageClassifyId())){
                    list.add(cb.like(helperDamageClassifyIdField,'%'+helperDamage.getHelperDamageClassifyId()+'%'));
                }
                // 车牌号模糊搜素
                if(StrUtil.isNotBlank(helperDamage.getCarNo())){
                    list.add(cb.like(carNoField,'%'+helperDamage.getCarNo()+'%'));
                }
                // 证件号模糊搜素
                if(StrUtil.isNotBlank(helperDamage.getIdNo())){
                    list.add(cb.like(idNoField,'%'+helperDamage.getIdNo()+'%'));
                }

                // 创建时间
                if(StrUtil.isNotBlank(searchVo.getStartDate())&&StrUtil.isNotBlank(searchVo.getEndDate())){
                    Date start = DateUtil.parse(searchVo.getStartDate());
                    Date end = DateUtil.parse(searchVo.getEndDate());
                    list.add(cb.between(createTimeField, start, DateUtil.endOfDay(end)));
                }

                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);
    }

}