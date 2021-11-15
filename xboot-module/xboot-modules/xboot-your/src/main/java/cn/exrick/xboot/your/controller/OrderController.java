package cn.exrick.xboot.your.controller;

import cn.exrick.xboot.core.base.XbootBaseController;
import cn.exrick.xboot.core.common.annotation.SystemLog;
import cn.exrick.xboot.core.common.constant.CommonConstant;
import cn.exrick.xboot.core.common.enums.LogType;
import cn.exrick.xboot.core.common.utils.PageUtil;
import cn.exrick.xboot.core.common.utils.ResultUtil;
import cn.exrick.xboot.core.common.vo.PageVo;
import cn.exrick.xboot.core.common.vo.Result;
import cn.exrick.xboot.core.common.vo.SearchVo;
import cn.exrick.xboot.core.entity.Log;
import cn.exrick.xboot.your.entity.Order;
import cn.exrick.xboot.your.entity.OrderDetail;
import cn.exrick.xboot.your.entity.OrderFile;
import cn.exrick.xboot.your.entity.req.OrderCreateReq;
import cn.exrick.xboot.your.entity.resp.OrderDetailResp;
import cn.exrick.xboot.your.service.OrderDetailService;
import cn.exrick.xboot.your.service.OrderFileService;
import cn.exrick.xboot.your.service.OrderService;
import cn.exrick.xboot.your.utils.security.SecretAnnotation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jingqi Chen
 */
@Slf4j
@RestController
@Api(description = "统筹单列表管理接口")
@RequestMapping("/xboot/order")
@Transactional
public class OrderController extends XbootBaseController<Order, String> {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderFileService orderFileService;

    @Override
    public OrderService getService() {
        return orderService;
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    @SecretAnnotation(decode=false,encode=true)
    public Result<Page<OrderDetailResp>> getByCondition(Order order,
                                                        SearchVo searchVo,
                                                        PageVo pageVo){
        ArrayList<OrderDetailResp> results = new ArrayList<>();
        order.setOrderType(CommonConstant.ORDER_TYPE_COMMON);
        Page<Order> page = orderService.findByCondition(order, searchVo, PageUtil.initPage(pageVo));
        if (page.getContent()!=null&&page.getContent().size()>0){

            page.getContent().forEach(item->{
                OrderDetailResp orderDetailResp = new OrderDetailResp();
                orderDetailResp.setOrder(item);
                List<OrderDetail> orderDetails = orderDetailService.findByOrderIdAndType(item.getId());
                orderDetailResp.setOrderDetails(orderDetails);
                List<OrderFile> orderFiles = orderFileService.findByOrderId(item.getId());
                orderDetailResp.setOrderFiles(orderFiles);
                results.add(orderDetailResp);

            });
        }
        Page<OrderDetailResp> finalPage=new PageImpl<OrderDetailResp>(results, PageUtil.initPage(pageVo), page.getTotalElements());

        return new ResultUtil<Page<OrderDetailResp>>().setData(finalPage);
    }


    @RequestMapping(value = "/orderCreate", method = RequestMethod.POST)
    @ApiOperation(value = "创建暂存统筹单")
    @SystemLog(description = "创建暂存统筹单", type = LogType.OPERATION_ORDER,dataFlag ="#{orderCreateReq.order.id}",
    desc = "统筹单创建")
    public Result orderCreate(@RequestBody  OrderCreateReq orderCreateReq) {
        orderService.orderCreate(orderCreateReq);

        return new ResultUtil().setSuccessMsg("操作成功");
    }


    @RequestMapping(value = "/orderEdit", method = RequestMethod.POST)
    @ApiOperation(value = "统筹单编辑")
    @SystemLog(description = "统筹单编辑", type = LogType.OPERATION_ORDER,dataFlag ="#{orderEditReq.order.id}",
            desc = "统筹单编辑")
    public Result orderEdit(@RequestBody OrderCreateReq orderEditReq) {
        orderService.orderEdit(orderEditReq);

        return new ResultUtil().setSuccessMsg("操作成功");
    }

    @RequestMapping(value = "/orderAuditEdit", method = RequestMethod.POST)
    @ApiOperation(value = "统筹单审核编辑")
    @SystemLog(description = "统筹单审核编辑", type = LogType.OPERATION_ORDER,dataFlag ="#{orderEditReq.order.id}",
            desc = "统筹单审核员编辑")
    public Result orderAuditEdit(@RequestBody OrderCreateReq orderEditReq) {
        orderService.orderAuditEdit(orderEditReq);

        return new ResultUtil().setSuccessMsg("操作成功");
    }


    @RequestMapping(value = "/baojia", method = RequestMethod.GET)
    @ApiOperation(value = "获取报价单")
    public Result baojia(String orderId) throws Exception {
        String fileUrl=orderService.baojia(orderId);

        return new ResultUtil().setData(fileUrl);
    }

    @RequestMapping(value = "/commitForAudit", method = RequestMethod.POST)
    @ApiOperation(value = "提交审核统筹单")
    @SystemLog(description = "提交审核统筹单", type = LogType.OPERATION_ORDER,dataFlag ="#{orderId}",
            desc = "提交审核统筹单")
    public Result commitForAudit(String orderId) throws Exception {

        orderService.commitForAudit( orderId);

        return new ResultUtil().setSuccessMsg("提交审核成功");
    }


    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    @ApiOperation(value = "审核统筹单")
    @SystemLog(description = "审核统筹单#{result}", type = LogType.OPERATION_ORDER,dataFlag ="#{ids}",
            desc = "#{auditResult}",specialDesc="#{remark}")
    public Result orderAudit(@RequestParam String[] ids,Boolean auditResult,String result,String remark) throws Exception {
        orderService.orderAudit( ids,auditResult);
        if (auditResult){
            //审核通过
            return new ResultUtil().setSuccessMsg("审核通过成功");
        }else {
            //审核不通过
            return new ResultUtil().setSuccessMsg("审核不通过成功");

        }

    }


    @RequestMapping(value = "/insuranceCert", method = RequestMethod.GET)
    @ApiOperation(value = "保险单打印")
    public Result insuranceCert(String orderId) throws Exception {
        String fileUrl=orderService.insuranceCert(orderId);

        return new ResultUtil().setData(fileUrl);
    }


    @RequestMapping(value = "/getValidOrderByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "根据条件查询未失效统筹单")
    public Result<List<Order>> getValidOrderByCondition(String carNoHead,String carNo,String carframeNo,String id){

        List<Order> orders = orderService.findByCarNoAndFrameNoAndOrderId(carNoHead, carNo, carframeNo, id, CommonConstant.ORDER_STATUS_FINISH);

        return new ResultUtil<List<Order>>().setData(orders);
    }


    @RequestMapping(value = "/orderActionLog", method = RequestMethod.GET)
    @ApiOperation(value = "统筹单操作记录")
    public Result<List<Log>> orderActionLog(String orderId){

        List<Log> logs = orderService.getOrderActionLog(orderId);

        return new ResultUtil<List<Log>>().setData(logs);
    }



    @RequestMapping(value = "/getEditOrderByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    public Result<Page<OrderDetailResp>> getEditOrderByCondition(Order order,
                                                        SearchVo searchVo,
                                                        PageVo pageVo){
        ArrayList<OrderDetailResp> results = new ArrayList<>();
        order.setOrderType(CommonConstant.ORDER_TYPE_EDIT);//批单
        Page<Order> page = orderService.findByCondition(order, searchVo, PageUtil.initPage(pageVo));
        if (page.getContent()!=null&&page.getContent().size()>0){

            page.getContent().forEach(item->{
                OrderDetailResp orderDetailResp = new OrderDetailResp();
                orderDetailResp.setOrder(item);
                List<OrderDetail> orderDetails = orderDetailService.findByOrderIdAndType(item.getId());
                orderDetailResp.setOrderDetails(orderDetails);
                List<OrderFile> orderFiles = orderFileService.findByOrderId(item.getId());
                orderDetailResp.setOrderFiles(orderFiles);
                results.add(orderDetailResp);

            });
        }
        Page<OrderDetailResp> finalPage=new PageImpl<OrderDetailResp>(PageUtil.listToPage(pageVo, results), PageUtil.initPage(pageVo), results.size());

        return new ResultUtil<Page<OrderDetailResp>>().setData(finalPage);
    }


    @RequestMapping(value = "/queryOrder", method = RequestMethod.GET)
    @ApiOperation(value = "查询统筹单")
    @SecretAnnotation(decode=false,encode=true)
    public Result<List<OrderDetailResp>> queryOrder(String carNo,String carframeNo,String queryId){

        List<OrderDetailResp> orders = orderService.queryOrder(carNo,carframeNo,queryId);

        return new ResultUtil<List<OrderDetailResp>>().setData(orders);
    }

    @RequestMapping(value = "/editOrderCommitForAudit", method = RequestMethod.POST)
    @ApiOperation(value = "批单提交审核")
    @SystemLog(description = "批单提交审核", type = LogType.OPERATION_ORDER,dataFlag ="#{orderEditReq.order.id}",
            desc = "批改统筹单")
    public Result editOrderCommitForAudit(@RequestBody OrderCreateReq orderEditReq) throws Exception {

        orderService.editOrderCommitForAudit( orderEditReq);

        return new ResultUtil().setSuccessMsg("批单提交审核成功");
    }

    @RequestMapping(value = "/orderEditEdit", method = RequestMethod.POST)
    @ApiOperation(value = "批单编辑")
    @SystemLog(description = "批单编辑", type = LogType.OPERATION_ORDER,dataFlag ="#{orderEditReq.order.id}",
            desc = "统筹单编辑")
    public Result orderEditEdit(@RequestBody OrderCreateReq orderEditReq) {
        orderService.orderEditEdit(orderEditReq);

        return new ResultUtil().setSuccessMsg("操作成功");
    }


    @RequestMapping(value = "/orderEditAudit", method = RequestMethod.POST)
    @ApiOperation(value = "审核批单")
    @SystemLog(description = "审核批单#{result}", type = LogType.OPERATION_ORDER,dataFlag ="#{ids}",
            desc = "#{auditResult}",specialDesc="#{remark}")
    public Result orderEditAudit(@RequestParam String[] ids,Boolean auditResult,String result,String remark) throws Exception {

        orderService.orderEditAudit( ids,auditResult);


        if (auditResult){
            //审核通过
            return new ResultUtil().setSuccessMsg("审核通过成功");
        }else {
            //审核不通过
            return new ResultUtil().setSuccessMsg("审核不通过成功");

        }

    }

    @RequestMapping(value = "/orderEditPrint", method = RequestMethod.GET)
    @ApiOperation(value = "批单打印")
    public Result orderEditPrint(String orderId) throws Exception {
        String fileUrl=orderService.orderEditPrint(orderId);

        return new ResultUtil().setData(fileUrl);
    }
}
