package com.api.basic.data.enums;

import lombok.Getter;

/**
 * 菜单类型枚举
 */
@Getter
public enum MenuTypeEnum {

    DIR(1, "目录"),
    PAGE(2, "页面"),
    PERMISSION(3, "权限");

    private final int code;
    private final String description;

    MenuTypeEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static MenuTypeEnum fromCode(Integer code) {
        for (MenuTypeEnum enumObj : MenuTypeEnum.values()) {
            if (enumObj.getCode() == code) {
                return enumObj;
            }
        }
        throw new IllegalArgumentException("无效的类型值: " + code);
    }
}