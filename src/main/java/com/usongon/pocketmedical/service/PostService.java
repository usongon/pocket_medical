package com.usongon.pocketmedical.service;

import java.util.List;
import com.usongon.pocketmedical.bean.entity.Post;
import com.usongon.pocketmedical.bean.param.PostInsertParams;
import com.usongon.pocketmedical.bean.result.PostListResult;
import com.usongon.pocketmedical.enums.EPostState;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author zhangdehua
 * @date 2020-04-14
 */
public interface PostService{


    int insert(Post record);

    int insertSelective(PostInsertParams params);

    List<Post> selectAllByPosterIdAndPostState(String posterId);

    int updateByPosterIdAndPostStateAndPostId(Post updated,String posterId,String postState,String postId);

    int updatePostStateByPosterIdAndPostId(String updatedPostState,String posterId,String postId);

    List<Post> selectAllByDepartmentIdAndPostState(String departmentId,String postState);

    int updatePostStateByPostIdAndDepartmentId(String updatedPostState,String postId,String departmentId);

    PostListResult selectPostDetailByPostId(String postId);

    List<PostListResult> getPostListByDepartmentIdOrPostState(String departmentId, EPostState postState);

}
