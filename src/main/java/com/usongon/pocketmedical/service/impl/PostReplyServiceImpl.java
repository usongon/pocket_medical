package com.usongon.pocketmedical.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.usongon.pocketmedical.dao.PostReplyMapper;
import com.usongon.pocketmedical.bean.entity.PostReply;
import com.usongon.pocketmedical.service.PostReplyService;

/**
 * @author zhangdehua
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
    public int insertSelective(PostReply record) {
        return postReplyMapper.insertSelective(record);
    }

}


