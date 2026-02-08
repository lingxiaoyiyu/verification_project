package com.api.basic.data.dto.sys.user.update.info;

import com.api.common.base.data.dto.BaseDto;
import com.api.common.enums.GenderEnum;
import com.api.common.validate.enumValue.ValidEnumValue;
import com.api.common.validate.phoneNumber.ValidPhoneNumber;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新当前登录用户的信息相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CurrentDto extends BaseDto {

    // 昵称
    @Nullable
    @Size(max = 32, message = "{validation.user.nickName.length}")
    private String nickName;

    // 手机号
    @Nullable
    @ValidPhoneNumber(message = "{validation.user.phoneNumber.format}")
    private String phoneNumber;

    // 真实姓名
    @Nullable
    @Size(max = 10, message = "{validation.user.realName.length}")
    private String realName;

    // 邮箱
    @Nullable
    @Size(max = 64, message = "{validation.user.email.length}")
    private String email;

    // 简介
    @Nullable
    @Size(max = 255, message = "{validation.user.introduction.length}")
    private String introduction;

    // 性别
    @Nullable
    @ValidEnumValue(enumClass = GenderEnum.class, expectedType = Integer.class, message = "{validation.user.gender.enumValue}")
    private Integer gender;

    // 头像
    @Nullable
    @Size(max = 255, message = "{validation.user.avatar.length}")
    private String avatar;
}
