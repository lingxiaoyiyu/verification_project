package com.api.basic.data.dto.sys.role;

import com.api.common.base.data.dto.BaseUpdateDto;
import com.api.common.enums.StatusEnum;
import com.api.common.validate.dateFormat.ValidDateFormat;
import com.api.common.validate.enumValue.ValidEnumValue;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 更新角色接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateDto extends BaseUpdateDto {

    // 角色ID
    @NotNull(message = "{validation.role.id.notNull}")
    @Min(value = 1, message = "{validation.role.id.min}")
    private Integer id;

    // 角色名称
    @NotBlank(message = "{validation.role.name.notBlank}")
    @Size(max = 64, message = "{validation.role.name.length}")
    private String name;

    // 角色标识
    @NotBlank(message = "{validation.role.identifying.notBlank}")
    @Size(max = 32, message = "{validation.role.identifying.length}")
    private String identifying;

    // 角色备注
    @Nullable
    @Size(max = 255, message = "{validation.role.remark.length}")
    private String remark;

    // 状态
    @NotNull(message = "{validation.role.status.notNull}")
    @ValidEnumValue(enumClass = StatusEnum.class, expectedType = Integer.class, message = "{validation.role.status.enumValue}")
    private Integer status;

    // 菜单列表
    @Nullable
    private List<Integer> menuIdList;

    // 更新时间
    @Nullable
    @ValidDateFormat(message = "{validation.role.updatedAt.format}")
    private String updatedAt;
}
