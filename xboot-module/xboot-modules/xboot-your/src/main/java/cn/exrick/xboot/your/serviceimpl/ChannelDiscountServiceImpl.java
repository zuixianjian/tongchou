package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.dao.ChannelDiscountDao;
import cn.exrick.xboot.your.entity.ChannelDiscount;
import cn.exrick.xboot.your.service.ChannelDiscountService;
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
 * 渠道折扣配置接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class ChannelDiscountServiceImpl implements ChannelDiscountService {

    @Autowired
    private ChannelDiscountDao channelDiscountDao;

    @Override
    public ChannelDiscountDao getRepository() {
        return channelDiscountDao;
    }

    @Override
    public Page<ChannelDiscount> findByCondition(ChannelDiscount channelDiscount, SearchVo searchVo, Pageable pageable) {

        return channelDiscountDao.findAll(new Specification<ChannelDiscount>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<ChannelDiscount> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                // TODO 请根据实际需求修改你的搜索条件
                Path<String> departmentIdField = root.get("departmentId");
                Path<java.math.BigDecimal> discountRateField = root.get("discountRate");
                Path<Date> createTimeField = root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                // 渠道id模糊搜素
                if(StrUtil.isNotBlank(channelDiscount.getDepartmentId())){
                    list.add(cb.like(departmentIdField,'%'+channelDiscount.getDepartmentId()+'%'));
                }
                // 渠道折扣相等匹配
                if(channelDiscount.getDiscountRate()!=null){
                    list.add(cb.equal(discountRateField, channelDiscount.getDiscountRate()));
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
    public List<ChannelDiscount> findByDepartMentId(String departMentId) {
        return channelDiscountDao.findByDepartmentId(departMentId);
    }
}