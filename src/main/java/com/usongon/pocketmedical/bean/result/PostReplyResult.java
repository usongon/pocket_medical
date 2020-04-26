package com.usongon.pocketmedical.bean.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangdehua
 * @date 2020-04-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostReplyResult {
    /**
     * 回复id
     */
    private String replyId;

    /**
     * 回复的问题的id
     */
    private String postId;

    /**
     * 回复人的Id
     */
    private String replierId;

    /**
     * 回复人的姓名
     */
    private String replierName;

    /**
     * 回复人的身份 Doc Patient Admin
     */
    private String replierRole;

    /**
     * 回复的内容
     */
    private String replyContent;

    /**
     * 回复时间
     */
    private String createTime;
}