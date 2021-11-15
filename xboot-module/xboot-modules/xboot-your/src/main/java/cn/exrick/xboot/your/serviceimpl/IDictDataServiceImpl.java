package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.mapper.DictDataMapper;
import cn.exrick.xboot.your.entity.DictData;
import cn.exrick.xboot.your.service.IDictDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class IDictDataServiceImpl extends ServiceImpl<DictDataMapper, DictData> implements IDictDataService {

    @Autowired
    private DictDataMapper dictDataMapper;

    @Override
    public DictData getByDictTypeAndDictValue(String dictType, String value) {
        return dictDataMapper.getByDictTypeAndDictValue( dictType,  value);
    }
}