package com.usongon.pocketmedical.common.utils;

import org.springframework.util.StringUtils;

public class EnumUtil {
    public static <T extends Enum<T>> T valueOf(Class<T> enumType, String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return Enum.valueOf(enumType, name);
    }
}
