package com.api.${moduleName}.data.dto.${packagePath};

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * ${apiComment}接口请求用DTO
 *
 * @author ${author}
 */
@Data
public class ${apiName?cap_first}Dto {

<#list fields as field>
    <#if field.fieldName != "default">
    // ${field.comment}
    private ${field.dataType} ${field.fieldName};
    </#if>
</#list>
}
