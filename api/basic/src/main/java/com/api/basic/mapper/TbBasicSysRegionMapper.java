package com.api.basic.mapper;

import com.api.basic.data.entity.TbBasicSysRegion;
import com.api.basic.data.po.TbBasicSysRegionPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.math.BigDecimal;

/**
 * 行政区域信息Mapper
 *
 * @author 裴金伟
 */
@Repository
@Mapper
public interface TbBasicSysRegionMapper {

    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicSysRegion entity);

    /**
     * 删除信息
     *
     * @param po 要删除的信息
     * @return 受影响的行数，即删除的数据数量
     */
    public Integer delete(TbBasicSysRegionPo po);

    /**
     * 更新信息
     *
     * @param po 要更新的信息
     * @return 受影响的行数，即更新的数据数量
     */
    public Integer update(TbBasicSysRegionPo po);

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<TbBasicSysRegion> query(TbBasicSysRegionPo po);

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    public Integer cnt(TbBasicSysRegionPo po);

    /**
     * 统计ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumId(TbBasicSysRegionPo po);

    /**
     * 统计ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxId(TbBasicSysRegionPo po);

    /**
     * 统计ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minId(TbBasicSysRegionPo po);

    /**
     * 统计ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgId(TbBasicSysRegionPo po);
    /**
     * 统计上一级的id值的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumParentCode(TbBasicSysRegionPo po);

    /**
     * 统计上一级的id值的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxParentCode(TbBasicSysRegionPo po);

    /**
     * 统计上一级的id值的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minParentCode(TbBasicSysRegionPo po);

    /**
     * 统计上一级的id值的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgParentCode(TbBasicSysRegionPo po);
    /**
     * 统计行政编码的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumCode(TbBasicSysRegionPo po);

    /**
     * 统计行政编码的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxCode(TbBasicSysRegionPo po);

    /**
     * 统计行政编码的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minCode(TbBasicSysRegionPo po);

    /**
     * 统计行政编码的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgCode(TbBasicSysRegionPo po);
    /**
     * 统计排序的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumSort(TbBasicSysRegionPo po);

    /**
     * 统计排序的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxSort(TbBasicSysRegionPo po);

    /**
     * 统计排序的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minSort(TbBasicSysRegionPo po);

    /**
     * 统计排序的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgSort(TbBasicSysRegionPo po);
    /**
     * 统计是否删除。1：未删除，2：已删除。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumIsDelete(TbBasicSysRegionPo po);

    /**
     * 统计是否删除。1：未删除，2：已删除。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxIsDelete(TbBasicSysRegionPo po);

    /**
     * 统计是否删除。1：未删除，2：已删除。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minIsDelete(TbBasicSysRegionPo po);

    /**
     * 统计是否删除。1：未删除，2：已删除。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgIsDelete(TbBasicSysRegionPo po);
    /**
     * 统计层级的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumLevel(TbBasicSysRegionPo po);

    /**
     * 统计层级的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxLevel(TbBasicSysRegionPo po);

    /**
     * 统计层级的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minLevel(TbBasicSysRegionPo po);

    /**
     * 统计层级的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgLevel(TbBasicSysRegionPo po);
    /**
     * 统计创建者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumCreatedUserId(TbBasicSysRegionPo po);

    /**
     * 统计创建者的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxCreatedUserId(TbBasicSysRegionPo po);

    /**
     * 统计创建者的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minCreatedUserId(TbBasicSysRegionPo po);

    /**
     * 统计创建者的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgCreatedUserId(TbBasicSysRegionPo po);
    /**
     * 统计更新者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumUpdatedUserId(TbBasicSysRegionPo po);

    /**
     * 统计更新者的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxUpdatedUserId(TbBasicSysRegionPo po);

    /**
     * 统计更新者的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minUpdatedUserId(TbBasicSysRegionPo po);

    /**
     * 统计更新者的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgUpdatedUserId(TbBasicSysRegionPo po);
}
