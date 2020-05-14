package com.usongon.pocketmedical.bean.param;

import lombok.Data;

/**
 * @author yxp
 * @date 2020-03-29
 */
@Data
public class PatientSelectParams {
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
     * 性别
     */
    private String patientSex;

    /**
     * 患者年龄
     */
    private Integer patientAge;

    /**
     * '状态 On启用  Off停用  Unaudited未审核 Del已删除'
     */
    private String patientState;
}