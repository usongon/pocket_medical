package com.usongon.pocketmedical.bean.param;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author zhangdehua
 * @date 2020-03-31
 */
@Data
@AllArgsConstructor
public class AdminInsertParams {

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

}