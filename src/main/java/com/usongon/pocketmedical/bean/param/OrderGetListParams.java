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
public class OrderGetListParams {

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
    * Wait-等待 Allow-同意 Refuse-拒绝 Finished-完成 Del-已删除
    */
    private String orderState;
}