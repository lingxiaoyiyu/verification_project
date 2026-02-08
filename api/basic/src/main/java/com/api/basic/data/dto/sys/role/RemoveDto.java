package com.api.basic.data.dto.sys.role;

import com.api.common.base.data.dto.BaseUpdateDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 删除角色相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RemoveDto extends BaseUpdateDto {

    // 角色ID
    @NotNull(message = "{validation.role.id.notNull}")
    @Min(value = 1, message = "{validation.role.id.min}")
    private Integer id;
}
