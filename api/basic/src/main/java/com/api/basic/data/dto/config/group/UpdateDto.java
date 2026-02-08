package com.api.basic.data.dto.config.group;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新配置组接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateDto extends BaseDto {

    // ID
    private Integer id;
    // 名称
    private String name;
    // 值
    private String value;
}
