package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.core.common.constant.CommonConstant;
import cn.exrick.xboot.your.dao.OrderDetailDao;
import cn.exrick.xboot.your.entity.OrderDetail;
import cn.exrick.xboot.your.service.OrderDetailService;
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
 * 统筹单详情接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Override
    public OrderDetailDao getRepository() {
        return orderDetailDao;
    }

    @Override
    public Page<OrderDetail> findByCondition(OrderDetail orderDetail, SearchVo searchVo, Pageable pageable) {

        return orderDetailDao.findAll(new Specification<OrderDetail>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<OrderDetail> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                // TODO 请根据实际需求修改你的搜索条件
                Path<String> orderIdField = root.get("orderId");
                Path<String> feeTypeField = root.get("feeType");
                Path<Integer> insuranceMoneyField = root.get("insuranceMoney");
                Path<java.math.BigDecimal> baseFeeField = root.get("baseFee");
                Path<java.math.BigDecimal> feeRateField = root.get("feeRate");
                Path<java.math.BigDecimal> actualFeeField = root.get("actualFee");
                Path<java.math.BigDecimal> deductibleFeeField = root.get("deductibleFee");
                Path<java.math.BigDecimal> everyFeeField = root.get("everyFee");
                Path<Integer> siteCountField = root.get("siteCount");
                Path<Date> createTimeField = root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                // 统筹单id模糊搜素
                if(StrUtil.isNotBlank(orderDetail.getOrderId())){
                    list.add(cb.equal(orderIdField,orderDetail.getOrderId()));
                }
                // 费率类型：车损 三者等  数据字典维护模糊搜素
                if(StrUtil.isNotBlank(orderDetail.getFeeType())){
                    list.add(cb.like(feeTypeField,'%'+orderDetail.getFeeType()+'%'));
                }
                // 保额  单位万相等匹配
                if(orderDetail.getInsuranceMoney()!=null){
                    list.add(cb.equal(insuranceMoneyField, orderDetail.getInsuranceMoney()));
                }
                // 基础保费相等匹配
                if(orderDetail.getBaseFee()!=null){
                    list.add(cb.equal(baseFeeField, orderDetail.getBaseFee()));
                }
                // 费率相等匹配
                if(orderDetail.getFeeRate()!=null){
                    list.add(cb.equal(feeRateField, orderDetail.getFeeRate()));
                }
                // 应收保费相等匹配
                if(orderDetail.getActualFee()!=null){
                    list.add(cb.equal(actualFeeField, orderDetail.getActualFee()));
                }
                // 不计免赔相等匹配
                if(orderDetail.getDeductibleFee()!=null){
                    list.add(cb.equal(deductibleFeeField, orderDetail.getDeductibleFee()));
                }
                // 每人统筹费相等匹配
                if(orderDetail.getEveryFee()!=null){
                    list.add(cb.equal(everyFeeField, orderDetail.getEveryFee()));
                }
                // 应收保费相等匹配
                if(orderDetail.getSiteCount()!=null){
                    list.add(cb.equal(siteCountField, orderDetail.getSiteCount()));
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
    public List<OrderDetail> findByOrderIdAndType(String orderId) {

        return orderDetailDao.findByOrderIdOrderByCreateTimeDesc(orderId);
    }
}