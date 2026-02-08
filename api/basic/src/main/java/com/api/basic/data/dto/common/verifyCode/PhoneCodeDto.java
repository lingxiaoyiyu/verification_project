package com.api.basic.data.dto.common.verifyCode;


import com.api.common.base.data.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 发送手机号验证码用Dto
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PhoneCodeDto extends BaseDto {

    @NotBlank(message = "{validation.phone.notBlank}")
//    @ValidPhoneNumber(message = "手机号格式错误")
    private String phoneNumber;
}
