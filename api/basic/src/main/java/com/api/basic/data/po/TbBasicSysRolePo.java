package com.api.basic.data.po;

import com.api.basic.data.entity.TbBasicSysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息表条件实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicSysRolePo extends TbBasicSysRole {

    public TbBasicSysRolePo(){
        super();
    }

    // 条件：ID，等于
    private Integer whereId;
    // 条件：ID，在列表中
    private List<Integer> whereInIds;
    // 条件：ID，在列表中，or连接
    private List<Integer> whereInOrIds;
    // 排除条件：ID
    private Integer whereNotId;
    // 条件：ID，不在列表中
    private List<Integer> whereNotInIds;
    // 条件：ID，为空
    private Boolean whereIsNullId;
    // 条件：ID，不为空
    private Boolean whereIsNotNullId;
    // 条件：ID，为空字符串
    private Boolean whereIsEmptyId;
    // 条件：ID，不为空字符串
    private Boolean whereIsNotEmptyId;
    // 条件：ID，大于
    private Integer whereGtId;
    // 条件：ID，大于等于
    private Integer whereGteId;
    // 条件：ID，小于
    private Integer whereLtId;
    // 条件：ID，小于等于
    private Integer whereLteId;
    // 条件：ID，开始范围
    private Integer whereStartId;
    // 条件：ID，结束范围
    private Integer whereEndId;
    // 条件：名称，等于
    private String whereName;
    // 条件：名称，在列表中
    private List<String> whereInNames;
    // 条件：名称，在列表中，or连接
    private List<String> whereInOrNames;
    // 排除条件：名称
    private String whereNotName;
    // 条件：名称，不在列表中
    private List<String> whereNotInNames;
    // 条件：名称，为空
    private Boolean whereIsNullName;
    // 条件：名称，不为空
    private Boolean whereIsNotNullName;
    // 条件：名称，为空字符串
    private Boolean whereIsEmptyName;
    // 条件：名称，不为空字符串
    private Boolean whereIsNotEmptyName;
    // 条件：名称，大于
    private String whereGtName;
    // 条件：名称，大于等于
    private String whereGteName;
    // 条件：名称，小于
    private String whereLtName;
    // 条件：名称，小于等于
    private String whereLteName;
    // 条件：名称，模糊查询
    private String whereLikeName;
    // 条件：名称，开始范围
    private String whereStartName;
    // 条件：名称，结束范围
    private String whereEndName;
    // 条件：角色标识，等于
    private String whereIdentifying;
    // 条件：角色标识，在列表中
    private List<String> whereInIdentifyings;
    // 条件：角色标识，在列表中，or连接
    private List<String> whereInOrIdentifyings;
    // 排除条件：角色标识
    private String whereNotIdentifying;
    // 条件：角色标识，不在列表中
    private List<String> whereNotInIdentifyings;
    // 条件：角色标识，为空
    private Boolean whereIsNullIdentifying;
    // 条件：角色标识，不为空
    private Boolean whereIsNotNullIdentifying;
    // 条件：角色标识，为空字符串
    private Boolean whereIsEmptyIdentifying;
    // 条件：角色标识，不为空字符串
    private Boolean whereIsNotEmptyIdentifying;
    // 条件：角色标识，大于
    private String whereGtIdentifying;
    // 条件：角色标识，大于等于
    private String whereGteIdentifying;
    // 条件：角色标识，小于
    private String whereLtIdentifying;
    // 条件：角色标识，小于等于
    private String whereLteIdentifying;
    // 条件：角色标识，模糊查询
    private String whereLikeIdentifying;
    // 条件：角色标识，开始范围
    private String whereStartIdentifying;
    // 条件：角色标识，结束范围
    private String whereEndIdentifying;
    // 条件：备注，等于
    private String whereRemark;
    // 条件：备注，在列表中
    private List<String> whereInRemarks;
    // 条件：备注，在列表中，or连接
    private List<String> whereInOrRemarks;
    // 排除条件：备注
    private String whereNotRemark;
    // 条件：备注，不在列表中
    private List<String> whereNotInRemarks;
    // 条件：备注，为空
    private Boolean whereIsNullRemark;
    // 条件：备注，不为空
    private Boolean whereIsNotNullRemark;
    // 条件：备注，为空字符串
    private Boolean whereIsEmptyRemark;
    // 条件：备注，不为空字符串
    private Boolean whereIsNotEmptyRemark;
    // 条件：备注，大于
    private String whereGtRemark;
    // 条件：备注，大于等于
    private String whereGteRemark;
    // 条件：备注，小于
    private String whereLtRemark;
    // 条件：备注，小于等于
    private String whereLteRemark;
    // 条件：备注，模糊查询
    private String whereLikeRemark;
    // 条件：备注，开始范围
    private String whereStartRemark;
    // 条件：备注，结束范围
    private String whereEndRemark;
    // 条件：状态。1：正常，2：禁用，等于
    private Integer whereStatus;
    // 条件：状态。1：正常，2：禁用，在列表中
    private List<Integer> whereInStatuss;
    // 条件：状态。1：正常，2：禁用，在列表中，or连接
    private List<Integer> whereInOrStatuss;
    // 排除条件：状态。1：正常，2：禁用
    private Integer whereNotStatus;
    // 条件：状态。1：正常，2：禁用，不在列表中
    private List<Integer> whereNotInStatuss;
    // 条件：状态。1：正常，2：禁用，为空
    private Boolean whereIsNullStatus;
    // 条件：状态。1：正常，2：禁用，不为空
    private Boolean whereIsNotNullStatus;
    // 条件：状态。1：正常，2：禁用，为空字符串
    private Boolean whereIsEmptyStatus;
    // 条件：状态。1：正常，2：禁用，不为空字符串
    private Boolean whereIsNotEmptyStatus;
    // 条件：状态。1：正常，2：禁用，大于
    private Integer whereGtStatus;
    // 条件：状态。1：正常，2：禁用，大于等于
    private Integer whereGteStatus;
    // 条件：状态。1：正常，2：禁用，小于
    private Integer whereLtStatus;
    // 条件：状态。1：正常，2：禁用，小于等于
    private Integer whereLteStatus;
    // 条件：状态。1：正常，2：禁用，开始范围
    private Integer whereStartStatus;
    // 条件：状态。1：正常，2：禁用，结束范围
    private Integer whereEndStatus;
    // 条件：是否已删除。1：未删除，2：已删除，等于
    private Integer whereIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，在列表中
    private List<Integer> whereInIsDeletes;
    // 条件：是否已删除。1：未删除，2：已删除，在列表中，or连接
    private List<Integer> whereInOrIsDeletes;
    // 排除条件：是否已删除。1：未删除，2：已删除
    private Integer whereNotIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，不在列表中
    private List<Integer> whereNotInIsDeletes;
    // 条件：是否已删除。1：未删除，2：已删除，为空
    private Boolean whereIsNullIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，不为空
    private Boolean whereIsNotNullIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，为空字符串
    private Boolean whereIsEmptyIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，不为空字符串
    private Boolean whereIsNotEmptyIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，大于
    private Integer whereGtIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，大于等于
    private Integer whereGteIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，小于
    private Integer whereLtIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，小于等于
    private Integer whereLteIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，开始范围
    private Integer whereStartIsDelete;
    // 条件：是否已删除。1：未删除，2：已删除，结束范围
    private Integer whereEndIsDelete;
    // 条件：创建时间，等于
    private String whereCreatedAt;
    // 条件：创建时间，在列表中
    private List<String> whereInCreatedAts;
    // 条件：创建时间，在列表中，or连接
    private List<String> whereInOrCreatedAts;
    // 排除条件：创建时间
    private String whereNotCreatedAt;
    // 条件：创建时间，不在列表中
    private List<String> whereNotInCreatedAts;
    // 条件：创建时间，为空
    private Boolean whereIsNullCreatedAt;
    // 条件：创建时间，不为空
    private Boolean whereIsNotNullCreatedAt;
    // 条件：创建时间，为空字符串
    private Boolean whereIsEmptyCreatedAt;
    // 条件：创建时间，不为空字符串
    private Boolean whereIsNotEmptyCreatedAt;
    // 条件：创建时间，大于
    private String whereGtCreatedAt;
    // 条件：创建时间，大于等于
    private String whereGteCreatedAt;
    // 条件：创建时间，小于
    private String whereLtCreatedAt;
    // 条件：创建时间，小于等于
    private String whereLteCreatedAt;
    // 条件：创建时间，开始范围
    private String whereStartCreatedAt;
    // 条件：创建时间，结束范围
    private String whereEndCreatedAt;
    // 条件：创建者，等于
    private Integer whereCreatedUserId;
    // 条件：创建者，在列表中
    private List<Integer> whereInCreatedUserIds;
    // 条件：创建者，在列表中，or连接
    private List<Integer> whereInOrCreatedUserIds;
    // 排除条件：创建者
    private Integer whereNotCreatedUserId;
    // 条件：创建者，不在列表中
    private List<Integer> whereNotInCreatedUserIds;
    // 条件：创建者，为空
    private Boolean whereIsNullCreatedUserId;
    // 条件：创建者，不为空
    private Boolean whereIsNotNullCreatedUserId;
    // 条件：创建者，为空字符串
    private Boolean whereIsEmptyCreatedUserId;
    // 条件：创建者，不为空字符串
    private Boolean whereIsNotEmptyCreatedUserId;
    // 条件：创建者，大于
    private Integer whereGtCreatedUserId;
    // 条件：创建者，大于等于
    private Integer whereGteCreatedUserId;
    // 条件：创建者，小于
    private Integer whereLtCreatedUserId;
    // 条件：创建者，小于等于
    private Integer whereLteCreatedUserId;
    // 条件：创建者，开始范围
    private Integer whereStartCreatedUserId;
    // 条件：创建者，结束范围
    private Integer whereEndCreatedUserId;
    // 条件：更新时间，等于
    private String whereUpdatedAt;
    // 条件：更新时间，在列表中
    private List<String> whereInUpdatedAts;
    // 条件：更新时间，在列表中，or连接
    private List<String> whereInOrUpdatedAts;
    // 排除条件：更新时间
    private String whereNotUpdatedAt;
    // 条件：更新时间，不在列表中
    private List<String> whereNotInUpdatedAts;
    // 条件：更新时间，为空
    private Boolean whereIsNullUpdatedAt;
    // 条件：更新时间，不为空
    private Boolean whereIsNotNullUpdatedAt;
    // 条件：更新时间，为空字符串
    private Boolean whereIsEmptyUpdatedAt;
    // 条件：更新时间，不为空字符串
    private Boolean whereIsNotEmptyUpdatedAt;
    // 条件：更新时间，大于
    private String whereGtUpdatedAt;
    // 条件：更新时间，大于等于
    private String whereGteUpdatedAt;
    // 条件：更新时间，小于
    private String whereLtUpdatedAt;
    // 条件：更新时间，小于等于
    private String whereLteUpdatedAt;
    // 条件：更新时间，开始范围
    private String whereStartUpdatedAt;
    // 条件：更新时间，结束范围
    private String whereEndUpdatedAt;
    // 条件：更新者，等于
    private Integer whereUpdatedUserId;
    // 条件：更新者，在列表中
    private List<Integer> whereInUpdatedUserIds;
    // 条件：更新者，在列表中，or连接
    private List<Integer> whereInOrUpdatedUserIds;
    // 排除条件：更新者
    private Integer whereNotUpdatedUserId;
    // 条件：更新者，不在列表中
    private List<Integer> whereNotInUpdatedUserIds;
    // 条件：更新者，为空
    private Boolean whereIsNullUpdatedUserId;
    // 条件：更新者，不为空
    private Boolean whereIsNotNullUpdatedUserId;
    // 条件：更新者，为空字符串
    private Boolean whereIsEmptyUpdatedUserId;
    // 条件：更新者，不为空字符串
    private Boolean whereIsNotEmptyUpdatedUserId;
    // 条件：更新者，大于
    private Integer whereGtUpdatedUserId;
    // 条件：更新者，大于等于
    private Integer whereGteUpdatedUserId;
    // 条件：更新者，小于
    private Integer whereLtUpdatedUserId;
    // 条件：更新者，小于等于
    private Integer whereLteUpdatedUserId;
    // 条件：更新者，开始范围
    private Integer whereStartUpdatedUserId;
    // 条件：更新者，结束范围
    private Integer whereEndUpdatedUserId;

    // 是否使用distinct
    private Boolean useDistinct;
    
    // group by字段列表
    private List<String> groupByFields;
    
    // having条件
    private String havingClause;
    
    /**
     * 添加group by字段
     * @param field 字段名称
     * @return 当前PO实例
     */
    public TbBasicSysRolePo addGroupByField(String field) {
        if (this.groupByFields == null) {
            this.groupByFields = new ArrayList<>();
        }
        this.groupByFields.add(field);
        return this;
    }
    
    /**
     * 设置group by字段列表
     * @param groupByFields 字段名称列表
     * @return 当前PO实例
     */
    public TbBasicSysRolePo setGroupByFields(List<String> groupByFields) {
        this.groupByFields = groupByFields;
        return this;
    }
}
