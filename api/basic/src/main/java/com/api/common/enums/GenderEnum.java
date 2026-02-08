package com.api.common.enums;

import lombok.Getter;

/**
 * 性别枚举对象
 */
@Getter
public enum GenderEnum {

    MALE(1, "男"),
    FEMALE(2, "女"),
    SECRET(3, "保密");

    private final int code;
    private final String description;

    GenderEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static GenderEnum fromCode(Integer code) {
        for (GenderEnum enumObj : GenderEnum.values()) {
            if (enumObj.getCode() == code) {
                return enumObj;
            }
        }
        throw new IllegalArgumentException("无效的性别值: " + code);
    }
}