package cn.exrick.xboot.your.controller;

import cn.exrick.xboot.core.base.XbootBaseController;
import cn.exrick.xboot.core.common.constant.CommonConstant;
import cn.exrick.xboot.core.common.exception.XbootException;
import cn.exrick.xboot.core.common.utils.PageUtil;
import cn.exrick.xboot.core.common.utils.ResultUtil;
import cn.exrick.xboot.core.common.vo.PageVo;
import cn.exrick.xboot.core.common.vo.Result;
import cn.exrick.xboot.core.common.vo.SearchVo;
import cn.exrick.xboot.your.entity.HelperOrder;
import cn.exrick.xboot.your.entity.HelperOrderCompensate;
import cn.exrick.xboot.your.service.HelperOrderCompensateService;
import cn.exrick.xboot.your.service.HelperOrderService;
import cn.exrick.xboot.your.utils.security.SecretAnnotation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jingqi Chen
 */
@Slf4j
@RestController
@Api(description = "互助单赔付信息管理接口")
@RequestMapping("/xboot/helperOrderCompensate")
@Transactional
public class HelperOrderCompensateController extends XbootBaseController<HelperOrderCompensate, String> {

    @Autowired
    private HelperOrderCompensateService helperOrderCompensateService;
    @Autowired
    private HelperOrderService helperOrderService;

    @Override
    public HelperOrderCompensateService getService() {
        return helperOrderCompensateService;
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    public Result<Page<HelperOrderCompensate>> getByCondition(HelperOrderCompensate helperOrderCompensate,
                                                            SearchVo searchVo,
                                                            PageVo pageVo){

        Page<HelperOrderCompensate> page = helperOrderCompensateService.findByCondition(helperOrderCompensate, searchVo, PageUtil.initPage(pageVo));
        return new ResultUtil<Page<HelperOrderCompensate>>().setData(page);
    }

    @RequestMapping(value = "/getByHelpOrderId", method = RequestMethod.GET)
    @ApiOperation(value = "根据互助单id查询")
    @SecretAnnotation(decode=false,encode=true)
    public Result<HelperOrderCompensate> getByHelpOrderId(String helpOrderId){

        HelperOrderCompensate helperOrderCompensate=helperOrderCompensateService.getByHelpOrderId(helpOrderId);
        return new ResultUtil<HelperOrderCompensate>().setData(helperOrderCompensate);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "更新数据")
    @Override
    public Result<HelperOrderCompensate> update(HelperOrderCompensate entity) throws InstantiationException, IllegalAccessException {
        HelperOrder helperOrder = helperOrderService.get(entity.getHelperOrderId());
        if (!helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_DIAOCHA)&&
                !helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_CHECK)&&
                !helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_CHECK_FAIL)){
            throw new XbootException("该互助单状态无法更新损失数据");
        }

        HelperOrderCompensate helpOrder = helperOrderCompensateService.getByHelpOrderId(entity.getId());
        if (helpOrder!=null){
            //更新
            entity= getService().update(entity);
        }else {
            //新增
            entity= getService().save(entity);
        }

        return new ResultUtil<HelperOrderCompensate>().setData(entity);
    }
}
