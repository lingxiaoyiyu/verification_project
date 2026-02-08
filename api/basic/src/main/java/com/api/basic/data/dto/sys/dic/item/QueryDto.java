package com.api.basic.data.dto.sys.dic.item;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取字典项列表接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryDto extends BaseDto {

    // 字典标识
    private String dicIdentifying;
}
