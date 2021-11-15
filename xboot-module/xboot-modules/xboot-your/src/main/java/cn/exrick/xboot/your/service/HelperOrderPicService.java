package cn.exrick.xboot.your.service;

import cn.exrick.xboot.core.base.XbootBaseService;
import cn.exrick.xboot.your.entity.HelperOrderPic;
import cn.exrick.xboot.your.entity.req.HelpOrderPicSaveReq;
import cn.exrick.xboot.your.entity.resp.HelpOrderPicsResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.exrick.xboot.core.common.vo.SearchVo;

import java.util.List;

/**
 * 互助单主表接口
 * @author Jingqi Chen
 */
public interface HelperOrderPicService extends XbootBaseService<HelperOrderPic, String> {

    /**
    * 多条件分页获取
    * @param helperOrderPic
    * @param searchVo
    * @param pageable
    * @return
    */
    Page<HelperOrderPic> findByCondition(HelperOrderPic helperOrderPic, SearchVo searchVo, Pageable pageable);

    List<HelpOrderPicsResp> getByHelpOrderId(String helpOrderId);

    void saveHelpOrderPics(HelpOrderPicSaveReq helpOrderPicSaveReq);
}