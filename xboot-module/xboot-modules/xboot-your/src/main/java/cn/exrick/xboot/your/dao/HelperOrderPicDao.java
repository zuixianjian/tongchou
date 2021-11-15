 package cn.exrick.xboot.your.dao;

import cn.exrick.xboot.core.base.XbootBaseDao;
import cn.exrick.xboot.your.entity.HelperOrderPic;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 互助单主表数据处理层
 * @author Jingqi Chen
 */
public interface HelperOrderPicDao extends XbootBaseDao<HelperOrderPic, String> {

    List<HelperOrderPic> getByClassifyAndHelperOrderIdOrderByCreateTime(String classify,String helperOrderId );

    @Transactional//事务的注解
    @Query("update HelperOrderPic n set n.delFlag=1 where n.helperOrderId =?1")
    @Modifying
    int deleteByOrderId(String helperOrderId);

}