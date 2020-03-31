package com.usongon.pocketmedical.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author zhangdehua
 * @date 2020-03-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private Integer id;

    /**
    * 管理员id
    */
    private String adminId;

    /**
    * 管理员名字
    */
    private String adminName;

    /**
    * 管理员手机号
    */
    private String adminMobile;

    /**
    * 管理员密码
    */
    private String adminPassword;

    /**
    * 状态 On启用  Off停用 Del已删除
    */
    private String adminState;

}