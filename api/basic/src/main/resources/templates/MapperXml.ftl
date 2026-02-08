<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.api.${moduleName}.mapper.${handleTableName?cap_first}Mapper">

    <insert id="add"
            parameterType="com.api.${moduleName}.data.entity.${handleTableName?cap_first}"
            useGeneratedKeys="true" keyProperty="id">
        INSERT INTO ${tableName} (
        <trim suffixOverrides=",">
            <#list tableColumns as field>
                <#if field.columnName != "id" && field.columnName != "createdAt">
                    <#if field.isNullable == "NO" && (field.columnDefault?default("")) == "">
                        <#-- 必填字段添加null检查 -->
                        <if test="${field.valueName} != null">
                            `${field.columnName}`,
                        </if>
                    <#else>
                        <#if field.dataType == "String">
                            <if test="${field.valueName} != null and ${field.valueName} != ''">
                                `${field.columnName}`,
                            </if>
                        <#else>
                            <if test="${field.valueName} != null">
                                `${field.columnName}`,
                            </if>
                        </#if>
                    </#if>
                </#if>
            </#list>
        </trim>
        )
        VALUES (
        <trim suffixOverrides=",">
            <#list tableColumns as field>
                <#if field.columnName != "id" && field.columnName != "createdAt">
                    <#if field.isNullable == "NO" && (field.columnDefault?default("")) == "">
                        <#-- 必填字段添加null检查 -->
                        <if test="${field.valueName} != null">
                            #\{${field.valueName}},
                        </if>
                    <#else>
                        <#if field.dataType == "String">
                            <if test="${field.valueName} != null and ${field.valueName} != ''">
                                #\{${field.valueName}},
                            </if>
                        <#else>
                            <if test="${field.valueName} != null">
                                #\{${field.valueName}},
                            </if>
                        </#if>
                    </#if>
                </#if>
            </#list>
        </trim>
        )
    </insert>

    <delete id="delete" parameterType="com.api.${moduleName}.data.po.${handleTableName?cap_first}Po">
        <bind name="${handleTableName?uncap_first}Po" value="_parameter"/>
        DELETE `${alias}` FROM ${tableName} as `${alias}`
        <where>
        <include refid="whereClause"/>
        </where>
    </delete>

    <update id="update"
            parameterType="com.api.${moduleName}.data.po.${handleTableName?cap_first}Po">
        <bind name="${handleTableName?uncap_first}Po" value="_parameter"/>
        UPDATE ${tableName} as `${alias}`
        <set>
            <#list tableColumns as field>
                <if test="${handleTableName?uncap_first}Po.${field.valueName} != null">
                    <#if field.dataType == "String">
                        <if test="${handleTableName?uncap_first}Po.${field.valueName} == ''">
                            <#if field.columnDefault??>
                                <#if field.columnDefault?default("") == "CURRENT_TIMESTAMP">
                    `${alias}`.`${field.columnName}` = '',
                                <#else>
                    `${alias}`.`${field.columnName}` = '${field.columnDefault}',
                                </#if>
                            <#else>
                    `${alias}`.`${field.columnName}` = NULL,
                            </#if>
                        </if>
                        <if test="${handleTableName?uncap_first}Po.${field.valueName} != ''">
                    `${alias}`.`${field.columnName}` = #\{${handleTableName?uncap_first}Po.${field.valueName}},
                        </if>
                    <#else>
                        <!-- 非字符串类型，直接更新，包括0值和false值 -->
                    `${alias}`.`${field.columnName}` = #\{${handleTableName?uncap_first}Po.${field.valueName}},
                    </#if>
                </if>
            </#list>
        </set>
        <where>
            <include refid="whereClause"/>
        </where>
    </update>

    <sql id="whereClause">
        <if test="${handleTableName?uncap_first}Po != null">
            <#list tableColumns as field>
                <#list field.whereTypes as whereType>
                    <#if whereType == "equal">
                        <#if field.dataType == "String">
                            <if test="${handleTableName?uncap_first}Po.where${field.valueName?cap_first} != null and ${handleTableName?uncap_first}Po.where${field.valueName?cap_first} != ''">
                                AND `${alias}`.`${field.columnName}` = #\{${handleTableName?uncap_first}Po.where${field.valueName?cap_first}}
                            </if>
                        <#else>
                            <if test="${handleTableName?uncap_first}Po.where${field.valueName?cap_first} != null">
                                AND `${alias}`.`${field.columnName}` = #\{${handleTableName?uncap_first}Po.where${field.valueName?cap_first}}
                            </if>
                        </#if>
                    </#if>
                    <#if whereType == "not">
                        <#if field.dataType == "String">
                            <if test="${handleTableName?uncap_first}Po.whereNot${field.valueName?cap_first} != null and ${handleTableName?uncap_first}Po.whereNot${field.valueName?cap_first} != ''">
                                AND `${alias}`.`${field.columnName}` != #\{${handleTableName?uncap_first}Po.whereNot${field.valueName?cap_first}}
                            </if>
                        <#else>
                            <if test="${handleTableName?uncap_first}Po.whereNot${field.valueName?cap_first} != null">
                                AND `${alias}`.`${field.columnName}` != #\{${handleTableName?uncap_first}Po.whereNot${field.valueName?cap_first}}
                            </if>
                        </#if>
                    </#if>
                    <#if whereType == "in">
                        <if test="${handleTableName?uncap_first}Po.whereIn${field.valueName?cap_first}s != null and ${handleTableName?uncap_first}Po.whereIn${field.valueName?cap_first}s.size() > 0">
                            AND `${alias}`.`${field.columnName}` IN
                            <foreach item="item" index="index" collection="${handleTableName?uncap_first}Po.whereIn${field.valueName?cap_first}s"
                                     open="(" separator="," close=")">
                                #\{item}
                            </foreach>
                        </if>
                    </#if>
                    <#if whereType == "inOr">
                        <if test="${handleTableName?uncap_first}Po.whereInOr${field.valueName?cap_first}s != null and ${handleTableName?uncap_first}Po.whereInOr${field.valueName?cap_first}s.size() > 0">
                            AND
                            <foreach item="item" index="index" collection="${handleTableName?uncap_first}Po.whereInOr${field.valueName?cap_first}s"
                                     open="(" separator="or" close=")">
                                <if test="item != null">
                                `${alias}`.`${field.columnName}` = #\{item}
                                </if>
                                <if test="item == null">
                                    `${alias}`.`${field.columnName}` is null
                                </if>
                            </foreach>
                        </if>
                    </#if>
                    <#if whereType == "notIn">
                        <if test="${handleTableName?uncap_first}Po.whereNotIn${field.valueName?cap_first}s != null and ${handleTableName?uncap_first}Po.whereNotIn${field.valueName?cap_first}s.size() > 0">
                            AND `${alias}`.`${field.columnName}` NOT IN
                            <foreach item="item" index="index" collection="${handleTableName?uncap_first}Po.whereNotIn${field.valueName?cap_first}s"
                                     open="(" separator="," close=")">
                                #\{item}
                            </foreach>
                        </if>
                    </#if>
                    <#if whereType == "like">
                        <#if field.dataType == "String">
                            <if test="${handleTableName?uncap_first}Po.whereLike${field.valueName?cap_first} != null and ${handleTableName?uncap_first}Po.whereLike${field.valueName?cap_first} != ''">
                                AND `${alias}`.`${field.columnName}` LIKE CONCAT('%', #\{${handleTableName?uncap_first}Po.whereLike${field.valueName?cap_first}}, '%')
                            </if>
                        <#else>
                            <if test="${handleTableName?uncap_first}Po.whereLike${field.valueName?cap_first} != null">
                                AND `${alias}`.`${field.columnName}` LIKE CONCAT('%', #\{${handleTableName?uncap_first}Po.whereLike${field.valueName?cap_first}}, '%')
                            </if>
                        </#if>
                    </#if>
                    <#if whereType == "start">
                        <#if field.dataType == "String">
                            <if test="${handleTableName?uncap_first}Po.whereStart${field.valueName?cap_first} != null and ${handleTableName?uncap_first}Po.whereStart${field.valueName?cap_first} != ''">
                                AND `${alias}`.`${field.columnName}` >= #\{${handleTableName?uncap_first}Po.whereStart${field.valueName?cap_first}}
                            </if>
                        <#else>
                            <if test="${handleTableName?uncap_first}Po.whereStart${field.valueName?cap_first} != null">
                                AND `${alias}`.`${field.columnName}` >= #\{${handleTableName?uncap_first}Po.whereStart${field.valueName?cap_first}}
                            </if>
                        </#if>
                    </#if>
                    <#if whereType == "end">
                        <#if field.dataType == "String">
                            <if test="${handleTableName?uncap_first}Po.whereEnd${field.valueName?cap_first} != null and ${handleTableName?uncap_first}Po.whereEnd${field.valueName?cap_first} != ''">
                                AND `${alias}`.`${field.columnName}` <= #\{${handleTableName?uncap_first}Po.whereEnd${field.valueName?cap_first}}
                            </if>
                        <#else>
                            <if test="${handleTableName?uncap_first}Po.whereEnd${field.valueName?cap_first} != null">
                                AND `${alias}`.`${field.columnName}` <= #\{${handleTableName?uncap_first}Po.whereEnd${field.valueName?cap_first}}
                            </if>
                        </#if>
                    </#if>
                    
                    <!-- 添加空值查询支持 -->
                    <#if whereType == "isNull">
                        <if test="${handleTableName?uncap_first}Po.whereIsNull${field.valueName?cap_first} != null and ${handleTableName?uncap_first}Po.whereIsNull${field.valueName?cap_first} == true">
                            AND `${alias}`.`${field.columnName}` IS NULL
                        </if>
                    </#if>
                    
                    <#if whereType == "isNotNull">
                        <if test="${handleTableName?uncap_first}Po.whereIsNotNull${field.valueName?cap_first} != null and ${handleTableName?uncap_first}Po.whereIsNotNull${field.valueName?cap_first} == true">
                            AND `${alias}`.`${field.columnName}` IS NOT NULL
                        </if>
                    </#if>
                    
                    <#if whereType == "isEmpty">
                        <if test="${handleTableName?uncap_first}Po.whereIsEmpty${field.valueName?cap_first} != null and ${handleTableName?uncap_first}Po.whereIsEmpty${field.valueName?cap_first} == true">
                            AND (`${alias}`.`${field.columnName}` IS NULL OR `${alias}`.`${field.columnName}` = '')
                        </if>
                    </#if>
                    
                    <#if whereType == "isNotEmpty">
                        <if test="${handleTableName?uncap_first}Po.whereIsNotEmpty${field.valueName?cap_first} != null and ${handleTableName?uncap_first}Po.whereIsNotEmpty${field.valueName?cap_first} == true">
                            AND `${alias}`.`${field.columnName}` IS NOT NULL AND `${alias}`.`${field.columnName}` != ''
                        </if>
                    </#if>
                    
                    <!-- 增加比较运算符条件 -->
                    <#if whereType == "gt">
                        <#if field.dataType == "String">
                            <if test="${handleTableName?uncap_first}Po.whereGt${field.valueName?cap_first} != null and ${handleTableName?uncap_first}Po.whereGt${field.valueName?cap_first} != ''">
                                AND `${alias}`.`${field.columnName}` &gt; #\{${handleTableName?uncap_first}Po.whereGt${field.valueName?cap_first}}
                            </if>
                        <#else>
                            <if test="${handleTableName?uncap_first}Po.whereGt${field.valueName?cap_first} != null">
                                AND `${alias}`.`${field.columnName}` &gt; #\{${handleTableName?uncap_first}Po.whereGt${field.valueName?cap_first}}
                            </if>
                        </#if>
                    </#if>
                    
                    <#if whereType == "gte">
                        <#if field.dataType == "String">
                            <if test="${handleTableName?uncap_first}Po.whereGte${field.valueName?cap_first} != null and ${handleTableName?uncap_first}Po.whereGte${field.valueName?cap_first} != ''">
                                AND `${alias}`.`${field.columnName}` &gt;= #\{${handleTableName?uncap_first}Po.whereGte${field.valueName?cap_first}}
                            </if>
                        <#else>
                            <if test="${handleTableName?uncap_first}Po.whereGte${field.valueName?cap_first} != null">
                                AND `${alias}`.`${field.columnName}` &gt;= #\{${handleTableName?uncap_first}Po.whereGte${field.valueName?cap_first}}
                            </if>
                        </#if>
                    </#if>
                    
                    <#if whereType == "lt">
                        <#if field.dataType == "String">
                            <if test="${handleTableName?uncap_first}Po.whereLt${field.valueName?cap_first} != null and ${handleTableName?uncap_first}Po.whereLt${field.valueName?cap_first} != ''">
                                AND `${alias}`.`${field.columnName}` &lt; #\{${handleTableName?uncap_first}Po.whereLt${field.valueName?cap_first}}
                            </if>
                        <#else>
                            <if test="${handleTableName?uncap_first}Po.whereLt${field.valueName?cap_first} != null">
                                AND `${alias}`.`${field.columnName}` &lt; #\{${handleTableName?uncap_first}Po.whereLt${field.valueName?cap_first}}
                            </if>
                        </#if>
                    </#if>
                    
                    <#if whereType == "lte">
                        <#if field.dataType == "String">
                            <if test="${handleTableName?uncap_first}Po.whereLte${field.valueName?cap_first} != null and ${handleTableName?uncap_first}Po.whereLte${field.valueName?cap_first} != ''">
                                AND `${alias}`.`${field.columnName}` &lt;= #\{${handleTableName?uncap_first}Po.whereLte${field.valueName?cap_first}}
                            </if>
                        <#else>
                            <if test="${handleTableName?uncap_first}Po.whereLte${field.valueName?cap_first} != null">
                                AND `${alias}`.`${field.columnName}` &lt;= #\{${handleTableName?uncap_first}Po.whereLte${field.valueName?cap_first}}
                            </if>
                        </#if>
                    </#if>
                </#list>
            </#list>
        </if>
    </sql>

    <select id="query"
            parameterType="com.api.${moduleName}.data.po.${handleTableName?cap_first}Po"
            resultType="com.api.${moduleName}.data.entity.${handleTableName?cap_first}">
        <bind name="${handleTableName?uncap_first}Po" value="_parameter"/>
        SELECT 
        <choose>
            <when test="${handleTableName?uncap_first}Po.useDistinct != null and ${handleTableName?uncap_first}Po.useDistinct == true"> DISTINCT </when>
        </choose>
        `${alias}`.*
        FROM ${tableName} as `${alias}`
        <where>
            <include refid="whereClause"/>
        </where>
        <if test="${handleTableName?uncap_first}Po.groupByFields != null and ${handleTableName?uncap_first}Po.groupByFields.size() > 0">
        GROUP BY
        <foreach item="field" index="index" collection="${handleTableName?uncap_first}Po.groupByFields" separator=",">
            `${alias}`.`#\{field}`
        </foreach>
        </if>
        <if test="${handleTableName?uncap_first}Po.havingClause != null and ${handleTableName?uncap_first}Po.havingClause != ''">
        HAVING #\{${handleTableName?uncap_first}Po.havingClause}
        </if>
        <include refid="com.api.mapper.CommonSqlFragments.orderByAndLimit"/>
    </select>

    <select id="cnt"
            parameterType="com.api.${moduleName}.data.po.${handleTableName?cap_first}Po"
            resultType="int">
        <bind name="${handleTableName?uncap_first}Po" value="_parameter"/>
        SELECT COUNT(
        <choose>
            <when test="${handleTableName?uncap_first}Po.useDistinct != null and ${handleTableName?uncap_first}Po.useDistinct == true"> DISTINCT `${alias}`.`${tableColumns[0].columnName}` </when>
            <otherwise> `${alias}`.`${tableColumns[0].columnName}` </otherwise>
        </choose>
        ) as cnt
        FROM ${tableName} as `${alias}`
        <where>
            <include refid="whereClause"/>
        </where>
        <if test="${handleTableName?uncap_first}Po.groupByFields != null and ${handleTableName?uncap_first}Po.groupByFields.size() > 0">
        GROUP BY
        <foreach item="field" index="index" collection="${handleTableName?uncap_first}Po.groupByFields" separator=",">
            `${alias}`.`#\{field}`
        </foreach>
        </if>
        <if test="${handleTableName?uncap_first}Po.havingClause != null and ${handleTableName?uncap_first}Po.havingClause != ''">
        HAVING #\{${handleTableName?uncap_first}Po.havingClause}
        </if>
    </select>

    <#list tableColumns as field>
    <#if field.dataType == "Integer" || field.dataType == "Long" || field.dataType == "Float" || field.dataType == "Double" || field.dataType == "BigDecimal">
    <!-- sum查询 -->
    <select id="sum${field.valueName?cap_first}"
            parameterType="com.api.${moduleName}.data.po.${handleTableName?cap_first}Po"
            resultType="java.math.BigDecimal">
        <bind name="${handleTableName?uncap_first}Po" value="_parameter"/>
        SELECT COALESCE(SUM(
        <choose>
            <when test="${handleTableName?uncap_first}Po.useDistinct != null and ${handleTableName?uncap_first}Po.useDistinct == true"> DISTINCT </when>
        </choose>
        `${alias}`.`${field.columnName}`), 0) as result
        FROM ${tableName} as `${alias}`
        <where>
            <include refid="whereClause"/>
        </where>
        <if test="${handleTableName?uncap_first}Po.groupByFields != null and ${handleTableName?uncap_first}Po.groupByFields.size() > 0">
        GROUP BY
        <foreach item="groupField" index="index" collection="${handleTableName?uncap_first}Po.groupByFields" separator=",">
            `${alias}`.`#\{groupField}`
        </foreach>
        </if>
        <if test="${handleTableName?uncap_first}Po.havingClause != null and ${handleTableName?uncap_first}Po.havingClause != ''">
        HAVING #\{${handleTableName?uncap_first}Po.havingClause}
        </if>
    </select>

    <!-- max查询 -->
    <select id="max${field.valueName?cap_first}"
            parameterType="com.api.${moduleName}.data.po.${handleTableName?cap_first}Po"
            resultType="java.math.BigDecimal">
        <bind name="${handleTableName?uncap_first}Po" value="_parameter"/>
        SELECT MAX(
        <choose>
            <when test="${handleTableName?uncap_first}Po.useDistinct != null and ${handleTableName?uncap_first}Po.useDistinct == true"> DISTINCT </when>
        </choose>
        `${alias}`.`${field.columnName}`) as result
        FROM ${tableName} as `${alias}`
        <where>
            <include refid="whereClause"/>
        </where>
        <if test="${handleTableName?uncap_first}Po.groupByFields != null and ${handleTableName?uncap_first}Po.groupByFields.size() > 0">
        GROUP BY
        <foreach item="groupField" index="index" collection="${handleTableName?uncap_first}Po.groupByFields" separator=",">
            `${alias}`.`#\{groupField}`
        </foreach>
        </if>
        <if test="${handleTableName?uncap_first}Po.havingClause != null and ${handleTableName?uncap_first}Po.havingClause != ''">
        HAVING #\{${handleTableName?uncap_first}Po.havingClause}
        </if>
    </select>

    <!-- min查询 -->
    <select id="min${field.valueName?cap_first}"
            parameterType="com.api.${moduleName}.data.po.${handleTableName?cap_first}Po"
            resultType="java.math.BigDecimal">
        <bind name="${handleTableName?uncap_first}Po" value="_parameter"/>
        SELECT MIN(
        <choose>
            <when test="${handleTableName?uncap_first}Po.useDistinct != null and ${handleTableName?uncap_first}Po.useDistinct == true"> DISTINCT </when>
        </choose>
        `${alias}`.`${field.columnName}`) as result
        FROM ${tableName} as `${alias}`
        <where>
            <include refid="whereClause"/>
        </where>
        <if test="${handleTableName?uncap_first}Po.groupByFields != null and ${handleTableName?uncap_first}Po.groupByFields.size() > 0">
        GROUP BY
        <foreach item="groupField" index="index" collection="${handleTableName?uncap_first}Po.groupByFields" separator=",">
            `${alias}`.`#\{groupField}`
        </foreach>
        </if>
        <if test="${handleTableName?uncap_first}Po.havingClause != null and ${handleTableName?uncap_first}Po.havingClause != ''">
        HAVING #\{${handleTableName?uncap_first}Po.havingClause}
        </if>
    </select>

    <!-- avg查询 -->
    <select id="avg${field.valueName?cap_first}"
            parameterType="com.api.${moduleName}.data.po.${handleTableName?cap_first}Po"
            resultType="java.math.BigDecimal">
        <bind name="${handleTableName?uncap_first}Po" value="_parameter"/>
        SELECT COALESCE(AVG(
        <choose>
            <when test="${handleTableName?uncap_first}Po.useDistinct != null and ${handleTableName?uncap_first}Po.useDistinct == true"> DISTINCT </when>
        </choose>
        `${alias}`.`${field.columnName}`), 0) as result
        FROM ${tableName} as `${alias}`
        <where>
            <include refid="whereClause"/>
        </where>
        <if test="${handleTableName?uncap_first}Po.groupByFields != null and ${handleTableName?uncap_first}Po.groupByFields.size() > 0">
        GROUP BY
        <foreach item="groupField" index="index" collection="${handleTableName?uncap_first}Po.groupByFields" separator=",">
            `${alias}`.`#\{groupField}`
        </foreach>
        </if>
        <if test="${handleTableName?uncap_first}Po.havingClause != null and ${handleTableName?uncap_first}Po.havingClause != ''">
        HAVING #\{${handleTableName?uncap_first}Po.havingClause}
        </if>
    </select>
    </#if>
    </#list>
</mapper>
