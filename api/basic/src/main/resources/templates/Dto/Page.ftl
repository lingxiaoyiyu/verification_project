package com.api.${moduleName}.data.dto.${packagePath};

import com.api.common.base.data.dto.BasePageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * ${apiComment}接口请求用DTO
 *
 * @author ${author}
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class ${apiName?cap_first}Dto extends BasePageDto {

<#list fields as field>
    <#if field.fieldName != "default">
    // ${field.comment}
    private ${field.dataType} ${field.fieldName};
    </#if>
</#list>
}
