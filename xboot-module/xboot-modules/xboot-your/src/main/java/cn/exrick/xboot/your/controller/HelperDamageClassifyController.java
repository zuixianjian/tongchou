package cn.exrick.xboot.your.controller;

import cn.exrick.xboot.core.base.XbootBaseController;
import cn.exrick.xboot.core.common.constant.CommonConstant;
import cn.exrick.xboot.core.common.exception.XbootException;
import cn.exrick.xboot.core.common.utils.PageUtil;
import cn.exrick.xboot.core.common.utils.ResultUtil;
import cn.exrick.xboot.core.common.vo.PageVo;
import cn.exrick.xboot.core.common.vo.Result;
import cn.exrick.xboot.core.common.vo.SearchVo;
import cn.exrick.xboot.your.entity.HelperDamageClassify;
import cn.exrick.xboot.your.entity.HelperOrder;
import cn.exrick.xboot.your.entity.resp.DamageListResp;
import cn.exrick.xboot.your.service.HelperDamageClassifyService;
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
@Api(description = "互助单损失主表分类(记录相应的险种以及损失额)管理接口")
@RequestMapping("/xboot/helperDamageClassify")
@Transactional
public class HelperDamageClassifyController extends XbootBaseController<HelperDamageClassify, String> {

    @Autowired
    private HelperDamageClassifyService helperDamageClassifyService;

    @Autowired
    private HelperOrderService helperOrderService;
    @Override
    public HelperDamageClassifyService getService() {
        return helperDamageClassifyService;
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    public Result<Page<HelperDamageClassify>> getByCondition(HelperDamageClassify helperDamageClassify,
                                                            SearchVo searchVo,
                                                            PageVo pageVo){

        Page<HelperDamageClassify> page = helperDamageClassifyService.findByCondition(helperDamageClassify, searchVo, PageUtil.initPage(pageVo));
        return new ResultUtil<Page<HelperDamageClassify>>().setData(page);
    }

    @RequestMapping(value = "/getDamageList", method = RequestMethod.GET)
    @ApiOperation(value = "获取损失列表")
    public Result<DamageListResp> getDamageList(String helpOrderId){

        DamageListResp damageList = helperDamageClassifyService.getDamageList(helpOrderId);
        return new ResultUtil<DamageListResp>().setData(damageList);
    }


    @RequestMapping(value = "/decide", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "是否已决数据修改")
    public Result<HelperDamageClassify> decide(HelperDamageClassify entity){


        HelperDamageClassify helperDamageClassify = getService().get(entity.getId());
        if (helperDamageClassify!=null){
            String helperOrderId = helperDamageClassify.getHelperOrderId();
            HelperOrder helperOrder = helperOrderService.get(helperOrderId);
            if (!helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_CHECK)){
                throw new XbootException("该互助单状态不可更改该数据");
            }
            helperDamageClassify.setIsDecide(entity.getIsDecide());
        }
        HelperDamageClassify e = getService().update(helperDamageClassify);
        return new ResultUtil<HelperDamageClassify>().setData(e);
    }
}
