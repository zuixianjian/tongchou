package cn.exrick.xboot.your.controller;

import cn.exrick.xboot.core.base.XbootBaseController;
import cn.exrick.xboot.core.common.utils.PageUtil;
import cn.exrick.xboot.core.common.utils.ResultUtil;
import cn.exrick.xboot.core.common.vo.PageVo;
import cn.exrick.xboot.core.common.vo.Result;
import cn.exrick.xboot.core.common.vo.SearchVo;
import cn.exrick.xboot.your.entity.CheckCode;
import cn.exrick.xboot.your.service.CheckCodeService;
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
@Api(description = "验车码管理接口")
@RequestMapping("/xboot/checkCode")
@Transactional
public class CheckCodeController extends XbootBaseController<CheckCode, String> {

    @Autowired
    private CheckCodeService checkCodeService;

    @Override
    public CheckCodeService getService() {
        return checkCodeService;
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    @SecretAnnotation(decode=false,encode=true)
    public Result<Page<CheckCode>> getByCondition(CheckCode checkCode,
                                                            SearchVo searchVo,
                                                            PageVo pageVo){

        Page<CheckCode> page = checkCodeService.findByCondition(checkCode, searchVo, PageUtil.initPage(pageVo));
        return new ResultUtil<Page<CheckCode>>().setData(page);
    }

    @RequestMapping(value = "/getTodyCode", method = RequestMethod.GET)
    @ApiOperation(value = "获取今日验车码")
    @SecretAnnotation(decode=false,encode=true)
    public Result<CheckCode> getTodyCode(){

        CheckCode checkCode= checkCodeService.getTodyCode();
        return new ResultUtil<CheckCode>().setData(checkCode);
    }

}
