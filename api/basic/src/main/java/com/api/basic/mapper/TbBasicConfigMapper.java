package com.api.basic.mapper;

import com.api.basic.data.entity.TbBasicConfig;
import com.api.basic.data.po.TbBasicConfigPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.math.BigDecimal;

/**
 * 配置管理Mapper
 *
 * @author 裴金伟
 */
@Repository
@Mapper
public interface TbBasicConfigMapper {

    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicConfig entity);

    /**
     * 删除信息
     *
     * @param po 要删除的信息
     * @return 受影响的行数，即删除的数据数量
     */
    public Integer delete(TbBasicConfigPo po);

    /**
     * 更新信息
     *
     * @param po 要更新的信息
     * @return 受影响的行数，即更新的数据数量
     */
    public Integer update(TbBasicConfigPo po);

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<TbBasicConfig> query(TbBasicConfigPo po);

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    public Integer cnt(TbBasicConfigPo po);

    /**
     * 统计的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumId(TbBasicConfigPo po);

    /**
     * 统计的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxId(TbBasicConfigPo po);

    /**
     * 统计的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minId(TbBasicConfigPo po);

    /**
     * 统计的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgId(TbBasicConfigPo po);
    /**
     * 统计配置分组ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumGroupId(TbBasicConfigPo po);

    /**
     * 统计配置分组ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxGroupId(TbBasicConfigPo po);

    /**
     * 统计配置分组ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minGroupId(TbBasicConfigPo po);

    /**
     * 统计配置分组ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgGroupId(TbBasicConfigPo po);
    /**
     * 统计数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumDataType(TbBasicConfigPo po);

    /**
     * 统计数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxDataType(TbBasicConfigPo po);

    /**
     * 统计数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minDataType(TbBasicConfigPo po);

    /**
     * 统计数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgDataType(TbBasicConfigPo po);
    /**
     * 统计状态。1：启用，2：禁用的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumStatus(TbBasicConfigPo po);

    /**
     * 统计状态。1：启用，2：禁用的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxStatus(TbBasicConfigPo po);

    /**
     * 统计状态。1：启用，2：禁用的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minStatus(TbBasicConfigPo po);

    /**
     * 统计状态。1：启用，2：禁用的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgStatus(TbBasicConfigPo po);
    /**
     * 统计排序值的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumSort(TbBasicConfigPo po);

    /**
     * 统计排序值的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxSort(TbBasicConfigPo po);

    /**
     * 统计排序值的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minSort(TbBasicConfigPo po);

    /**
     * 统计排序值的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgSort(TbBasicConfigPo po);
    /**
     * 统计创建用户的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumCreatedUserId(TbBasicConfigPo po);

    /**
     * 统计创建用户的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxCreatedUserId(TbBasicConfigPo po);

    /**
     * 统计创建用户的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minCreatedUserId(TbBasicConfigPo po);

    /**
     * 统计创建用户的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgCreatedUserId(TbBasicConfigPo po);
    /**
     * 统计更新用户的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumUpdatedUserId(TbBasicConfigPo po);

    /**
     * 统计更新用户的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxUpdatedUserId(TbBasicConfigPo po);

    /**
     * 统计更新用户的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minUpdatedUserId(TbBasicConfigPo po);

    /**
     * 统计更新用户的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgUpdatedUserId(TbBasicConfigPo po);
}
