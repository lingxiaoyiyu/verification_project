package com.api.basic.mapper;

import com.api.basic.data.entity.TbBasicFeedback;
import com.api.basic.data.po.TbBasicFeedbackPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.math.BigDecimal;

/**
 * 问题反馈表Mapper
 *
 * @author 裴金伟
 */
@Repository
@Mapper
public interface TbBasicFeedbackMapper {

    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicFeedback entity);

    /**
     * 删除信息
     *
     * @param po 要删除的信息
     * @return 受影响的行数，即删除的数据数量
     */
    public Integer delete(TbBasicFeedbackPo po);

    /**
     * 更新信息
     *
     * @param po 要更新的信息
     * @return 受影响的行数，即更新的数据数量
     */
    public Integer update(TbBasicFeedbackPo po);

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<TbBasicFeedback> query(TbBasicFeedbackPo po);

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    public Integer cnt(TbBasicFeedbackPo po);

    /**
     * 统计ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumId(TbBasicFeedbackPo po);

    /**
     * 统计ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxId(TbBasicFeedbackPo po);

    /**
     * 统计ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minId(TbBasicFeedbackPo po);

    /**
     * 统计ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgId(TbBasicFeedbackPo po);
    /**
     * 统计反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumType(TbBasicFeedbackPo po);

    /**
     * 统计反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxType(TbBasicFeedbackPo po);

    /**
     * 统计反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minType(TbBasicFeedbackPo po);

    /**
     * 统计反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgType(TbBasicFeedbackPo po);
    /**
     * 统计处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumStatus(TbBasicFeedbackPo po);

    /**
     * 统计处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxStatus(TbBasicFeedbackPo po);

    /**
     * 统计处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minStatus(TbBasicFeedbackPo po);

    /**
     * 统计处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgStatus(TbBasicFeedbackPo po);
    /**
     * 统计是否已删除。1：未删除，2：已删除的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumIsDelete(TbBasicFeedbackPo po);

    /**
     * 统计是否已删除。1：未删除，2：已删除的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxIsDelete(TbBasicFeedbackPo po);

    /**
     * 统计是否已删除。1：未删除，2：已删除的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minIsDelete(TbBasicFeedbackPo po);

    /**
     * 统计是否已删除。1：未删除，2：已删除的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgIsDelete(TbBasicFeedbackPo po);
    /**
     * 统计创建者用户ID（可为空，允许匿名反馈）的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumCreatedUserId(TbBasicFeedbackPo po);

    /**
     * 统计创建者用户ID（可为空，允许匿名反馈）的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxCreatedUserId(TbBasicFeedbackPo po);

    /**
     * 统计创建者用户ID（可为空，允许匿名反馈）的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minCreatedUserId(TbBasicFeedbackPo po);

    /**
     * 统计创建者用户ID（可为空，允许匿名反馈）的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgCreatedUserId(TbBasicFeedbackPo po);
    /**
     * 统计更新者用户ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumUpdatedUserId(TbBasicFeedbackPo po);

    /**
     * 统计更新者用户ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxUpdatedUserId(TbBasicFeedbackPo po);

    /**
     * 统计更新者用户ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minUpdatedUserId(TbBasicFeedbackPo po);

    /**
     * 统计更新者用户ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgUpdatedUserId(TbBasicFeedbackPo po);
}
