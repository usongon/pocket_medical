package com.usongon.pocketmedical.dao;

import com.usongon.pocketmedical.bean.entity.Department;
import java.util.List;

import com.usongon.pocketmedical.bean.result.DepartmentResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author zhangdehua
 * @date 2020-04-01
 */
@Mapper
public interface DepartmentMapper {
    int insert(Department record);

    int insertSelective(Department record);

    List<DepartmentResult> selectAllByDepartmentIdAndIsDel();

    int updateIsDelByDepartmentId(@Param("departmentId") String departmentId);
}