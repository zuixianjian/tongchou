 package cn.exrick.xboot.your.dao;

import cn.exrick.xboot.core.base.XbootBaseDao;
import cn.exrick.xboot.your.entity.CarTypeRelation;

import java.util.List;

/**
 * 车型关联信息表数据处理层
 * @author Jingqi Chen
 */
public interface CarTypeRelationDao extends XbootBaseDao<CarTypeRelation, String> {

    List<CarTypeRelation> getByCarTypeA(String carTypeA);
}