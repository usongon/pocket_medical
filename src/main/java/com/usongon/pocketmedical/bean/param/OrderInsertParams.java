package com.usongon.pocketmedical.bean.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author yxp
 * @date 2020/5/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInsertParams {
    /**
    * 预约的患者id
    */
    private String orderPatient;

    /**
    * 预约的部门id
    */
    private String orderDepartment;

    /**
    * 预约的医生的id，可以为空
    */
    private String orderDoctor;

    /**
    * 预约的详细信息
    */
    private String orderContent;

    /**
    * 预约的时间
    */
    private String orderTime;
}