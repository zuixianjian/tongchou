package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.mapper.CarDepreciationMapper;
import cn.exrick.xboot.your.entity.CarDepreciation;
import cn.exrick.xboot.your.service.ICarDepreciationService;
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
public class ICarDepreciationServiceImpl extends ServiceImpl<CarDepreciationMapper, CarDepreciation> implements ICarDepreciationService {

    @Autowired
    private CarDepreciationMapper carDepreciationMapper;
}