package cn.exrick.xboot.your.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.exrick.xboot.your.entity.DictData;

import java.util.List;

/**
 * 数据字典接口
 * @author Jingqi Chen
 */
public interface IDictDataService extends IService<DictData> {

    DictData getByDictTypeAndDictValue(String dictType,String value);
}