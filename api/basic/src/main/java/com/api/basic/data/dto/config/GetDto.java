package com.api.basic.data.dto.config;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取配置项详情接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetDto extends BaseDto {

    // 配置项ID
    private Integer id;
}
