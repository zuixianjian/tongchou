package cn.exrick.xboot.your.controller;

import cn.exrick.xboot.core.base.XbootBaseController;
import cn.exrick.xboot.core.common.utils.PageUtil;
import cn.exrick.xboot.core.common.utils.ResultUtil;
import cn.exrick.xboot.core.common.vo.PageVo;
import cn.exrick.xboot.core.common.vo.Result;
import cn.exrick.xboot.core.common.vo.SearchVo;
import cn.exrick.xboot.your.entity.Youhui;
import cn.exrick.xboot.your.service.YouhuiService;
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
@Api(description = "优惠政策配置管理接口")
@RequestMapping("/xboot/youhui")
@Transactional
public class YouhuiController extends XbootBaseController<Youhui, String> {

    @Autowired
    private YouhuiService youhuiService;

    @Override
    public YouhuiService getService() {
        return youhuiService;
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    @SecretAnnotation(decode=false,encode=true)
    public Result<Page<Youhui>> getByCondition(Youhui youhui,
                                                            SearchVo searchVo,
                                                            PageVo pageVo){

        Page<Youhui> page = youhuiService.findByCondition(youhui, searchVo, PageUtil.initPage(pageVo));
        return new ResultUtil<Page<Youhui>>().setData(page);
    }


    @RequestMapping(value = "/getMineYouhui", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    @SecretAnnotation(decode=false,encode=true)
    public Result<Page<Youhui>> getMineYouhui(Youhui youhui,
                                               SearchVo searchVo,
                                               PageVo pageVo){

        Page<Youhui> page = youhuiService.getMineYouhui(youhui, searchVo, PageUtil.initPage(pageVo));

        return new ResultUtil<Page<Youhui>>().setData(page);
    }
}
