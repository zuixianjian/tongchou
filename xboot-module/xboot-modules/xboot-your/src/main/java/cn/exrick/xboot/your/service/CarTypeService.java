package cn.exrick.xboot.your.service;

import cn.exrick.xboot.core.base.XbootBaseService;
import cn.exrick.xboot.your.entity.CarType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.exrick.xboot.core.common.vo.SearchVo;

import java.util.List;

/**
 * 车型信息维护接口
 * @author Jingqi Chen
 */
public interface CarTypeService extends XbootBaseService<CarType, String> {

    /**
    * 多条件分页获取
    * @param carType
    * @param searchVo
    * @param pageable
    * @return
    */
    Page<CarType> findByCondition(CarType carType, SearchVo searchVo, Pageable pageable);

}