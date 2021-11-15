package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.core.entity.Department;
import cn.exrick.xboot.your.mapper.DepartmentMapper;
import cn.exrick.xboot.your.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 部门接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class IDepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAllHeadDepartmentsByDepartmentId(String departmentId) {
        return departmentMapper.getAllHeadDepartmentsByDepartmentId(departmentId);
    }
}