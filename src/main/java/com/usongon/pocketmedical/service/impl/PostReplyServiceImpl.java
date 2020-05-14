package com.usongon.pocketmedical.service.impl;

import com.usongon.pocketmedical.bean.param.PostReplyInsertParams;
import com.usongon.pocketmedical.bean.result.PostReplyResult;
import com.usongon.pocketmedical.common.utils.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.usongon.pocketmedical.dao.PostReplyMapper;
import com.usongon.pocketmedical.bean.entity.PostReply;
import com.usongon.pocketmedical.service.PostReplyService;

import java.util.List;

/**
 * @author yxp
 * @date 2020-04-14
 */
@Service
public class PostReplyServiceImpl implements PostReplyService {

    @Resource
    private PostReplyMapper postReplyMapper;

    @Override
    public int insert(PostReply record) {
        return postReplyMapper.insert(record);
    }

    @Override
    public int insertSelective(PostReplyInsertParams params) {
        PostReply postReply = new PostReply();
        BeanUtils.copyProperties(params, postReply);
        postReply.setReplyId(UuidUtil.randomUUID());
        return postReplyMapper.insertSelective(postReply);
    }

    @Override
    public List<PostReplyResult> selectAllReplyByPostId(String postId) {
        return postReplyMapper.selectAllReplyByPostId(postId);
    }
}


