package com.usongon.pocketmedical.service.impl;

import com.usongon.pocketmedical.bean.result.DepartmentResult;
import com.usongon.pocketmedical.common.utils.UuidUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.usongon.pocketmedical.bean.entity.Department;
import java.util.List;
import com.usongon.pocketmedical.dao.DepartmentMapper;
import com.usongon.pocketmedical.service.DepartmentService;
/**
 *
 * @author yxp
 * @date 2020-04-01
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public int insert(String departmentName) {
        Department department = new Department();
        department.setDepartmentId(UuidUtil.randomUUID());
        department.setDepartmentName(departmentName);
        return departmentMapper.insert(department);
    }

    @Override
    public int insertSelective(Department record) {
        return departmentMapper.insertSelective(record);
    }

    @Override
    public List<DepartmentResult> selectAllByDepartmentIdAndIsDel() {
        return departmentMapper.selectAllByDepartmentIdAndIsDel();
    }

    @Override
    public Department selectByDepartmentIdAndIsDel(String departmentId) {
        return departmentMapper.selectByDepartmentIdAndIsDel(departmentId);
    }

    @Override
    public int deleteDepartmentById(String departmentId) {
        return departmentMapper.updateIsDelByDepartmentId(departmentId);
    }

}
