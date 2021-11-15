package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.mapper.ChannelDiscountMapper;
import cn.exrick.xboot.your.entity.ChannelDiscount;
import cn.exrick.xboot.your.service.IChannelDiscountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 渠道折扣配置接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class IChannelDiscountServiceImpl extends ServiceImpl<ChannelDiscountMapper, ChannelDiscount> implements IChannelDiscountService {

    @Autowired
    private ChannelDiscountMapper channelDiscountMapper;
}