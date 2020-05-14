package com.usongon.pocketmedical.bean.param;

import lombok.Data;

/**
 * @author yxp
 * @date 2020-03-29
 */
@Data
public class PatientRegisterParams {

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
     * 患者年龄
     */
    private Integer patientAge;

    /**
     * 过敏史
     */
    private String allergyHistory;

}