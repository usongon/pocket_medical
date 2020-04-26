package com.usongon.pocketmedical.dao;

import com.usongon.pocketmedical.bean.entity.Post;

import java.util.List;

import com.usongon.pocketmedical.bean.result.PostListResult;
import com.usongon.pocketmedical.enums.EPostCategory;
import com.usongon.pocketmedical.enums.EPostState;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhangdehua
 * @date 2020-04-14
 */
@Mapper
public interface PostMapper {
    int insert(Post record);

    int insertSelective(Post record);

    List<Post> selectAllByPosterIdAndPostState(@Param("posterId") String posterId);

    int updateByPosterIdAndPostStateAndPostId(@Param("updated") Post updated, @Param("posterId") String posterId, @Param("postState") String postState, @Param("postId") String postId);

    int updatePostStateByPosterIdAndPostId(@Param("updatedPostState") String updatedPostState, @Param("posterId") String posterId, @Param("postId") String postId);

    List<Post> selectAllByDepartmentIdAndPostState(@Param("departmentId") String departmentId, @Param("postState") String postState);

    int updatePostStateByPostIdAndDepartmentId(@Param("updatedPostState") String updatedPostState, @Param("postId") String postId, @Param("departmentId") String departmentId);

    PostListResult selectPostDetailByPostId(@Param("postId") String postId);

    List<PostListResult> getPostListByDepartmentIdOrPostState(@Param("departmentId") String departmentId, @Param("postState") EPostState postState, @Param("postCategory") EPostCategory postCategory);

    List<PostListResult> adminGetPostListByDepartmentIdOrPostState(@Param("departmentId") String departmentId, @Param("postState") EPostState postState, @Param("postCategory") EPostCategory postCategory);

    void updatePostStateByPostId(@Param("updatedPostState")String updatedPostState,@Param("postId")String postId);


}