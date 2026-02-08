package com.api.basic.data.dto.sys.user.update;

import com.api.common.base.data.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 绑定邀请码接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BindInviteCodeDto extends BaseDto {

    // 邀请码
    @NotBlank(message = "{validation.user.inviteCode.notBlank}")
    private String inviteCode;
}
