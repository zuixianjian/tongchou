package cn.exrick.xboot.your.controller;

import cn.exrick.xboot.core.base.XbootBaseController;
import cn.exrick.xboot.core.common.exception.XbootException;
import cn.exrick.xboot.core.common.utils.PageUtil;
import cn.exrick.xboot.core.common.utils.ResultUtil;
import cn.exrick.xboot.core.common.vo.PageVo;
import cn.exrick.xboot.core.common.vo.Result;
import cn.exrick.xboot.core.common.vo.SearchVo;
import cn.exrick.xboot.your.entity.CarDepreciation;
import cn.exrick.xboot.your.entity.ClientInfo;
import cn.exrick.xboot.your.entity.RateConfig;
import cn.exrick.xboot.your.service.CarDepreciationService;
import cn.exrick.xboot.your.utils.security.SecretAnnotation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jingqi Chen
 */
@Slf4j
@RestController
@Api(description = "月折旧率管理接口")
@RequestMapping("/xboot/carDepreciation")
@Transactional
public class CarDepreciationController extends XbootBaseController<CarDepreciation, String> {

    @Autowired
    private CarDepreciationService carDepreciationService;

    @Override
    public CarDepreciationService getService() {
        return carDepreciationService;
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    @SecretAnnotation(decode=false,encode=true)
    public Result<Page<CarDepreciation>> getByCondition(CarDepreciation carDepreciation,
                                                            SearchVo searchVo,
                                                            PageVo pageVo){

        Page<CarDepreciation> page = carDepreciationService.findByCondition(carDepreciation, searchVo, PageUtil.initPage(pageVo));
        return new ResultUtil<Page<CarDepreciation>>().setData(page);
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存数据")
    @Override
    public Result<CarDepreciation> save(CarDepreciation entity){
        PageRequest of = PageRequest.of(1, 10);
        Page<CarDepreciation> results = carDepreciationService.findByCondition(entity, null, of);
        if (results.getTotalElements()>0){
            //已有该组数据配置
            throw new XbootException("系统已存在相似月折旧率信息");
        }
        CarDepreciation e = carDepreciationService.save(entity);
        return new ResultUtil<CarDepreciation>().setData(e);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "更新数据")
    @Override
    public Result<CarDepreciation> update(CarDepreciation entity) throws InstantiationException, IllegalAccessException {
        CarDepreciation xbootBaseEntity = entity.clearBaseData();
        PageRequest of = PageRequest.of(1, 10);
        Page<CarDepreciation> results = carDepreciationService.findByCondition(xbootBaseEntity, null, of);
        if (results.getTotalElements()>0){
            //已有该组数据配置
            throw new XbootException("系统已存在相似月折旧率信息");
        }
        CarDepreciation e = carDepreciationService.update(entity);
        return new ResultUtil<CarDepreciation>().setData(e);
    }
}
