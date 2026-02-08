package com.api.basic.data.dto.sys.user.update;

import com.api.basic.data.enums.UserStatusEnum;
import com.api.common.base.data.dto.BaseDto;
import com.api.common.validate.dateFormat.ValidDateFormat;
import com.api.common.validate.enumValue.ValidEnumValue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新用户状态相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StatusDto extends BaseDto {

    // 用户ID
    @NotNull(message = "{validation.user.id.notNull}")
    @Min(value = 1, message = "{validation.user.id.min}")
    Integer id;

    // 状态值
    @NotNull(message = "{validation.user.status.notNull}")
    @ValidEnumValue(enumClass = UserStatusEnum.class, expectedType = Integer.class, message = "{validation.user.status.enumValue}")
    private Integer status;

    // 最后更新时间
    @NotBlank(message = "{validation.user.updatedAt.notBlank}")
    @ValidDateFormat(message = "{validation.user.updatedAt.format}")
    private String updatedAt;
}
