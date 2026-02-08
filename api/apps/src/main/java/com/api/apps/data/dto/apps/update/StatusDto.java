package com.api.apps.data.dto.apps.update;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 应用状态接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StatusDto extends BaseDto {

    // 
    private String id;
    // 状态。1：启用，2：禁用
    private Integer status;
}
