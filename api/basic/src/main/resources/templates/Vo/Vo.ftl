package com.api.${moduleName}.data.vo.${packagePath};

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * ${apiComment}接口响应用VO
 *
 * @author ${author}
 */
@Data
public class ${apiName?cap_first}Vo {

<#list fields as field>
 <#if field.fieldName != "default">
  // ${field.comment}
  private ${field.dataType} ${field.fieldName};
 </#if>
</#list>
}
