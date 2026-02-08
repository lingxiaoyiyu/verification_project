package com.api.${moduleName}.mapper;

import com.api.${moduleName}.data.entity.${handleTableName?cap_first};
import com.api.${moduleName}.data.po.${handleTableName?cap_first}Po;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.math.BigDecimal;

/**
 * ${tableAnnotation}Mapper
 *
 * @author ${author}
 */
@Repository
@Mapper
public interface ${handleTableName?cap_first}Mapper {

    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(${handleTableName?cap_first} entity);

    /**
     * 删除信息
     *
     * @param po 要删除的信息
     * @return 受影响的行数，即删除的数据数量
     */
    public Integer delete(${handleTableName?cap_first}Po po);

    /**
     * 更新信息
     *
     * @param po 要更新的信息
     * @return 受影响的行数，即更新的数据数量
     */
    public Integer update(${handleTableName?cap_first}Po po);

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<${handleTableName?cap_first}> query(${handleTableName?cap_first}Po po);

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    public Integer cnt(${handleTableName?cap_first}Po po);

    <#list tableColumns as field>
    <#if field.dataType == "Integer" || field.dataType == "Long" || field.dataType == "Float" || field.dataType == "Double" || field.dataType == "BigDecimal">
    /**
     * 统计${field.columnComment}的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sum${field.valueName?cap_first}(${handleTableName?cap_first}Po po);

    /**
     * 统计${field.columnComment}的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal max${field.valueName?cap_first}(${handleTableName?cap_first}Po po);

    /**
     * 统计${field.columnComment}的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal min${field.valueName?cap_first}(${handleTableName?cap_first}Po po);

    /**
     * 统计${field.columnComment}的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avg${field.valueName?cap_first}(${handleTableName?cap_first}Po po);
    </#if>
    </#list>
}
