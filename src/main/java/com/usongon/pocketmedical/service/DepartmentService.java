package com.usongon.pocketmedical.service;

import com.usongon.pocketmedical.bean.entity.Department;
import com.usongon.pocketmedical.bean.result.DepartmentResult;

import java.util.List;
    /**
 *
 * @author yxp
 * @date 2020-04-01
 */
public interface DepartmentService{


    int insert(String departmentName);

    int insertSelective(Department record);

    List<DepartmentResult> selectAllByDepartmentIdAndIsDel();

    Department selectByDepartmentIdAndIsDel(String departmentId);

    int deleteDepartmentById(String departmentId);

}
