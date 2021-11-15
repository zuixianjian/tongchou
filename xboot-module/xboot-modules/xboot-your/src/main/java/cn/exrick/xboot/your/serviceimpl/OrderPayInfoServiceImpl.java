package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.core.common.constant.CommonConstant;
import cn.exrick.xboot.core.common.exception.XbootException;
import cn.exrick.xboot.core.common.utils.SecurityUtil;
import cn.exrick.xboot.core.common.utils.SnowFlakeUtil;
import cn.exrick.xboot.your.dao.*;
import cn.exrick.xboot.your.entity.*;
import cn.exrick.xboot.your.entity.Order;
import cn.exrick.xboot.your.entity.req.StatisticBussinessReq;
import cn.exrick.xboot.your.entity.req.StatisticOrderReq;
import cn.exrick.xboot.your.entity.resp.StatisticBussinessItemResp;
import cn.exrick.xboot.your.entity.resp.StatisticBussinessResp;
import cn.exrick.xboot.your.entity.resp.StatisticOrderItemResp;
import cn.exrick.xboot.your.entity.resp.StatisticOrderResp;
import cn.exrick.xboot.your.mapper.OrderMapper;
import cn.exrick.xboot.your.mapper.OrderPayInfoMapper;
import cn.exrick.xboot.your.service.OrderPayInfoService;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * 批单/统筹单收款单接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class OrderPayInfoServiceImpl implements OrderPayInfoService {

    @Autowired
    private OrderPayInfoDao orderPayInfoDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private SecurityUtil securityUtil;
    @Autowired
    private HelperOrderDao helperOrderDao;
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private OrderFileDao orderFileDao;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderPayInfoMapper orderPayInfoMapper;
    @Override
    public OrderPayInfoDao getRepository() {
        return orderPayInfoDao;
    }

    @Override
    public Page<OrderPayInfo> findByCondition(OrderPayInfo orderPayInfo, SearchVo searchVo, Pageable pageable) {

        return orderPayInfoDao.findAll(new Specification<OrderPayInfo>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<OrderPayInfo> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                // TODO 请根据实际需求修改你的搜索条件
                Path<String> bussinessTypeField = root.get("bussinessType");
                Path<String> bussinessIdField = root.get("bussinessId");
                Path<String> recieveManField = root.get("recieveMan");
                Path<java.math.BigDecimal> shouldRecieveField = root.get("shouldRecieve");
                Path<java.math.BigDecimal> shouldCleanRecieveField = root.get("shouldCleanRecieve");
                Path<java.math.BigDecimal> realRecieveField = root.get("realRecieve");
                Path<String> recieveTypeField = root.get("recieveType");
                Path<String> sendNoField = root.get("sendNo");
                Path<String> recieveEvidenceField = root.get("recieveEvidence");
                Path<String> recieveStatusField = root.get("recieveStatus");
                Path<java.time.LocalDateTime> recieveTimeField = root.get("recieveTime");
                Path<Date> createTimeField = root.get("createTime");

                Path<String> departmentIdField = root.get("departmentId");

                List<Predicate> list = new ArrayList<Predicate>();
                // 数据权限过滤
                List<String> depIds = securityUtil.getDeparmentIds();
                if(depIds!=null&&depIds.size()>0){
                    list.add(departmentIdField.in(depIds));
                }

                // 收款单类型模糊搜素
                if(StrUtil.isNotBlank(orderPayInfo.getBussinessType())){
                    list.add(cb.like(bussinessTypeField,'%'+orderPayInfo.getBussinessType()+'%'));
                }
                // 业务id模糊搜素
                if(StrUtil.isNotBlank(orderPayInfo.getBussinessId())){
                    list.add(cb.like(bussinessIdField,'%'+orderPayInfo.getBussinessId()+'%'));
                }
                // 收款人模糊搜素
                if(StrUtil.isNotBlank(orderPayInfo.getRecieveMan())){
                    list.add(cb.like(recieveManField,'%'+orderPayInfo.getRecieveMan()+'%'));
                }
                // 应收全款相等匹配
                if(orderPayInfo.getShouldRecieve()!=null){
                    list.add(cb.equal(shouldRecieveField, orderPayInfo.getShouldRecieve()));
                }
                // 应收净费相等匹配
                if(orderPayInfo.getShouldCleanRecieve()!=null){
                    list.add(cb.equal(shouldCleanRecieveField, orderPayInfo.getShouldCleanRecieve()));
                }
                // 实收款项相等匹配
                if(orderPayInfo.getRealRecieve()!=null){
                    list.add(cb.equal(realRecieveField, orderPayInfo.getRealRecieve()));
                }
                // 收款方式模糊搜素
                if(StrUtil.isNotBlank(orderPayInfo.getRecieveType())){
                    list.add(cb.like(recieveTypeField,'%'+orderPayInfo.getRecieveType()+'%'));
                }
                // 付款账号模糊搜素
                if(StrUtil.isNotBlank(orderPayInfo.getSendNo())){
                    list.add(cb.like(sendNoField,'%'+orderPayInfo.getSendNo()+'%'));
                }
                // 收款凭证附件模糊搜素
                if(StrUtil.isNotBlank(orderPayInfo.getRecieveEvidence())){
                    list.add(cb.like(recieveEvidenceField,'%'+orderPayInfo.getRecieveEvidence()+'%'));
                }
                // 收款单状态模糊搜素
                if(StrUtil.isNotBlank(orderPayInfo.getRecieveStatus())){
                    list.add(cb.like(recieveStatusField,'%'+orderPayInfo.getRecieveStatus()+'%'));
                }
                // 收款时间相等匹配
                if(orderPayInfo.getRecieveTime()!=null){
                    list.add(cb.equal(recieveTimeField, orderPayInfo.getRecieveTime()));
                }

                // 创建时间
                if (searchVo!=null)
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
    @Transactional
    public void paySuccess(OrderPayInfo entity) {
        if (entity.getRealRecieve().compareTo(entity.getShouldCleanRecieve())== -1){
            throw new XbootException("实收(付)款额不得小于应收净费");
        }
        if (entity.getBussinessType().equals(CommonConstant.ORDER_TYPE_COMMON)){
            //统筹单
            //确认收款
            Order order = orderDao.findById(entity.getBussinessId()).get();
            if (ObjectUtil.isEmpty(order)) {
                throw new XbootException("该统筹单不存在");
            }
            if (!order.getOrderStatus().equals(CommonConstant.ORDER_STATUS_WAITE_PAY)) {
                throw new XbootException("该统筹单状态不可操作");
            }
            //更新统筹单状态
            order.setOrderStatus(CommonConstant.ORDER_STATUS_FINISH);

            orderDao.save(order);

        }else if (entity.getBussinessType().equals(CommonConstant.ORDER_TYPE_EDIT)){
            //批单
            Order order = orderDao.findById(entity.getBussinessId()).get();
            if (ObjectUtil.isEmpty(order)) {
                throw new XbootException("该批单不存在");
            }
            if (!order.getOrderStatus().equals(CommonConstant.ORDER_STATUS_WAITE_PAY)) {
                throw new XbootException("该批单状态不可操作");
            }
            if (!order.getOrderType().equals(CommonConstant.ORDER_TYPE_EDIT)) {
                throw new XbootException("非法操作");
            }
            //批单收款确认生效
            editOrderPay(order);
        }else if (entity.getBussinessType().equals(CommonConstant.ORDER_TYPE_HELP)){
            //互助单
            HelperOrder helperOrder = helperOrderDao.findById(entity.getBussinessId()).get();
            if (ObjectUtil.isEmpty(helperOrder)) {
                throw new XbootException("该互助单不存在");
            }
            if (!helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_WAITFORPAY)) {
                throw new XbootException("该互助单状态不可操作");
            }
            helperOrder.setStatus(CommonConstant.PAYINFO_STATUS_SUCCESS_TO);
            helperOrderDao.save(helperOrder);
        }
        //更新收款信息
        entity.setRecieveStatus(CommonConstant.PAYINFO_STATUS_SUCCESS);
        entity.setRecieveMan(securityUtil.getCurrUser().getUsername());
        entity.setRecieveTime(LocalDateTime.now());
        orderPayInfoDao.save(entity);
    }
    //批单收款处理
    public void editOrderPay(Order order){
        //更新批单状态
        order.setOrderStatus(CommonConstant.ORDER_STATUS_FINISH);
        orderDao.save(order);

        //将关联的orderdetail全部删除
        orderDetailDao.modifyByOrderId(order.getParentId());
        //将关联的文件删除
        orderFileDao.modifyByOrderId(order.getParentId());
        List<OrderDetail> orderDetails = orderDetailDao.findByOrderIdOrderByCreateTimeDesc(order.getId());
        List<OrderFile> orderFiles = orderFileDao.findByOrderIdOrderByCreateTimeDesc(order.getId());
        orderDetails.forEach(item -> {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(item,orderDetail);
            orderDetail.setOrderId(order.getParentId());
            orderDetail.setId(SnowFlakeUtil.nextId().toString());
            orderDetailDao.save(orderDetail);
        });
        orderFiles.forEach(item -> {
            OrderFile orderFile = new OrderFile();
            BeanUtils.copyProperties(item,orderFile);
            orderFile.setOrderId(order.getParentId());
            orderFile.setId(SnowFlakeUtil.nextId().toString());
            orderFileDao.save(orderFile);
        });
        //更新原统筹单数据
        Order orderParent = orderDao.findById(order.getParentId()).get();
        BeanUtils.copyProperties(order,orderParent);
        orderParent.setId(order.getParentId());
        orderParent.setOrderStatus(CommonConstant.ORDER_STATUS_FINISH);
        orderParent.setParentId(null);
        orderParent.setOrderType(CommonConstant.ORDER_TYPE_COMMON);
        orderParent.setOriginData(null);
        orderDao.save(orderParent);
    }


    @Override
    public StatisticOrderResp statisticOrder(StatisticOrderReq statisticOrderReq) {
        StatisticOrderResp statisticOrderResp = orderMapper.statisticOrderMoney(statisticOrderReq);
        List<StatisticOrderItemResp> statisticOrderResps= orderMapper.statisticOrder(statisticOrderReq);
        if (statisticOrderResp==null){
            statisticOrderResp=new StatisticOrderResp();
        }
        if (statisticOrderResps==null){
            statisticOrderResps=new ArrayList<>();

        }
        statisticOrderResp.setStatisticOrderItemResps(statisticOrderResps);

        return statisticOrderResp;
    }

    @Override
    public StatisticBussinessResp statisticBussiness(StatisticBussinessReq statisticBussinessReq) {

        List<StatisticBussinessItemResp> statisticBussinessResps= orderMapper.statisticBussiness(statisticBussinessReq);
        statisticBussinessReq.setStatisticType(null);
        List<StatisticBussinessItemResp> statisticBussinessRespsTotal= orderMapper.statisticBussiness(statisticBussinessReq);
        StatisticBussinessItemResp statisticBussinessItemResp=null;
        if (statisticBussinessRespsTotal!=null&&statisticBussinessRespsTotal.size()>0){
            statisticBussinessItemResp=statisticBussinessRespsTotal.get(0);
        }
        StatisticBussinessResp statisticBussinessResp = new StatisticBussinessResp();
        statisticBussinessResp.setStatisticBussinessItemResps(statisticBussinessResps);
        statisticBussinessResp.setAccidentFinishCount(statisticBussinessItemResp.getAccidentFinishCount());
        statisticBussinessResp.setAccidentLogCount(statisticBussinessItemResp.getAccidentLogCount());
        statisticBussinessResp.setCarCount(statisticBussinessItemResp.getCarCount());
        statisticBussinessResp.setDamageTotalMoney(statisticBussinessItemResp.getDamageTotalMoney());
        statisticBussinessResp.setOrderCompensateMoney(statisticBussinessItemResp.getOrderCompensateMoney());
        statisticBussinessResp.setRealRecieveTotal(statisticBussinessItemResp.getRealRecieveTotal());
        statisticBussinessResp.setShouldCleanRecieveTotal(statisticBussinessItemResp.getShouldCleanRecieveTotal());
        statisticBussinessResp.setShouldRecieveTotal(statisticBussinessItemResp.getShouldRecieveTotal());
        return statisticBussinessResp;
    }
}