package com.api.basic.data.dto.auth.login;


import com.api.common.base.data.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信小程序登录请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WechatMpDto extends BaseDto {

    @NotBlank(message = "{validation.code.notBlank}")
    private String code;
}
