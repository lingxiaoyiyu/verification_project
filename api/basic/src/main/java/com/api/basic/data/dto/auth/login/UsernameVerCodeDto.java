package com.api.basic.data.dto.auth.login;


import com.api.common.base.data.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户名密码+验证码登录请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UsernameVerCodeDto extends BaseDto {

    @NotBlank(message = "{validation.username.notBlank}")
    private String username;
    
    @NotBlank(message = "{validation.password.notBlank}")
    private String password;
    
    @NotBlank(message = "{validation.verCode.notBlank}")
    private String verCode;
    
    @NotBlank(message = "{validation.verCode.key.notBlank}")
    private String verCodeKey;
}
