package com.usongon.pocketmedical.dao;
import com.usongon.pocketmedical.bean.param.DoctorSelectParams;
import com.usongon.pocketmedical.bean.result.DoctorResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.usongon.pocketmedical.bean.entity.Doctor;
import org.apache.ibatis.annotations.Select;

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

    void updateDecDocStateByDocId(@Param("deductedDocState")String deductedDocState,@Param("docId")String docId);

    List<Doctor> selectAllByDocState(@Param("docState")String docState);

    List<Doctor> selectByAllExceptId(Doctor doctor);

    @Select("<script> " +
            "select d.*, dp.department_name from doctor d, department dp " +
            "where dp.department_id = d.department_id and d.doc_state != 'Del' " +
            "<if test = 'docId != null'> and d.doc_id = #{docId} </if> " +
            "<if test = 'departmentId != null'> and d.department_id = #{departmentId} </if> " +
            "<if test = 'keywords != null'> and (" +
            "d.doc_name like concat(concat('%',#{keywords}),'%') or " +
            " d.mobile like concat(concat('%',#{keywords}),'%')) </if> " +
            "<if test = 'docState != null'> and d.doc_state = #{docState} </if> " +
            "</script>")
    List<DoctorResult> getDoctorList(DoctorSelectParams params);




}