package com.api.basic.data.dto.sys.department;

import com.api.common.base.data.dto.BaseUpdateDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 删除部门相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RemoveDto extends BaseUpdateDto {

    // 部门ID
    @NotNull(message = "{validation.department.id.notNull}")
    public Integer id;
}
