package com.api.basic.mapper;

import com.api.basic.data.entity.TbBasicStatisticsUserTotal;
import com.api.basic.data.po.TbBasicStatisticsUserTotalPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.math.BigDecimal;

/**
 * 用户每日注册统计表Mapper
 *
 * @author 裴金伟
 */
@Repository
@Mapper
public interface TbBasicStatisticsUserTotalMapper {

    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicStatisticsUserTotal entity);

    /**
     * 删除信息
     *
     * @param po 要删除的信息
     * @return 受影响的行数，即删除的数据数量
     */
    public Integer delete(TbBasicStatisticsUserTotalPo po);

    /**
     * 更新信息
     *
     * @param po 要更新的信息
     * @return 受影响的行数，即更新的数据数量
     */
    public Integer update(TbBasicStatisticsUserTotalPo po);

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<TbBasicStatisticsUserTotal> query(TbBasicStatisticsUserTotalPo po);

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    public Integer cnt(TbBasicStatisticsUserTotalPo po);

    /**
     * 统计的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumId(TbBasicStatisticsUserTotalPo po);

    /**
     * 统计的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxId(TbBasicStatisticsUserTotalPo po);

    /**
     * 统计的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minId(TbBasicStatisticsUserTotalPo po);

    /**
     * 统计的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgId(TbBasicStatisticsUserTotalPo po);
    /**
     * 统计新增用户数的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumCnt(TbBasicStatisticsUserTotalPo po);

    /**
     * 统计新增用户数的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxCnt(TbBasicStatisticsUserTotalPo po);

    /**
     * 统计新增用户数的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minCnt(TbBasicStatisticsUserTotalPo po);

    /**
     * 统计新增用户数的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgCnt(TbBasicStatisticsUserTotalPo po);
}
