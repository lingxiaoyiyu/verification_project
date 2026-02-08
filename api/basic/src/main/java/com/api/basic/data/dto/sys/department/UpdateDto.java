package com.api.basic.data.dto.sys.department;

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
 * 添加部门相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateDto extends BaseUpdateDto {

    // 部门ID
    @NotNull(message = "{validation.department.id.notNull}")
    @Min(value = 1, message = "{validation.department.id.min}")
    public Integer id;

    // 名称
    @NotBlank(message = "{validation.department.name.notBlank}")
    @Size(max = 63, message = "{validation.department.name.length}")
    public String name;

    // 上级部门ID
    @NotNull(message = "{validation.department.parent.notNull}")
    @Min(value = 0, message = "{validation.department.parent.min}")
    public Integer parentId;

    // 更新时间
    @Nullable
    @ValidDateFormat(message = "{validation.department.updatedAt.format}")
    private String updatedAt;
}
