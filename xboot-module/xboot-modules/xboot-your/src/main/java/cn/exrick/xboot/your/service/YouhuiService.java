package cn.exrick.xboot.your.service;

import cn.exrick.xboot.core.base.XbootBaseService;
import cn.exrick.xboot.your.entity.Youhui;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.exrick.xboot.core.common.vo.SearchVo;

import java.util.List;

/**
 * 优惠政策配置接口
 * @author Jingqi Chen
 */
public interface YouhuiService extends XbootBaseService<Youhui, String> {

    /**
    * 多条件分页获取
    * @param youhui
    * @param searchVo
    * @param pageable
    * @return
    */
    Page<Youhui> findByCondition(Youhui youhui, SearchVo searchVo, Pageable pageable);

    Page<Youhui> getMineYouhui(Youhui youhui, SearchVo searchVo, Pageable initPage);
}