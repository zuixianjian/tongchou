package cn.exrick.xboot.your.service;

import cn.exrick.xboot.core.base.XbootBaseService;
import cn.exrick.xboot.core.entity.Log;
import cn.exrick.xboot.your.entity.Order;
import cn.exrick.xboot.your.entity.req.OrderCreateReq;
import cn.exrick.xboot.your.entity.resp.OrderDetailResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.exrick.xboot.core.common.vo.SearchVo;

import java.util.List;

/**
 * 统筹单接口
 * @author Jingqi Chen
 */
public interface OrderService extends XbootBaseService<Order, String> {

    /**
    * 多条件分页获取
    * @param order
    * @param searchVo
    * @param pageable
    * @return
    */
    Page<Order> findByCondition(Order order, SearchVo searchVo, Pageable pageable);

    void orderCreate(OrderCreateReq orderCreateReq);

    void orderEdit(OrderCreateReq orderEditReq);

    String baojia(String orderId) throws Exception;

    void commitForAudit(String orderId);

    void orderAudit(String[] ids, Boolean auditResult);

    String insuranceCert(String orderId) throws Exception;
     List<Order> findByCarNoAndFrameNoAndOrderId(String carNoHead,String carNo,String carframeNo,String id,String orderStatus);

     void  validOrder();

    List<Log> getOrderActionLog(String orderId);

    List<OrderDetailResp> queryOrder(String carNo, String carframeNo, String queryId);

    void editOrderCommitForAudit(OrderCreateReq orderEditReq);

    void orderEditEdit(OrderCreateReq orderEditReq);

    void orderEditAudit(String[] ids, Boolean auditResult);

    void orderAuditEdit(OrderCreateReq orderEditReq);

    String orderEditPrint(String orderId) throws Exception;
}