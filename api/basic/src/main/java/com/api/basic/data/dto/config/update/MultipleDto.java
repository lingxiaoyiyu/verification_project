package com.api.basic.data.dto.config.update;

import com.api.common.base.data.dto.BaseUpdateDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新配置接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MultipleDto extends BaseUpdateDto {

    @NotBlank(message = "{validation.config.configs.notBlank}")
    private String configs;
}
