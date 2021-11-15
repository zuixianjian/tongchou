package cn.exrick.xboot.your.service;

import cn.exrick.xboot.core.base.XbootBaseService;
import cn.exrick.xboot.your.entity.HelperDamageDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.exrick.xboot.core.common.vo.SearchVo;

import java.util.List;

/**
 * 互助单损失明细接口
 * @author Jingqi Chen
 */
public interface HelperDamageDetailService extends XbootBaseService<HelperDamageDetail, String> {

    /**
    * 多条件分页获取
    * @param helperDamageDetail
    * @param searchVo
    * @param pageable
    * @return
    */
    Page<HelperDamageDetail> findByCondition(HelperDamageDetail helperDamageDetail, SearchVo searchVo, Pageable pageable);

}