package com.usongon.pocketmedical.bean.entity;

import lombok.Data;

/**
 * @author zhangdehua
 * @date 2020-03-29
 */
@Data
public class Patient {
    private Integer id;

    /**
     * 患者id
     */
    private String patientId;

    /**
     * 患者姓名
     */
    private String patientName;

    /**
     * 患者手机号
     */
    private String patientMobile;

    /**
     * 患者账号的密码
     */
    private String patientPassword;

    /**
     * 性别
     */
    private String patientSex;

    /**
     * 过敏史
     */
    private String allergyHistory;

    /**
     * '状态 On启用  Off停用  Unaudited未审核 Del已删除'
     */
    private String patientState;
}