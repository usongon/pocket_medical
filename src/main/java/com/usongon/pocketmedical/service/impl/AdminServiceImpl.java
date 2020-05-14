package com.usongon.pocketmedical.service.impl;

import com.usongon.pocketmedical.bean.entity.Doctor;
import com.usongon.pocketmedical.bean.param.AdminInsertParams;
import com.usongon.pocketmedical.bean.param.DoctorSelectParams;
import com.usongon.pocketmedical.bean.param.PatientSelectParams;
import com.usongon.pocketmedical.bean.result.DepartmentResult;
import com.usongon.pocketmedical.bean.result.DoctorResult;
import com.usongon.pocketmedical.bean.result.PatientResult;
import com.usongon.pocketmedical.common.utils.PasswordUtil;
import com.usongon.pocketmedical.common.utils.UuidUtil;
import com.usongon.pocketmedical.enums.EDoctorAndPatientState;
import com.usongon.pocketmedical.enums.EResponseCode;
import com.usongon.pocketmedical.framework.exception.BusinessException;
import com.usongon.pocketmedical.service.DepartmentService;
import com.usongon.pocketmedical.service.DoctorService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.usongon.pocketmedical.bean.entity.Admin;
import java.util.List;
import com.usongon.pocketmedical.dao.AdminMapper;
import com.usongon.pocketmedical.service.AdminService;
/**
 *
 * @author zhangdehua
 * @date 2020-03-31
 */
@Service
public class AdminServiceImpl implements AdminService{

    @Resource
    private AdminMapper adminMapper;
    @Resource
    private DoctorService doctorService;
    @Resource
    private PatientServiceImpl patientService;
    @Resource
    private DepartmentService departmentService;

    @Override
    public int insert(AdminInsertParams params) {
        if (adminMapper.selectByAdminMobileAndAdminState(params.getAdminMobile()) != null){
            throw new BusinessException(EResponseCode.BizError, "手机号重复", "");
        }
        //密码加密
        params.setAdminPassword(PasswordUtil.encode(params.getAdminPassword()));
        Admin record = new Admin();
        BeanUtils.copyProperties(params, record);
        record.setAdminId(UuidUtil.randomUUID());
        return adminMapper.insert(record);
    }

    @Override
    public int insertSelective(Admin record) {
        return adminMapper.insertSelective(record);
    }

    @Override
    public Admin selectByAdminMobileAndAdminState(String adminMobile) {
        return adminMapper.selectByAdminMobileAndAdminState(adminMobile);
    }

    @Override
    public int updateByAdminId(Admin updated,String adminId) {
        return adminMapper.updateByAdminId(updated,adminId);
    }

    @Override
    public List<Admin> selectByAdminIdAndAdminState(String adminId) {
        return adminMapper.selectByAdminIdAndAdminState(adminId);
    }

    @Override
    public void changeDocState(String docId, EDoctorAndPatientState state) {
        doctorService.updateDecDocStateByDocId(docId, state.toString());
    }

    @Override
    public List<DoctorResult> getDoctorList(DoctorSelectParams params) {
        return doctorService.getDoctorList(params);
    }

    @Override
    public List<PatientResult> getPatientList(PatientSelectParams params) {
        return patientService.selectByAllExceptId(params);
    }

    @Override
    public void changePatientState(String patientId, EDoctorAndPatientState state) {
        patientService.updatePatientStateByPatientIdAndPatientState(patientId, state.toString());
    }

    @Override
    public void insertDepartment(String departmentName) {
        departmentService.insert(departmentName);
    }

    @Override
    public List<DepartmentResult> getDepartmenrList() {
        return departmentService.selectAllByDepartmentIdAndIsDel();
    }

}
