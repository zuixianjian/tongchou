package cn.exrick.xboot.your.service;

import cn.exrick.xboot.core.base.XbootBaseService;
import cn.exrick.xboot.your.entity.HelperDamageClassify;
import cn.exrick.xboot.your.entity.resp.DamageListResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.exrick.xboot.core.common.vo.SearchVo;

import java.util.List;

/**
 * 互助单损失主表分类(记录相应的险种以及损失额)接口
 * @author Jingqi Chen
 */
public interface HelperDamageClassifyService extends XbootBaseService<HelperDamageClassify, String> {

    /**
    * 多条件分页获取
    * @param helperDamageClassify
    * @param searchVo
    * @param pageable
    * @return
    */
    Page<HelperDamageClassify> findByCondition(HelperDamageClassify helperDamageClassify, SearchVo searchVo, Pageable pageable);

    DamageListResp getDamageList(String helpOrderId);
}