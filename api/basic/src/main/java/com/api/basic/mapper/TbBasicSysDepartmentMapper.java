package com.api.basic.mapper;

import com.api.basic.data.entity.TbBasicSysDepartment;
import com.api.basic.data.po.TbBasicSysDepartmentPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.math.BigDecimal;

/**
 * 单位信息表Mapper
 *
 * @author 裴金伟
 */
@Repository
@Mapper
public interface TbBasicSysDepartmentMapper {

    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicSysDepartment entity);

    /**
     * 删除信息
     *
     * @param po 要删除的信息
     * @return 受影响的行数，即删除的数据数量
     */
    public Integer delete(TbBasicSysDepartmentPo po);

    /**
     * 更新信息
     *
     * @param po 要更新的信息
     * @return 受影响的行数，即更新的数据数量
     */
    public Integer update(TbBasicSysDepartmentPo po);

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<TbBasicSysDepartment> query(TbBasicSysDepartmentPo po);

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    public Integer cnt(TbBasicSysDepartmentPo po);

    /**
     * 统计ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumId(TbBasicSysDepartmentPo po);

    /**
     * 统计ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxId(TbBasicSysDepartmentPo po);

    /**
     * 统计ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minId(TbBasicSysDepartmentPo po);

    /**
     * 统计ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgId(TbBasicSysDepartmentPo po);
    /**
     * 统计上级ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumParentId(TbBasicSysDepartmentPo po);

    /**
     * 统计上级ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxParentId(TbBasicSysDepartmentPo po);

    /**
     * 统计上级ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minParentId(TbBasicSysDepartmentPo po);

    /**
     * 统计上级ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgParentId(TbBasicSysDepartmentPo po);
    /**
     * 统计是否删除。1：未删除，2：已删除。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumIsDelete(TbBasicSysDepartmentPo po);

    /**
     * 统计是否删除。1：未删除，2：已删除。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxIsDelete(TbBasicSysDepartmentPo po);

    /**
     * 统计是否删除。1：未删除，2：已删除。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minIsDelete(TbBasicSysDepartmentPo po);

    /**
     * 统计是否删除。1：未删除，2：已删除。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgIsDelete(TbBasicSysDepartmentPo po);
    /**
     * 统计排序的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumSort(TbBasicSysDepartmentPo po);

    /**
     * 统计排序的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxSort(TbBasicSysDepartmentPo po);

    /**
     * 统计排序的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minSort(TbBasicSysDepartmentPo po);

    /**
     * 统计排序的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgSort(TbBasicSysDepartmentPo po);
    /**
     * 统计创建者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumCreatedUserId(TbBasicSysDepartmentPo po);

    /**
     * 统计创建者的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxCreatedUserId(TbBasicSysDepartmentPo po);

    /**
     * 统计创建者的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minCreatedUserId(TbBasicSysDepartmentPo po);

    /**
     * 统计创建者的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgCreatedUserId(TbBasicSysDepartmentPo po);
    /**
     * 统计更新者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumUpdatedUserId(TbBasicSysDepartmentPo po);

    /**
     * 统计更新者的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxUpdatedUserId(TbBasicSysDepartmentPo po);

    /**
     * 统计更新者的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minUpdatedUserId(TbBasicSysDepartmentPo po);

    /**
     * 统计更新者的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgUpdatedUserId(TbBasicSysDepartmentPo po);
}
