package com.api.basic.data.dto.config.group;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取配置组详情接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetDto extends BaseDto {

    // ID
    private Integer Id;
}
