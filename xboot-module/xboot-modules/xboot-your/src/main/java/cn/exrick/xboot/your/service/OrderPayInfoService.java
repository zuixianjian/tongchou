package cn.exrick.xboot.your.service;

import cn.exrick.xboot.core.base.XbootBaseService;
import cn.exrick.xboot.your.entity.OrderPayInfo;
import cn.exrick.xboot.your.entity.req.StatisticBussinessReq;
import cn.exrick.xboot.your.entity.req.StatisticOrderReq;
import cn.exrick.xboot.your.entity.resp.StatisticBussinessItemResp;
import cn.exrick.xboot.your.entity.resp.StatisticBussinessResp;
import cn.exrick.xboot.your.entity.resp.StatisticOrderResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.exrick.xboot.core.common.vo.SearchVo;

import java.util.List;

/**
 * 批单/统筹单收款单接口
 * @author Jingqi Chen
 */
public interface OrderPayInfoService extends XbootBaseService<OrderPayInfo, String> {

    /**
    * 多条件分页获取
    * @param orderPayInfo
    * @param searchVo
    * @param pageable
    * @return
    */
    Page<OrderPayInfo> findByCondition(OrderPayInfo orderPayInfo, SearchVo searchVo, Pageable pageable);

    void paySuccess(OrderPayInfo entity);

    StatisticOrderResp statisticOrder(StatisticOrderReq statisticOrderReq);

    StatisticBussinessResp statisticBussiness(StatisticBussinessReq statisticBussinessReq);
}