package com.api.basic.data.dto.sys.region;

import com.api.common.base.data.dto.BaseUpdateDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 删除行政区域相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RemoveDto extends BaseUpdateDto {

    // 行政区域ID
    @NotNull(message = "{validation.region.id.notNull}")
    public Integer id;
}
