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
import cn.hutool.core.util.StrUtil;
<#if voHasCreatedUserName || voHasUpdatedUserName>
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.po.TbBasicSysUserPo;
</#if>
<#if voIsDelete>
import com.api.common.enums.IsDeleteEnum;
</#if>
import com.api.${moduleName}.data.dto.${packagePath}.${apiName?cap_first}Dto;
import com.api.${moduleName}.data.entity.${tableName?cap_first};
import com.api.${moduleName}.data.po.${tableName?cap_first}Po;
import com.api.${moduleName}.dao.${tableName?cap_first}Dao;
import com.api.${moduleName}.data.vo.${packagePath}.${apiName?cap_first}ItemVo;
import com.api.common.base.AbstractService;
import com.api.common.base.data.po.BasePageSortList;
import com.api.common.base.data.vo.BasePageVo;
import com.api.common.base.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import java.util.ArrayList;
import java.util.List;
<#if voHasCreatedUserName || voHasUpdatedUserName>
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
    private final ${tableName?cap_first}Dao ${tableName}Dao;

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
        ${tableName?cap_first}Po queryPo = handleQueryData(dto);
        List<${tableName?cap_first}> entityList = ${tableName}Dao.query(queryPo);
        List<${apiName?cap_first}ItemVo> voList = new ArrayList<>();
        if (entityList != null) {
            <#if voHasCreatedUserName || voHasUpdatedUserName>
            List<Integer> userIds = entityList.stream()
                .flatMap(entity -> Stream.of(
                    <#if voHasCreatedUserName && !voHasUpdatedUserName>
                    entity.getCreatedUserId()
                    </#if>
                    <#if voHasUpdatedUserName && !voHasCreatedUserName>
                    entity.getUpdatedUserId()
                    </#if>
                    <#if voHasCreatedUserName && voHasUpdatedUserName>
                    entity.getCreatedUserId(),
                    entity.getUpdatedUserId()
                    </#if>
                ))
                .distinct()
                .toList();
            Map<Integer, String> userNameMap = tbBasicSysUserDao.query(TbBasicSysUserPo.builder().whereInIds(userIds).build())
                .stream()
                .collect(Collectors.toMap(TbBasicSysUser::getId, TbBasicSysUser::getUsername));
            </#if>

            entityList.forEach(entity -> {
                ${apiName?cap_first}ItemVo vo = convertToVo(entity);
                <#if voHasCreatedUserName>
                vo.setCreatedUserName(userNameMap.get(entity.getCreatedUserId()));
                </#if>
                <#if voHasUpdatedUserName>
                vo.setUpdatedUserName(userNameMap.get(entity.getUpdatedUserId()));
                </#if>
                voList.add(vo);
            });
        }

        BasePageVo<${apiName?cap_first}ItemVo> basePageVo = new BasePageVo<>();
        basePageVo.setList(voList);
        basePageVo.setTotal(${tableName}Dao.cnt(queryPo)); // 统计总数
        return Result.ok(basePageVo);
    }

    /**
     * 处理要查询的条件
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected ${tableName?cap_first}Po handleQueryData(${apiName?cap_first}Dto dto){
        ${tableName?cap_first}Po po = new ${tableName?cap_first}Po();
        BeanUtils.copyProperties(dto, po);
        <#list dtoFields as field>
        po.setWhere${field.fieldName?cap_first}(dto.get${field.fieldName?cap_first}());
        </#list>
        <#if voIsDelete>
        po.setWhereIsDelete(IsDeleteEnum.NO_DELETE.getCode());
        </#if>

        <#if entityHasId>
        po.setPage(dto.getPage(), dto.getPageSize());
        po.setSortList(new BasePageSortList().addSort(StrUtil.isBlank(dto.getSortFiled()) ? "id" : dto.getSortFiled(),
        StrUtil.isBlank(dto.getSortType()) ? "DESC" : dto.getSortType()).getSortList());
        </#if>
        return po;
    }

    /**
     * entity转换成vo
     *
     * @param entity 转换前的Entity对象
     * @return 转换后的vo对象
     */
    private ${apiName?cap_first}ItemVo convertToVo(${tableName?cap_first} entity){
        ${apiName?cap_first}ItemVo vo = new ${apiName?cap_first}ItemVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
