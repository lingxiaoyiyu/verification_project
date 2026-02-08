package com.api.basic.data.po;

import com.api.basic.data.entity.TbBasicSysRegion;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 行政区域信息条件实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicSysRegionPo extends TbBasicSysRegion {

    public TbBasicSysRegionPo(){
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
    // 条件：上一级的id值，等于
    private Long whereParentCode;
    // 条件：上一级的id值，在列表中
    private List<Long> whereInParentCodes;
    // 条件：上一级的id值，在列表中，or连接
    private List<Long> whereInOrParentCodes;
    // 排除条件：上一级的id值
    private Long whereNotParentCode;
    // 条件：上一级的id值，不在列表中
    private List<Long> whereNotInParentCodes;
    // 条件：上一级的id值，为空
    private Boolean whereIsNullParentCode;
    // 条件：上一级的id值，不为空
    private Boolean whereIsNotNullParentCode;
    // 条件：上一级的id值，为空字符串
    private Boolean whereIsEmptyParentCode;
    // 条件：上一级的id值，不为空字符串
    private Boolean whereIsNotEmptyParentCode;
    // 条件：上一级的id值，大于
    private Long whereGtParentCode;
    // 条件：上一级的id值，大于等于
    private Long whereGteParentCode;
    // 条件：上一级的id值，小于
    private Long whereLtParentCode;
    // 条件：上一级的id值，小于等于
    private Long whereLteParentCode;
    // 条件：上一级的id值，开始范围
    private Long whereStartParentCode;
    // 条件：上一级的id值，结束范围
    private Long whereEndParentCode;
    // 条件：行政编码，等于
    private Long whereCode;
    // 条件：行政编码，在列表中
    private List<Long> whereInCodes;
    // 条件：行政编码，在列表中，or连接
    private List<Long> whereInOrCodes;
    // 排除条件：行政编码
    private Long whereNotCode;
    // 条件：行政编码，不在列表中
    private List<Long> whereNotInCodes;
    // 条件：行政编码，为空
    private Boolean whereIsNullCode;
    // 条件：行政编码，不为空
    private Boolean whereIsNotNullCode;
    // 条件：行政编码，为空字符串
    private Boolean whereIsEmptyCode;
    // 条件：行政编码，不为空字符串
    private Boolean whereIsNotEmptyCode;
    // 条件：行政编码，大于
    private Long whereGtCode;
    // 条件：行政编码，大于等于
    private Long whereGteCode;
    // 条件：行政编码，小于
    private Long whereLtCode;
    // 条件：行政编码，小于等于
    private Long whereLteCode;
    // 条件：行政编码，开始范围
    private Long whereStartCode;
    // 条件：行政编码，结束范围
    private Long whereEndCode;
    // 条件：地区名称，等于
    private String whereName;
    // 条件：地区名称，在列表中
    private List<String> whereInNames;
    // 条件：地区名称，在列表中，or连接
    private List<String> whereInOrNames;
    // 排除条件：地区名称
    private String whereNotName;
    // 条件：地区名称，不在列表中
    private List<String> whereNotInNames;
    // 条件：地区名称，为空
    private Boolean whereIsNullName;
    // 条件：地区名称，不为空
    private Boolean whereIsNotNullName;
    // 条件：地区名称，为空字符串
    private Boolean whereIsEmptyName;
    // 条件：地区名称，不为空字符串
    private Boolean whereIsNotEmptyName;
    // 条件：地区名称，大于
    private String whereGtName;
    // 条件：地区名称，大于等于
    private String whereGteName;
    // 条件：地区名称，小于
    private String whereLtName;
    // 条件：地区名称，小于等于
    private String whereLteName;
    // 条件：地区名称，模糊查询
    private String whereLikeName;
    // 条件：地区名称，开始范围
    private String whereStartName;
    // 条件：地区名称，结束范围
    private String whereEndName;
    // 条件：排序，等于
    private Integer whereSort;
    // 条件：排序，在列表中
    private List<Integer> whereInSorts;
    // 条件：排序，在列表中，or连接
    private List<Integer> whereInOrSorts;
    // 排除条件：排序
    private Integer whereNotSort;
    // 条件：排序，不在列表中
    private List<Integer> whereNotInSorts;
    // 条件：排序，为空
    private Boolean whereIsNullSort;
    // 条件：排序，不为空
    private Boolean whereIsNotNullSort;
    // 条件：排序，为空字符串
    private Boolean whereIsEmptySort;
    // 条件：排序，不为空字符串
    private Boolean whereIsNotEmptySort;
    // 条件：排序，大于
    private Integer whereGtSort;
    // 条件：排序，大于等于
    private Integer whereGteSort;
    // 条件：排序，小于
    private Integer whereLtSort;
    // 条件：排序，小于等于
    private Integer whereLteSort;
    // 条件：排序，开始范围
    private Integer whereStartSort;
    // 条件：排序，结束范围
    private Integer whereEndSort;
    // 条件：是否删除。1：未删除，2：已删除。，等于
    private Integer whereIsDelete;
    // 条件：是否删除。1：未删除，2：已删除。，在列表中
    private List<Integer> whereInIsDeletes;
    // 条件：是否删除。1：未删除，2：已删除。，在列表中，or连接
    private List<Integer> whereInOrIsDeletes;
    // 排除条件：是否删除。1：未删除，2：已删除。
    private Integer whereNotIsDelete;
    // 条件：是否删除。1：未删除，2：已删除。，不在列表中
    private List<Integer> whereNotInIsDeletes;
    // 条件：是否删除。1：未删除，2：已删除。，为空
    private Boolean whereIsNullIsDelete;
    // 条件：是否删除。1：未删除，2：已删除。，不为空
    private Boolean whereIsNotNullIsDelete;
    // 条件：是否删除。1：未删除，2：已删除。，为空字符串
    private Boolean whereIsEmptyIsDelete;
    // 条件：是否删除。1：未删除，2：已删除。，不为空字符串
    private Boolean whereIsNotEmptyIsDelete;
    // 条件：是否删除。1：未删除，2：已删除。，大于
    private Integer whereGtIsDelete;
    // 条件：是否删除。1：未删除，2：已删除。，大于等于
    private Integer whereGteIsDelete;
    // 条件：是否删除。1：未删除，2：已删除。，小于
    private Integer whereLtIsDelete;
    // 条件：是否删除。1：未删除，2：已删除。，小于等于
    private Integer whereLteIsDelete;
    // 条件：是否删除。1：未删除，2：已删除。，开始范围
    private Integer whereStartIsDelete;
    // 条件：是否删除。1：未删除，2：已删除。，结束范围
    private Integer whereEndIsDelete;
    // 条件：层级，等于
    private Integer whereLevel;
    // 条件：层级，在列表中
    private List<Integer> whereInLevels;
    // 条件：层级，在列表中，or连接
    private List<Integer> whereInOrLevels;
    // 排除条件：层级
    private Integer whereNotLevel;
    // 条件：层级，不在列表中
    private List<Integer> whereNotInLevels;
    // 条件：层级，为空
    private Boolean whereIsNullLevel;
    // 条件：层级，不为空
    private Boolean whereIsNotNullLevel;
    // 条件：层级，为空字符串
    private Boolean whereIsEmptyLevel;
    // 条件：层级，不为空字符串
    private Boolean whereIsNotEmptyLevel;
    // 条件：层级，大于
    private Integer whereGtLevel;
    // 条件：层级，大于等于
    private Integer whereGteLevel;
    // 条件：层级，小于
    private Integer whereLtLevel;
    // 条件：层级，小于等于
    private Integer whereLteLevel;
    // 条件：层级，开始范围
    private Integer whereStartLevel;
    // 条件：层级，结束范围
    private Integer whereEndLevel;
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
    public TbBasicSysRegionPo addGroupByField(String field) {
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
    public TbBasicSysRegionPo setGroupByFields(List<String> groupByFields) {
        this.groupByFields = groupByFields;
        return this;
    }
}
