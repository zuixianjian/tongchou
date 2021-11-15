 package cn.exrick.xboot.your.dao;

import cn.exrick.xboot.core.base.XbootBaseDao;
import cn.exrick.xboot.your.entity.OrderDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 统筹单详情数据处理层
 * @author Jingqi Chen
 */
public interface OrderDetailDao extends XbootBaseDao<OrderDetail, String> {

    List<OrderDetail> findByOrderIdOrderByCreateTimeDesc(String OrderId);

    @Transactional//事务的注解
    @Query("update OrderDetail n set n.delFlag=1 where n.orderId =?1")
    @Modifying
    int modifyByOrderId(String orderId);


}