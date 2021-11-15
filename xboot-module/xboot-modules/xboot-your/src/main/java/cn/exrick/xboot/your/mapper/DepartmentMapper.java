package cn.exrick.xboot.your.mapper;

import cn.exrick.xboot.core.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 部门数据处理层
 * @author Jingqi Chen
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> getAllHeadDepartmentsByDepartmentId(String departmentId);
}