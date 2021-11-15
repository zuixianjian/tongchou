package cn.exrick.xboot.your.service;

import cn.exrick.xboot.core.base.XbootBaseService;
import cn.exrick.xboot.your.entity.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.exrick.xboot.core.common.vo.SearchVo;

import java.util.List;

/**
 * 统筹单详情接口
 * @author Jingqi Chen
 */
public interface OrderDetailService extends XbootBaseService<OrderDetail, String> {

    /**
    * 多条件分页获取
    * @param orderDetail
    * @param searchVo
    * @param pageable
    * @return
    */
    Page<OrderDetail> findByCondition(OrderDetail orderDetail, SearchVo searchVo, Pageable pageable);


    List<OrderDetail> findByOrderIdAndType(String orderId);

}