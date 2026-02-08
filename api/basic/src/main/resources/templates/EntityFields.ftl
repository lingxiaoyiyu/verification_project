package com.api.${moduleName}.data.entity.fields;

/**
 * ${tableAnnotation}字段常量类
 */
public class ${handleTableName?cap_first}Fields {
    <#list tableColumns as field>
    <#if field.valueName != "default">
    /**
     * ${field.columnComment}
     */
    public static final String ${field.valueName?upper_case} = "${field.valueName}";
    </#if>
    </#list>
}