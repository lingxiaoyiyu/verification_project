package com.api.basic.data.dto.common.verifyCode;


import com.api.common.base.data.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 发送邮箱验证码用Dto
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EmailCodeDto extends BaseDto {

    @NotBlank(message = "{validation.email.notBlank}")
    private String email;
}
