package com.api.basic.data.dto.sys.role;

import com.api.common.base.data.dto.BaseDto;
import com.api.common.enums.StatusEnum;
import com.api.common.validate.enumValue.ValidEnumValue;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 添加角色接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AddDto extends BaseDto {

    // 角色名称
    @NotBlank(message = "{validation.role.name.notBlank}")
    @Size(max = 64, message = "{validation.role.name.length}")
    private String name;

    // 角色标识
    @NotBlank(message = "{validation.role.identifying.notBlank}")
    @Size(max = 32, message = "{validation.role.identifying.length}")
    private String identifying;

    // 角色状态
    @NotNull(message = "{validation.role.status.notNull}")
    @ValidEnumValue(enumClass = StatusEnum.class, expectedType = Integer.class, message = "{validation.role.status.enumValue}")
    private Integer status;

    // 角色备注
    @Nullable
    @Size(max = 255, message = "{validation.role.remark.length}")
    private String remark;

    // 菜单列表
    @Nullable
    private List<Integer> menuIdList;
}
