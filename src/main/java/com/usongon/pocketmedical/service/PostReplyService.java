package com.usongon.pocketmedical.service;

import com.usongon.pocketmedical.bean.entity.PostReply;

/**
 * @author zhangdehua
 * @date 2020-04-14
 */
public interface PostReplyService {


    int insert(PostReply record);

    int insertSelective(PostReply record);

}


