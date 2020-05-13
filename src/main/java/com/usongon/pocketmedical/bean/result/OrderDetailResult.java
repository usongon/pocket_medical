package com.usongon.pocketmedical.bean.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author zhangdehua
 * @date 2020/5/13
 */

/**
    * 预约表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResult {
    /**
    * 预约id
    */
    private String orderId;

    /**
    * 预约的患者id
    */
    private String orderPatient;

    /**
     * 预约的患者姓名
     */
    private String patientName;

    /**
    * 预约的部门id
    */
    private String orderDepartment;

    /**
     * 预约的部门名
     */
    private String departmentName;

    /**
    * 预约的医生的id，可以为空
    */
    private String orderDoctor;

    /**
     * 预约的医生名
     */
    private String doctorName;

    /**
    * 预约的详细信息
    */
    private String orderContent;

    /**
    * 预约的时间
    */
    private LocalDateTime orderTime;

    /**
    * Wait-等待 Allow-同意 Refuse-拒绝 Finished-完成 Del-已删除
    */
    private String orderState;

    /**
    * 创建时间
    */
    private Date createTime;

}