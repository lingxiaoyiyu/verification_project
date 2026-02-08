package com.api.basic.data.dto.auth.login;


import com.api.common.base.data.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 无界unionid登录请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WujieUnionidDto extends BaseDto {

    @NotBlank(message = "{validation.unionId.notBlank}")
    private String unionId;
}
