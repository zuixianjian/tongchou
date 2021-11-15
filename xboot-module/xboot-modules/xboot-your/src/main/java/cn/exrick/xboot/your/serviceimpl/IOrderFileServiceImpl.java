package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.mapper.OrderFileMapper;
import cn.exrick.xboot.your.entity.OrderFile;
import cn.exrick.xboot.your.service.IOrderFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 统筹单附件接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class IOrderFileServiceImpl extends ServiceImpl<OrderFileMapper, OrderFile> implements IOrderFileService {

    @Autowired
    private OrderFileMapper orderFileMapper;
}