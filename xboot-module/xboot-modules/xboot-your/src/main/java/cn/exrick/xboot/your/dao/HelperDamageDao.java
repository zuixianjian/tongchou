 package cn.exrick.xboot.your.dao;

import cn.exrick.xboot.core.base.XbootBaseDao;
import cn.exrick.xboot.your.entity.HelperDamage;

import java.util.List;

/**
 * 互助单损失主表数据处理层
 * @author Jingqi Chen
 */
public interface HelperDamageDao extends XbootBaseDao<HelperDamage, String> {

    List<HelperDamage> getByHelperOrderIdAndAndHelperDamageClassifyId(String helperOrderId,String HelperDamageClassifyId);
    List<HelperDamage> getByHelperOrderId(String helperOrderId);
}