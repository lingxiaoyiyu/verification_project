package com.api.basic.data.dto.develop.generate;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 生成基础代码接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseCodeDto extends BaseDto {

    // 模块名
    private String moduleName;
    // 表名列表
    private List<String> tableNameList;
}


