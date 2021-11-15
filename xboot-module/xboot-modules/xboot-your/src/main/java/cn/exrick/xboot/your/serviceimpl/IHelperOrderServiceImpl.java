package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.mapper.HelperOrderMapper;
import cn.exrick.xboot.your.entity.HelperOrder;
import cn.exrick.xboot.your.service.IHelperOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 互助单接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class IHelperOrderServiceImpl extends ServiceImpl<HelperOrderMapper, HelperOrder> implements IHelperOrderService {

    @Autowired
    private HelperOrderMapper helperOrderMapper;


}