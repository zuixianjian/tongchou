package cn.exrick.xboot.your.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.exrick.xboot.your.entity.DictData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据字典数据处理层
 * @author Jingqi Chen
 */
public interface DictDataMapper extends BaseMapper<DictData> {

    DictData getByDictTypeAndDictValue(@Param("dictType")String dictType, @Param("value")String value);
    DictData getByDictTypeAndDictTitle(@Param("dictType")String dictType, @Param("title")String title);

    List<DictData> getByDictType(@Param("dictType") String dictType);
}