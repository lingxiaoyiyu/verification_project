package com.api.basic.data.dto.sys.role.update;

import com.api.common.base.data.dto.BaseUpdateDto;
import com.api.common.enums.StatusEnum;
import com.api.common.validate.dateFormat.ValidDateFormat;
import com.api.common.validate.enumValue.ValidEnumValue;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新角色接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StatusDto extends BaseUpdateDto {

    // 角色ID
    @NotNull(message = "{validation.role.id.notNull}")
    @Min(value = 1, message = "{validation.role.id.min}")
    private Integer id;

    // 状态
    @NotNull(message = "{validation.role.status.notNull}")
    @ValidEnumValue(enumClass = StatusEnum.class, expectedType = Integer.class, message = "{validation.role.status.valid}")
    private Integer status;

    // 更新时间
    @Nullable
    @ValidDateFormat(message = "{validation.role.updatedAt.dateValid}")
    private String updatedAt;
}
