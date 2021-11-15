package cn.exrick.xboot.your.controller;

import cn.exrick.xboot.core.base.XbootBaseController;
import cn.exrick.xboot.core.common.utils.PageUtil;
import cn.exrick.xboot.core.common.utils.ResultUtil;
import cn.exrick.xboot.core.common.vo.PageVo;
import cn.exrick.xboot.core.common.vo.Result;
import cn.exrick.xboot.core.common.vo.SearchVo;
import cn.exrick.xboot.your.entity.OrderFile;
import cn.exrick.xboot.your.service.OrderFileService;
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
@Api(description = "统筹单附件管理接口")
@RequestMapping("/xboot/orderFile")
@Transactional
public class OrderFileController extends XbootBaseController<OrderFile, String> {

    @Autowired
    private OrderFileService orderFileService;

    @Override
    public OrderFileService getService() {
        return orderFileService;
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    public Result<Page<OrderFile>> getByCondition(OrderFile orderFile,
                                                            SearchVo searchVo,
                                                            PageVo pageVo){

        Page<OrderFile> page = orderFileService.findByCondition(orderFile, searchVo, PageUtil.initPage(pageVo));
        return new ResultUtil<Page<OrderFile>>().setData(page);
    }
}
