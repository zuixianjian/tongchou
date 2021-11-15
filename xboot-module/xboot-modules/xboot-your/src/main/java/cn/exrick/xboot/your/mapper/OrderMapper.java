package cn.exrick.xboot.your.mapper;

import cn.exrick.xboot.your.entity.req.StatisticBussinessReq;
import cn.exrick.xboot.your.entity.req.StatisticOrderReq;
import cn.exrick.xboot.your.entity.resp.StatisticBussinessItemResp;
import cn.exrick.xboot.your.entity.resp.StatisticOrderItemResp;
import cn.exrick.xboot.your.entity.resp.StatisticOrderResp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.exrick.xboot.your.entity.Order;

import java.util.List;

/**
 * 统筹单列表数据处理层
 * @author Jingqi Chen
 */
public interface OrderMapper extends BaseMapper<Order> {

    void validOrder();

    List<Order> queryOrder(Order order);

    List<StatisticOrderItemResp> statisticOrder(StatisticOrderReq statisticOrderReq);

    StatisticOrderResp statisticOrderMoney(StatisticOrderReq statisticOrderReq);

    List<StatisticBussinessItemResp> statisticBussiness(StatisticBussinessReq statisticBussinessReq);
}