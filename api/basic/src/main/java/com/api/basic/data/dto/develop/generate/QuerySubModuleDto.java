package com.api.basic.data.dto.develop.generate;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取子模块列表接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuerySubModuleDto extends BaseDto {

    // 模块名
    private String moduleName;
}
