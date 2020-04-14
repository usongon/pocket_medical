package com.usongon.pocketmedical.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhangdehua
 * @date 2020-04-14
 */
@AllArgsConstructor
public enum EPostCategory {
    /**
     * refer 咨询
     * advice 建议
     * complain 投诉
     * praise 表扬
     */
    refer ("refer"),
    advice ("advice"),
    complain ("complain"),
    praise ("praise");

    @Getter
    private final String category;

}
