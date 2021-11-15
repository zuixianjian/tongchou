package cn.exrick.xboot.your.controller;

import cn.exrick.xboot.core.base.XbootBaseController;
import cn.exrick.xboot.core.common.utils.PageUtil;
import cn.exrick.xboot.core.common.utils.ResultUtil;
import cn.exrick.xboot.core.common.vo.PageVo;
import cn.exrick.xboot.core.common.vo.Result;
import cn.exrick.xboot.core.common.vo.SearchVo;
import cn.exrick.xboot.your.entity.OrderDetail;
import cn.exrick.xboot.your.service.OrderDetailService;
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
@Api(description = "统筹单明细列表管理接口")
@RequestMapping("/xboot/orderDetail")
@Transactional
public class OrderDetailController extends XbootBaseController<OrderDetail, String> {

    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    public OrderDetailService getService() {
        return orderDetailService;
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    public Result<Page<OrderDetail>> getByCondition(OrderDetail orderDetail,
                                                            SearchVo searchVo,
                                                            PageVo pageVo){

        Page<OrderDetail> page = orderDetailService.findByCondition(orderDetail, searchVo, PageUtil.initPage(pageVo));
        return new ResultUtil<Page<OrderDetail>>().setData(page);
    }
}
