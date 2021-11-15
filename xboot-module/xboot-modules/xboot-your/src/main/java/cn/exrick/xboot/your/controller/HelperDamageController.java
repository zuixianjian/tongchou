package cn.exrick.xboot.your.controller;

import cn.exrick.xboot.core.base.XbootBaseController;
import cn.exrick.xboot.core.common.constant.CommonConstant;
import cn.exrick.xboot.core.common.exception.XbootException;
import cn.exrick.xboot.core.common.utils.PageUtil;
import cn.exrick.xboot.core.common.utils.ResultUtil;
import cn.exrick.xboot.core.common.vo.PageVo;
import cn.exrick.xboot.core.common.vo.Result;
import cn.exrick.xboot.core.common.vo.SearchVo;
import cn.exrick.xboot.your.entity.HelperDamage;
import cn.exrick.xboot.your.entity.HelperOrder;
import cn.exrick.xboot.your.service.HelperDamageService;
import cn.exrick.xboot.your.service.HelperOrderService;
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
@Api(description = "互助单损失主表管理接口")
@RequestMapping("/xboot/helperDamage")
@Transactional
public class HelperDamageController extends XbootBaseController<HelperDamage, String> {

    @Autowired
    private HelperDamageService helperDamageService;
    @Autowired
    private HelperOrderService helperOrderService;

    @Override
    public HelperDamageService getService() {
        return helperDamageService;
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    public Result<Page<HelperDamage>> getByCondition(HelperDamage helperDamage,
                                                            SearchVo searchVo,
                                                            PageVo pageVo){

        Page<HelperDamage> page = helperDamageService.findByCondition(helperDamage, searchVo, PageUtil.initPage(pageVo));
        return new ResultUtil<Page<HelperDamage>>().setData(page);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存数据")
    @Override
    public Result<HelperDamage> save(HelperDamage entity){
        String helperOrderId = entity.getHelperOrderId();
        HelperOrder helperOrder = helperOrderService.get(helperOrderId);
        if (!helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_DIAOCHA)&&
                !helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_CHECK)&&
                        !helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_CHECK_FAIL)){
            throw new XbootException("该互助单状态无法新增损失数据");
        }
        HelperDamage e = getService().save(entity);
        return new ResultUtil<HelperDamage>().setData(e);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "更新数据")
    @Override
    public Result<HelperDamage> update(HelperDamage entity) throws InstantiationException, IllegalAccessException {
        String helperOrderId = entity.getHelperOrderId();
        HelperOrder helperOrder = helperOrderService.get(helperOrderId);
        if (!helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_DIAOCHA)&&
                !helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_CHECK)&&
                !helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_CHECK_FAIL)){
            throw new XbootException("该互助单状态无法更新损失数据");
        }
        HelperDamage e = getService().update(entity);
        return new ResultUtil<HelperDamage>().setData(e);
    }
}
