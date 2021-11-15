package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.mapper.YouhuiMapper;
import cn.exrick.xboot.your.entity.Youhui;
import cn.exrick.xboot.your.service.IYouhuiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 优惠政策配置接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class IYouhuiServiceImpl extends ServiceImpl<YouhuiMapper, Youhui> implements IYouhuiService {

    @Autowired
    private YouhuiMapper youhuiMapper;
}