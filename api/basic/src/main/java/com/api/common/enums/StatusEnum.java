package com.api.common.enums;

import lombok.Getter;

/**
 * 状态枚举
 */
@Getter
public enum StatusEnum {

    ENABLE(1, "启用"),
    DISABLED(2, "禁用");

    private final int code;
    private final String description;

    StatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static StatusEnum fromCode(Integer code) {
        for (StatusEnum enumObj : StatusEnum.values()) {
            if (enumObj.getCode() == code) {
                return enumObj;
            }
        }
        throw new IllegalArgumentException("无效的状态值: " + code);
    }
}
