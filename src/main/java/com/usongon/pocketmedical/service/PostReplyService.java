package com.usongon.pocketmedical.service;

import com.usongon.pocketmedical.bean.entity.PostReply;
import com.usongon.pocketmedical.bean.param.PostReplyInsertParams;
import com.usongon.pocketmedical.bean.result.PostReplyResult;

import java.util.List;

/**
 * @author yxp
 * @date 2020-04-14
 */
public interface PostReplyService {


    int insert(PostReply record);

    int insertSelective(PostReplyInsertParams params);

    List<PostReplyResult> selectAllReplyByPostId(String postId);

}


