package com.api.basic.mapper;

import com.api.basic.data.entity.TbBasicTrackingPointRecord;
import com.api.basic.data.po.TbBasicTrackingPointRecordPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.math.BigDecimal;

/**
 * 埋点记录Mapper
 *
 * @author 裴金伟
 */
@Repository
@Mapper
public interface TbBasicTrackingPointRecordMapper {

    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicTrackingPointRecord entity);

    /**
     * 删除信息
     *
     * @param po 要删除的信息
     * @return 受影响的行数，即删除的数据数量
     */
    public Integer delete(TbBasicTrackingPointRecordPo po);

    /**
     * 更新信息
     *
     * @param po 要更新的信息
     * @return 受影响的行数，即更新的数据数量
     */
    public Integer update(TbBasicTrackingPointRecordPo po);

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<TbBasicTrackingPointRecord> query(TbBasicTrackingPointRecordPo po);

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    public Integer cnt(TbBasicTrackingPointRecordPo po);
}
