package cn.exrick.xboot.your.service;

import cn.exrick.xboot.core.base.XbootBaseService;
import cn.exrick.xboot.your.entity.ChannelDiscount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.exrick.xboot.core.common.vo.SearchVo;

import java.util.List;

/**
 * 渠道折扣配置接口
 * @author Jingqi Chen
 */
public interface ChannelDiscountService extends XbootBaseService<ChannelDiscount, String> {

    /**
    * 多条件分页获取
    * @param channelDiscount
    * @param searchVo
    * @param pageable
    * @return
    */
    Page<ChannelDiscount> findByCondition(ChannelDiscount channelDiscount, SearchVo searchVo, Pageable pageable);

    List<ChannelDiscount> findByDepartMentId(String departMentId);
}