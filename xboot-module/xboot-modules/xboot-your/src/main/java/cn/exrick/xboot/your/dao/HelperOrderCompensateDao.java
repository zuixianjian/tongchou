 package cn.exrick.xboot.your.dao;

import cn.exrick.xboot.core.base.XbootBaseDao;
import cn.exrick.xboot.your.entity.HelperOrderCompensate;

import java.util.List;

/**
 * 互助单赔付信息数据处理层
 * @author Jingqi Chen
 */
public interface HelperOrderCompensateDao extends XbootBaseDao<HelperOrderCompensate, String> {

    HelperOrderCompensate getByHelperOrderId(String helperOrderId);
}