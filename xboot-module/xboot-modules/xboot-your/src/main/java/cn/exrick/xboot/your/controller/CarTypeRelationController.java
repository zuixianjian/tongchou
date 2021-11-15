package cn.exrick.xboot.your.controller;

import cn.exrick.xboot.core.base.XbootBaseController;
import cn.exrick.xboot.core.common.utils.PageUtil;
import cn.exrick.xboot.core.common.utils.ResultUtil;
import cn.exrick.xboot.core.common.vo.PageVo;
import cn.exrick.xboot.core.common.vo.Result;
import cn.exrick.xboot.core.common.vo.SearchVo;
import cn.exrick.xboot.your.entity.CarTypeRelation;
import cn.exrick.xboot.your.service.CarTypeRelationService;
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
@Api(description = "车型关联信息表管理接口")
@RequestMapping("/xboot/carTypeRelation")
@Transactional
public class CarTypeRelationController extends XbootBaseController<CarTypeRelation, String> {

    @Autowired
    private CarTypeRelationService carTypeRelationService;

    @Override
    public CarTypeRelationService getService() {
        return carTypeRelationService;
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    @SecretAnnotation(decode=false,encode=true)
    public Result<Page<CarTypeRelation>> getByCondition(CarTypeRelation carTypeRelation,
                                                            SearchVo searchVo,
                                                            PageVo pageVo){

        Page<CarTypeRelation> page = carTypeRelationService.findByCondition(carTypeRelation, searchVo, PageUtil.initPage(pageVo));
        return new ResultUtil<Page<CarTypeRelation>>().setData(page);
    }

    @RequestMapping(value = "/carTypeB", method = RequestMethod.GET)
    @ApiOperation(value = "获取车型细分类别")
    public Result<List<CarTypeRelation>> carTypeB(String carTypeA){

        List<CarTypeRelation> page = carTypeRelationService.getCarTypeB(carTypeA);
        return new ResultUtil<List<CarTypeRelation>>().setData(page);
    }

}
