package com.api.basic.data.dto.sys.region;

import com.api.common.base.data.dto.BaseUpdateDto;
import com.api.common.validate.dateFormat.ValidDateFormat;
import jakarta.annotation.Nullable;
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
public class UpdateDto extends BaseUpdateDto {

    // 行政区域ID
    @NotNull(message = "{validation.region.id.notNull}")
    @Min(value = 1, message = "{validation.region.id.min}")
    public Integer id;

    // 名称
    @NotBlank(message = "{validation.region.name.notBlank}")
    @Size(max = 63, message = "{validation.region.name.length}")
    public String name;

    // 上级行政区域编码
    @NotNull(message = "{validation.region.parentCode.notNull}")
    @Min(value = 0, message = "{validation.region.parentCode.min}")
    public Long parentCode;

    // 更新时间
    @Nullable
    @ValidDateFormat(message = "{validation.region.updatedAt.format}")
    private String updatedAt;
}
