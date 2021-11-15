package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.mapper.OrderMapper;
import cn.exrick.xboot.your.entity.Order;
import cn.exrick.xboot.your.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 统筹单列表接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class IOrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
}