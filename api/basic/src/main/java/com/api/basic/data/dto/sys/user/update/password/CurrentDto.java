package com.api.basic.data.dto.sys.user.update.password;


import com.api.common.base.data.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户修改自己的密码相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CurrentDto extends BaseDto {

    @NotBlank(message = "{validation.user.password.old.notBlank}")
    private String oldPassword;
    
    @NotBlank(message = "{validation.user.password.new.notBlank}")
    @Size(min = 6, max = 20, message = "{validation.user.password.new.length}")
    private String newPassword;
    
    @NotBlank(message = "{validation.user.password.confirm.notBlank}")
    @Size(min = 6, max = 20, message = "{validation.user.password.confirm.length}")
    private String confirmPassword;
}
