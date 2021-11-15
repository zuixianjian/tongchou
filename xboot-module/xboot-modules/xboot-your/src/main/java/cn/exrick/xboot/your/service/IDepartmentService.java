package cn.exrick.xboot.your.service;

import cn.exrick.xboot.core.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 部门接口
 * @author Jingqi Chen
 */
public interface IDepartmentService extends IService<Department> {

    List<Department> getAllHeadDepartmentsByDepartmentId(String departmentId);

}