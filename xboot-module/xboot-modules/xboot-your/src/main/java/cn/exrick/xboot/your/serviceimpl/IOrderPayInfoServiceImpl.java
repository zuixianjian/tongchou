package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.mapper.OrderPayInfoMapper;
import cn.exrick.xboot.your.entity.OrderPayInfo;
import cn.exrick.xboot.your.service.IOrderPayInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 批单/统筹单收款单接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class IOrderPayInfoServiceImpl extends ServiceImpl<OrderPayInfoMapper, OrderPayInfo> implements IOrderPayInfoService {

    @Autowired
    private OrderPayInfoMapper orderPayInfoMapper;
}