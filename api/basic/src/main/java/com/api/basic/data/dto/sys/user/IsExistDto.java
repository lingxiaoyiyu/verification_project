package com.api.basic.data.dto.sys.user;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 验证用户是否存在相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class IsExistDto extends BaseDto {

    // 用户名
    private String username;
    // 昵称
    private String nickname;
    // 手机号
    private String phoneNumber;
}
