package cn.exrick.xboot.your.service;

import cn.exrick.xboot.core.base.XbootBaseService;
import cn.exrick.xboot.your.entity.OrderFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.exrick.xboot.core.common.vo.SearchVo;

import java.util.List;

/**
 * 统筹单附件接口
 * @author Jingqi Chen
 */
public interface OrderFileService extends XbootBaseService<OrderFile, String> {

    /**
    * 多条件分页获取
    * @param orderFile
    * @param searchVo
    * @param pageable
    * @return
    */
    Page<OrderFile> findByCondition(OrderFile orderFile, SearchVo searchVo, Pageable pageable);

    List<OrderFile> findByOrderId(String orderId);

}