package com.api.basic.data.enums;

import lombok.Getter;

/**
 * 用户状态枚举
 */
@Getter
public enum UserStatusEnum {

    NORMAL(1, "启用"),
    DISABLED(2, "禁用"),
    DELETED(3, "注销");

    private final int code;
    private final String description;

    UserStatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static UserStatusEnum fromCode(Integer code) {
        for (UserStatusEnum enumObj : UserStatusEnum.values()) {
            if (enumObj.getCode() == code) {
                return enumObj;
            }
        }
        throw new IllegalArgumentException("无效的类型值: " + code);
    }
}
