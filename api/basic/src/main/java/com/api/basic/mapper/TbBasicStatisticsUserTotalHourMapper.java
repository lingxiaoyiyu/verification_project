package com.api.basic.mapper;

import com.api.basic.data.entity.TbBasicStatisticsUserTotalHour;
import com.api.basic.data.po.TbBasicStatisticsUserTotalHourPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.math.BigDecimal;

/**
 * 用户每小时注册统计表Mapper
 *
 * @author 裴金伟
 */
@Repository
@Mapper
public interface TbBasicStatisticsUserTotalHourMapper {

    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicStatisticsUserTotalHour entity);

    /**
     * 删除信息
     *
     * @param po 要删除的信息
     * @return 受影响的行数，即删除的数据数量
     */
    public Integer delete(TbBasicStatisticsUserTotalHourPo po);

    /**
     * 更新信息
     *
     * @param po 要更新的信息
     * @return 受影响的行数，即更新的数据数量
     */
    public Integer update(TbBasicStatisticsUserTotalHourPo po);

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<TbBasicStatisticsUserTotalHour> query(TbBasicStatisticsUserTotalHourPo po);

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    public Integer cnt(TbBasicStatisticsUserTotalHourPo po);

    /**
     * 统计的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumId(TbBasicStatisticsUserTotalHourPo po);

    /**
     * 统计的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxId(TbBasicStatisticsUserTotalHourPo po);

    /**
     * 统计的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minId(TbBasicStatisticsUserTotalHourPo po);

    /**
     * 统计的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgId(TbBasicStatisticsUserTotalHourPo po);
    /**
     * 统计用户注册数的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumCnt(TbBasicStatisticsUserTotalHourPo po);

    /**
     * 统计用户注册数的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxCnt(TbBasicStatisticsUserTotalHourPo po);

    /**
     * 统计用户注册数的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minCnt(TbBasicStatisticsUserTotalHourPo po);

    /**
     * 统计用户注册数的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgCnt(TbBasicStatisticsUserTotalHourPo po);
}
