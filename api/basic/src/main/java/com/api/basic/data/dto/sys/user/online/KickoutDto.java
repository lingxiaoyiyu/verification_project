package com.api.basic.data.dto.sys.user.online;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 踢人下线相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class KickoutDto extends BaseDto {

    // 踢下线的用户ID
    private Integer kickoutUserId;
    // 踢下线的Token
    private String kickoutToken;
    // 踢下线的终端
    private String kickoutClientFrom;
}
