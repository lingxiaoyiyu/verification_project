package com.api.${moduleName}.service.${packagePath};

<#assign entityHasId = false>
<#assign entityHasIsDelete = false>
<#assign entityHasUpdatedAt = false>
<#assign entityHasCreatedUserId = false>
<#assign entityHasUpdatedUserId = false>
<#list tableColumns as field>
    <#if field.valueName == "id">
        <#assign entityHasId = true>
    </#if>
    <#if field.valueName == "isDelete">
        <#assign entityHasIsDelete = true>
    </#if>
    <#if field.valueName == "createdUserId">
        <#assign entityHasCreatedUserId = true>
    </#if>
    <#if field.valueName == "updatedAt">
        <#assign entityHasUpdatedAt = true>
    </#if>
    <#if field.valueName == "updatedUserId">
        <#assign entityHasUpdatedUserId = true>
    </#if>
</#list>
<#assign dtoHasId = false>
<#assign dtoHasUpdatedAt = false>
<#list dtoFields as field>
    <#if field.fieldName == "id">
        <#assign dtoHasId = true>
    </#if>
    <#if field.fieldName == "updatedAt">
        <#assign dtoHasUpdatedAt = true>
    </#if>
</#list>
<#assign voHasUpdatedAt = false>
<#assign voHasCreatedUserName = false>
<#assign voHasUpdatedUserName = false>
<#assign voIsDelete = false>
<#list voFields as field>
    <#if field.fieldName == "updatedAt" && entityHasUpdatedAt>
        <#assign voHasUpdatedAt = true>
    </#if>
    <#if field.fieldName == "createdUserName" && entityHasCreatedUserId>
        <#assign voHasCreatedUserName = true>
    </#if>
    <#if field.fieldName == "updatedUserName" && entityHasUpdatedUserId>
        <#assign voHasUpdatedUserName = true>
    </#if>
    <#if field.fieldName == "isDelete" && entityHasIsDelete>
        <#assign voIsDelete = true>
    </#if>
</#list>
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.${moduleName}.data.dto.${packagePath}.${apiName?cap_first}Dto;
import com.api.${moduleName}.data.entity.${tableName?cap_first};
import com.api.${moduleName}.data.po.${tableName?cap_first}Po;
import com.api.${moduleName}.dao.${tableName?cap_first}Dao;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
<#if entityHasIsDelete>
    import com.api.common.enums.IsDeleteEnum;
</#if>
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        <#if dtoHasId == true>
        ${tableName?cap_first} entity = ${tableName}Dao.get(${tableName?cap_first}Po.builder()
                                                .whereId(dto.getId())
            <#if entityHasIsDelete == true>
                                                .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
            </#if>
                                                .build());

        if (entity == null) {
            throw new ServerException("${subModuleAnnotation}不存在");
        }

            <#if dtoHasUpdatedAt>
        if (StrUtil.isNotBlank(dto.getUpdatedAt()) && !dto.getUpdatedAt().equals(entity.getUpdatedAt())) {
            throw new ServerException("${subModuleAnnotation}信息已被其他用户修改");
        }
            </#if>
        </#if>
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(${apiName?cap_first}Dto dto) {
        // 构建删除对象
        ${tableName?cap_first}Po po = handleRemoveData(dto);
        <#if apiType == "remove">
        if (${tableName}Dao.update(po) == 0) {
            throw new ServerException("删除失败");
        }
        </#if>
        <#if apiType == "delete">
        if (${tableName}Dao.delete(po) == 0) {
            throw new ServerException("删除失败");
        }
        </#if>
        return Result.ok("删除成功");
    }

    /**
     * 处理要删除的数据
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected ${tableName?cap_first}Po handleRemoveData(${apiName?cap_first}Dto dto){
        ${tableName?cap_first}Po po = new ${tableName?cap_first}Po();
        <#list dtoFields as field>
            <#if field.fieldName != "updatedAt">
                <#if field.removeWhere?? == true>
        po.setWhere${field.fieldName?cap_first}(dto.get${field.fieldName?cap_first}());
                </#if>
            </#if>
        </#list>
        <#if entityHasUpdatedUserId>
        po.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        </#if>
        <#if entityHasIsDelete>
        po.setIsDelete(IsDeleteEnum.DELETED.getCode());
        </#if>
        return po;
    }
}
