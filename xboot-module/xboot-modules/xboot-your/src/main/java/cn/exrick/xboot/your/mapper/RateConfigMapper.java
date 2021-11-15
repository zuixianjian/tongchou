package cn.exrick.xboot.your.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.exrick.xboot.your.entity.RateConfig;

import java.util.List;

/**
 * 费率设置数据处理层
 * @author Jingqi Chen
 */
public interface RateConfigMapper extends BaseMapper<RateConfig> {
List<RateConfig> getDriverOrPassenger(RateConfig rateConfig);
    List<RateConfig> getCarloss(RateConfig rateConfig);
    List<RateConfig> getThirdDuty(RateConfig rateConfig);
}