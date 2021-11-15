package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.dao.OrderFileDao;
import cn.exrick.xboot.your.entity.OrderFile;
import cn.exrick.xboot.your.service.OrderFileService;
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
 * 统筹单附件接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class OrderFileServiceImpl implements OrderFileService {

    @Autowired
    private OrderFileDao orderFileDao;

    @Override
    public OrderFileDao getRepository() {
        return orderFileDao;
    }

    @Override
    public Page<OrderFile> findByCondition(OrderFile orderFile, SearchVo searchVo, Pageable pageable) {

        return orderFileDao.findAll(new Specification<OrderFile>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<OrderFile> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                // TODO 请根据实际需求修改你的搜索条件
                Path<String> orderIdField = root.get("orderId");
                Path<String> fileUrlField = root.get("fileUrl");
                Path<Date> createTimeField = root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                // 统筹单id模糊搜素
                if(StrUtil.isNotBlank(orderFile.getOrderId())){
                    list.add(cb.like(orderIdField,'%'+orderFile.getOrderId()+'%'));
                }
                // 附件地址模糊搜素
                if(StrUtil.isNotBlank(orderFile.getFileUrl())){
                    list.add(cb.like(fileUrlField,'%'+orderFile.getFileUrl()+'%'));
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

    @Override
    public List<OrderFile> findByOrderId(String orderId) {
        return orderFileDao.findByOrderIdOrderByCreateTimeDesc(orderId);
    }
}