package cn.exrick.xboot.core.dao;

import cn.exrick.xboot.core.base.XbootBaseDao;
import cn.exrick.xboot.core.entity.Log;

import java.util.List;

/**
 * 日志数据处理层
 * @author Exrickx
 */
public interface LogDao extends XbootBaseDao<Log, String> {

    List<Log> findByLogTypeAndDataFlagAndLogDesc(Integer logType, String dataFlag, String logDesc);
}
