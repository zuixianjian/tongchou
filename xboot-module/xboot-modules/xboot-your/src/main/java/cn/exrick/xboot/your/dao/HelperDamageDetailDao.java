 package cn.exrick.xboot.your.dao;

import cn.exrick.xboot.core.base.XbootBaseDao;
import cn.exrick.xboot.your.entity.HelperDamageDetail;

import java.util.List;

/**
 * 互助单损失明细数据处理层
 * @author Jingqi Chen
 */
public interface HelperDamageDetailDao extends XbootBaseDao<HelperDamageDetail, String> {

    List<HelperDamageDetail> getByHelperDamageId(String helperDamageId);
}