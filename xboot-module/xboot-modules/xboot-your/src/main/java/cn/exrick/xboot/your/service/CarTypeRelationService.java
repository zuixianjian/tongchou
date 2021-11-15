package cn.exrick.xboot.your.service;

import cn.exrick.xboot.core.base.XbootBaseService;
import cn.exrick.xboot.your.entity.CarTypeRelation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.exrick.xboot.core.common.vo.SearchVo;

import java.util.List;

/**
 * 车型关联信息表接口
 * @author Jingqi Chen
 */
public interface CarTypeRelationService extends XbootBaseService<CarTypeRelation, String> {

    /**
    * 多条件分页获取
    * @param carTypeRelation
    * @param searchVo
    * @param pageable
    * @return
    */
    Page<CarTypeRelation> findByCondition(CarTypeRelation carTypeRelation, SearchVo searchVo, Pageable pageable);

    List<CarTypeRelation> getCarTypeB(String carTypeA);
}