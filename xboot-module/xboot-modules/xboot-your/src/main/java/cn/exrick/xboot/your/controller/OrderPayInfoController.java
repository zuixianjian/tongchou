package cn.exrick.xboot.your.controller;

import cn.exrick.xboot.core.base.XbootBaseController;
import cn.exrick.xboot.core.common.annotation.SystemLog;
import cn.exrick.xboot.core.common.enums.LogType;
import cn.exrick.xboot.core.common.utils.PageUtil;
import cn.exrick.xboot.core.common.utils.ResultUtil;
import cn.exrick.xboot.core.common.vo.PageVo;
import cn.exrick.xboot.core.common.vo.Result;
import cn.exrick.xboot.core.common.vo.SearchVo;
import cn.exrick.xboot.your.entity.OrderPayInfo;
import cn.exrick.xboot.your.entity.req.StatisticBussinessReq;
import cn.exrick.xboot.your.entity.req.StatisticOrderReq;
import cn.exrick.xboot.your.entity.resp.StatisticBussinessItemResp;
import cn.exrick.xboot.your.entity.resp.StatisticBussinessResp;
import cn.exrick.xboot.your.entity.resp.StatisticOrderResp;
import cn.exrick.xboot.your.service.OrderPayInfoService;
import cn.exrick.xboot.your.utils.security.SecretAnnotation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jingqi Chen
 */
@Slf4j
@RestController
@Api(description = "批单/统筹单收款单管理接口")
@RequestMapping("/xboot/orderPayInfo")
@Transactional
public class OrderPayInfoController extends XbootBaseController<OrderPayInfo, String> {

    @Autowired
    private OrderPayInfoService orderPayInfoService;

    @Override
    public OrderPayInfoService getService() {
        return orderPayInfoService;
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    public Result<Page<OrderPayInfo>> getByCondition(OrderPayInfo orderPayInfo,
                                                            SearchVo searchVo,
                                                            PageVo pageVo){

        Page<OrderPayInfo> page = orderPayInfoService.findByCondition(orderPayInfo, searchVo, PageUtil.initPage(pageVo));
        return new ResultUtil<Page<OrderPayInfo>>().setData(page);
    }


    //确认收款
    @RequestMapping(value = "/paySuccess", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "确认收款")
    @SystemLog(description = "确认收款", type = LogType.OPERATION_PAYINFO,dataFlag ="#{entity.bussinessId}",
            desc = "true",specialDesc = "#{entity.bussinessType}")
    public Result<OrderPayInfo> paySuccess(OrderPayInfo entity){
        orderPayInfoService.paySuccess(entity);

        return new ResultUtil().setSuccessMsg("收款成功");
    }

    //收款统计
    @RequestMapping(value = "/statisticOrder", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "收款统计")
    public Result<StatisticOrderResp> statisticOrder(StatisticOrderReq statisticOrderReq){
        StatisticOrderResp statisticOrderResp=orderPayInfoService.statisticOrder(statisticOrderReq);
        return new ResultUtil().setData(statisticOrderResp);
    }

    //业务统计
    @RequestMapping(value = "/statisticBussiness", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "业务统计")
    @SecretAnnotation(decode=false,encode=true)
    public Result<StatisticBussinessResp> statisticBussiness(StatisticBussinessReq statisticBussinessReq){
        StatisticBussinessResp statisticBussinessResp=orderPayInfoService.statisticBussiness(statisticBussinessReq);
        return new ResultUtil().setData(statisticBussinessResp);
    }

}
