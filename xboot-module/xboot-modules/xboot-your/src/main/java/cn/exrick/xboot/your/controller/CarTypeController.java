package cn.exrick.xboot.your.controller;

import cn.exrick.xboot.core.base.XbootBaseController;
import cn.exrick.xboot.core.common.exception.XbootException;
import cn.exrick.xboot.core.common.utils.PageUtil;
import cn.exrick.xboot.core.common.utils.ResultUtil;
import cn.exrick.xboot.core.common.vo.PageVo;
import cn.exrick.xboot.core.common.vo.Result;
import cn.exrick.xboot.core.common.vo.SearchVo;
import cn.exrick.xboot.your.entity.CarDepreciation;
import cn.exrick.xboot.your.entity.CarType;
import cn.exrick.xboot.your.service.CarTypeService;
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
@Api(description = "车型信息维护管理接口")
@RequestMapping("/xboot/carType")
@Transactional
public class CarTypeController extends XbootBaseController<CarType, String> {

    @Autowired
    private CarTypeService carTypeService;

    @Override
    public CarTypeService getService() {
        return carTypeService;
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    @SecretAnnotation(decode=false,encode=true)
    public Result<Page<CarType>> getByCondition(CarType carType,
                                                            SearchVo searchVo,
                                                            PageVo pageVo){

        Page<CarType> page = carTypeService.findByCondition(carType, searchVo, PageUtil.initPage(pageVo));
        return new ResultUtil<Page<CarType>>().setData(page);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存数据")
    @Override
    public Result<CarType> save(CarType entity){
        PageRequest of = PageRequest.of(1, 10);
        Page<CarType> results = getService().findByCondition(entity, null, of);
        if (results.getTotalElements()>0){
            //已有该组数据配置
            throw new XbootException("系统已存在相似费率信息");
        }
        CarType e = getService().save(entity);
        return new ResultUtil<CarType>().setData(e);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "更新数据")
    @Override
    public Result<CarType> update(CarType entity) throws InstantiationException, IllegalAccessException {
        CarType xbootBaseEntity = entity.clearBaseData();
        PageRequest of = PageRequest.of(1, 10);
        Page<CarType> results = getService().findByCondition(xbootBaseEntity, null, of);
        if (results.getTotalElements()>0){
            //已有该组数据配置
            throw new XbootException("系统已存在相似费率信息");
        }
        CarType e = getService().update(entity);
        return new ResultUtil<CarType>().setData(e);
    }
}
