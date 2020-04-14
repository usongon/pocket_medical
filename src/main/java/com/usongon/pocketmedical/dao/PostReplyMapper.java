package com.usongon.pocketmedical.dao;

import com.usongon.pocketmedical.bean.entity.PostReply;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhangdehua
 * @date 2020-04-14
 */
@Mapper
public interface PostReplyMapper {
    int insert(PostReply record);

    int insertSelective(PostReply record);
}