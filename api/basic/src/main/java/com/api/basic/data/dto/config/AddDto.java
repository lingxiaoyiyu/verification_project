package com.api.basic.data.dto.config;

import com.api.common.base.data.dto.BaseDto;
import com.api.common.enums.StatusEnum;
import com.api.common.validate.enumValue.ValidEnumValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 添加配项置接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AddDto extends BaseDto {

    @NotNull(message = "{validation.config.group.notNull}")
    private Integer groupId;

    @NotNull(message = "{validation.config.dataType.notNull}")
    private Integer dataType;

    @NotBlank(message = "{validation.config.title.notBlank}")
    @Size(max = 32, message = "{validation.config.title.length}")
    private String title;

    @NotBlank(message = "{validation.config.name.notBlank}")
    @Size(max = 32, message = "{validation.config.name.length}")
    private String name;

    // 配置描述
    @Size(max = 255, message = "{validation.config.description.length}")
    private String description;

    @NotNull(message = "{validation.config.status.notNull}")
    @ValidEnumValue(enumClass = StatusEnum.class, expectedType = Integer.class, message = "{validation.config.status.enumValue}")
    private Integer status;

    // 组件数据
    private String componentData;
}
