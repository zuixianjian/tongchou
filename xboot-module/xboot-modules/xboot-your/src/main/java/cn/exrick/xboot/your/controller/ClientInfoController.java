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
import cn.exrick.xboot.your.service.ClientInfoService;
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
@Api(description = "客户信息维护管理接口")
@RequestMapping("/xboot/clientInfo")
@Transactional
public class ClientInfoController extends XbootBaseController<ClientInfo, String> {

    @Autowired
    private ClientInfoService clientInfoService;

    @Override
    public ClientInfoService getService() {
        return clientInfoService;
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    @SecretAnnotation(decode=false,encode=true)
    public Result<Page<ClientInfo>> getByCondition(ClientInfo clientInfo,
                                                            SearchVo searchVo,
                                                            PageVo pageVo){

        Page<ClientInfo> page = clientInfoService.findByCondition(clientInfo, searchVo, PageUtil.initPage(pageVo));
        return new ResultUtil<Page<ClientInfo>>().setData(page);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存数据")
    @Override
    public Result<ClientInfo> save(ClientInfo entity){
        PageRequest of = PageRequest.of(1, 10);
        Page<ClientInfo> results = clientInfoService.findByCondition(entity, null, of);
        if (results.getTotalElements()>0){
            //已有该组数据配置
            throw new XbootException("已存在该客户信息");
        }
        ClientInfo e = clientInfoService.save(entity);
        return new ResultUtil<ClientInfo>().setData(e);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "更新数据")
    @Override
    public Result<ClientInfo> update(ClientInfo entity) throws InstantiationException, IllegalAccessException {
        ClientInfo xbootBaseEntity = entity.clearBaseData();
        PageRequest of = PageRequest.of(1, 10);
        Page<ClientInfo> results = clientInfoService.findByCondition(xbootBaseEntity, null, of);
        if (results.getTotalElements()>0){
            //已有该组数据配置
            throw new XbootException("系统已存在相似客户信息："+results.getContent().get(0).getName());
        }
        ClientInfo e = clientInfoService.update(entity);
        return new ResultUtil<ClientInfo>().setData(e);
    }
}
