 package cn.exrick.xboot.your.dao;

import cn.exrick.xboot.core.base.XbootBaseDao;
import cn.exrick.xboot.your.entity.OrderFile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 统筹单附件数据处理层
 * @author Jingqi Chen
 */
public interface OrderFileDao extends XbootBaseDao<OrderFile, String> {

    List<OrderFile> findByOrderIdOrderByCreateTimeDesc(String orderId);

    @Transactional//事务的注解
    @Query("update OrderFile n set n.delFlag=1 where n.orderId =?1")
    @Modifying
    int modifyByOrderId(String orderId);
}