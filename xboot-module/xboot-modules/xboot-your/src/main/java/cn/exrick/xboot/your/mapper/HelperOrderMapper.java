package cn.exrick.xboot.your.mapper;

import cn.exrick.xboot.core.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.exrick.xboot.your.entity.HelperOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 互助单数据处理层
 * @author Jingqi Chen
 */
public interface HelperOrderMapper extends BaseMapper<HelperOrder> {

    List<User> getDiochaUserList(@Param("roleId") String roleId, @Param("departmentids")String[] departmentids);
}