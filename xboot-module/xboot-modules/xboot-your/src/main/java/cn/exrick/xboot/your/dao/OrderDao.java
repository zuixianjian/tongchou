 package cn.exrick.xboot.your.dao;

import cn.exrick.xboot.core.base.XbootBaseDao;
import cn.exrick.xboot.your.entity.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 统筹单数据处理层
 * @author Jingqi Chen
 */
public interface OrderDao extends XbootBaseDao<Order, String> {

    @Transactional
    @Modifying
    @Query("update Order m set m.orderStatus= ?2 where m.id=?1")
    void changeStatus(String id, String status);

    List<Order> findByCarNo(String carNo);
}