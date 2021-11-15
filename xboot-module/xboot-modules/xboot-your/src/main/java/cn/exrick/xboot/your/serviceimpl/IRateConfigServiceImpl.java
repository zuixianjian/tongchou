package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.mapper.RateConfigMapper;
import cn.exrick.xboot.your.entity.RateConfig;
import cn.exrick.xboot.your.service.IRateConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 费率设置接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class IRateConfigServiceImpl extends ServiceImpl<RateConfigMapper, RateConfig> implements IRateConfigService {

    @Autowired
    private RateConfigMapper rateConfigMapper;
}