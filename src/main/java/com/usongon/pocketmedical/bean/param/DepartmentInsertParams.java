package com.usongon.pocketmedical.bean.param;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 *
 * @author zhangdehua
 * @date 2020-04-01
 */
@Data
@AllArgsConstructor
public class DepartmentInsertParams {
    /**
    * 部门名
    */
    private String departmentName;
}