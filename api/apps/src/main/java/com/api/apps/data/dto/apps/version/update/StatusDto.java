package com.api.apps.data.dto.apps.version.update;

import com.api.common.base.data.dto.BaseDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 应用版本接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StatusDto extends BaseDto {

    // ID
    @NotNull(message = "{validation.apps.version.id.notNull}")
    private Integer id;
    // 状态。1：下线，2：测试，3：正式上线。
    @NotNull(message = "{validation.apps.version.status.notNull}")
    private Integer status;
}
