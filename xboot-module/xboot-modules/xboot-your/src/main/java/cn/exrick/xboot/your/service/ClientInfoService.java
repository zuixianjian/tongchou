package cn.exrick.xboot.your.service;

import cn.exrick.xboot.core.base.XbootBaseService;
import cn.exrick.xboot.your.entity.ClientInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.exrick.xboot.core.common.vo.SearchVo;

import java.util.List;

/**
 * 客户信息维护接口
 * @author Jingqi Chen
 */
public interface ClientInfoService extends XbootBaseService<ClientInfo, String> {

    /**
    * 多条件分页获取
    * @param clientInfo
    * @param searchVo
    * @param pageable
    * @return
    */
    Page<ClientInfo> findByCondition(ClientInfo clientInfo, SearchVo searchVo, Pageable pageable);

}