package com.api.${moduleName}.data.po;

import com.api.${moduleName}.data.entity.${handleTableName?cap_first};
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * ${tableAnnotation}条件实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class ${handleTableName?cap_first}Po extends ${handleTableName?cap_first} {

    public ${handleTableName?cap_first}Po(){
        super();
    }

<#list tableColumns as field>
    <#list field.whereTypes as item>
        <#if item == "equal">
    // 条件：${field.columnComment}，等于
    private ${field.dataType} where${field.valueName?cap_first};
        </#if>
        <#if item == "not">
    // 排除条件：${field.columnComment}
    private ${field.dataType} whereNot${field.valueName?cap_first};
        </#if>
        <#if item == "in">
    // 条件：${field.columnComment}，在列表中
    private List<${field.dataType}> whereIn${field.valueName?cap_first}s;
        </#if>
        <#if item == "inOr">
    // 条件：${field.columnComment}，在列表中，or连接
    private List<${field.dataType}> whereInOr${field.valueName?cap_first}s;
        </#if>
        <#if item == "notIn">
    // 条件：${field.columnComment}，不在列表中
    private List<${field.dataType}> whereNotIn${field.valueName?cap_first}s;
        </#if>
        <#if item == "like">
    // 条件：${field.columnComment}，模糊查询
    private ${field.dataType} whereLike${field.valueName?cap_first};
        </#if>
        <#if item == "start">
    // 条件：${field.columnComment}，开始范围
    private ${field.dataType} whereStart${field.valueName?cap_first};
        </#if>
        <#if item == "end">
    // 条件：${field.columnComment}，结束范围
    private ${field.dataType} whereEnd${field.valueName?cap_first};
        </#if>
        <#if item == "gt">
    // 条件：${field.columnComment}，大于
    private ${field.dataType} whereGt${field.valueName?cap_first};
        </#if>
        <#if item == "gte">
    // 条件：${field.columnComment}，大于等于
    private ${field.dataType} whereGte${field.valueName?cap_first};
        </#if>
        <#if item == "lt">
    // 条件：${field.columnComment}，小于
    private ${field.dataType} whereLt${field.valueName?cap_first};
        </#if>
        <#if item == "lte">
    // 条件：${field.columnComment}，小于等于
    private ${field.dataType} whereLte${field.valueName?cap_first};
        </#if>
        <#if item == "isNull">
    // 条件：${field.columnComment}，为空
    private Boolean whereIsNull${field.valueName?cap_first};
        </#if>
        <#if item == "isNotNull">
    // 条件：${field.columnComment}，不为空
    private Boolean whereIsNotNull${field.valueName?cap_first};
        </#if>
        <#if item == "isEmpty">
    // 条件：${field.columnComment}，为空字符串
    private Boolean whereIsEmpty${field.valueName?cap_first};
        </#if>
        <#if item == "isNotEmpty">
    // 条件：${field.columnComment}，不为空字符串
    private Boolean whereIsNotEmpty${field.valueName?cap_first};
        </#if>
    </#list>
</#list>

    // 是否使用distinct
    private Boolean useDistinct;
    
    // group by字段列表
    private List<String> groupByFields;
    
    // having条件
    private String havingClause;
    
    /**
     * 添加group by字段
     * @param field 字段名称
     * @return 当前PO实例
     */
    public ${handleTableName?cap_first}Po addGroupByField(String field) {
        if (this.groupByFields == null) {
            this.groupByFields = new ArrayList<>();
        }
        this.groupByFields.add(field);
        return this;
    }
    
    /**
     * 设置group by字段列表
     * @param groupByFields 字段名称列表
     * @return 当前PO实例
     */
    public ${handleTableName?cap_first}Po setGroupByFields(List<String> groupByFields) {
        this.groupByFields = groupByFields;
        return this;
    }
}
