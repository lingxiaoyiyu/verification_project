package com.api.common.enums;

import lombok.Getter;

/**
 * 是否删除枚举对象
 */
@Getter
public enum IsDeleteEnum {

    NO_DELETE(1, "未删除"),
    DELETED(2, "已删除");

    private final int code;
    private final String description;

    IsDeleteEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static IsDeleteEnum fromCode(Integer code) {
        for (IsDeleteEnum enumObj : IsDeleteEnum.values()) {
            if (enumObj.getCode() == code) {
                return enumObj;
            }
        }
        throw new IllegalArgumentException("无效的删除状态值: " + code);
    }
}