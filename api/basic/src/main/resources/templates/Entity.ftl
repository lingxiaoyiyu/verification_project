package com.api.${moduleName}.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * ${tableAnnotation}实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class ${handleTableName?cap_first} extends BasePagePo {

    public ${handleTableName?cap_first}(){
        super();
    }

<#list tableColumns as field>
    <#if field.valueName != "default">
    // ${field.columnComment}
    private ${field.dataType} ${field.valueName};
    </#if>
</#list>
}
