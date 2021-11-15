package cn.exrick.xboot.your.service;

import cn.exrick.xboot.core.base.XbootBaseService;
import cn.exrick.xboot.your.entity.RateConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.exrick.xboot.core.common.vo.SearchVo;

import java.util.List;

/**
 * 费率设置接口
 * @author Jingqi Chen
 */
public interface RateConfigService extends XbootBaseService<RateConfig, String> {

    /**
    * 多条件分页获取
    * @param rateConfig
    * @param searchVo
    * @param pageable
    * @return
    */
    Page<RateConfig> findByCondition(RateConfig rateConfig, SearchVo searchVo, Pageable pageable);

    List<RateConfig> getMineFeeList(RateConfig rateConfig);
}