package cn.exrick.xboot.your.mapper;

import cn.exrick.xboot.core.entity.Log;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统日志数据处理层
 * @author Jingqi Chen
 */
public interface LogMapper extends BaseMapper<Log> {


    List<Log> findByDataflagAndlogDescAndType(@Param("type") Integer type, @Param("dataFlag") String dataFlag, @Param("desc") String desc );
}