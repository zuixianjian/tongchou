package cn.exrick.xboot.your.service;

import cn.exrick.xboot.core.base.XbootBaseService;
import cn.exrick.xboot.your.entity.HelperDamage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.exrick.xboot.core.common.vo.SearchVo;

import java.util.List;

/**
 * 互助单损失主表接口
 * @author Jingqi Chen
 */
public interface HelperDamageService extends XbootBaseService<HelperDamage, String> {

    /**
    * 多条件分页获取
    * @param helperDamage
    * @param searchVo
    * @param pageable
    * @return
    */
    Page<HelperDamage> findByCondition(HelperDamage helperDamage, SearchVo searchVo, Pageable pageable);

}