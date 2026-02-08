package com.api.basic.data.enums;

import lombok.Getter;

/**
 * 用户注册来源枚举
 */
@Getter
public enum UserSourceEnum {

    ADMIN_ADDITION(10, "管理员添加"),
    USERNAME_REGISTRATION(20, "用户名注册"),
    EMAIL_REGISTRATION(21, "邮箱注册"),
    WECHAT_MINI_REGISTRATION(30, "微信小程序注册"),
    WECHAT_OFFICIAL_ACCOUNT_REGISTRATION(31, "微信公众号注册"),
    APP_WECHAT_LOGIN_REGISTRATION(32, "APP微信登录注册"),
    WECHAT_OFFICIAL_ACCOUNT_OAUTH2_SCAN_REGISTRATION(33, "微信公众号Ouath2扫码登录注册"),
    PHONE_NUMBER_REGISTRATION(40, "手机号码注册"),
    WUJIE_UNIOINID_REGISTRATION(50, "无界ID注册");

    private final int code;
    private final String description;

    UserSourceEnum(int sourceCode, String description) {
        this.code = sourceCode;
        this.description = description;
    }

    public static UserSourceEnum fromCode(Integer code) {
        for (UserSourceEnum enumObj : UserSourceEnum.values()) {
            if (enumObj.getCode() == code) {
                return enumObj;
            }
        }
        throw new IllegalArgumentException("无效的注册来源值: " + code);
    }
}
