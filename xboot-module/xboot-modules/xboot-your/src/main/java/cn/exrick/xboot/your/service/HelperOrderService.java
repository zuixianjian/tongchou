package cn.exrick.xboot.your.service;

import cn.exrick.xboot.core.base.XbootBaseService;
import cn.exrick.xboot.core.entity.User;
import cn.exrick.xboot.your.entity.HelperOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.exrick.xboot.core.common.vo.SearchVo;

import java.util.List;

/**
 * 互助单主表接口
 * @author Jingqi Chen
 */
public interface HelperOrderService extends XbootBaseService<HelperOrder, String> {

    /**
    * 多条件分页获取
    * @param helperOrder
    * @param searchVo
    * @param pageable
    * @return
    */
    Page<HelperOrder> findByCondition(HelperOrder helperOrder, SearchVo searchVo, Pageable pageable);

    void createHelpOrder(HelperOrder entity);

    List<User> getDiaochaUserList();

    void submitStartDiaocha(HelperOrder entity);

    void checkFail(String helpOrderId);

    void checkSuccess(String helpOrderId);

    void commitForCheck(String helpOrderId);

    User getDiaochaUserById(String diaochaUserId);
}