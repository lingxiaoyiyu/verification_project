package com.api.basic.data.dto.sys.region;

import com.api.common.base.data.dto.BaseDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 添加行政区域相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AddDto extends BaseDto {

    // 名称
    @NotBlank(message = "{validation.region.name.notBlank}")
    @Size(max = 64, message = "{validation.region.name.length}")
    public String name;

    // 行政编码
    @NotNull(message = "{validation.region.code.notNull}")
    @Min(value = 0, message = "{validation.region.code.min}")
    public Long code;

    // 上级行政区域编码
    @NotNull(message = "{validation.region.parentCode.notNull}")
    @Min(value = 0, message = "{validation.region.parentCode.min}")
    public Long parentCode;
}
