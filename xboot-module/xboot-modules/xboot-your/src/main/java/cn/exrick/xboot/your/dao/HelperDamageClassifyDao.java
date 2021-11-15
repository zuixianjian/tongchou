 package cn.exrick.xboot.your.dao;

import cn.exrick.xboot.core.base.XbootBaseDao;
import cn.exrick.xboot.your.entity.HelperDamageClassify;

import java.util.List;

/**
 * 互助单损失主表分类(记录相应的险种以及损失额)数据处理层
 * @author Jingqi Chen
 */
public interface HelperDamageClassifyDao extends XbootBaseDao<HelperDamageClassify, String> {

    List<HelperDamageClassify> getByHelperOrderId(String helpOrderId);

}