package com.api.basic.mapper;

import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.po.TbBasicSysUserPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.math.BigDecimal;

/**
 * 用户登录信息表Mapper
 *
 * @author 裴金伟
 */
@Repository
@Mapper
public interface TbBasicSysUserMapper {

    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicSysUser entity);

    /**
     * 删除信息
     *
     * @param po 要删除的信息
     * @return 受影响的行数，即删除的数据数量
     */
    public Integer delete(TbBasicSysUserPo po);

    /**
     * 更新信息
     *
     * @param po 要更新的信息
     * @return 受影响的行数，即更新的数据数量
     */
    public Integer update(TbBasicSysUserPo po);

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<TbBasicSysUser> query(TbBasicSysUserPo po);

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    public Integer cnt(TbBasicSysUserPo po);

    /**
     * 统计ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumId(TbBasicSysUserPo po);

    /**
     * 统计ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxId(TbBasicSysUserPo po);

    /**
     * 统计ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minId(TbBasicSysUserPo po);

    /**
     * 统计ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgId(TbBasicSysUserPo po);
    /**
     * 统计邀请者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumInviter(TbBasicSysUserPo po);

    /**
     * 统计邀请者的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxInviter(TbBasicSysUserPo po);

    /**
     * 统计邀请者的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minInviter(TbBasicSysUserPo po);

    /**
     * 统计邀请者的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgInviter(TbBasicSysUserPo po);
    /**
     * 统计注册来源的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumSource(TbBasicSysUserPo po);

    /**
     * 统计注册来源的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxSource(TbBasicSysUserPo po);

    /**
     * 统计注册来源的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minSource(TbBasicSysUserPo po);

    /**
     * 统计注册来源的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgSource(TbBasicSysUserPo po);
    /**
     * 统计状态。1：正常，2:禁用，3：注销的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumStatus(TbBasicSysUserPo po);

    /**
     * 统计状态。1：正常，2:禁用，3：注销的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxStatus(TbBasicSysUserPo po);

    /**
     * 统计状态。1：正常，2:禁用，3：注销的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minStatus(TbBasicSysUserPo po);

    /**
     * 统计状态。1：正常，2:禁用，3：注销的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgStatus(TbBasicSysUserPo po);
    /**
     * 统计性别。1：男，2：女，3：保密的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumGender(TbBasicSysUserPo po);

    /**
     * 统计性别。1：男，2：女，3：保密的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxGender(TbBasicSysUserPo po);

    /**
     * 统计性别。1：男，2：女，3：保密的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minGender(TbBasicSysUserPo po);

    /**
     * 统计性别。1：男，2：女，3：保密的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgGender(TbBasicSysUserPo po);
    /**
     * 统计所属部门ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumDepartmentId(TbBasicSysUserPo po);

    /**
     * 统计所属部门ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxDepartmentId(TbBasicSysUserPo po);

    /**
     * 统计所属部门ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minDepartmentId(TbBasicSysUserPo po);

    /**
     * 统计所属部门ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgDepartmentId(TbBasicSysUserPo po);
    /**
     * 统计身高的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumHeight(TbBasicSysUserPo po);

    /**
     * 统计身高的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxHeight(TbBasicSysUserPo po);

    /**
     * 统计身高的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minHeight(TbBasicSysUserPo po);

    /**
     * 统计身高的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgHeight(TbBasicSysUserPo po);
    /**
     * 统计省的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumProvince(TbBasicSysUserPo po);

    /**
     * 统计省的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxProvince(TbBasicSysUserPo po);

    /**
     * 统计省的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minProvince(TbBasicSysUserPo po);

    /**
     * 统计省的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgProvince(TbBasicSysUserPo po);
    /**
     * 统计市的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumCity(TbBasicSysUserPo po);

    /**
     * 统计市的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxCity(TbBasicSysUserPo po);

    /**
     * 统计市的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minCity(TbBasicSysUserPo po);

    /**
     * 统计市的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgCity(TbBasicSysUserPo po);
    /**
     * 统计区县的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumDistrict(TbBasicSysUserPo po);

    /**
     * 统计区县的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxDistrict(TbBasicSysUserPo po);

    /**
     * 统计区县的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minDistrict(TbBasicSysUserPo po);

    /**
     * 统计区县的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgDistrict(TbBasicSysUserPo po);
    /**
     * 统计余额的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumBalance(TbBasicSysUserPo po);

    /**
     * 统计余额的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxBalance(TbBasicSysUserPo po);

    /**
     * 统计余额的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minBalance(TbBasicSysUserPo po);

    /**
     * 统计余额的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgBalance(TbBasicSysUserPo po);
    /**
     * 统计是否是VIP。1：是，2：否。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumIsVip(TbBasicSysUserPo po);

    /**
     * 统计是否是VIP。1：是，2：否。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxIsVip(TbBasicSysUserPo po);

    /**
     * 统计是否是VIP。1：是，2：否。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minIsVip(TbBasicSysUserPo po);

    /**
     * 统计是否是VIP。1：是，2：否。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgIsVip(TbBasicSysUserPo po);
    /**
     * 统计创建者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumCreatedUserId(TbBasicSysUserPo po);

    /**
     * 统计创建者的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxCreatedUserId(TbBasicSysUserPo po);

    /**
     * 统计创建者的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minCreatedUserId(TbBasicSysUserPo po);

    /**
     * 统计创建者的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgCreatedUserId(TbBasicSysUserPo po);
    /**
     * 统计最后更新者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumUpdatedUserId(TbBasicSysUserPo po);

    /**
     * 统计最后更新者的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxUpdatedUserId(TbBasicSysUserPo po);

    /**
     * 统计最后更新者的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minUpdatedUserId(TbBasicSysUserPo po);

    /**
     * 统计最后更新者的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgUpdatedUserId(TbBasicSysUserPo po);
}
