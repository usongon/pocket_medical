package com.usongon.pocketmedical.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhangdehua
 * @date 2020/4/14
 */
@AllArgsConstructor
public enum EPostState {
    /**
     * Wait-未回复
     * Finished—已结束
     * Chatting-进行中
     * Del-已删除
     */
    Wait ("Wait"),
    Finished ("Finished"),
    Chatting ("Chatting"),
    Del ("Del");

    @Getter
    private final String postState;
}
