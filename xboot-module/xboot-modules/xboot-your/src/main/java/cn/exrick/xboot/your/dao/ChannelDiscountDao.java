 package cn.exrick.xboot.your.dao;

import cn.exrick.xboot.core.base.XbootBaseDao;
import cn.exrick.xboot.your.entity.ChannelDiscount;

import java.util.List;

/**
 * 渠道折扣配置数据处理层
 * @author Jingqi Chen
 */
public interface ChannelDiscountDao extends XbootBaseDao<ChannelDiscount, String> {

    List<ChannelDiscount> findByDepartmentId(String departMentId);
}