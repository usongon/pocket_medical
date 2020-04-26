package com.usongon.pocketmedical.dao;

import com.usongon.pocketmedical.bean.entity.PostReply;
import com.usongon.pocketmedical.bean.result.PostReplyResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhangdehua
 * @date 2020-04-14
 */
@Mapper
public interface PostReplyMapper {
    int insert(PostReply record);

    int insertSelective(PostReply record);

    List<PostReplyResult> selectAllReplyByPostId(String postId);
}