package com.usongon.pocketmedical.service.impl;

import com.usongon.pocketmedical.bean.param.DoctorRegisterParams;
import com.usongon.pocketmedical.common.utils.PasswordUtil;
import com.usongon.pocketmedical.common.utils.UuidUtil;
import com.usongon.pocketmedical.enums.EResponseCode;
import com.usongon.pocketmedical.framework.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.usongon.pocketmedical.dao.DoctorMapper;
import com.usongon.pocketmedical.bean.entity.Doctor;
import com.usongon.pocketmedical.service.DoctorService;
import sun.security.krb5.internal.PAData;

/**
 * @author zhangdehua
 * @date 2020-03-29
 */
@Service
public class DoctorServiceImpl implements DoctorService {

    @Resource
    private DoctorMapper doctorMapper;

    @Override
    public int insert(DoctorRegisterParams params) {
        if(doctorMapper.selectByDocMobile(params.getDocMobile()) != null){
            throw new BusinessException(EResponseCode.BizError, "手机号已注册", "");
        }
        params.setDocPassword(PasswordUtil.encode(params.getDocPassword()));
        Doctor record = new Doctor();
        BeanUtils.copyProperties(params, record);
        record.setDocId(UuidUtil.randomUUID());
        return doctorMapper.insert(record);
    }

    @Override
    public int insertSelective(Doctor record) {
        return doctorMapper.insertSelective(record);
    }

    @Override
    public Doctor selectByPrimaryKey(Integer id) {
        return doctorMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Doctor record) {
        return doctorMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Doctor record) {
        return doctorMapper.updateByPrimaryKey(record);
    }

}

