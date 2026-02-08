package com.api.basic.mapper;

import com.api.basic.data.entity.TbBasicWorkbenchHeader;
import com.api.basic.data.po.TbBasicWorkbenchHeaderPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.math.BigDecimal;

/**
 * 工作台头部项目信息表Mapper
 *
 * @author 裴金伟
 */
@Repository
@Mapper
public interface TbBasicWorkbenchHeaderMapper {

    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicWorkbenchHeader entity);

    /**
     * 删除信息
     *
     * @param po 要删除的信息
     * @return 受影响的行数，即删除的数据数量
     */
    public Integer delete(TbBasicWorkbenchHeaderPo po);

    /**
     * 更新信息
     *
     * @param po 要更新的信息
     * @return 受影响的行数，即更新的数据数量
     */
    public Integer update(TbBasicWorkbenchHeaderPo po);

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<TbBasicWorkbenchHeader> query(TbBasicWorkbenchHeaderPo po);

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    public Integer cnt(TbBasicWorkbenchHeaderPo po);

    /**
     * 统计ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumId(TbBasicWorkbenchHeaderPo po);

    /**
     * 统计ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxId(TbBasicWorkbenchHeaderPo po);

    /**
     * 统计ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minId(TbBasicWorkbenchHeaderPo po);

    /**
     * 统计ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgId(TbBasicWorkbenchHeaderPo po);
}
