package com.usongon.pocketmedical.controller;

import com.usongon.pocketmedical.bean.ResponseResult;
import com.usongon.pocketmedical.bean.session.AdminSession;
import com.usongon.pocketmedical.common.helper.GlobalHelper;
import com.usongon.pocketmedical.framework.annotation.Authorize;
import com.usongon.pocketmedical.service.DepartmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yxp
 * @date 2020-04-01
 */
@RestController
@Authorize
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @Authorize(login = false)
    @PostMapping("/department/getlist")
    public Object getDepartmentList(){
        return ResponseResult.success(departmentService.selectAllByDepartmentIdAndIsDel());
    }
}
