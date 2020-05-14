package com.usongon.pocketmedical.controller;

import com.usongon.pocketmedical.bean.ResponseResult;
import com.usongon.pocketmedical.bean.entity.Post;
import com.usongon.pocketmedical.bean.param.PatientSelectParams;
import com.usongon.pocketmedical.bean.param.PostInsertParams;
import com.usongon.pocketmedical.bean.param.PostReplyInsertParams;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yxp
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
        if (params.getDepartmentId() != null && departmentService.selectByDepartmentIdAndIsDel(params.getDepartmentId()) == null){
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
    public Object doctorGetPostList(EPostState postState, EPostCategory postCategory){
        DoctorSession session = GlobalHelper.get();
        return ResponseResult.success(postService.
                getPostListByDepartmentIdOrPostState(
                doctorService.selectByDocIdAndDocState(
                        session.getDoctorId()).
                        getDepartmentId(), postState, postCategory));
    }

    @PostMapping("/admin/post/list")
    public Object adminGetPostList(String departmentId, EPostState postState, EPostCategory postCategory){
        AdminSession session = GlobalHelper.get();
        if(adminService.selectByAdminIdAndAdminState(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "你不是管理员，无法操作", "");
        }
        return ResponseResult.success(postService.adminGetPostListByDepartmentIdOrPostState(departmentId, postState, postCategory));
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
        if (!StringUtils.isEmpty(result.getDepartmentId()) &&
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

    @PostMapping("/admin/post/reply")
    @Transactional(rollbackFor = RuntimeException.class)
    public Object adminReplyPost(PostReplyInsertParams params){
        AdminSession session = GlobalHelper.get();
        PostListResult post = postService.selectPostDetailByPostId(params.getPostId());
        if (post.getPostState().equals(EPostState.Del.getPostState()) || post.getPostState().equals(EPostState.Finished.getPostState())){
            throw new BusinessException(EResponseCode.BizError, "本帖已无法回复", "");
        }
        if(adminService.selectByAdminIdAndAdminState(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "你不是管理员，无法操作", "");
        }
        params.setReplierId(session.getAdminId());
        params.setReplierRole("Admin");
        changePostStateToChat(params.getPostId());
        postReplyService.insertSelective(params);
        return ResponseResult.success();
    }
    @PostMapping("/doctor/post/reply")
    @Transactional(rollbackFor = RuntimeException.class)
    public Object doctorReplyPost(PostReplyInsertParams params){
        DoctorSession session = GlobalHelper.get();
        PostListResult post = postService.selectPostDetailByPostId(params.getPostId());
        if (post.getPostState().equals(EPostState.Del.getPostState()) || post.getPostState().equals(EPostState.Finished.getPostState())){
            throw new BusinessException(EResponseCode.BizError, "本帖已无法回复", "");
        }
        if(doctorService.selectByDocIdAndDocState(session.getDoctorId()) == null){
            throw new BusinessException(EResponseCode.BizError, "请登录医生账号后回复", "");
        }
        String departmentId = post.getDepartmentId();
        if (!StringUtils.isEmpty(departmentId) && !doctorService.selectByDocIdAndDocState(session.getDoctorId()).getDepartmentId().equals(departmentId)){
            throw new BusinessException(EResponseCode.BizError, "你没有权限回复该帖子", "");
        }
        if (post.getPostCategory().equals(EPostCategory.complain.getCategory())){
            throw new BusinessException(EResponseCode.BizError, "你不可以回复投诉贴", "");
        }
        params.setReplierId(session.getDoctorId());
        params.setReplierRole("Doctor");
        changePostStateToChat(params.getPostId());
        postReplyService.insertSelective(params);
        return ResponseResult.success();
    }

    @PostMapping("/patient/post/reply")
    @Transactional(rollbackFor = RuntimeException.class)
    public Object patientReplyPost(PostReplyInsertParams params){
        PatientSession session = GlobalHelper.get();
        PostListResult post = postService.selectPostDetailByPostId(params.getPostId());
        if (post.getPostState().equals(EPostState.Del.getPostState()) || post.getPostState().equals(EPostState.Finished.getPostState())){
            throw new BusinessException(EResponseCode.BizError, "本帖已无法回复", "");
        }
        if (!post.getPosterId().equals(session.getPatientId())){
            throw new BusinessException(EResponseCode.BizError, "你不可以回复非本人的帖子", "");
        }
        params.setReplierId(session.getPatientId());
        params.setReplierRole("Patient");
        postReplyService.insertSelective(params);
        changePostStateToChat(params.getPostId());
        return ResponseResult.success();
    }

    @PostMapping("/post/reply/list")
    public Object getAllPostReply(String postId){
        return ResponseResult.success(postReplyService.selectAllReplyByPostId(postId));
    }

    @PostMapping("/admin/post/state")
    public Object adminDelPost(String postId, EPostState toBeState){
        AdminSession session = GlobalHelper.get();
        if(toBeState.equals(EPostState.Wait ) || toBeState.equals(EPostState.Chatting)){
            throw new BusinessException(EResponseCode.BizError, "你只能删除或者关闭帖子", "");
        }
        if(adminService.selectByAdminIdAndAdminState(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "你不是管理员，无法操作", "");
        }
        if (postService.selectPostDetailByPostId(postId) == null){
            throw new BusinessException(EResponseCode.BizError, "操作的帖子不存在", "");
        }
        postService.updatePostStateByPostId(toBeState, postId);
        return ResponseResult.success();
    }

    @PostMapping("/patient/post/state")
    public Object patientDelPost(String postId, EPostState toBeState){
        PatientSession session = GlobalHelper.get();
        if(toBeState.equals(EPostState.Wait ) || toBeState.equals(EPostState.Chatting)){
            throw new BusinessException(EResponseCode.BizError, "你只能删除或者关闭帖子", "");
        }
        if (!postService.selectPostDetailByPostId(postId).getPosterId().equals(session.getPatientId())){
            throw new BusinessException(EResponseCode.BizError, "你不可以删除非本人的帖子", "");
        }
        if (postService.selectPostDetailByPostId(postId) == null){
            throw new BusinessException(EResponseCode.BizError, "删除的帖子不存在", "");
        }
        postService.updatePostStateByPostId(toBeState, postId);
        return ResponseResult.success();
    }

    public void changePostStateToChat(String postId){
        postService.updatePostStateByPostId(EPostState.Chatting, postId);
    }
}
