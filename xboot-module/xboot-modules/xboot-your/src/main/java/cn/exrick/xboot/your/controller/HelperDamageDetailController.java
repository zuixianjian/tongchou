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
import cn.exrick.xboot.your.entity.HelperDamageDetail;
import cn.exrick.xboot.your.entity.HelperOrder;
import cn.exrick.xboot.your.service.HelperDamageDetailService;
import cn.exrick.xboot.your.service.HelperDamageService;
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
@Api(description = "互助单损失明细管理接口")
@RequestMapping("/xboot/helperDamageDetail")
@Transactional
public class HelperDamageDetailController extends XbootBaseController<HelperDamageDetail, String> {

    @Autowired
    private HelperDamageDetailService helperDamageDetailService;
    @Autowired
    private HelperOrderService helperOrderService;
    @Autowired
    private HelperDamageService helperDamageService;
    @Override
    public HelperDamageDetailService getService() {
        return helperDamageDetailService;
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    @SecretAnnotation(decode=false,encode=true)
    public Result<Page<HelperDamageDetail>> getByCondition(HelperDamageDetail helperDamageDetail,
                                                            SearchVo searchVo,
                                                            PageVo pageVo){

        Page<HelperDamageDetail> page = helperDamageDetailService.findByCondition(helperDamageDetail, searchVo, PageUtil.initPage(pageVo));
        return new ResultUtil<Page<HelperDamageDetail>>().setData(page);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存数据")
    @Override
    public Result<HelperDamageDetail> save(HelperDamageDetail entity){
        String helperDamageId = entity.getHelperDamageId();
        HelperDamage helperDamage = helperDamageService.get(helperDamageId);
        HelperOrder helperOrder = helperOrderService.get(helperDamage.getHelperOrderId());
        if (!helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_DIAOCHA)&&
                !helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_CHECK)&&
                !helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_CHECK_FAIL)){
            throw new XbootException("该互助单状态无法新增损失数据");
        }
        HelperDamageDetail e = getService().save(entity);
        return new ResultUtil<HelperDamageDetail>().setData(e);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "更新数据")
    @Override
    public Result<HelperDamageDetail> update(HelperDamageDetail entity) throws InstantiationException, IllegalAccessException {
        String helperDamageId = entity.getHelperDamageId();
        HelperDamage helperDamage = helperDamageService.get(helperDamageId);
        HelperOrder helperOrder = helperOrderService.get(helperDamage.getHelperOrderId());
        if (!helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_DIAOCHA)&&
                !helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_CHECK)&&
                !helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_CHECK_FAIL)){
            throw new XbootException("该互助单状态无法更新损失数据");
        }
        HelperDamageDetail e = getService().update(entity);
        return new ResultUtil<HelperDamageDetail>().setData(e);
    }
}
