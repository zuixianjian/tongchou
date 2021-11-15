 package cn.exrick.xboot.your.dao;

import cn.exrick.xboot.core.base.XbootBaseDao;
import cn.exrick.xboot.your.entity.HelperOrder;

import java.util.List;

/**
 * 互助单主表数据处理层
 * @author Jingqi Chen
 */
public interface HelperOrderDao extends XbootBaseDao<HelperOrder, String> {

    List<HelperOrder> findByOrderIdAndStatusIn(String orderId,List<String> status);
}