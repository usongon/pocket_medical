package com.usongon.pocketmedical.bean.param;

import lombok.Data;

/**
 * @author yxp
 * @date 2020-03-29
 */
@Data
public class DoctorSelectParams {

    /**
     * 医生id
     */
    private String docId;

    /**
     * 所属部门id
     */
    private String departmentId;

    /**
     * 关键字
     */
    private String keywords;


    /**
     * '状态 On启用  Off停用  Unaudited未审核 Del已删除'
     */
    private String docState;
}