package com.api.basic.data.dto.sys.user.get;

import com.api.common.base.data.dto.BaseDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取指定用户详细信息接口相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ByIdDto extends BaseDto {

    // 用户ID
    @NotNull(message = "{validation.user.id.notNull}")
    private Integer id;
}
