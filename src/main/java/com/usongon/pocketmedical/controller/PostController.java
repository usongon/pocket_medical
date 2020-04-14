package com.usongon.pocketmedical.controller;

import com.usongon.pocketmedical.bean.ResponseResult;
import com.usongon.pocketmedical.bean.param.PatientSelectParams;
import com.usongon.pocketmedical.bean.param.PostInsertParams;
import com.usongon.pocketmedical.bean.result.PostListResult;
import com.usongon.pocketmedical.bean.session.AdminSession;
import com.usongon.pocketmedical.bean.session.DoctorSession;
import com.usongon.pocketmedical.bean.session.LoginSession;
import com.usongon.pocketmedical.bean.session.PatientSession;
import com.usongon.pocketmedical.common.helper.GlobalHelper;
import com.usongon.pocketmedical.enums.EPostCategory;
import com.usongon.pocketmedical.enums.EPostState;
import com.usongon.pocketmedical.enums.EResponseCode;
import com.usongon.pocketmedical.framework.annotation.Authorize;
import com.usongon.pocketmedical.framework.exception.BusinessException;
import com.usongon.pocketmedical.service.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2020-04-14
 */
@RestController
@Authorize
public class PostController {
    @Resource
    private PostService postService;
    @Resource
    private PostReplyService postReplyService;
    @Resource
    private PatientService patientService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private DoctorService doctorService;
    @Resource
    private AdminService adminService;

    @PostMapping("/patient/post/insert")
    public Object addPost(PostInsertParams params){
        PatientSession session = GlobalHelper.get();
        PatientSelectParams patientSelectParams = new PatientSelectParams();
        patientSelectParams.setPatientId(session.getPatientId());
        if (patientService.selectByAllExceptId(patientSelectParams) == null){
            throw new BusinessException(EResponseCode.BizError, "只有患者可以发帖", "");
        }
        if (departmentService.selectByDepartmentIdAndIsDel(params.getDepartmentId()) == null){
            throw new BusinessException(EResponseCode.BizError, "部门不存在", "");
        }
        params.setPosterId(session.getPatientId());
        postService.insertSelective(params);
        return ResponseResult.success();
    }

    @PostMapping("/patient/post/list")
    public Object patientGetList(){
        PatientSession session = GlobalHelper.get();
        return ResponseResult.success(postService.selectAllByPosterIdAndPostState(session.getPatientId()));
    }

    @PostMapping("/doctor/post/list")
    public Object doctorGetPostList(EPostState postState){
        DoctorSession session = GlobalHelper.get();
        return ResponseResult.success(postService.
                getPostListByDepartmentIdOrPostState(
                doctorService.selectByDocIdAndDocState(
                        session.getDoctorId()).
                        getDepartmentId(), postState));
    }

    @PostMapping("/patient/post/detail")
    public Object patientGetPostDetail(String postId){
        PatientSession session = GlobalHelper.get();
        PostListResult result = postService.selectPostDetailByPostId(postId);
        if (!result.getPosterId().equals(session.getPatientId())){
            throw new BusinessException(EResponseCode.BizError, "你不可以查看别人发布的帖子", "");
        }
        return ResponseResult.success(result);
    }

    @PostMapping("/doctor/post/detail")
    public Object doctorGetPostDetail(String postId){
        DoctorSession session = GlobalHelper.get();
        PostListResult result = postService.selectPostDetailByPostId(postId);
        if (result.getDepartmentId() != null &&
                !doctorService.selectByDocIdAndDocState(
                        session.getDoctorId()).getDepartmentId().
                        equals(result.getDepartmentId())){
            throw new BusinessException(EResponseCode.BizError, "你不可以查看其他部门的帖子", "");
        }
        if (result.getPostCategory().equals(EPostCategory.complain.getCategory())){
            throw new BusinessException(EResponseCode.BizError, "你不可以查看投诉贴", "");
        }
        return ResponseResult.success(result);
    }

    @PostMapping("/admin/post/detail")
    public Object adminGetPostDetail(String postId){
        AdminSession session = GlobalHelper.get();
        if(adminService.selectByAdminIdAndAdminState(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "你不是管理员，无法操作", "");
        }
        return ResponseResult.success(postService.selectPostDetailByPostId(postId));
    }
}
