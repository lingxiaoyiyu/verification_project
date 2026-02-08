package com.api.basic.data.dto.auth.login;


import com.api.common.base.data.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户名密码登录请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UsernameDto extends BaseDto {

    @NotBlank(message = "{validation.username.notBlank}")
    private String username;
    
    @NotBlank(message = "{validation.password.notBlank}")
    @Size(min = 6, max = 20, message = "{validation.password.length}")
    private String password;
}
