package com.usongon.pocketmedical.service;

import com.usongon.pocketmedical.bean.entity.Admin;
import com.usongon.pocketmedical.bean.param.AdminInsertParams;

import java.util.List;
    /**
 *
 * @author zhangdehua
 * @date 2020-03-31
 */
public interface AdminService{


    int insert(AdminInsertParams params);

    int insertSelective(Admin record);

    Admin selectByAdminMobileAndAdminState(String adminMobile);

    int updateByAdminId(Admin updated,String adminId);

    List<Admin> selectByAdminIdAndAdminState(String adminId);

}
