package com.usongon.pocketmedical.bean.param;

import com.usongon.pocketmedical.enums.EPostCategory;
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
public class PostInsertParams {
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
    private EPostCategory postCategory;

    /**
    * 咨询的科室
    */
    private String departmentId;

    /**
    * 帖子内容
    */
    private String postContent;
}