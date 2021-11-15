package cn.exrick.xboot.your.service;

import cn.exrick.xboot.core.base.XbootBaseService;
import cn.exrick.xboot.your.entity.HelperOrderCompensate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.exrick.xboot.core.common.vo.SearchVo;

import java.util.List;

/**
 * 互助单赔付信息接口
 * @author Jingqi Chen
 */
public interface HelperOrderCompensateService extends XbootBaseService<HelperOrderCompensate, String> {

    /**
    * 多条件分页获取
    * @param helperOrderCompensate
    * @param searchVo
    * @param pageable
    * @return
    */
    Page<HelperOrderCompensate> findByCondition(HelperOrderCompensate helperOrderCompensate, SearchVo searchVo, Pageable pageable);

    HelperOrderCompensate getByHelpOrderId(String helpOrderId);
}