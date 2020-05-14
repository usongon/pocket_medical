package com.usongon.pocketmedical.dao;

import com.usongon.pocketmedical.bean.entity.Admin;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author yxp
 * @date 2020-03-31
 */
@Mapper
public interface AdminMapper {
    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByAdminIdAndAdminState(@Param("adminId")String adminId);

    Admin selectByAdminMobileAndAdminState(@Param("adminMobile") String adminMobile);

    int updateByAdminId(@Param("updated") Admin updated, @Param("adminId") String adminId);
}