package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.core.common.constant.CommonConstant;
import cn.exrick.xboot.core.common.exception.XbootException;
import cn.exrick.xboot.core.common.utils.SecurityUtil;
import cn.exrick.xboot.core.dao.UserDao;
import cn.exrick.xboot.core.entity.User;
import cn.exrick.xboot.your.dao.*;
import cn.exrick.xboot.your.entity.*;
import cn.exrick.xboot.your.entity.Order;
import cn.exrick.xboot.your.mapper.DictDataMapper;
import cn.exrick.xboot.your.mapper.HelperOrderMapper;
import cn.exrick.xboot.your.service.HelperDamageClassifyService;
import cn.exrick.xboot.your.service.HelperOrderService;
import cn.exrick.xboot.your.service.OrderService;
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
 * 互助单主表接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class HelperOrderServiceImpl implements HelperOrderService {

    @Autowired
    private HelperOrderDao helperOrderDao;
    @Autowired
    private SecurityUtil securityUtil;
    @Autowired
    private OrderService orderService;
    @Autowired
    private HelperOrderMapper helperOrderMapper;
    @Autowired
    private DictDataMapper dictDataMapper;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private HelperDamageClassifyDao helperDamageClassifyDao;
    @Autowired
    private HelperOrderCompensateDao helperOrderCompensateDao;
    @Autowired
    private HelperDamageDetailDao helperDamageDetailDao;
    @Autowired
    private HelperDamageDao helperDamageDao;
    @Autowired
    private OrderPayInfoDao orderPayInfoDao;
    @Autowired
    private UserDao userDao;
    @Override
    public HelperOrderDao getRepository() {
        return helperOrderDao;
    }

    @Override
    public Page<HelperOrder> findByCondition(HelperOrder helperOrder, SearchVo searchVo, Pageable pageable) {

        return helperOrderDao.findAll(new Specification<HelperOrder>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<HelperOrder> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                // TODO 请根据实际需求修改你的搜索条件
                Path<String> orderIdField = root.get("orderId");
                Path<String> clientNameField = root.get("clientName");
                Path<String> carTypeNameField = root.get("carTypeName");
                Path<String> carNoHeadField = root.get("carNoHead");
                Path<String> carNoField = root.get("carNo");
                Path<java.time.LocalDateTime> startDateField = root.get("startDate");
                Path<java.time.LocalDateTime> endDateField = root.get("endDate");
                Path<String> motorNoField = root.get("motorNo");
                Path<String> carframeNoField = root.get("carframeNo");
                Path<String> reportNameField = root.get("reportName");
                Path<String> reportTelField = root.get("reportTel");
                Path<String> accidentTypeField = root.get("accidentType");
                Path<java.time.LocalDateTime> accidentTimeField = root.get("accidentTime");
                Path<String> thirdCarNoField = root.get("thirdCarNo");
                Path<String> thirdCarTypeField = root.get("thirdCarType");
                Path<String> thirdPhoneField = root.get("thirdPhone");
                Path<String> accidentPlaceField = root.get("accidentPlace");
                Path<String> accidentDescField = root.get("accidentDesc");
                Path<String> remarkField = root.get("remark");
                Path<String> statusField = root.get("status");
                Path<Date> createTimeField = root.get("createTime");
                Path<String> departmentIdField = root.get("departmentId");

                List<Predicate> list = new ArrayList<Predicate>();
                // 数据权限过滤
                List<String> depIds = securityUtil.getDeparmentIds();
                if(depIds!=null&&depIds.size()>0){
                    list.add(departmentIdField.in(depIds));
                }

                // 统筹单id模糊搜素
                if(StrUtil.isNotBlank(helperOrder.getOrderId())){
                    list.add(cb.like(orderIdField,'%'+helperOrder.getOrderId()+'%'));
                }
                // 被统筹人姓名模糊搜素
                if(StrUtil.isNotBlank(helperOrder.getClientName())){
                    list.add(cb.like(clientNameField,'%'+helperOrder.getClientName()+'%'));
                }
                // 车型名称模糊搜素
                if(StrUtil.isNotBlank(helperOrder.getCarTypeName())){
                    list.add(cb.like(carTypeNameField,'%'+helperOrder.getCarTypeName()+'%'));
                }
                // 省份   比如  鲁 陕   数据字典维护模糊搜素
                if(StrUtil.isNotBlank(helperOrder.getCarNoHead())){
                    list.add(cb.like(carNoHeadField,'%'+helperOrder.getCarNoHead()+'%'));
                }
                // 具体车牌号码模糊搜素
                if(StrUtil.isNotBlank(helperOrder.getCarNo())){
                    list.add(cb.like(carNoField,'%'+helperOrder.getCarNo()+'%'));
                }
                // 统筹开始时间相等匹配
                if(helperOrder.getStartDate()!=null){
                    list.add(cb.equal(startDateField, helperOrder.getStartDate()));
                }
                // 统筹结束时间相等匹配
                if(helperOrder.getEndDate()!=null){
                    list.add(cb.equal(endDateField, helperOrder.getEndDate()));
                }
                // 发动机号模糊搜素
                if(StrUtil.isNotBlank(helperOrder.getMotorNo())){
                    list.add(cb.like(motorNoField,'%'+helperOrder.getMotorNo()+'%'));
                }
                // 车架号模糊搜素
                if(StrUtil.isNotBlank(helperOrder.getCarframeNo())){
                    list.add(cb.like(carframeNoField,'%'+helperOrder.getCarframeNo()+'%'));
                }
                // 报案人模糊搜素
                if(StrUtil.isNotBlank(helperOrder.getReportName())){
                    list.add(cb.like(reportNameField,'%'+helperOrder.getReportName()+'%'));
                }
                // 报案电话模糊搜素
                if(StrUtil.isNotBlank(helperOrder.getReportTel())){
                    list.add(cb.like(reportTelField,'%'+helperOrder.getReportTel()+'%'));
                }
                // 事故类型模糊搜素
                if(StrUtil.isNotBlank(helperOrder.getAccidentType())){
                    list.add(cb.like(accidentTypeField,'%'+helperOrder.getAccidentType()+'%'));
                }
                // 事故时间相等匹配
                if(helperOrder.getAccidentTime()!=null){
                    list.add(cb.equal(accidentTimeField, helperOrder.getAccidentTime()));
                }
                // 三者车牌模糊搜素
                if(StrUtil.isNotBlank(helperOrder.getThirdCarNo())){
                    list.add(cb.like(thirdCarNoField,'%'+helperOrder.getThirdCarNo()+'%'));
                }
                // 三者车型模糊搜素
                if(StrUtil.isNotBlank(helperOrder.getThirdCarType())){
                    list.add(cb.like(thirdCarTypeField,'%'+helperOrder.getThirdCarType()+'%'));
                }
                // 三者电话模糊搜素
                if(StrUtil.isNotBlank(helperOrder.getThirdPhone())){
                    list.add(cb.like(thirdPhoneField,'%'+helperOrder.getThirdPhone()+'%'));
                }
                // 事故地点模糊搜素
                if(StrUtil.isNotBlank(helperOrder.getAccidentPlace())){
                    list.add(cb.like(accidentPlaceField,'%'+helperOrder.getAccidentPlace()+'%'));
                }
                // 事故概况模糊搜素
                if(StrUtil.isNotBlank(helperOrder.getAccidentDesc())){
                    list.add(cb.like(accidentDescField,'%'+helperOrder.getAccidentDesc()+'%'));
                }
                // 事故备注说明模糊搜素
                if(StrUtil.isNotBlank(helperOrder.getRemark())){
                    list.add(cb.like(remarkField,'%'+helperOrder.getRemark()+'%'));
                }
                // 事故状态模糊搜素
                if(StrUtil.isNotBlank(helperOrder.getStatus())){
                    list.add(cb.like(statusField,'%'+helperOrder.getStatus()+'%'));
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
    public void createHelpOrder(HelperOrder entity) {
        String orderId = entity.getOrderId();
        List<Order> orders = orderService.findByCarNoAndFrameNoAndOrderId(entity.getCarNoHead(), entity.getCarNo(), entity.getCarframeNo(), orderId, CommonConstant.ORDER_STATUS_FINISH);
        if (orders==null||orders.size()==0){
            throw new XbootException("该统筹单不存在或已失效");
        }
        entity.setStatus(CommonConstant.HELPORDER_STATUS_ZANCUN);
        entity.setDepartmentId(securityUtil.getCurrUser().getDepartmentId());
        helperOrderDao.save(entity);
    }

    @Override
    public List<User> getDiaochaUserList() {
        DictData diaochaRole = dictDataMapper.getByDictTypeAndDictTitle("systemConfig", "diaochaRoleID");
        if (diaochaRole==null){
            throw new XbootException("未配置调查员角色id");
        }
        List<User> diochaUserList = helperOrderMapper.getDiochaUserList(diaochaRole.getValue(), null);
        return diochaUserList;
    }

    @Override
    @Transactional
    public void submitStartDiaocha(HelperOrder entity) {
        HelperOrder helperOrder = helperOrderDao.findById(entity.getId()).get();
        if (helperOrder==null){
            throw new XbootException("该互助单不存在");
        }
        Order order = orderDao.findById(helperOrder.getOrderId()).get();
        if (order==null){
            throw new XbootException("该统筹单不存在");
        }
        if (!helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_ZANCUN)){
            throw new XbootException("该互助单状态不允许派工");
        }
        if (entity.getDiaochaUserId()==null){
            throw new XbootException("未选择调查员");
        }
        helperOrder.setDiaochaUserId(entity.getDiaochaUserId());
        helperOrder.setStatus(CommonConstant.HELPORDER_STATUS_DIAOCHA);
        helperOrderDao.save(helperOrder);
        //损失分类入库
        List<OrderDetail> orderDetails = orderDetailDao.findByOrderIdOrderByCreateTimeDesc(order.getId());
        if (orderDetails!=null){
            for (OrderDetail orderDetail : orderDetails) {
                HelperDamageClassify helperDamageClassify = new HelperDamageClassify();
                helperDamageClassify.setHelperOrderId(helperOrder.getId());
                helperDamageClassify.setDeductibleFee(orderDetail.getDeductibleFee());
                helperDamageClassify.setFeeType(orderDetail.getFeeType());
                helperDamageClassify.setInsuranceMoney(orderDetail.getInsuranceMoney());
                helperDamageClassifyDao.save(helperDamageClassify);
            }
        }
        //损失补偿表新增数据
        HelperOrderCompensate helperOrderCompensate = new HelperOrderCompensate();
        helperOrderCompensate.setHelperOrderId(helperOrder.getId());
        helperOrderCompensateDao.save(helperOrderCompensate);
    }

    @Override
    public void checkFail(String helpOrderId) {
        HelperOrder helperOrder = helperOrderDao.getOne(helpOrderId);
        if (!helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_CHECK)){
            throw new XbootException("该互助单状态目前不可操作");
        }
        helperOrder.setStatus(CommonConstant.HELPORDER_STATUS_CHECK_FAIL);
        helperOrderDao.save(helperOrder);
    }

    @Override
    public void checkSuccess(String helpOrderId) {
        HelperOrder helperOrder = helperOrderDao.getOne(helpOrderId);
        if (!helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_CHECK)){
            throw new XbootException("该互助单状态目前不可操作");
        }
        //判断是否都已决
        List<HelperDamageClassify> helperDamageClassifies = helperDamageClassifyDao.getByHelperOrderId(helpOrderId);
        if (helperDamageClassifies!=null){
            for (HelperDamageClassify helperDamageClassify : helperDamageClassifies) {
                if (!helperDamageClassify.getIsDecide()){
                    throw new XbootException("该互助单存在未决项目");
                }
            }
        }
        List<HelperDamage> helperDamages = helperDamageDao.getByHelperOrderId(helpOrderId);
        if (helperDamages!=null){
            for (HelperDamage helperDamage : helperDamages) {
                List<HelperDamageDetail> helperDamageDetails = helperDamageDetailDao.getByHelperDamageId(helperDamage.getId());
                if (helperDamageDetails!=null){
                    for (HelperDamageDetail helperDamageDetail : helperDamageDetails) {
                        if (!helperDamageDetail.getCheckTotalPrice().equals(helperDamageDetail.getTotalPrice())){
                            throw new XbootException("该互助单存在审核价格与调查价格不同项目");
                        }
                    }
                }
            }
        }
        //改为待结案
        helperOrder.setStatus(CommonConstant.HELPORDER_STATUS_WAITFORPAY);
        helperOrderDao.save(helperOrder);
        //生成收款单
        OrderPayInfo orderPayInfo = new OrderPayInfo();
        orderPayInfo.setBussinessType(CommonConstant.ORDER_TYPE_HELP);
        orderPayInfo.setBussinessId(helpOrderId);
        orderPayInfo.setRecieveStatus(CommonConstant.PAYINFO_STATUS_WAIT_TO);
        orderPayInfo.setDepartmentId(securityUtil.getCurrUser().getDepartmentId());
        orderPayInfoDao.save(orderPayInfo);
    }

    @Override
    public void commitForCheck(String helpOrderId) {
        HelperOrder helperOrder = helperOrderDao.getOne(helpOrderId);
        if (!helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_DIAOCHA)&&!helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_CHECK_FAIL)){
            throw new XbootException("该互助单状态目前不可操作");
        }
        helperOrder.setStatus(CommonConstant.HELPORDER_STATUS_CHECK);
        helperOrderDao.save(helperOrder);
    }

    @Override
    public User getDiaochaUserById(String diaochaUserId) {
        return userDao.findById(diaochaUserId).get();
    }

    /**
     * 根据Id删除
     * @param id
     */
    public  void delete(String id) {
        HelperOrder helperOrder = helperOrderDao.getOne(id);
        if (CommonConstant.HELPORDER_STATUS_FINISH.equals(helperOrder.getStatus())){
            throw new XbootException("该案件已结案不可删除");
        }
        getRepository().deleteById(id);
    }

}