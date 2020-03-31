package com.usongon.pocketmedical.controller;

import com.usongon.pocketmedical.bean.ResponseResult;
import com.usongon.pocketmedical.bean.param.AdminInsertParams;
import com.usongon.pocketmedical.bean.session.AdminSession;
import com.usongon.pocketmedical.common.helper.GlobalHelper;
import com.usongon.pocketmedical.enums.EResponseCode;
import com.usongon.pocketmedical.framework.annotation.Authorize;
import com.usongon.pocketmedical.framework.exception.BusinessException;
import com.usongon.pocketmedical.service.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2020-03-31
 */
@RestController
@Authorize
public class AdminController {
    @Resource
    private AdminService adminService;

    @PostMapping("/admin/insert")
    public Object insertAdmin(AdminInsertParams params){
        AdminSession session = GlobalHelper.get();
        if (adminService.selectByAdminIdAndAdminState(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "只有管理员可以插入新的管理员", "");
        }
        adminService.insert(params);
        return ResponseResult.success();
    }
}
