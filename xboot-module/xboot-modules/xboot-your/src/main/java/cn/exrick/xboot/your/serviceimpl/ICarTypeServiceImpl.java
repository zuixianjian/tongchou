package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.mapper.CarTypeMapper;
import cn.exrick.xboot.your.entity.CarType;
import cn.exrick.xboot.your.service.ICarTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 车型代码接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class ICarTypeServiceImpl extends ServiceImpl<CarTypeMapper, CarType> implements ICarTypeService {

    @Autowired
    private CarTypeMapper carTypeMapper;
}