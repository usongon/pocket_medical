package com.usongon.pocketmedical.bean.entity;

import lombok.Data;

/**
 * @author zhangdehua
 * @date 2020-03-29
 */
@Data
public class Doctor {
    /**
     * id
     */
    private Integer id;

    /**
     * 医生id
     */
    private String docId;

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
    private String docSex;

    /**
     * 医生年龄
     */
    private Integer docAge;

    /**
     * 医生身份
     */
    private String docRole;

    /**
     * '状态 On启用  Off停用  Unaudited未审核 Del已删除'
     */
    private String docState;
}