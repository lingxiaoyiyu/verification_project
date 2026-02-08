package com.api.${moduleName}.service.${packagePath};

<#assign hasId = false>
<#assign hasIsDelete = false>
<#assign hasUpdatedAt = false>
<#assign hasCreatedUserId = false>
<#assign hasUpdatedUserId = false>
<#list tableColumns as field>
    <#if field.valueName == "id">
        <#assign hasId = true>
    </#if>
    <#if field.valueName == "isDelete">
        <#assign hasIsDelete = true>
    </#if>
    <#if field.valueName == "createdUserId">
        <#assign hasCreatedUserId = true>
    </#if>
    <#if field.valueName == "updatedAt">
        <#assign hasUpdatedAt = true>
    </#if>
    <#if field.valueName == "updatedUserId">
        <#assign hasUpdatedUserId = true>
    </#if>
</#list>
import cn.dev33.satoken.stp.StpUtil;
import com.api.${moduleName}.data.dto.${packagePath}.${apiName?cap_first}Dto;
import com.api.${moduleName}.data.entity.${tableName?cap_first};
import com.api.${moduleName}.dao.${tableName?cap_first}Dao;
import com.api.${moduleName}.data.po.${tableName?cap_first}Po;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

/**
 * ${apiComment}
 *
 * @author ${author}
 */
@Service("${moduleName?cap_first}${serviceName?cap_first}ServiceImpl")
@RequiredArgsConstructor
public class ${apiName?cap_first} extends AbstractService {

    private final ${tableName?cap_first}Dao ${tableName}Dao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(${apiName?cap_first}Dto dto) {
        <#list dtoFields as field>
            <#if field.fieldUnique?? && field.fieldUnique == true>
        if (${tableName}Dao.cnt(${tableName?cap_first}Po.builder()
            <#if hasIsDelete>
            .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
            </#if>
            .where${field.fieldName?cap_first}(dto.get${field.fieldName?cap_first}())
            .build()) != 0) {
            throw new ServerException("${field.comment}已存在");
        }
            </#if>
        </#list>
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(${apiName?cap_first}Dto dto) {
        // 构建添加对象
        ${tableName?cap_first} entity = handleAddData(dto);
        if (${tableName}Dao.add(entity) == 0) {
            throw new ServerException("添加失败");
        }
        <#assign hasId = false>
        <#list tableColumns as field>
            <#if field.valueName == "id">
                <#assign hasId = true>
                <#break>
            </#if>
        </#list>
        <#if hasId>
        return Result.ok("添加成功", Map.of("id", entity.getId()));
        <#else>
        return Result.ok("添加成功");
        </#if>
    }

    /**
     * 处理要添加的数据
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected ${tableName?cap_first} handleAddData(${apiName?cap_first}Dto dto){
        ${tableName?cap_first} entity = new ${tableName?cap_first}();
        <#list dtoFields as field>
        entity.set${field.fieldName?cap_first}(dto.get${field.fieldName?cap_first}());
        </#list>
        <#if hasCreatedUserId>
        entity.setCreatedUserId(StpUtil.getLoginIdAsInt());
        </#if>
        <#if hasUpdatedUserId>
        entity.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        </#if>
        return entity;
    }
}
