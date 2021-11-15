package cn.exrick.xboot.your.controller;

import cn.exrick.xboot.core.base.XbootBaseController;
import cn.exrick.xboot.core.common.exception.XbootException;
import cn.exrick.xboot.core.common.utils.PageUtil;
import cn.exrick.xboot.core.common.utils.ResultUtil;
import cn.exrick.xboot.core.common.vo.PageVo;
import cn.exrick.xboot.core.common.vo.Result;
import cn.exrick.xboot.core.common.vo.SearchVo;
import cn.exrick.xboot.your.entity.ClientInfo;
import cn.exrick.xboot.your.entity.RateConfig;
import cn.exrick.xboot.your.service.RateConfigService;
import cn.exrick.xboot.your.utils.security.SecretAnnotation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jingqi Chen
 */
@Slf4j
@RestController
@Api(description = "费率设置管理接口")
@RequestMapping("/xboot/rateConfig")
@Transactional
public class RateConfigController extends XbootBaseController<RateConfig, String> {

    @Autowired
    private RateConfigService rateConfigService;

    @Override
    public RateConfigService getService() {
        return rateConfigService;
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    @SecretAnnotation(decode=false,encode=true)
    public Result<Page<RateConfig>> getByCondition(RateConfig rateConfig,
                                                            SearchVo searchVo,
                                                            PageVo pageVo){

        Page<RateConfig> page = rateConfigService.findByCondition(rateConfig, searchVo, PageUtil.initPage(pageVo));
        return new ResultUtil<Page<RateConfig>>().setData(page);
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存数据")
    @Override
    public Result<RateConfig> save(RateConfig entity){
        PageRequest of = PageRequest.of(1, 10);
        Page<RateConfig> results = rateConfigService.findByCondition(entity, null, of);
        if (results.getTotalElements()>0){
            //已有该组数据配置
            throw new XbootException("系统已存在相似费率信息");
        }
        RateConfig e = rateConfigService.save(entity);
        return new ResultUtil<RateConfig>().setData(e);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "更新数据")
    @Override
    public Result<RateConfig> update(RateConfig entity) throws InstantiationException, IllegalAccessException {
        RateConfig xbootBaseEntity = entity.clearBaseData();
        PageRequest of = PageRequest.of(1, 10);
        Page<RateConfig> results = rateConfigService.findByCondition(xbootBaseEntity, null, of);
        if (results.getTotalElements()>0){
            //已有该组数据配置
            throw new XbootException("系统已存在相似费率信息");
        }
        RateConfig e = rateConfigService.update(entity);
        return new ResultUtil<RateConfig>().setData(e);
    }


    @RequestMapping(value = "/getMineFeeList", method = RequestMethod.GET)
    @ApiOperation(value = "根据usetype 和车型查询相关费率配置")
    public Result<List<RateConfig>> getMineFeeList(RateConfig rateConfig){

        List<RateConfig> rateConfigs = rateConfigService.getMineFeeList(rateConfig);
        return new ResultUtil<List<RateConfig>>().setData(rateConfigs);
    }
}
