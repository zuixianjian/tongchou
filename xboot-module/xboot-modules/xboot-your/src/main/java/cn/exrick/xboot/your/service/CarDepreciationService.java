package cn.exrick.xboot.your.service;

import cn.exrick.xboot.core.base.XbootBaseService;
import cn.exrick.xboot.your.entity.CarDepreciation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.exrick.xboot.core.common.vo.SearchVo;

import java.util.List;

/**
 * 月折旧率接口
 * @author Jingqi Chen
 */
public interface CarDepreciationService extends XbootBaseService<CarDepreciation, String> {

    /**
    * 多条件分页获取
    * @param carDepreciation
    * @param searchVo
    * @param pageable
    * @return
    */
    Page<CarDepreciation> findByCondition(CarDepreciation carDepreciation, SearchVo searchVo, Pageable pageable);

}