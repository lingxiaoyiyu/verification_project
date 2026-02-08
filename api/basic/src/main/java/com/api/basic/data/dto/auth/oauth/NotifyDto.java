package com.api.basic.data.dto.auth.oauth;


import com.api.common.base.data.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * OAuth登录通知请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NotifyDto extends BaseDto {

    @NotBlank(message = "{validation.oauth.fd.notBlank}")
    private String fd;
    
    @NotBlank(message = "{validation.oauth.type.notBlank}")
    private String type;
}
