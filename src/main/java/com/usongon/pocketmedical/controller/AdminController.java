package com.usongon.pocketmedical.controller;

import com.usongon.pocketmedical.bean.ResponseResult;
import com.usongon.pocketmedical.bean.param.AdminInsertParams;
import com.usongon.pocketmedical.bean.param.DoctorSelectParams;
import com.usongon.pocketmedical.bean.param.PatientSelectParams;
import com.usongon.pocketmedical.bean.session.AdminSession;
import com.usongon.pocketmedical.common.helper.GlobalHelper;
import com.usongon.pocketmedical.enums.EDoctorAndPatientState;
import com.usongon.pocketmedical.enums.EResponseCode;
import com.usongon.pocketmedical.framework.annotation.Authorize;
import com.usongon.pocketmedical.framework.exception.BusinessException;
import com.usongon.pocketmedical.service.AdminService;
import com.usongon.pocketmedical.service.DepartmentService;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yxp
 * @date 2020-03-31
 */
@RestController
@Authorize
public class AdminController {
    @Resource
    private AdminService adminService;
    @Resource
    private DepartmentService departmentService;

    @PostMapping("/admin/insert")
    public Object insertAdmin(AdminInsertParams params){
        //获取session
        AdminSession session = GlobalHelper.get();
        //权限验证
        if (adminService.selectByAdminIdAndAdminState(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "只有管理员可以插入新的管理员", "");
        }
        //调用service 层
        adminService.insert(params);
        return ResponseResult.success();
    }

    @PostMapping("/admin/doctor/changestate")
    public Object changeDocState(String docId, EDoctorAndPatientState state){
        AdminSession session = GlobalHelper.get();
        if (adminService.selectByAdminIdAndAdminState(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "只有管理员可以变更医生账号状态", "");
        }
        adminService.changeDocState(docId, state);
        return ResponseResult.success();
    }

    @PostMapping("/admin/doctor/getlist")
    public Object getDoctorList(DoctorSelectParams params){
        AdminSession session = GlobalHelper.get();
        if (adminService.selectByAdminIdAndAdminState(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "只有管理员可以获取医生账号列表", "");
        }
        return ResponseResult.success(adminService.getDoctorList(params));
    }

    @PostMapping("/admin/patient/getlist")
    public Object getPatientList(PatientSelectParams params){
        AdminSession session = GlobalHelper.get();
        if (adminService.selectByAdminIdAndAdminState(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "只有管理员可以获取医生账号列表", "");
        }
        return ResponseResult.success(adminService.getPatientList(params));
    }

    @PostMapping("/admin/patient/changestate")
    public Object changePatientState(String patientId, EDoctorAndPatientState state){
        AdminSession session = GlobalHelper.get();
        if (adminService.selectByAdminIdAndAdminState(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "只有管理员可以变更患者账号状态", "");
        }
        if (state.equals(EDoctorAndPatientState.Unaudited)){
            throw new BusinessException(EResponseCode.BizError, "操作有误！", "");
        }
        adminService.changePatientState(patientId, state);
        return ResponseResult.success();
    }

    @PostMapping("/admin/department/insert")
    public Object insertDepartment(String departmentName){
        AdminSession session = GlobalHelper.get();
        if (adminService.selectByAdminIdAndAdminState(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "只有管理员可以添加部门！", "");
        }
        adminService.insertDepartment(departmentName);
        return ResponseResult.success();
    }

    /**
     * 管理员删除部门
     */
    @PostMapping("/admin/department/delete")
    public Object deleteDepartment(String departmentId){
        AdminSession session = GlobalHelper.get();
        departmentService.deleteDepartmentById(departmentId);
        return ResponseResult.success();
    }

}
