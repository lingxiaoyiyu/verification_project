package com.api.apps.data.dto.apps.update;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 设置入口小程序接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EntranceMpDto extends BaseDto {

    // 
    private String id;
}
