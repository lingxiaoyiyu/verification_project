package com.api.common.enums;

import lombok.Getter;

@Getter
public enum SortEnum {

    ASC("ASC", "升序排列"),
    DESC("DESC", "降序排列");

    private final String code;
    private final String description;

    SortEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static SortEnum fromValue(String value) {
        for (SortEnum enumObj : SortEnum.values()) {
            if (enumObj.getCode().equals(value)) {
                return enumObj;
            }
        }
        throw new IllegalArgumentException("无效的排序方式: " + value);
    }
}