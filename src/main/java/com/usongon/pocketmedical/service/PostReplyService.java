package com.usongon.pocketmedical.service;

import com.usongon.pocketmedical.bean.entity.PostReply;
import com.usongon.pocketmedical.bean.param.PostReplyInsertParams;

/**
 * @author zhangdehua
 * @date 2020-04-14
 */
public interface PostReplyService {


    int insert(PostReply record);

    int insertSelective(PostReplyInsertParams params);

}


