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
<#if voHasCreatedUserName || voHasUpdatedUserName>
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.po.TbBasicSysUserPo;
</#if>
<#if voIsDelete>
import com.api.common.enums.IsDeleteEnum;
</#if>
import com.api.${moduleName}.dao.${tableName?cap_first}Dao;
import com.api.${moduleName}.data.dto.${packagePath}.${apiName?cap_first}Dto;
import com.api.${moduleName}.data.entity.${tableName?cap_first};
import com.api.${moduleName}.data.po.${tableName?cap_first}Po;
import com.api.${moduleName}.data.vo.${packagePath}.${apiName?cap_first}Vo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
<#if voHasCreatedUserName || voHasUpdatedUserName>
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
</#if>

/**
 * ${apiComment}
 *
 * @author ${author}
 */
@Service("${moduleName?cap_first}${serviceName?cap_first}ServiceImpl")
@RequiredArgsConstructor
public class ${apiName?cap_first} extends AbstractService {

    <#if voHasCreatedUserName || voHasUpdatedUserName>
    protected final TbBasicSysUserDao tbBasicSysUserDao;
    </#if>
    protected final ${tableName?cap_first}Dao ${tableName}Dao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(${apiName?cap_first}Dto dto) {
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    public Result<?> service(${apiName?cap_first}Dto dto) {
        ${tableName?cap_first} entity = ${tableName}Dao.get(${tableName?cap_first}Po.builder()
            <#if entityHasIsDelete>
            .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
            </#if>
            <#list dtoFields as field>
            .where${field.fieldName?cap_first}(dto.get${field.fieldName?cap_first}())
            </#list >
            .build());
        ${apiName?cap_first}Vo vo = new ${apiName?cap_first}Vo();
        if (entity != null) {
            BeanUtils.copyProperties(entity, vo);
            <#if voHasCreatedUserName || voHasUpdatedUserName>
            List<Integer> userIds = Arrays.asList(entity.getCreatedUserId(), entity.getUpdatedUserId());
            Map<Integer, String> userNameMap = tbBasicSysUserDao.query(TbBasicSysUserPo.builder().whereInIds(userIds).build())
                .stream()
                .collect(Collectors.toMap(TbBasicSysUser::getId, TbBasicSysUser::getUsername));
            </#if>
            <#if voHasUpdatedUserName>
            vo.setCreatedUserName(userNameMap.get(entity.getCreatedUserId()));
            </#if>
            <#if voHasUpdatedUserName>
            vo.setUpdatedUserName(userNameMap.get(entity.getUpdatedUserId()));
            </#if>
        }

        return Result.ok(vo);
    }
}
