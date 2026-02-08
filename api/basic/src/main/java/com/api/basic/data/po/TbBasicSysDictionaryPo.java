package com.api.basic.data.po;

import com.api.basic.data.entity.TbBasicSysDictionary;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 字典信息表条件实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicSysDictionaryPo extends TbBasicSysDictionary {

    public TbBasicSysDictionaryPo(){
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
    // 条件：父节点ID，等于
    private Integer whereParentId;
    // 条件：父节点ID，在列表中
    private List<Integer> whereInParentIds;
    // 条件：父节点ID，在列表中，or连接
    private List<Integer> whereInOrParentIds;
    // 排除条件：父节点ID
    private Integer whereNotParentId;
    // 条件：父节点ID，不在列表中
    private List<Integer> whereNotInParentIds;
    // 条件：父节点ID，为空
    private Boolean whereIsNullParentId;
    // 条件：父节点ID，不为空
    private Boolean whereIsNotNullParentId;
    // 条件：父节点ID，为空字符串
    private Boolean whereIsEmptyParentId;
    // 条件：父节点ID，不为空字符串
    private Boolean whereIsNotEmptyParentId;
    // 条件：父节点ID，大于
    private Integer whereGtParentId;
    // 条件：父节点ID，大于等于
    private Integer whereGteParentId;
    // 条件：父节点ID，小于
    private Integer whereLtParentId;
    // 条件：父节点ID，小于等于
    private Integer whereLteParentId;
    // 条件：父节点ID，开始范围
    private Integer whereStartParentId;
    // 条件：父节点ID，结束范围
    private Integer whereEndParentId;
    // 条件：字典名称，等于
    private String whereName;
    // 条件：字典名称，在列表中
    private List<String> whereInNames;
    // 条件：字典名称，在列表中，or连接
    private List<String> whereInOrNames;
    // 排除条件：字典名称
    private String whereNotName;
    // 条件：字典名称，不在列表中
    private List<String> whereNotInNames;
    // 条件：字典名称，为空
    private Boolean whereIsNullName;
    // 条件：字典名称，不为空
    private Boolean whereIsNotNullName;
    // 条件：字典名称，为空字符串
    private Boolean whereIsEmptyName;
    // 条件：字典名称，不为空字符串
    private Boolean whereIsNotEmptyName;
    // 条件：字典名称，大于
    private String whereGtName;
    // 条件：字典名称，大于等于
    private String whereGteName;
    // 条件：字典名称，小于
    private String whereLtName;
    // 条件：字典名称，小于等于
    private String whereLteName;
    // 条件：字典名称，模糊查询
    private String whereLikeName;
    // 条件：字典名称，开始范围
    private String whereStartName;
    // 条件：字典名称，结束范围
    private String whereEndName;
    // 条件：字典标识，等于
    private String whereIdentifying;
    // 条件：字典标识，在列表中
    private List<String> whereInIdentifyings;
    // 条件：字典标识，在列表中，or连接
    private List<String> whereInOrIdentifyings;
    // 排除条件：字典标识
    private String whereNotIdentifying;
    // 条件：字典标识，不在列表中
    private List<String> whereNotInIdentifyings;
    // 条件：字典标识，为空
    private Boolean whereIsNullIdentifying;
    // 条件：字典标识，不为空
    private Boolean whereIsNotNullIdentifying;
    // 条件：字典标识，为空字符串
    private Boolean whereIsEmptyIdentifying;
    // 条件：字典标识，不为空字符串
    private Boolean whereIsNotEmptyIdentifying;
    // 条件：字典标识，大于
    private String whereGtIdentifying;
    // 条件：字典标识，大于等于
    private String whereGteIdentifying;
    // 条件：字典标识，小于
    private String whereLtIdentifying;
    // 条件：字典标识，小于等于
    private String whereLteIdentifying;
    // 条件：字典标识，模糊查询
    private String whereLikeIdentifying;
    // 条件：字典标识，开始范围
    private String whereStartIdentifying;
    // 条件：字典标识，结束范围
    private String whereEndIdentifying;
    // 条件：排序ID，等于
    private Integer whereSort;
    // 条件：排序ID，在列表中
    private List<Integer> whereInSorts;
    // 条件：排序ID，在列表中，or连接
    private List<Integer> whereInOrSorts;
    // 排除条件：排序ID
    private Integer whereNotSort;
    // 条件：排序ID，不在列表中
    private List<Integer> whereNotInSorts;
    // 条件：排序ID，为空
    private Boolean whereIsNullSort;
    // 条件：排序ID，不为空
    private Boolean whereIsNotNullSort;
    // 条件：排序ID，为空字符串
    private Boolean whereIsEmptySort;
    // 条件：排序ID，不为空字符串
    private Boolean whereIsNotEmptySort;
    // 条件：排序ID，大于
    private Integer whereGtSort;
    // 条件：排序ID，大于等于
    private Integer whereGteSort;
    // 条件：排序ID，小于
    private Integer whereLtSort;
    // 条件：排序ID，小于等于
    private Integer whereLteSort;
    // 条件：排序ID，开始范围
    private Integer whereStartSort;
    // 条件：排序ID，结束范围
    private Integer whereEndSort;
    // 条件：描述，等于
    private String whereDescription;
    // 条件：描述，在列表中
    private List<String> whereInDescriptions;
    // 条件：描述，在列表中，or连接
    private List<String> whereInOrDescriptions;
    // 排除条件：描述
    private String whereNotDescription;
    // 条件：描述，不在列表中
    private List<String> whereNotInDescriptions;
    // 条件：描述，为空
    private Boolean whereIsNullDescription;
    // 条件：描述，不为空
    private Boolean whereIsNotNullDescription;
    // 条件：描述，为空字符串
    private Boolean whereIsEmptyDescription;
    // 条件：描述，不为空字符串
    private Boolean whereIsNotEmptyDescription;
    // 条件：描述，大于
    private String whereGtDescription;
    // 条件：描述，大于等于
    private String whereGteDescription;
    // 条件：描述，小于
    private String whereLtDescription;
    // 条件：描述，小于等于
    private String whereLteDescription;
    // 条件：描述，模糊查询
    private String whereLikeDescription;
    // 条件：描述，开始范围
    private String whereStartDescription;
    // 条件：描述，结束范围
    private String whereEndDescription;
    // 条件：状态。1：启用，2：禁用，等于
    private Integer whereStatus;
    // 条件：状态。1：启用，2：禁用，在列表中
    private List<Integer> whereInStatuss;
    // 条件：状态。1：启用，2：禁用，在列表中，or连接
    private List<Integer> whereInOrStatuss;
    // 排除条件：状态。1：启用，2：禁用
    private Integer whereNotStatus;
    // 条件：状态。1：启用，2：禁用，不在列表中
    private List<Integer> whereNotInStatuss;
    // 条件：状态。1：启用，2：禁用，为空
    private Boolean whereIsNullStatus;
    // 条件：状态。1：启用，2：禁用，不为空
    private Boolean whereIsNotNullStatus;
    // 条件：状态。1：启用，2：禁用，为空字符串
    private Boolean whereIsEmptyStatus;
    // 条件：状态。1：启用，2：禁用，不为空字符串
    private Boolean whereIsNotEmptyStatus;
    // 条件：状态。1：启用，2：禁用，大于
    private Integer whereGtStatus;
    // 条件：状态。1：启用，2：禁用，大于等于
    private Integer whereGteStatus;
    // 条件：状态。1：启用，2：禁用，小于
    private Integer whereLtStatus;
    // 条件：状态。1：启用，2：禁用，小于等于
    private Integer whereLteStatus;
    // 条件：状态。1：启用，2：禁用，开始范围
    private Integer whereStartStatus;
    // 条件：状态。1：启用，2：禁用，结束范围
    private Integer whereEndStatus;
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
    public TbBasicSysDictionaryPo addGroupByField(String field) {
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
    public TbBasicSysDictionaryPo setGroupByFields(List<String> groupByFields) {
        this.groupByFields = groupByFields;
        return this;
    }
}
