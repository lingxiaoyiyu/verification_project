package com.api.basic.data.dto.config;

import com.api.common.base.data.dto.BaseUpdateDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 删除配置项相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DeleteDto extends BaseUpdateDto {

    // 配置项ID
    @NotNull(message = "{validation.config.id.notNull}")
    private Integer id;
}
