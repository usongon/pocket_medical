package com.usongon.pocketmedical.bean.entity;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author zhangdehua
 * @date 2020/5/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;

    /**
    * 预约id
    */
    private String orderId;

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
    private LocalDateTime orderTime;

    /**
    * Wait-等待 Allow-同意 Refuse-拒绝 Finished-完成 Del-已删除
    */
    private String orderState;

    /**
    * 创建时间
    */
    private Date createTime;

    private Date updateTime;
}