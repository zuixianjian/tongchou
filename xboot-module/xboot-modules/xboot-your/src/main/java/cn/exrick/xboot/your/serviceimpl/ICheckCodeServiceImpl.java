package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.mapper.CheckCodeMapper;
import cn.exrick.xboot.your.entity.CheckCode;
import cn.exrick.xboot.your.service.ICheckCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 验车码接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class ICheckCodeServiceImpl extends ServiceImpl<CheckCodeMapper, CheckCode> implements ICheckCodeService {

    @Autowired
    private CheckCodeMapper checkCodeMapper;
}