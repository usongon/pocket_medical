package com.usongon.pocketmedical.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.usongon.pocketmedical.bean.entity.Doctor;

/**
 * @author zhangdehua
 * @date 2020-03-29
 */
@Mapper
public interface DoctorMapper {

    int insert(Doctor record);

    int insertSelective(Doctor record);

    Doctor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Doctor record);

    int updateByPrimaryKey(Doctor record);

    Doctor selectByDocMobile(@Param("docMobile")String docMobile);


}