package cn.exrick.xboot.your.controller;

import cn.exrick.xboot.core.base.XbootBaseController;
import cn.exrick.xboot.core.common.annotation.SystemLog;
import cn.exrick.xboot.core.common.constant.CommonConstant;
import cn.exrick.xboot.core.common.enums.LogType;
import cn.exrick.xboot.core.common.exception.XbootException;
import cn.exrick.xboot.core.common.utils.PageUtil;
import cn.exrick.xboot.core.common.utils.ResultUtil;
import cn.exrick.xboot.core.common.vo.PageVo;
import cn.exrick.xboot.core.common.vo.Result;
import cn.exrick.xboot.core.common.vo.SearchVo;
import cn.exrick.xboot.core.entity.User;
import cn.exrick.xboot.your.entity.HelperOrder;
import cn.exrick.xboot.your.service.HelperOrderService;
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
@Api(description = "互助单主表管理接口")
@RequestMapping("/xboot/helperOrder")
@Transactional
public class HelperOrderController extends XbootBaseController<HelperOrder, String> {

    @Autowired
    private HelperOrderService helperOrderService;

    @Override
    public HelperOrderService getService() {
        return helperOrderService;
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    @SecretAnnotation(decode=false,encode=true)
    public Result<Page<HelperOrder>> getByCondition(HelperOrder helperOrder,
                                                            SearchVo searchVo,
                                                            PageVo pageVo){
        Page<HelperOrder> page = helperOrderService.findByCondition(helperOrder, searchVo, PageUtil.initPage(pageVo));
        if (page.getContent()!=null){
            for (HelperOrder order : page.getContent()) {
                if (order.getDiaochaUserId()!=null){
                    User user=helperOrderService.getDiaochaUserById(order.getDiaochaUserId());
                    if (user!=null){
                        order.setDiaochaUserName(user.getUsername());
                    }
                }

            }
        }

        return new ResultUtil<Page<HelperOrder>>().setData(page);
    }

    @RequestMapping(value = "/createHelpOrder", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "创建互助单")
    @SystemLog(type=LogType.OPERATION_HELP_ORDER,dataFlag = "#{entity.id}",description = "创建互助单",desc = "",specialDesc = "")
    public Result<HelperOrder> createHelpOrder(HelperOrder entity){
        entity.setStatus(CommonConstant.HELPORDER_STATUS_ZANCUN);
        helperOrderService.createHelpOrder(entity);
        return new ResultUtil<HelperOrder>().setSuccessMsg("事故登记成功");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    @Override
    @ApiOperation(value = "更新数据")
    @SystemLog(type=LogType.OPERATION_HELP_ORDER,dataFlag = "#{entity.id}",description = "修改互助单",desc = "",specialDesc = "")
    public Result<HelperOrder> update(HelperOrder entity) throws InstantiationException, IllegalAccessException {
        HelperOrder helperOrder = helperOrderService.get(entity.getId());
        if (helperOrder!=null){
            if (helperOrder.getStatus()!= CommonConstant.HELPORDER_STATUS_ZANCUN){
                throw new XbootException("当前互助单状态不可编辑");
            }
        }else {
            throw new XbootException("该互助单不存在");
        }
        HelperOrder e = getService().update(entity);
        return new ResultUtil<HelperOrder>().setData(e);
    }

    @RequestMapping(value = "/getDiaochaUserList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取调查员列表")
    public Result<List<User>> getDiaochaUserList(HelperOrder entity){
        List<User> users=helperOrderService.getDiaochaUserList();
        return new ResultUtil<List<User>>().setData(users);
    }


    @RequestMapping(value = "/submitStartDiaocha", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "状态改为调查")
    @SystemLog(type=LogType.OPERATION_HELP_ORDER,dataFlag = "#{entity.id}",description = "互助单调查派工",desc = "",specialDesc = "")
    public Result submitStartDiaocha(HelperOrder entity){

        helperOrderService.submitStartDiaocha(entity);
        return new ResultUtil<>().setSuccessMsg("调查派工成功");
    }

    @RequestMapping(value = "/checkFail", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "审核不通过，退回调查")
    @SystemLog(type=LogType.OPERATION_HELP_ORDER,dataFlag = "#{entity.id}",description = "互助单审核不通过",desc = "true",specialDesc = "")
    public Result checkFail(String helpOrderId){

        helperOrderService.checkFail(helpOrderId);

        return new ResultUtil<>().setSuccessMsg("操作成功");
    }

    @RequestMapping(value = "/checkSuccess", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "审核通过，待付款结案")
    @SystemLog(type=LogType.OPERATION_HELP_ORDER,dataFlag = "#{entity.id}",description = "互助单审核通过",desc = "false",specialDesc = "")
    public Result checkSuccess(String helpOrderId){

        helperOrderService.checkSuccess(helpOrderId);

        return new ResultUtil<>().setSuccessMsg("操作成功");
    }

    @RequestMapping(value = "/commitForCheck", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "提交审核")
    @SystemLog(type=LogType.OPERATION_HELP_ORDER,dataFlag = "#{entity.id}",description = "互助单提交审核",desc = "false",specialDesc = "")
    public Result commitForCheck(String helpOrderId){

        helperOrderService.commitForCheck(helpOrderId);

        return new ResultUtil<>().setSuccessMsg("操作成功");
    }


}
