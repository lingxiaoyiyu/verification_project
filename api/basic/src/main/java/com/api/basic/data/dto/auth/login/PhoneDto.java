package com.api.basic.data.dto.auth.login;


import com.api.common.base.data.dto.BaseDto;
import com.api.common.validate.phoneNumber.ValidPhoneNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 手机号验证码登录请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PhoneDto extends BaseDto {

    @NotBlank(message = "{validation.phone.notBlank}")
    @ValidPhoneNumber(message = "{validation.phone.format}")
    private String phoneNumber;

    @NotBlank(message = "{validation.verCode.notBlank}")
    @Pattern(regexp = "^\\d{6}$", message = "{validation.verCode.format}")
    private String verCode;
}
