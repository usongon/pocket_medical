package com.usongon.pocketmedical.bean.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yxp
 * @date 2020-04-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostReplyInsertParams {

    /**
     * 回复的问题的id
     */
    private String postId;

    /**
     * 回复人的Id
     */
    private String replierId;

    /**
     * 回复人的身份 Doc Patient Admin
     */
    private String replierRole;

    /**
     * 回复的内容
     */
    private String replyContent;
}