package com.api.basic.mapper;

import com.api.basic.data.entity.TbBasicSysRoleMenuRelation;
import com.api.basic.data.po.TbBasicSysRoleMenuRelationPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.math.BigDecimal;

/**
 * 角色-菜单关联信息表Mapper
 *
 * @author 裴金伟
 */
@Repository
@Mapper
public interface TbBasicSysRoleMenuRelationMapper {

    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicSysRoleMenuRelation entity);

    /**
     * 删除信息
     *
     * @param po 要删除的信息
     * @return 受影响的行数，即删除的数据数量
     */
    public Integer delete(TbBasicSysRoleMenuRelationPo po);

    /**
     * 更新信息
     *
     * @param po 要更新的信息
     * @return 受影响的行数，即更新的数据数量
     */
    public Integer update(TbBasicSysRoleMenuRelationPo po);

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<TbBasicSysRoleMenuRelation> query(TbBasicSysRoleMenuRelationPo po);

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    public Integer cnt(TbBasicSysRoleMenuRelationPo po);

    /**
     * 统计ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumId(TbBasicSysRoleMenuRelationPo po);

    /**
     * 统计ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxId(TbBasicSysRoleMenuRelationPo po);

    /**
     * 统计ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minId(TbBasicSysRoleMenuRelationPo po);

    /**
     * 统计ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgId(TbBasicSysRoleMenuRelationPo po);
    /**
     * 统计角色ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumRoleId(TbBasicSysRoleMenuRelationPo po);

    /**
     * 统计角色ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxRoleId(TbBasicSysRoleMenuRelationPo po);

    /**
     * 统计角色ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minRoleId(TbBasicSysRoleMenuRelationPo po);

    /**
     * 统计角色ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgRoleId(TbBasicSysRoleMenuRelationPo po);
    /**
     * 统计菜单ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumMenuId(TbBasicSysRoleMenuRelationPo po);

    /**
     * 统计菜单ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxMenuId(TbBasicSysRoleMenuRelationPo po);

    /**
     * 统计菜单ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minMenuId(TbBasicSysRoleMenuRelationPo po);

    /**
     * 统计菜单ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgMenuId(TbBasicSysRoleMenuRelationPo po);
    /**
     * 统计是否已删除。1：未删除，2：已删除的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumIsDelete(TbBasicSysRoleMenuRelationPo po);

    /**
     * 统计是否已删除。1：未删除，2：已删除的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxIsDelete(TbBasicSysRoleMenuRelationPo po);

    /**
     * 统计是否已删除。1：未删除，2：已删除的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minIsDelete(TbBasicSysRoleMenuRelationPo po);

    /**
     * 统计是否已删除。1：未删除，2：已删除的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgIsDelete(TbBasicSysRoleMenuRelationPo po);
    /**
     * 统计创建者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumCreatedUserId(TbBasicSysRoleMenuRelationPo po);

    /**
     * 统计创建者的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxCreatedUserId(TbBasicSysRoleMenuRelationPo po);

    /**
     * 统计创建者的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minCreatedUserId(TbBasicSysRoleMenuRelationPo po);

    /**
     * 统计创建者的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgCreatedUserId(TbBasicSysRoleMenuRelationPo po);
    /**
     * 统计更新者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumUpdatedUserId(TbBasicSysRoleMenuRelationPo po);

    /**
     * 统计更新者的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxUpdatedUserId(TbBasicSysRoleMenuRelationPo po);

    /**
     * 统计更新者的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minUpdatedUserId(TbBasicSysRoleMenuRelationPo po);

    /**
     * 统计更新者的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgUpdatedUserId(TbBasicSysRoleMenuRelationPo po);
}
