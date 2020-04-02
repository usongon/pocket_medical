package com.usongon.pocketmedical.bean.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author zhangdehua
 * @date 2020-04-01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private Integer id;

    /**
    * 部门Id

    */
    private String departmentId;

    /**
    * 部门名
    */
    private String departmentName;

    /**
    * 0-未删除  1-已删除
    */
    private Byte isDel;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 修改时间
    */
    private Date updateTime;
}