package com.api.${moduleName}.dao;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.api.${moduleName}.data.entity.${handleTableName?cap_first};
import com.api.${moduleName}.data.po.${handleTableName?cap_first}Po;
import com.api.${moduleName}.mapper.${handleTableName?cap_first}Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

/**
 * ${tableAnnotation}Dao
 *
 * @author ${author}
 */
@Component
@RequiredArgsConstructor
public class ${handleTableName?cap_first}Dao {

    private final ${handleTableName?cap_first}Mapper mapper;

    //***************************************************添加 START******************************************************
    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(${handleTableName?cap_first} entity) {
        if (entity == null) {
            return 0;
        }
        return mapper.add(entity);
    }
    //***************************************************添加 END********************************************************

    //***************************************************删除 START******************************************************
    /**
     * 删除信息
     *
     * @param po 要删除的信息
     * @return 受影响的行数，即删除的数据数量
     */
    public Integer delete(${handleTableName?cap_first}Po po) {
        if (po == null) {
            return 0;
        }
        return mapper.delete(po);
    }
    //***************************************************删除 END********************************************************


    //***************************************************更新 START******************************************************
    /**
     * 更新信息
     *
     * @param po 要更新的信息
     * @return 受影响的行数，即更新的数据数量
     */
    public Integer update(${handleTableName?cap_first}Po po) {
        if (po == null) {
            return 0;
        }
        return mapper.update(po);
    }
    //***************************************************更新 END********************************************************

    //***************************************************获取 START******************************************************
    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<${handleTableName?cap_first}> query(${handleTableName?cap_first}Po po) {
        if (po == null) {
            return null;
        }
        return mapper.query(po);
    }

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    public Integer cnt(${handleTableName?cap_first}Po po) {
        if (po == null) {
            return 0;
        }
        return mapper.cnt(po);
    }

    /**
     * 查询单条数据
     *
     * @param po 查询条件
     * @return 查询结果
     */
    public ${handleTableName?cap_first} get(${handleTableName?cap_first}Po po) {
        if (po == null) {
            return null;
        }
        return mapper.query(po).stream().findFirst().orElse(null);
    }
<#list tableColumns as field>

    /**
     * 根据${field.columnComment}查询单条数据
     *
     * @param ${field.valueName} ${field.columnComment}
     * @return 查询结果
     */
    public ${handleTableName?cap_first} getBy${field.valueName?cap_first}(${field.dataType} ${field.valueName}) {
        <#if field.dataType == "String">
        if (StrUtil.isBlank(${field.valueName})) {
            return null;
        }
        <#else>
        if (ObjectUtil.isNull(${field.valueName})) {
            return null;
        }
        </#if>

        ${handleTableName?cap_first}Po po = new ${handleTableName?cap_first}Po();
        po.setWhere${field.valueName?cap_first}(${field.valueName});
        return get(po);
    }

    /**
     * 根据${field.columnComment}查询多条数据
     *
     * @param ${field.valueName} ${field.columnComment}
     * @return 查询结果
     */
    public List<${handleTableName?cap_first}> queryBy${field.valueName?cap_first}(${field.dataType} ${field.valueName}) {
        <#if field.dataType == "String">
        if (StrUtil.isBlank(${field.valueName})) {
            return null;
        }
        <#else>
        if (ObjectUtil.isNull(${field.valueName})) {
            return null;
        }
        </#if>

        ${handleTableName?cap_first}Po po = new ${handleTableName?cap_first}Po();
        po.setWhere${field.valueName?cap_first}(${field.valueName});
        return query(po);
    }

    /**
     * 根据${field.columnComment}获取指定字段的值
     *
     * @param ${field.valueName} ${field.columnComment}
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldBy${field.valueName?cap_first}(${field.dataType} ${field.valueName}, Function<${handleTableName?cap_first}, T> fieldMapper) {
        <#if field.dataType == "String">
        if (StrUtil.isBlank(${field.valueName})) {
            return Optional.empty();
        }
        <#else>
        if (ObjectUtil.isNull(${field.valueName})) {
            return Optional.empty();
        }
        </#if>

        return Optional.ofNullable(getBy${field.valueName?cap_first}(${field.valueName})).map(fieldMapper);
   }
</#list>

    //***************************************************获取 END********************************************************

    //***************************************************聚合操作 START******************************************************
    <#list tableColumns as field>
    <#if field.dataType == "Integer" || field.dataType == "Long" || field.dataType == "Float" || field.dataType == "Double" || field.dataType == "BigDecimal">
    /**
     * 统计${field.columnComment}的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sum${field.valueName?cap_first}(${handleTableName?cap_first}Po po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sum${field.valueName?cap_first}(po);
    }

    /**
     * 统计${field.columnComment}的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal max${field.valueName?cap_first}(${handleTableName?cap_first}Po po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.max${field.valueName?cap_first}(po);
    }

    /**
     * 统计${field.columnComment}的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal min${field.valueName?cap_first}(${handleTableName?cap_first}Po po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.min${field.valueName?cap_first}(po);
    }

    /**
     * 统计${field.columnComment}的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avg${field.valueName?cap_first}(${handleTableName?cap_first}Po po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avg${field.valueName?cap_first}(po);
    }
    </#if>
    </#list>
    //***************************************************聚合操作 END********************************************************
}