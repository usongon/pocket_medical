package com.usongon.pocketmedical.bean.param;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yxp
 * @date 2020-03-29
 */
@Data
@AllArgsConstructor
public class DoctorRegisterParams {

    /**
     * 所属部门id
     */
    private String departmentId;

    /**
     * 医生姓名
     */
    private String docName;

    /**
     * 手机号
     */
    private String docMobile;

    /**
     * 密码
     */
    private String docPassword;

    /**
     * 性别  填'男' or '女'
     */
    private Integer docSex;

    /**
     * 医生年龄
     */
    private Integer docAge;

    /**
     * 医生身份
     */
    private String docRole;

}