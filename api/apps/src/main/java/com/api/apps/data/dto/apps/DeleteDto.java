package com.api.apps.data.dto.apps;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 应用接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DeleteDto extends BaseDto {

    // 
    private String id;
}
