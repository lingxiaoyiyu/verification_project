package com.api.common.enums;

import lombok.Getter;

/**
 * 是否状态枚举
 */
@Getter
public enum YesOrNoEnum {

    YES(1, "是"),
    NO(2, "否");

    private final int code;
    private final String description;

    YesOrNoEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static YesOrNoEnum fromCode(Integer code) {
        for (YesOrNoEnum enumObj : YesOrNoEnum.values()) {
            if (enumObj.getCode() == code) {
                return enumObj;
            }
        }
        throw new IllegalArgumentException("无效的类型值: " + code);
    }
}