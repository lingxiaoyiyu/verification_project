package com.api.basic.data.dto.config;

import com.api.common.base.data.dto.BasePageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取配置项列表接口请求用DTO
 *
 * @author 裴金伟
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class PageDto extends BasePageDto {

    // 分组ID
    private Integer groupId;
}
