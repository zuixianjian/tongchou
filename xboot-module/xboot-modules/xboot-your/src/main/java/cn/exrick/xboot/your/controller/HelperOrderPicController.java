package cn.exrick.xboot.your.controller;

import cn.exrick.xboot.core.base.XbootBaseController;
import cn.exrick.xboot.core.common.constant.CommonConstant;
import cn.exrick.xboot.core.common.exception.XbootException;
import cn.exrick.xboot.core.common.utils.PageUtil;
import cn.exrick.xboot.core.common.utils.ResultUtil;
import cn.exrick.xboot.core.common.vo.PageVo;
import cn.exrick.xboot.core.common.vo.Result;
import cn.exrick.xboot.core.common.vo.SearchVo;
import cn.exrick.xboot.your.entity.HelperOrder;
import cn.exrick.xboot.your.entity.HelperOrderPic;
import cn.exrick.xboot.your.entity.req.HelpOrderPicSaveReq;
import cn.exrick.xboot.your.entity.resp.HelpOrderPicsResp;
import cn.exrick.xboot.your.service.HelperOrderPicService;
import cn.exrick.xboot.your.service.HelperOrderService;
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
@Api(description = "互助单主表管理接口")
@RequestMapping("/xboot/helperOrderPic")
@Transactional
public class HelperOrderPicController extends XbootBaseController<HelperOrderPic, String> {

    @Autowired
    private HelperOrderPicService helperOrderPicService;

    @Autowired
    private HelperOrderService helperOrderService;
    @Override
    public HelperOrderPicService getService() {
        return helperOrderPicService;
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    public Result<Page<HelperOrderPic>> getByCondition(HelperOrderPic helperOrderPic,
                                                            SearchVo searchVo,
                                                            PageVo pageVo){

        Page<HelperOrderPic> page = helperOrderPicService.findByCondition(helperOrderPic, searchVo, PageUtil.initPage(pageVo));
        return new ResultUtil<Page<HelperOrderPic>>().setData(page);
    }

    @RequestMapping(value = "/getByHelpOrderId", method = RequestMethod.GET)
    @ApiOperation(value = "根据互助单id获取附件列表")
    public Result<List<HelpOrderPicsResp>> getByHelpOrderId(String helpOrderId){

        List<HelpOrderPicsResp> pics=helperOrderPicService.getByHelpOrderId(helpOrderId);
        return new ResultUtil<List<HelpOrderPicsResp>>().setData(pics);
    }

    @RequestMapping(value = "/saveHelpOrderPics", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存数据")
    public Result saveHelpOrderPics(@RequestBody HelpOrderPicSaveReq helpOrderPicSaveReq){
        HelperOrder helperOrder = helperOrderService.get(helpOrderPicSaveReq.getHelpOrderId());
        helperOrderPicService.saveHelpOrderPics(helpOrderPicSaveReq);
        if (!helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_DIAOCHA)&&
                !helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_CHECK)&&
                !helperOrder.getStatus().equals(CommonConstant.HELPORDER_STATUS_CHECK_FAIL)){
            throw new XbootException("该互助单状态无法更新损失数据");
        }
        return new ResultUtil().setSuccessMsg("操作成功");
    }
}
