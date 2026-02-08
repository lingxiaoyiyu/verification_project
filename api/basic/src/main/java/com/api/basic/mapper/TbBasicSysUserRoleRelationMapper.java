package com.api.basic.mapper;

import com.api.basic.data.entity.TbBasicSysUserRoleRelation;
import com.api.basic.data.po.TbBasicSysUserRoleRelationPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.math.BigDecimal;

/**
 * 用户-角色关联信息表Mapper
 *
 * @author 裴金伟
 */
@Repository
@Mapper
public interface TbBasicSysUserRoleRelationMapper {

    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicSysUserRoleRelation entity);

    /**
     * 删除信息
     *
     * @param po 要删除的信息
     * @return 受影响的行数，即删除的数据数量
     */
    public Integer delete(TbBasicSysUserRoleRelationPo po);

    /**
     * 更新信息
     *
     * @param po 要更新的信息
     * @return 受影响的行数，即更新的数据数量
     */
    public Integer update(TbBasicSysUserRoleRelationPo po);

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<TbBasicSysUserRoleRelation> query(TbBasicSysUserRoleRelationPo po);

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    public Integer cnt(TbBasicSysUserRoleRelationPo po);

    /**
     * 统计ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumId(TbBasicSysUserRoleRelationPo po);

    /**
     * 统计ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxId(TbBasicSysUserRoleRelationPo po);

    /**
     * 统计ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minId(TbBasicSysUserRoleRelationPo po);

    /**
     * 统计ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgId(TbBasicSysUserRoleRelationPo po);
    /**
     * 统计用户id的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumUserId(TbBasicSysUserRoleRelationPo po);

    /**
     * 统计用户id的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxUserId(TbBasicSysUserRoleRelationPo po);

    /**
     * 统计用户id的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minUserId(TbBasicSysUserRoleRelationPo po);

    /**
     * 统计用户id的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgUserId(TbBasicSysUserRoleRelationPo po);
    /**
     * 统计角色ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumRoleId(TbBasicSysUserRoleRelationPo po);

    /**
     * 统计角色ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxRoleId(TbBasicSysUserRoleRelationPo po);

    /**
     * 统计角色ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minRoleId(TbBasicSysUserRoleRelationPo po);

    /**
     * 统计角色ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgRoleId(TbBasicSysUserRoleRelationPo po);
    /**
     * 统计是否已删除。1：未删除，2：已删除的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumIsDelete(TbBasicSysUserRoleRelationPo po);

    /**
     * 统计是否已删除。1：未删除，2：已删除的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxIsDelete(TbBasicSysUserRoleRelationPo po);

    /**
     * 统计是否已删除。1：未删除，2：已删除的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minIsDelete(TbBasicSysUserRoleRelationPo po);

    /**
     * 统计是否已删除。1：未删除，2：已删除的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgIsDelete(TbBasicSysUserRoleRelationPo po);
    /**
     * 统计创建者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumCreatedUserId(TbBasicSysUserRoleRelationPo po);

    /**
     * 统计创建者的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxCreatedUserId(TbBasicSysUserRoleRelationPo po);

    /**
     * 统计创建者的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minCreatedUserId(TbBasicSysUserRoleRelationPo po);

    /**
     * 统计创建者的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgCreatedUserId(TbBasicSysUserRoleRelationPo po);
    /**
     * 统计更新者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumUpdatedUserId(TbBasicSysUserRoleRelationPo po);

    /**
     * 统计更新者的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxUpdatedUserId(TbBasicSysUserRoleRelationPo po);

    /**
     * 统计更新者的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minUpdatedUserId(TbBasicSysUserRoleRelationPo po);

    /**
     * 统计更新者的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgUpdatedUserId(TbBasicSysUserRoleRelationPo po);
}
