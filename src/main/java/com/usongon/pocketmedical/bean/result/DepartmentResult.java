package com.usongon.pocketmedical.bean.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 * @author yxp
 * @date 2020-04-01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResult {
    private String departmentId;

    /**
    * 部门名
    */
    private String departmentName;
}