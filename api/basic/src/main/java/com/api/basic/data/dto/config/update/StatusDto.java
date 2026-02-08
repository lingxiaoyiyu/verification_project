package com.api.basic.data.dto.config.update;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新状态接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StatusDto extends BaseDto {

    // ID
    private Integer id;
    // 状态
    private Integer status;
}
