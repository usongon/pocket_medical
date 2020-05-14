package com.usongon.pocketmedical.bean.result;

import lombok.Data;

/**
 * @author yxp
 * @date 2020-03-29
 */
@Data
public class DoctorResult {

    /**
     * 医生id
     */
    private String docId;

    /**
     * 所属部门id
     */
    private String departmentId;

    private String departmentName;

    /**
     * 医生姓名
     */
    private String docName;

    /**
     * 手机号
     */
    private String docMobile;


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