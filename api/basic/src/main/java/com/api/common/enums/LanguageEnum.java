package com.api.common.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * 文章热点状态枚举
 */
@Getter
public enum LanguageEnum {

    ZHHANS(1, "zh-Hans", "简体中文"),
    ZHHANT(2, "zh-Hant", "繁体中文"),
    EN(3, "en", "英语"),
    KO(4, "ko", "韩语"),
    JA(5, "ja", "日语"),
    HI(6,"hi", "印地语"),
    BN(7,"bn", "孟加拉语"),
    TE(8,"te", "泰卢固语"),
    MR(9,"mr", "马拉地语"),
    TA(10,"ta", "泰米尔语"),
    UR(11,"ur", "乌尔都语"),
    GU(12,"gu", "古吉拉特语"),
    KN(13,"kn", "卡纳达语"),
    OR(14,"or", "奥里亚语"),
    PA(15,"pa", "旁遮普语");

    private final int code;
    private final String name;
    private final String description;

    LanguageEnum(int code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public static LanguageEnum fromCode(Integer code) {
        for (LanguageEnum enumObj : LanguageEnum.values()) {
            if (Objects.equals(enumObj.getCode(), code)) {
                return enumObj;
            }
        }
        throw new IllegalArgumentException("无效的语言代码: " + code);
    }

    public static LanguageEnum fromName(String name) {
        for (LanguageEnum enumObj : LanguageEnum.values()) {
            if (Objects.equals(enumObj.getName(), name)) {
                return enumObj;
            }
        }
        throw new IllegalArgumentException("The platform does not support the current language");
    }
}