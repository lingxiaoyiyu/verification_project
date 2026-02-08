package com.api.basic.mapper;

import com.api.basic.data.entity.TbBasicConfigGroup;
import com.api.basic.data.po.TbBasicConfigGroupPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.math.BigDecimal;

/**
 * 配置分组管理Mapper
 *
 * @author 裴金伟
 */
@Repository
@Mapper
public interface TbBasicConfigGroupMapper {

    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicConfigGroup entity);

    /**
     * 删除信息
     *
     * @param po 要删除的信息
     * @return 受影响的行数，即删除的数据数量
     */
    public Integer delete(TbBasicConfigGroupPo po);

    /**
     * 更新信息
     *
     * @param po 要更新的信息
     * @return 受影响的行数，即更新的数据数量
     */
    public Integer update(TbBasicConfigGroupPo po);

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<TbBasicConfigGroup> query(TbBasicConfigGroupPo po);

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    public Integer cnt(TbBasicConfigGroupPo po);

    /**
     * 统计的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumId(TbBasicConfigGroupPo po);

    /**
     * 统计的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxId(TbBasicConfigGroupPo po);

    /**
     * 统计的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minId(TbBasicConfigGroupPo po);

    /**
     * 统计的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgId(TbBasicConfigGroupPo po);
    /**
     * 统计创建用户的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumCreatedUserId(TbBasicConfigGroupPo po);

    /**
     * 统计创建用户的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxCreatedUserId(TbBasicConfigGroupPo po);

    /**
     * 统计创建用户的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minCreatedUserId(TbBasicConfigGroupPo po);

    /**
     * 统计创建用户的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgCreatedUserId(TbBasicConfigGroupPo po);
}
