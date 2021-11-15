package cn.exrick.xboot.your.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.exrick.xboot.your.entity.CheckCode;

import java.util.List;

/**
 * 验车码数据处理层
 * @author Jingqi Chen
 */
public interface CheckCodeMapper extends BaseMapper<CheckCode> {

    CheckCode getTodayCode();
}