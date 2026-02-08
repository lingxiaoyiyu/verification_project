package com.api.basic.data.dto.develop.generate;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 生成接口代码接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ApiCodeDto extends BaseDto {

    // 模块名
    private String moduleName;
    // 接口注释
    private String apiComment;
    // 接口名称
    private String apiName;
    // 接口路径
    private String apiPath;
    // 子模块名称
    private String subModuleName;
    // 模块注释
    private String subModuleAnnotation;
    // API类型
    private String apiType;
    // 表名
    private String tableName;
    // dto字段列表
    private List<DtoVoItemDto> dtoFields;
    // vo字段列表
    private List<DtoVoItemDto> voFields;
}


