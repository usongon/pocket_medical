package com.usongon.pocketmedical.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author yxp
 * @date 2020-04-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private String postId;

    /**
    * 发布人id
    */
    private String posterId;

    /**
    * 帖子标题
    */
    private String postTitle;

    /**
    * refer-咨询(需选择科室)
      advice-建议
      complain-投诉
      praise-表扬
    */
    private String postCategory;

    /**
    * 咨询的科室
    */
    private String departmentId;

    /**
    * 帖子内容
    */
    private String postContent;

    /**
    * Wait-未回复
Finished—已结束
Chatting-进行中
Del-已删除
    */
    private String postState;
}