package com.api.basic.data.dto.sys.menu;

import com.api.common.base.data.dto.BaseDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询指定菜单的子菜单相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryChildrenDto extends BaseDto {

    // ID
    @NotNull(message = "{validation.menu.id.notNull}")
    @Min(value = 0, message = "{validation.menu.id.min}")
    private Integer id;
}
