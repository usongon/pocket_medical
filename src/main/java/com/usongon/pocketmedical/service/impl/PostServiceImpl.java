package com.usongon.pocketmedical.service.impl;

import com.usongon.pocketmedical.bean.param.PostInsertParams;
import com.usongon.pocketmedical.bean.result.PostListResult;
import com.usongon.pocketmedical.common.utils.UuidUtil;
import com.usongon.pocketmedical.enums.EPostCategory;
import com.usongon.pocketmedical.enums.EPostState;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.usongon.pocketmedical.dao.PostMapper;
import java.util.List;
import com.usongon.pocketmedical.bean.entity.Post;
import com.usongon.pocketmedical.service.PostService;
/**
 *
 * @author yxp
 * @date 2020-04-14
 */
@Service
public class PostServiceImpl implements PostService{

    @Resource
    private PostMapper postMapper;

    @Override
    public int insert(Post record) {
        return postMapper.insert(record);
    }

    @Override
    public int insertSelective(PostInsertParams params) {
        Post post = new Post();
        BeanUtils.copyProperties(params, post);
        post.setPostId(UuidUtil.randomUUID());
        post.setPostCategory(params.getPostCategory().getCategory());
        return postMapper.insertSelective(post);
    }

    @Override
    public List<Post> selectAllByPosterIdAndPostState(String posterId) {
        return postMapper.selectAllByPosterIdAndPostState(posterId);
    }

    @Override
    public int updateByPosterIdAndPostStateAndPostId(Post updated,String posterId,String postState,String postId) {
        return postMapper.updateByPosterIdAndPostStateAndPostId(updated,posterId,postState,postId);
    }

    @Override
    public int updatePostStateByPosterIdAndPostId(String updatedPostState,String posterId,String postId) {
        return postMapper.updatePostStateByPosterIdAndPostId(updatedPostState,posterId,postId);
    }

    @Override
    public List<Post> selectAllByDepartmentIdAndPostState(String departmentId,String postState) {
        return postMapper.selectAllByDepartmentIdAndPostState(departmentId,postState);
    }

    @Override
    public int updatePostStateByPostIdAndDepartmentId(String updatedPostState,String postId,String departmentId) {
        return postMapper.updatePostStateByPostIdAndDepartmentId(updatedPostState,postId,departmentId);
    }

    @Override
    public PostListResult selectPostDetailByPostId(String postId) {
        return postMapper.selectPostDetailByPostId(postId);
    }

    @Override
    public List<PostListResult> getPostListByDepartmentIdOrPostState(String departmentId, EPostState postState, EPostCategory postCategory) {
        return postMapper.getPostListByDepartmentIdOrPostState(departmentId, postState, postCategory);
    }

    @Override
    public List<PostListResult> adminGetPostListByDepartmentIdOrPostState(String departmentId, EPostState postState, EPostCategory postCategory) {
        return postMapper.adminGetPostListByDepartmentIdOrPostState(departmentId, postState, postCategory);
    }

    @Override
    public void updatePostStateByPostId(EPostState updatedPostState, String postId) {
        postMapper.updatePostStateByPostId(updatedPostState.getPostState(), postId);
    }

}
