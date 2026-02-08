package com.api.basic.mapper;

import com.api.basic.data.entity.TbBasicSysDictionary;
import com.api.basic.data.po.TbBasicSysDictionaryPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.math.BigDecimal;

/**
 * 字典信息表Mapper
 *
 * @author 裴金伟
 */
@Repository
@Mapper
public interface TbBasicSysDictionaryMapper {

    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicSysDictionary entity);

    /**
     * 删除信息
     *
     * @param po 要删除的信息
     * @return 受影响的行数，即删除的数据数量
     */
    public Integer delete(TbBasicSysDictionaryPo po);

    /**
     * 更新信息
     *
     * @param po 要更新的信息
     * @return 受影响的行数，即更新的数据数量
     */
    public Integer update(TbBasicSysDictionaryPo po);

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<TbBasicSysDictionary> query(TbBasicSysDictionaryPo po);

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    public Integer cnt(TbBasicSysDictionaryPo po);

    /**
     * 统计ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumId(TbBasicSysDictionaryPo po);

    /**
     * 统计ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxId(TbBasicSysDictionaryPo po);

    /**
     * 统计ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minId(TbBasicSysDictionaryPo po);

    /**
     * 统计ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgId(TbBasicSysDictionaryPo po);
    /**
     * 统计父节点ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumParentId(TbBasicSysDictionaryPo po);

    /**
     * 统计父节点ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxParentId(TbBasicSysDictionaryPo po);

    /**
     * 统计父节点ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minParentId(TbBasicSysDictionaryPo po);

    /**
     * 统计父节点ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgParentId(TbBasicSysDictionaryPo po);
    /**
     * 统计排序ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumSort(TbBasicSysDictionaryPo po);

    /**
     * 统计排序ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxSort(TbBasicSysDictionaryPo po);

    /**
     * 统计排序ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minSort(TbBasicSysDictionaryPo po);

    /**
     * 统计排序ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgSort(TbBasicSysDictionaryPo po);
    /**
     * 统计状态。1：启用，2：禁用的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumStatus(TbBasicSysDictionaryPo po);

    /**
     * 统计状态。1：启用，2：禁用的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxStatus(TbBasicSysDictionaryPo po);

    /**
     * 统计状态。1：启用，2：禁用的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minStatus(TbBasicSysDictionaryPo po);

    /**
     * 统计状态。1：启用，2：禁用的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgStatus(TbBasicSysDictionaryPo po);
    /**
     * 统计创建者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumCreatedUserId(TbBasicSysDictionaryPo po);

    /**
     * 统计创建者的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxCreatedUserId(TbBasicSysDictionaryPo po);

    /**
     * 统计创建者的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minCreatedUserId(TbBasicSysDictionaryPo po);

    /**
     * 统计创建者的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgCreatedUserId(TbBasicSysDictionaryPo po);
    /**
     * 统计更新者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumUpdatedUserId(TbBasicSysDictionaryPo po);

    /**
     * 统计更新者的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxUpdatedUserId(TbBasicSysDictionaryPo po);

    /**
     * 统计更新者的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minUpdatedUserId(TbBasicSysDictionaryPo po);

    /**
     * 统计更新者的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgUpdatedUserId(TbBasicSysDictionaryPo po);
}
