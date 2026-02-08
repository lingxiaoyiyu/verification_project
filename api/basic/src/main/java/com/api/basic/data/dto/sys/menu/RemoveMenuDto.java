package com.api.basic.data.dto.sys.menu;

import com.api.common.base.data.dto.BaseUpdateDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜删除单相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RemoveMenuDto extends BaseUpdateDto {

    // 菜单ID
    @NotNull(message = "{validation.menu.id.notNull}")
    @Min(value = 1, message = "{validation.menu.id.min}")
    private Integer id;
}
