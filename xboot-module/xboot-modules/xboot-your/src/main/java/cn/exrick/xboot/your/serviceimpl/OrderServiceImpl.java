package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.core.common.annotation.SystemLog;
import cn.exrick.xboot.core.common.constant.CommonConstant;
import cn.exrick.xboot.core.common.enums.LogType;
import cn.exrick.xboot.core.common.exception.XbootException;
import cn.exrick.xboot.core.common.lock.Callback;
import cn.exrick.xboot.core.common.lock.RedisLockTemplate;
import cn.exrick.xboot.core.common.utils.SecurityUtil;
import cn.exrick.xboot.core.common.utils.SnowFlakeUtil;
import cn.exrick.xboot.core.config.interceptor.WebMvcConfig;
import cn.exrick.xboot.core.entity.Department;
import cn.exrick.xboot.core.entity.Log;
import cn.exrick.xboot.core.entity.User;
import cn.exrick.xboot.core.service.LogService;
import cn.exrick.xboot.your.dao.HelperOrderDao;
import cn.exrick.xboot.your.dao.OrderDao;
import cn.exrick.xboot.your.dao.OrderDetailDao;
import cn.exrick.xboot.your.dao.OrderFileDao;
import cn.exrick.xboot.your.entity.*;
import cn.exrick.xboot.your.entity.Order;
import cn.exrick.xboot.your.entity.req.OrderCreateReq;
import cn.exrick.xboot.your.entity.resp.OrderDetailResp;
import cn.exrick.xboot.your.mapper.LogMapper;
import cn.exrick.xboot.your.mapper.OrderMapper;
import cn.exrick.xboot.your.service.*;
import cn.exrick.xboot.your.utils.CommonUtils;
import cn.exrick.xboot.your.utils.ConvertUpMoneyUtil;
import cn.exrick.xboot.your.utils.FreeMarkUtils;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.exrick.xboot.core.common.vo.SearchVo;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

/**
 * ?????????????????????
 *
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private WebMvcConfig webMvcConfig;
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderFileDao orderFileDao;
    @Autowired
    private SecurityUtil securityUtil;

    @Override
    public OrderDao getRepository() {
        return orderDao;
    }

    @Autowired
    private RedisLockTemplate redisLockTemplate;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private ChannelDiscountService channelDiscountService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private IDictDataService dictDataService;
    @Autowired
    private FreeMarkUtils freeMarkUtils;
    @Autowired
    private OrderPayInfoService orderPayInfoService;

    @Autowired
    private LogMapper logMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private LogService logService;
    @Autowired
    private OrderFileService orderFileService;
    @Autowired
    private HelperOrderDao helperOrderDao;
    @Override
    public Page<Order> findByCondition(Order order, SearchVo searchVo, Pageable pageable) {

        return orderDao.findAll(new Specification<Order>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                // TODO ?????????????????????????????????????????????
                Path<String> timeLimitTypeField = root.get("timeLimitType");
                Path<String> monthField = root.get("month");
                Path<java.time.LocalDateTime> startDateField = root.get("startDate");
                Path<java.time.LocalDateTime> endDateField = root.get("endDate");
                Path<java.math.BigDecimal> shortDiscountField = root.get("shortDiscount");
                Path<String> clientNameField = root.get("clientName");
                Path<String> clientCardNoField = root.get("clientCardNo");
                Path<String> clientTypeField = root.get("clientType");
                Path<String> clientPhoneField = root.get("clientPhone");
                Path<String> handleNameField = root.get("handleName");
                Path<String> handleCardNoField = root.get("handleCardNo");
                Path<String> handlePhoneField = root.get("handlePhone");
                Path<String> runCardNameField = root.get("runCardName");
                Path<String> runCardCardNoField = root.get("runCardCardNo");
                Path<String> runCardPhoneField = root.get("runCardPhone");
                Path<String> carTypeAField = root.get("carTypeA");
                Path<String> carTypeBField = root.get("carTypeB");
                Path<String> carTypeNameField = root.get("carTypeName");
                Path<java.math.BigDecimal> carNewPriceField = root.get("carNewPrice");
//                Path<String> carNoHeadField = root.get("carNoHead");
                Path<String> carNoField = root.get("carNo");
                Path<String> useTypeField = root.get("useType");
                Path<String> belongTypeField = root.get("belongType");
                Path<String> runCardCarTypeField = root.get("runCardCarType");
                Path<String> carSieralTypeField = root.get("carSieralType");
                Path<Integer> siteCountField = root.get("siteCount");
                Path<java.time.LocalDateTime> firstLogDateField = root.get("firstLogDate");
                Path<java.math.BigDecimal> beforeMonthOldRatePriceField = root.get("beforeMonthOldRatePrice");
                Path<java.math.BigDecimal> monthOldRateField = root.get("monthOldRate");
                Path<java.math.BigDecimal> monthOldRatePriceField = root.get("monthOldRatePrice");
                Path<java.math.BigDecimal> negotiatePriceField = root.get("negotiatePrice");
                Path<String> motorNoField = root.get("motorNo");
                Path<String> carframeNoField = root.get("carframeNo");
                Path<Boolean> isCheckCarField = root.get("isCheckCar");
                Path<String> orderStatusField = root.get("orderStatus");
                Path<java.math.BigDecimal> totalFeeField = root.get("totalFee");
                Path<String> feeFloatReasonField = root.get("feeFloatReason");
                Path<String> feeFloatRateField = root.get("feeFloatRate");
                Path<java.math.BigDecimal> feeFloatField = root.get("feeFloat");
                Path<String> orderAgreeField = root.get("orderAgree");
                Path<java.math.BigDecimal> discountRateField = root.get("discountRate");
                Path<java.math.BigDecimal> afterDiscountMoneyField = root.get("afterDiscountMoney");
                Path<String> remarkField = root.get("remark");
                Path<String> orderTypeField = root.get("orderType");
                Path<String> parentIdField = root.get("parentId");
                Path<String> originDataField = root.get("originData");
                Path<Date> createTimeField = root.get("createTime");
                Path<String> idField = root.get("id");
                Path<String> departmentIdField = root.get("departmentId");

                List<Predicate> list = new ArrayList<Predicate>();
                // ??????????????????
                List<String> depIds = securityUtil.getDeparmentIds();
                if(depIds!=null&&depIds.size()>0){
                    list.add(departmentIdField.in(depIds));
                }
                //?????????id??????
                if (StrUtil.isNotBlank(order.getQueryId())) {
                    list.add(cb.like(idField, '%' + order.getQueryId() + '%'));
                }
                // ????????????????????????
                if (StrUtil.isNotBlank(order.getTimeLimitType())) {
                    list.add(cb.like(timeLimitTypeField, '%' + order.getTimeLimitType() + '%'));
                }

                // ????????????????????????
                if (StrUtil.isNotBlank(order.getMonth())) {
                    list.add(cb.like(monthField, '%' + order.getMonth() + '%'));
                }
                // ??????????????????????????????
                if (order.getStartDate() != null) {
                    list.add(cb.equal(startDateField, order.getStartDate()));
                }
                // ??????????????????????????????
                if (order.getEndDate() != null) {
                    list.add(cb.equal(endDateField, order.getEndDate()));
                }
                // ????????????????????????
                if (order.getShortDiscount() != null) {
                    list.add(cb.equal(shortDiscountField, order.getShortDiscount()));
                }
                // ??????????????????????????????
                if (StrUtil.isNotBlank(order.getClientName())) {
                    list.add(cb.like(clientNameField, '%' + order.getClientName() + '%'));
                }
                // ????????????????????????????????????
                if (StrUtil.isNotBlank(order.getClientCardNo())) {
                    list.add(cb.like(clientCardNoField, '%' + order.getClientCardNo() + '%'));
                }
                // ??????????????????  ????????????????????????
                if (StrUtil.isNotBlank(order.getClientType())) {
                    list.add(cb.like(clientTypeField, '%' + order.getClientType() + '%'));
                }
                // ??????????????????????????????
                if (StrUtil.isNotBlank(order.getClientPhone())) {
                    list.add(cb.like(clientPhoneField, '%' + order.getClientPhone() + '%'));
                }
                // ???????????????????????????
                if (StrUtil.isNotBlank(order.getHandleName())) {
                    list.add(cb.like(handleNameField, '%' + order.getHandleName() + '%'));
                }
                // ?????????????????????????????????
                if (StrUtil.isNotBlank(order.getHandleCardNo())) {
                    list.add(cb.like(handleCardNoField, '%' + order.getHandleCardNo() + '%'));
                }
                // ???????????????????????????
                if (StrUtil.isNotBlank(order.getHandlePhone())) {
                    list.add(cb.like(handlePhoneField, '%' + order.getHandlePhone() + '%'));
                }
                // ?????????????????????????????????
                if (StrUtil.isNotBlank(order.getRunCardName())) {
                    list.add(cb.like(runCardNameField, '%' + order.getRunCardName() + '%'));
                }
                // ???????????????????????????????????????
                if (StrUtil.isNotBlank(order.getRunCardCardNo())) {
                    list.add(cb.like(runCardCardNoField, '%' + order.getRunCardCardNo() + '%'));
                }
                // ?????????????????????????????????
                if (StrUtil.isNotBlank(order.getRunCardPhone())) {
                    list.add(cb.like(runCardPhoneField, '%' + order.getRunCardPhone() + '%'));
                }
                // ????????????????????????
                if (StrUtil.isNotBlank(order.getCarTypeA())) {
                    list.add(cb.like(carTypeAField, '%' + order.getCarTypeA() + '%'));
                } if (StrUtil.isNotBlank(order.getCarTypeB())) {
                    list.add(cb.like(carTypeBField, '%' + order.getCarTypeB() + '%'));
                }
                // ????????????????????????
                if (StrUtil.isNotBlank(order.getCarTypeName())) {
                    list.add(cb.like(carTypeNameField, '%' + order.getCarTypeName() + '%'));
                }
                // ???????????????????????????
                if (order.getCarNewPrice() != null) {
                    list.add(cb.equal(carNewPriceField, order.getCarNewPrice()));
                }
                // ??????   ??????  ??? ???   ??????????????????????????????
//                if (StrUtil.isNotBlank(order.getCarNoHead())) {
//                    list.add(cb.like(carNoHeadField, '%' + order.getCarNoHead() + '%'));
//                }
                // ??????????????????????????????
                if (StrUtil.isNotBlank(order.getCarNo())) {
                    list.add(cb.like(carNoField, '%' + order.getCarNo() + '%'));
                }
                // ????????????????????????
                if (StrUtil.isNotBlank(order.getUseType())) {
                    list.add(cb.like(useTypeField, '%' + order.getUseType() + '%'));
                }
                // ????????????????????????
                if (StrUtil.isNotBlank(order.getBelongType())) {
                    list.add(cb.like(belongTypeField, '%' + order.getBelongType() + '%'));
                }
                // ?????????????????????????????????
                if (StrUtil.isNotBlank(order.getRunCardCarType())) {
                    list.add(cb.like(runCardCarTypeField, '%' + order.getRunCardCarType() + '%'));
                }
                // ????????????????????????
                if (StrUtil.isNotBlank(order.getCarSieralType())) {
                    list.add(cb.like(carSieralTypeField, '%' + order.getCarSieralType() + '%'));
                }
                // ?????????????????????
                if (order.getSiteCount() != null) {
                    list.add(cb.equal(siteCountField, order.getSiteCount()));
                }
                // ????????????????????????
                if (order.getFirstLogDate() != null) {
                    list.add(cb.equal(firstLogDateField, order.getFirstLogDate()));
                }
                // ???????????????????????????
                if (order.getBeforeMonthOldRatePrice() != null) {
                    list.add(cb.equal(beforeMonthOldRatePriceField, order.getBeforeMonthOldRatePrice()));
                }
                // ????????????????????????
                if (order.getMonthOldRate() != null) {
                    list.add(cb.equal(monthOldRateField, order.getMonthOldRate()));
                }
                // ???????????????????????????
                if (order.getMonthOldRatePrice() != null) {
                    list.add(cb.equal(monthOldRatePriceField, order.getMonthOldRatePrice()));
                }
                // ????????????????????????
                if (order.getNegotiatePrice() != null) {
                    list.add(cb.equal(negotiatePriceField, order.getNegotiatePrice()));
                }
                // ????????????????????????
                if (StrUtil.isNotBlank(order.getMotorNo())) {
                    list.add(cb.like(motorNoField, '%' + order.getMotorNo() + '%'));
                }
                // ?????????????????????
                if (StrUtil.isNotBlank(order.getCarframeNo())) {
                    list.add(cb.like(carframeNoField, '%' + order.getCarframeNo() + '%'));
                }
                // ??????????????????????????????
                if (order.getIsCheckCar() != null) {
                    list.add(cb.equal(isCheckCarField, order.getIsCheckCar()));
                }
                // ???????????????????????????
                if (StrUtil.isNotBlank(order.getOrderStatus())) {
                    list.add(cb.like(orderStatusField, '%' + order.getOrderStatus() + '%'));
                }
                // ???????????????????????????
                if (order.getTotalFee() != null) {
                    list.add(cb.equal(totalFeeField, order.getTotalFee()));
                }
                // ??????????????????(??????)????????????
                if (StrUtil.isNotBlank(order.getFeeFloatReason())) {
                    list.add(cb.like(feeFloatReasonField, '%' + order.getFeeFloatReason() + '%'));
                }
                // ??????????????????(??????)????????????
                if (StrUtil.isNotBlank(order.getFeeFloatRate())) {
                    list.add(cb.like(feeFloatRateField, '%' + order.getFeeFloatRate() + '%'));
                }
                // ?????????????????????????????????
                if (order.getFeeFloat() != null) {
                    list.add(cb.equal(feeFloatField, order.getFeeFloat()));
                }
                // ????????????????????????
                if (StrUtil.isNotBlank(order.getOrderAgree())) {
                    list.add(cb.like(orderAgreeField, '%' + order.getOrderAgree() + '%'));
                }
                // ????????????????????????
                if (order.getDiscountRate() != null) {
                    list.add(cb.equal(discountRateField, order.getDiscountRate()));
                }
                // ??????????????????????????????????????????
                if (order.getAfterDiscountMoney() != null) {
                    list.add(cb.equal(afterDiscountMoneyField, order.getAfterDiscountMoney()));
                }
                // ????????????????????????
                if (StrUtil.isNotBlank(order.getRemark())) {
                    list.add(cb.like(remarkField, '%' + order.getRemark() + '%'));
                }
                // ?????????????????????????????????????????????
                if (StrUtil.isNotBlank(order.getOrderType())) {
                    list.add(cb.like(orderTypeField, '%' + order.getOrderType() + '%'));
                }
                // ?????????id????????????
                if (StrUtil.isNotBlank(order.getParentId())) {
                    list.add(cb.like(parentIdField, '%' + order.getParentId() + '%'));
                }
                // ????????????????????????????????????
                if (StrUtil.isNotBlank(order.getOriginData())) {
                    list.add(cb.like(originDataField, '%' + order.getOriginData() + '%'));
                }

                // ????????????
                if (searchVo != null) {
                    if (StrUtil.isNotBlank(searchVo.getStartDate()) && StrUtil.isNotBlank(searchVo.getEndDate())) {
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
    @Override
    public List<Order> findByCarNoAndFrameNoAndOrderId(String carNoHead,String carNo,String carframeNo,String id,String orderStatus){
        Order order=new Order();
        if (StrUtil.isNotBlank(carNoHead)){
            order.setCarNoHead(carNoHead);
        }
        if (StrUtil.isNotBlank(carNo)){
            order.setCarNo(carNo);
        }
        if (StrUtil.isNotBlank(carframeNo)){
            order.setCarframeNo(carframeNo);
        }
        if (StrUtil.isNotBlank(orderStatus)){
            order.setOrderStatus(orderStatus);
        }
        if (StrUtil.isNotBlank(id)){
            order.setId(id);
        }else {
            order.setId(null);
        }
        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<Order> example = Example.of(order ,matcher);

        List<Order> orders = orderDao.findAll(example);
        return orders;
    }

    @Override
    public void orderCreate(OrderCreateReq orderCreateReq) {

        // ????????????????????????
        User user = securityUtil.getCurrUser();
        //????????????????????????????????????????????????(????????????????????????)
        BigDecimal discount = null;
        if (user.getDepartmentId() != null) {
            List<Department> departments = departmentService.getAllHeadDepartmentsByDepartmentId(user.getDepartmentId());
            if (departments != null) {
                for (Department department : departments) {
                    List<ChannelDiscount> channelDiscounts = channelDiscountService.findByDepartMentId(department.getId());
                    if (channelDiscounts != null && channelDiscounts.size() > 0) {
                        discount = channelDiscounts.get(0).getDiscountRate();
                        break;
                    }
                }

            }
        }
        if (discount != null) {
            //??????????????????
            orderCreateReq.getOrder().setDiscountRate(discount);
            orderCreateReq.getOrder().setAfterDiscountMoney(discount.multiply(orderCreateReq.getOrder().getFeeFloat()));
        }
        orderCreateReq.getOrder().setOrderType(CommonConstant.ORDER_TYPE_COMMON);
        orderCreateReq.getOrder().setOrderStatus(CommonConstant.ORDER_STATUS_ZANCUN);
        if (securityUtil.getCurrUser().getDepartmentId()!=null)
        orderCreateReq.getOrder().setDepartmentId(securityUtil.getCurrUser().getDepartmentId());
        Order order=createOrer(orderCreateReq.getOrder());
        List<OrderDetail> orderDetails = orderCreateReq.getOrderDetails();
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrderId(order.getId());
            orderDetailDao.save(orderDetail);
        }
        List<OrderFile> orderFiles = orderCreateReq.getOrderFiles();
        for (OrderFile orderFile : orderFiles) {
            orderFile.setOrderId(order.getId());
            orderFileDao.save(orderFile);
        }
    }


    public Order createOrer(Order order){
        Order orderR = orderDao.save(order);
        return orderR;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void orderEdit(OrderCreateReq orderEditReq) {
        String orderId = orderEditReq.getOrder().getId();
        Order order = orderDao.findById(orderId).get();
        if (ObjectUtil.isEmpty(order)) {
            throw new XbootException("?????????????????????");
        }
        //?????????????????????????????????????????????
        if (!order.getOrderStatus().equals(CommonConstant.ORDER_STATUS_AUDIT_FAILURE) &&
                !order.getOrderStatus().equals(CommonConstant.ORDER_STATUS_ZANCUN)) {
            throw new XbootException("??????????????????????????????");
        }

        redisLockTemplate.execute("order:" + orderId, 3, TimeUnit.SECONDS, new Callback() {
            @Override
            public Object onGetLock() throws InterruptedException {
                //?????????  ???????????????????????????
                orderDao.saveAndFlush(orderEditReq.getOrder());
                List<OrderDetail> orderDetails = orderEditReq.getOrderDetails();
                List<OrderFile> orderFiles = orderEditReq.getOrderFiles();
                //????????????orderdetail????????????
                orderDetailDao.modifyByOrderId(orderId);
                //????????????????????????
                orderFileDao.modifyByOrderId(orderId);
                orderDetails.forEach(item -> {
                    item.setOrderId(orderId);
                    item.setId(SnowFlakeUtil.nextId().toString());
                    orderDetailDao.save(item);
                });
                orderFiles.forEach(item -> {
                    item.setOrderId(orderId);
                    item.setId(SnowFlakeUtil.nextId().toString());
                    orderFileDao.save(item);
                });
                return null;
            }

            @Override
            public Object onTimeout() throws InterruptedException {
                //???????????????
                throw new XbootException("????????????????????????????????????????????????");
            }
        });
    }

    @Override
    public String baojia(String orderId) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat dateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
        Order order = orderDao.findById(orderId).get();

        HashMap<String, Object> wordHashMap = new HashMap<>();
        HashMap<String, Object> orderMap = new HashMap<>();
        List<Log> aTrues = logMapper.findByDataflagAndlogDescAndType(2, orderId, "true");
        if (aTrues!=null&&aTrues.size()>0){
            orderMap.put("checkName", aTrues.get(0).getUsername());
        }else {
            orderMap.put("checkName", "");
        }
        orderMap.put("id", order.getId());
        orderMap.put("clientName", order.getClientName());
        orderMap.put("clientCardNo", order.getClientCardNo());
        orderMap.put("carNoHead", order.getCarNoHead()==null?"":order.getCarNoHead());
        orderMap.put("carNo", order.getCarNo());
        orderMap.put("carTypeName", order.getCarTypeName());
        orderMap.put("motorNo", order.getMotorNo());
        orderMap.put("carframeNo", order.getCarframeNo());
        orderMap.put("carTypeA", dictDataService.getByDictTypeAndDictValue("carTypeA",order.getCarTypeA()).getTitle());
        orderMap.put("carTypeB", dictDataService.getByDictTypeAndDictValue("carTypeB",order.getCarTypeB()).getTitle());
        orderMap.put("useType", dictDataService.getByDictTypeAndDictValue("useType",order.getUseType()).getTitle());
        orderMap.put("totalFeeBig", ConvertUpMoneyUtil.toChinese(order.getTotalFee()+""));
        orderMap.put("siteCount", order.getSiteCount()==null?0:order.getSiteCount());
        orderMap.put("totalFee", order.getTotalFee());
        orderMap.put("loadCapacity",order.getLoadCapacity()==null?0:order.getLoadCapacity());
        orderMap.put("displacement",order.getDisplacement()==null?0:order.getDisplacement());
        orderMap.put("startDate", dtf.format(order.getStartDate()));
        orderMap.put("endDate", dtf.format(order.getEndDate()));
        orderMap.put("orderAgree", order.getOrderAgree()==null?"":order.getOrderAgree());
        orderMap.put("createTime", dateFormat.format(new Date()));
        orderMap.put("createBy", order.getCreateBy());
        orderMap.put("useYear", CommonUtils.yearCompare( order.getFirstLogDate(),new Date()));
        orderMap.put("firstLogDate", dateFormatDay.format(order.getFirstLogDate()));
        ArrayList<HashMap<String, Object>> orderDetails = new ArrayList<>();
        List<OrderDetail> ods = orderDetailService.findByOrderIdAndType(orderId);
        if (orderDetails != null) {
            ods.forEach(item->{
                HashMap<String, Object> orderDetail = new HashMap<>();
                orderDetail.put("feeType", dictDataService.getByDictTypeAndDictValue("feeType",item.getFeeType()).getTitle());
                orderDetail.put("deductibleFee", item.getDeductibleFee()==null?0:item.getDeductibleFee());
                orderDetail.put("insuranceMoney", item.getInsuranceMoney()==null?0:item.getInsuranceMoney());
                orderDetail.put("actualFee", item.getActualFee()==null?0:item.getInsuranceMoney());
                orderDetails.add(orderDetail);
            });
        }
        wordHashMap.put("orderDetails",orderDetails);
        wordHashMap.put("order",orderMap);
        String fileName = freeMarkUtils.getFreemarkerContentInputStreamForBaojia(wordHashMap, orderId);
        return fileName;
    }

    @Override
    public void commitForAudit(String orderId) {
        Order order = orderDao.findById(orderId).get();
        if (ObjectUtil.isEmpty(order)) {
            throw new XbootException("?????????????????????");
        }
        if (!order.getOrderStatus().equals(CommonConstant.ORDER_STATUS_AUDIT_FAILURE) &&
                !order.getOrderStatus().equals(CommonConstant.ORDER_STATUS_ZANCUN)) {
            throw new XbootException("??????????????????????????????");
        }
        //???????????????????????????????????????????????????????????????????????????????????????
        List<Order> orders = orderDao.findByCarNo(order.getCarNo());
        if (orders!=null){
            for (Order order1 : orders) {
                if (CommonConstant.ORDER_STATUS_WAIT_AUDIT.equals(order1.getOrderStatus())){
                    //?????????
                    throw new XbootException("?????????????????????????????????");
                }
                if (CommonConstant.ORDER_STATUS_WAITE_PAY.equals(order1.getOrderStatus())){
                    //?????????
                    throw new XbootException("?????????????????????????????????");
                }
            }
        }
        redisLockTemplate.execute("order:" + orderId, 3, TimeUnit.SECONDS, new Callback() {
            @Override
            public Object onGetLock() throws InterruptedException {
                //?????????  ???????????????????????????
                orderDao.changeStatus(order.getId(),CommonConstant.ORDER_STATUS_WAIT_AUDIT);
                return null;
            }

            @Override
            public Object onTimeout() throws InterruptedException {
                //???????????????
                throw new XbootException("????????????????????????????????????????????????");
            }
        });


    }

    @Override
    @Transactional
    public void orderAudit(String[] ids, Boolean auditResult) {
        if (ids!=null){
            for (String id : ids) {
                Order order = orderDao.findById(id).get();
                if (ObjectUtil.isEmpty(order)) {
                    throw new XbootException("?????????????????????");
                }
                if (!order.getOrderStatus().equals(CommonConstant.ORDER_STATUS_WAIT_AUDIT)) {
                    throw new XbootException("??????????????????????????????");
                }
                redisLockTemplate.execute("order:" + id, 3, TimeUnit.SECONDS, new Callback() {
                    @Override
                    public Object onGetLock() throws InterruptedException {
                        //?????????  ???????????????????????????
                        if (auditResult){
                            orderDao.changeStatus(order.getId(),CommonConstant.ORDER_STATUS_WAITE_PAY);
                            //???????????????????????????
                            OrderPayInfo orderPayInfo = new OrderPayInfo();
                            orderPayInfo.setBussinessId(order.getId());
                            orderPayInfo.setBussinessType(CommonConstant.ORDER_TYPE_COMMON);//???????????????
                            orderPayInfo.setShouldRecieve(order.getFeeFloat());
                            orderPayInfo.setShouldCleanRecieve(order.getAfterDiscountMoney()==null?order.getFeeFloat():order.getAfterDiscountMoney());
                            orderPayInfo.setRecieveStatus(CommonConstant.PAYINFO_STATUS_WAIT);
                            if (securityUtil.getCurrUser().getDepartmentId()!=null)
                                orderPayInfo.setDepartmentId(securityUtil.getCurrUser().getDepartmentId());
                            orderPayInfoService.save(orderPayInfo);
                        }else{
                            orderDao.changeStatus(order.getId(),CommonConstant.ORDER_STATUS_AUDIT_FAILURE);
                        }
                        return null;
                    }

                    @Override
                    public Object onTimeout() throws InterruptedException {
                        //???????????????
                        throw new XbootException("????????????????????????????????????????????????");
                    }
                });

            }
        }

    }

    @Override
    public void delete(String id) {
        //??????????????????
        Order order = orderDao.findById(id).get();
        if (ObjectUtil.isEmpty(order)) {
            throw new XbootException("?????????:"+id+"?????????");
        }
        if (order.getOrderStatus().equals(CommonConstant.ORDER_STATUS_FINISH)) {
            throw new XbootException("?????????:"+id+"????????????????????????");
        }
        orderDao.deleteById(id);
    }


    @Override
    public String insuranceCert(String orderId) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat dateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
        Order order = orderDao.findById(orderId).get();
        if (ObjectUtil.isEmpty(order)) {
            throw new XbootException("?????????????????????");
        }
        if (!order.getOrderStatus().equals(CommonConstant.ORDER_STATUS_FINISH)) {
            throw new XbootException("?????????????????????????????????");
        }
        HashMap<String, Object> wordHashMap = new HashMap<>();
        HashMap<String, Object> orderMap = new HashMap<>();
        List<Log> aTrues = logMapper.findByDataflagAndlogDescAndType(2, orderId, "true");
        if (aTrues!=null&&aTrues.size()>0){
            orderMap.put("checkName", aTrues.get(0).getUsername());
        }else {
            orderMap.put("checkName", "");
        }
        orderMap.put("id", order.getId());
        orderMap.put("clientName", order.getClientName());
        orderMap.put("clientCardNo", order.getClientCardNo());
        orderMap.put("carNoHead", order.getCarNoHead()==null?"":order.getCarNoHead());
        orderMap.put("carNo", order.getCarNo());
        orderMap.put("carTypeName", order.getCarTypeName());
        orderMap.put("motorNo", order.getMotorNo());
        orderMap.put("carframeNo", order.getCarframeNo());
        orderMap.put("carTypeA", dictDataService.getByDictTypeAndDictValue("carTypeA",order.getCarTypeA()).getTitle());
        orderMap.put("carTypeB", dictDataService.getByDictTypeAndDictValue("carTypeB",order.getCarTypeB()).getTitle());
        orderMap.put("useType", dictDataService.getByDictTypeAndDictValue("useType",order.getUseType()).getTitle());
        orderMap.put("totalFeeBig", ConvertUpMoneyUtil.toChinese(order.getTotalFee()+""));
        orderMap.put("siteCount", order.getSiteCount()==null?0:order.getSiteCount());
        orderMap.put("totalFee", order.getTotalFee());
        orderMap.put("loadCapacity",order.getLoadCapacity()==null?0:order.getLoadCapacity());
        orderMap.put("displacement",order.getDisplacement()==null?0:order.getDisplacement());
        orderMap.put("startDate", dtf.format(order.getStartDate()));
        orderMap.put("endDate", dtf.format(order.getEndDate()));
        orderMap.put("orderAgree", order.getOrderAgree()==null?"":order.getOrderAgree());
        orderMap.put("createTime", dateFormat.format(new Date()));
        orderMap.put("createBy", order.getCreateBy());
        orderMap.put("useYear", CommonUtils.yearCompare( order.getFirstLogDate(),new Date()));
        orderMap.put("firstLogDate", dateFormatDay.format(order.getFirstLogDate()));
        ArrayList<HashMap<String, Object>> orderDetails = new ArrayList<>();
        List<OrderDetail> ods = orderDetailService.findByOrderIdAndType(orderId);
        if (orderDetails != null) {
            ods.forEach(item->{
                HashMap<String, Object> orderDetail = new HashMap<>();
                orderDetail.put("feeType", dictDataService.getByDictTypeAndDictValue("feeType",item.getFeeType()).getTitle());
                orderDetail.put("deductibleFee", item.getDeductibleFee()==null?0:item.getDeductibleFee());
                orderDetail.put("insuranceMoney", item.getInsuranceMoney()==null?0:item.getInsuranceMoney());
                orderDetail.put("actualFee", item.getActualFee()==null?0:item.getInsuranceMoney());
                orderDetails.add(orderDetail);
            });
        }
        wordHashMap.put("orderDetails",orderDetails);
        wordHashMap.put("order",orderMap);
        String fileName = freeMarkUtils.getFreemarkerContentInputStreamForInsurance(wordHashMap, orderId);
        return fileName;
    }

    //??????????????????
    @Override
    public void validOrder() {
        orderMapper.validOrder();
    }

    @Override
    public List<Log> getOrderActionLog(String orderId) {
        Pageable pageable = PageRequest.of(0,100, Sort.Direction.DESC,"createTime");
        Page<Log> logs = logService.findByConfition(2, orderId, null, pageable);
        return logs.getContent();
    }

    @Override
    public List<OrderDetailResp> queryOrder(String carNo, String carframeNo, String queryId) {
        Order order = new Order();
        order.setCarNo(carNo);
        order.setCarframeNo(carframeNo);
        order.setId(queryId);
        order.setOrderStatus(CommonConstant.ORDER_STATUS_FINISH);
        order.setOrderType(CommonConstant.ORDER_TYPE_COMMON);//?????????
        ArrayList<OrderDetailResp> results = new ArrayList<>();
        List<Order> orders=orderMapper.queryOrder(order);
        if (orders!=null&&orders.size()>0){

            orders.forEach(item->{
                OrderDetailResp orderDetailResp = new OrderDetailResp();
                orderDetailResp.setOrder(item);
                List<OrderDetail> orderDetails = orderDetailService.findByOrderIdAndType(item.getId());
                orderDetailResp.setOrderDetails(orderDetails);
                List<OrderFile> orderFiles = orderFileService.findByOrderId(item.getId());
                orderDetailResp.setOrderFiles(orderFiles);
                results.add(orderDetailResp);

            });
        }
        return results;
    }

    @Override
    public void editOrderCommitForAudit(OrderCreateReq orderEditReq) {
        String orderId = orderEditReq.getOrder().getId();
        Order order = orderDao.findById(orderId).get();
        if (ObjectUtil.isEmpty(order)) {
            throw new XbootException("?????????????????????");
        }

        //?????????????????????????????????????????????
        if (!order.getOrderStatus().equals(CommonConstant.ORDER_STATUS_FINISH)) {
            throw new XbootException("??????????????????????????????");
        }
        //????????????????????????????????????????????????
        Order orderQuery = new Order();
        orderQuery.setParentId(orderEditReq.getOrder().getId());
        orderQuery.setOrderType(CommonConstant.ORDER_TYPE_EDIT);
        orderQuery.setId(null);
        List<Order> orders = orderMapper.queryOrder(orderQuery);
        if (orders!=null){
            for (Order order1 : orders) {
                if (order1.getOrderStatus().equals(CommonConstant.ORDER_STATUS_WAITE_PAY)||
                        order1.getOrderStatus().equals(CommonConstant.ORDER_STATUS_WAIT_AUDIT)){
                    throw new XbootException("?????????????????????????????????");
                }
            }
        }
        //????????????????????????????????????????????????
        ArrayList<String> statuss = new ArrayList<>();
        statuss.add(CommonConstant.HELPORDER_STATUS_CHECK);
        statuss.add(CommonConstant.HELPORDER_STATUS_DIAOCHA);
        statuss.add(CommonConstant.HELPORDER_STATUS_WAITFORPAY);
        statuss.add(CommonConstant.HELPORDER_STATUS_CHECK);
        List<HelperOrder> helpOrdes = helperOrderDao.findByOrderIdAndStatusIn(orderEditReq.getOrder().getId(), statuss);
        if (helpOrdes!=null&&helpOrdes.size()>0){
            throw new XbootException("?????????????????????????????????");
        }
        // ????????????????????????
        User user = securityUtil.getCurrUser();
        //????????????????????????????????????????????????(????????????????????????)
        BigDecimal discount = null;
        if (user.getDepartmentId() != null) {
            List<Department> departments = departmentService.getAllHeadDepartmentsByDepartmentId(user.getDepartmentId());
            if (departments != null) {
                for (Department department : departments) {
                    List<ChannelDiscount> channelDiscounts = channelDiscountService.findByDepartMentId(department.getId());
                    if (channelDiscounts != null && channelDiscounts.size() > 0) {
                        discount = channelDiscounts.get(0).getDiscountRate();
                        break;
                    }
                }

            }
        }
        if (discount != null) {
            //??????????????????
            orderEditReq.getOrder().setDiscountRate(discount);
            orderEditReq.getOrder().setAfterDiscountMoney(discount.multiply(orderEditReq.getOrder().getFeeFloat()));
        }
        orderEditReq.getOrder().setOrderType(CommonConstant.ORDER_TYPE_EDIT);
        orderEditReq.getOrder().setOrderStatus(CommonConstant.ORDER_STATUS_WAIT_AUDIT);
        if (securityUtil.getCurrUser().getDepartmentId()!=null)
            orderEditReq.getOrder().setDepartmentId(securityUtil.getCurrUser().getDepartmentId());
        //??????????????????????????????
        List<OrderDetailResp> orderDetailResps = queryOrder(null, null, orderEditReq.getOrder().getId());
        if (orderDetailResps==null||orderDetailResps.size()==0||orderDetailResps.size()>1){
            throw new XbootException("?????????????????????");
        }

        orderEditReq.getOrder().setOriginData(new Gson().toJson(orderDetailResps.get(0)));
        //??????id
        orderEditReq.getOrder().setParentId(orderEditReq.getOrder().getId());
        orderEditReq.getOrder().setId( SnowFlakeUtil.nextId().toString());

        Order orderCreate=createOrer(orderEditReq.getOrder());
        List<OrderDetail> orderDetails = orderEditReq.getOrderDetails();
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrderId(orderCreate.getId());
            orderDetailDao.save(orderDetail);
        }
        List<OrderFile> orderFiles = orderEditReq.getOrderFiles();
        for (OrderFile orderFile : orderFiles) {
            orderFile.setOrderId(orderCreate.getId());
            orderFileDao.save(orderFile);
        }
    }


    @Override
    public void orderEditEdit(OrderCreateReq orderEditReq) {
        String orderId = orderEditReq.getOrder().getId();
        Order order = orderDao.findById(orderId).get();
        if (ObjectUtil.isEmpty(order)) {
            throw new XbootException("??????????????????");
        }
        if (!order.getOrderType().equals(CommonConstant.ORDER_TYPE_EDIT)){
            throw new XbootException("????????????");
        }
        if (!order.getOrderStatus().equals(CommonConstant.ORDER_STATUS_AUDIT_FAILURE)){
            throw new XbootException("???????????????????????????");
        }


        redisLockTemplate.execute("order:" + orderId, 3, TimeUnit.SECONDS, new Callback() {
            @Override
            public Object onGetLock() throws InterruptedException {
                //?????????  ???????????????????????????
                orderEditReq.getOrder().setOrderStatus(CommonConstant.ORDER_STATUS_WAIT_AUDIT);
                orderDao.saveAndFlush(orderEditReq.getOrder());
                List<OrderDetail> orderDetails = orderEditReq.getOrderDetails();
                List<OrderFile> orderFiles = orderEditReq.getOrderFiles();
                //????????????orderdetail????????????
                orderDetailDao.modifyByOrderId(orderId);
                //????????????????????????
                orderFileDao.modifyByOrderId(orderId);
                orderDetails.forEach(item -> {
                    item.setOrderId(orderId);
                    item.setId(SnowFlakeUtil.nextId().toString());
                    orderDetailDao.save(item);
                });
                orderFiles.forEach(item -> {
                    item.setOrderId(orderId);
                    item.setId(SnowFlakeUtil.nextId().toString());
                    orderFileDao.save(item);
                });
                return null;
            }

            @Override
            public Object onTimeout() throws InterruptedException {
                //???????????????
                throw new XbootException("????????????????????????????????????????????????");
            }
        });
    }


    @Override
    public void orderEditAudit(String[] ids, Boolean auditResult) {
        if (ids!=null){
            for (String id : ids) {
                Order order = orderDao.findById(id).get();
                if (!order.getOrderType().equals(CommonConstant.ORDER_TYPE_EDIT)){
                    throw new XbootException("????????????");
                }
                if (ObjectUtil.isEmpty(order)) {
                    throw new XbootException("??????????????????");
                }
                if (!order.getOrderStatus().equals(CommonConstant.ORDER_STATUS_WAIT_AUDIT)) {
                    throw new XbootException("???????????????????????????");
                }
                //????????????
                Order orderParent = orderDao.findById(order.getParentId()).get();
                redisLockTemplate.execute("order:" + id, 3, TimeUnit.SECONDS, new Callback() {
                    @Override
                    public Object onGetLock() throws InterruptedException {
                        //?????????  ???????????????????????????
                        if (auditResult){
                            orderDao.changeStatus(order.getId(),CommonConstant.ORDER_STATUS_WAITE_PAY);
                            //???????????????????????????
                            OrderPayInfo orderPayInfo = new OrderPayInfo();
                            orderPayInfo.setBussinessId(order.getId());
                            orderPayInfo.setBussinessType(CommonConstant.ORDER_TYPE_EDIT);//???????????????
                            orderPayInfo.setShouldRecieve((order.getFeeFloat().subtract(orderParent.getFeeFloat())).setScale(4,BigDecimal.ROUND_HALF_UP));
                            if (order.getAfterDiscountMoney()==null){
                                orderPayInfo.setShouldCleanRecieve((order.getFeeFloat().subtract(orderParent.getFeeFloat())).setScale(4,BigDecimal.ROUND_HALF_UP));
                            }else {
                                orderPayInfo.setShouldCleanRecieve((order.getAfterDiscountMoney().subtract(orderParent.getAfterDiscountMoney())).setScale(4,BigDecimal.ROUND_HALF_UP));
                            }
                            orderPayInfo.setRecieveStatus(CommonConstant.PAYINFO_STATUS_WAIT);
                            if (securityUtil.getCurrUser().getDepartmentId()!=null)
                                orderPayInfo.setDepartmentId(securityUtil.getCurrUser().getDepartmentId());
                            orderPayInfoService.save(orderPayInfo);
                        }else{
                            orderDao.changeStatus(order.getId(),CommonConstant.ORDER_STATUS_AUDIT_FAILURE);
                        }
                        return null;
                    }

                    @Override
                    public Object onTimeout() throws InterruptedException {
                        //???????????????
                        throw new XbootException("????????????????????????????????????????????????");
                    }
                });

            }
        }
    }

    @Override
    public void orderAuditEdit(OrderCreateReq orderEditReq) {
        String orderId = orderEditReq.getOrder().getId();
        Order order = orderDao.findById(orderId).get();
        if (ObjectUtil.isEmpty(order)) {
            throw new XbootException("?????????????????????");
        }
        //?????????????????????????????????????????????
        if (!order.getOrderStatus().equals(CommonConstant.ORDER_STATUS_WAIT_AUDIT) ) {
            throw new XbootException("??????????????????????????????");
        }

        redisLockTemplate.execute("order:" + orderId, 3, TimeUnit.SECONDS, new Callback() {
            @Override
            public Object onGetLock() throws InterruptedException {
                //?????????  ???????????????????????????
                orderDao.saveAndFlush(orderEditReq.getOrder());
                List<OrderDetail> orderDetails = orderEditReq.getOrderDetails();
                List<OrderFile> orderFiles = orderEditReq.getOrderFiles();
                //????????????orderdetail????????????
                orderDetailDao.modifyByOrderId(orderId);
                //????????????????????????
                orderFileDao.modifyByOrderId(orderId);
                orderDetails.forEach(item -> {
                    item.setOrderId(orderId);
                    item.setId(SnowFlakeUtil.nextId().toString());
                    orderDetailDao.save(item);
                });
                orderFiles.forEach(item -> {
                    item.setOrderId(orderId);
                    item.setId(SnowFlakeUtil.nextId().toString());
                    orderFileDao.save(item);
                });
                return null;
            }

            @Override
            public Object onTimeout() throws InterruptedException {
                //???????????????
                throw new XbootException("????????????????????????????????????????????????");
            }
        });
    }

    @Override
    public String orderEditPrint(String orderId) throws Exception {
        DateFormat dateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
        Order order = orderDao.findById(orderId).get();
        if (ObjectUtil.isEmpty(order)) {
            throw new XbootException("?????????????????????");
        }
        if (!CommonConstant.ORDER_TYPE_EDIT.equals(order.getOrderType())) {
            throw new XbootException("????????????");
        }
        if (!order.getOrderStatus().equals(CommonConstant.ORDER_STATUS_FINISH) ) {
            throw new XbootException("???????????????????????????");
        }
        Map<String, String> data = new HashMap<String, String>();
        data.put("Text2",order.getParentId());
        data.put("Text3",order.getClientName());
        data.put("Text4",order.getId());
        data.put("Text5",dateFormatDay.format(new Date()));
        data.put("Text7",order.getOrderAgree());
        data.put("Text9",order.getRemark());
        data.put("Text10",order.getCreateBy());
        List<Log> aTrues = logMapper.findByDataflagAndlogDescAndType(2, orderId, "true");
        if (aTrues!=null&&aTrues.size()>0){
            data.put("Text11", aTrues.get(0).getUsername());
        }else {
            data.put("Text11", "");
        }
        String fileName = freeMarkUtils.generateOrderEditPdf(data, orderId);
        return fileName;
    }
}

