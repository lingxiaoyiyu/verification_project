package com.api.basic.data.dto.sys.role;

import com.api.common.base.data.dto.BaseDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取指定角色详细信息接口相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetDto extends BaseDto {

    // 角色ID
    @NotNull(message = "{validation.role.id.notNull}")
    private Integer id;
}
