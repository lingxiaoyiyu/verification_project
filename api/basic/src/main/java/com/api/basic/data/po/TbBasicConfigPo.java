package com.api.basic.data.po;

import com.api.basic.data.entity.TbBasicConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 配置管理条件实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicConfigPo extends TbBasicConfig {

    public TbBasicConfigPo(){
        super();
    }

    // 条件：，等于
    private Integer whereId;
    // 条件：，在列表中
    private List<Integer> whereInIds;
    // 条件：，在列表中，or连接
    private List<Integer> whereInOrIds;
    // 排除条件：
    private Integer whereNotId;
    // 条件：，不在列表中
    private List<Integer> whereNotInIds;
    // 条件：，为空
    private Boolean whereIsNullId;
    // 条件：，不为空
    private Boolean whereIsNotNullId;
    // 条件：，为空字符串
    private Boolean whereIsEmptyId;
    // 条件：，不为空字符串
    private Boolean whereIsNotEmptyId;
    // 条件：，大于
    private Integer whereGtId;
    // 条件：，大于等于
    private Integer whereGteId;
    // 条件：，小于
    private Integer whereLtId;
    // 条件：，小于等于
    private Integer whereLteId;
    // 条件：，开始范围
    private Integer whereStartId;
    // 条件：，结束范围
    private Integer whereEndId;
    // 条件：配置分组ID，等于
    private Integer whereGroupId;
    // 条件：配置分组ID，在列表中
    private List<Integer> whereInGroupIds;
    // 条件：配置分组ID，在列表中，or连接
    private List<Integer> whereInOrGroupIds;
    // 排除条件：配置分组ID
    private Integer whereNotGroupId;
    // 条件：配置分组ID，不在列表中
    private List<Integer> whereNotInGroupIds;
    // 条件：配置分组ID，为空
    private Boolean whereIsNullGroupId;
    // 条件：配置分组ID，不为空
    private Boolean whereIsNotNullGroupId;
    // 条件：配置分组ID，为空字符串
    private Boolean whereIsEmptyGroupId;
    // 条件：配置分组ID，不为空字符串
    private Boolean whereIsNotEmptyGroupId;
    // 条件：配置分组ID，大于
    private Integer whereGtGroupId;
    // 条件：配置分组ID，大于等于
    private Integer whereGteGroupId;
    // 条件：配置分组ID，小于
    private Integer whereLtGroupId;
    // 条件：配置分组ID，小于等于
    private Integer whereLteGroupId;
    // 条件：配置分组ID，开始范围
    private Integer whereStartGroupId;
    // 条件：配置分组ID，结束范围
    private Integer whereEndGroupId;
    // 条件：配置项标题，等于
    private String whereTitle;
    // 条件：配置项标题，在列表中
    private List<String> whereInTitles;
    // 条件：配置项标题，在列表中，or连接
    private List<String> whereInOrTitles;
    // 排除条件：配置项标题
    private String whereNotTitle;
    // 条件：配置项标题，不在列表中
    private List<String> whereNotInTitles;
    // 条件：配置项标题，为空
    private Boolean whereIsNullTitle;
    // 条件：配置项标题，不为空
    private Boolean whereIsNotNullTitle;
    // 条件：配置项标题，为空字符串
    private Boolean whereIsEmptyTitle;
    // 条件：配置项标题，不为空字符串
    private Boolean whereIsNotEmptyTitle;
    // 条件：配置项标题，大于
    private String whereGtTitle;
    // 条件：配置项标题，大于等于
    private String whereGteTitle;
    // 条件：配置项标题，小于
    private String whereLtTitle;
    // 条件：配置项标题，小于等于
    private String whereLteTitle;
    // 条件：配置项标题，模糊查询
    private String whereLikeTitle;
    // 条件：配置项标题，开始范围
    private String whereStartTitle;
    // 条件：配置项标题，结束范围
    private String whereEndTitle;
    // 条件：配置项名称，即key，等于
    private String whereName;
    // 条件：配置项名称，即key，在列表中
    private List<String> whereInNames;
    // 条件：配置项名称，即key，在列表中，or连接
    private List<String> whereInOrNames;
    // 排除条件：配置项名称，即key
    private String whereNotName;
    // 条件：配置项名称，即key，不在列表中
    private List<String> whereNotInNames;
    // 条件：配置项名称，即key，为空
    private Boolean whereIsNullName;
    // 条件：配置项名称，即key，不为空
    private Boolean whereIsNotNullName;
    // 条件：配置项名称，即key，为空字符串
    private Boolean whereIsEmptyName;
    // 条件：配置项名称，即key，不为空字符串
    private Boolean whereIsNotEmptyName;
    // 条件：配置项名称，即key，大于
    private String whereGtName;
    // 条件：配置项名称，即key，大于等于
    private String whereGteName;
    // 条件：配置项名称，即key，小于
    private String whereLtName;
    // 条件：配置项名称，即key，小于等于
    private String whereLteName;
    // 条件：配置项名称，即key，模糊查询
    private String whereLikeName;
    // 条件：配置项名称，即key，开始范围
    private String whereStartName;
    // 条件：配置项名称，即key，结束范围
    private String whereEndName;
    // 条件：配置项value，等于
    private String whereValue;
    // 条件：配置项value，在列表中
    private List<String> whereInValues;
    // 条件：配置项value，在列表中，or连接
    private List<String> whereInOrValues;
    // 排除条件：配置项value
    private String whereNotValue;
    // 条件：配置项value，不在列表中
    private List<String> whereNotInValues;
    // 条件：配置项value，为空
    private Boolean whereIsNullValue;
    // 条件：配置项value，不为空
    private Boolean whereIsNotNullValue;
    // 条件：配置项value，为空字符串
    private Boolean whereIsEmptyValue;
    // 条件：配置项value，不为空字符串
    private Boolean whereIsNotEmptyValue;
    // 条件：配置项value，大于
    private String whereGtValue;
    // 条件：配置项value，大于等于
    private String whereGteValue;
    // 条件：配置项value，小于
    private String whereLtValue;
    // 条件：配置项value，小于等于
    private String whereLteValue;
    // 条件：配置项value，模糊查询
    private String whereLikeValue;
    // 条件：配置项value，开始范围
    private String whereStartValue;
    // 条件：配置项value，结束范围
    private String whereEndValue;
    // 条件：配置项描述，等于
    private String whereDescription;
    // 条件：配置项描述，在列表中
    private List<String> whereInDescriptions;
    // 条件：配置项描述，在列表中，or连接
    private List<String> whereInOrDescriptions;
    // 排除条件：配置项描述
    private String whereNotDescription;
    // 条件：配置项描述，不在列表中
    private List<String> whereNotInDescriptions;
    // 条件：配置项描述，为空
    private Boolean whereIsNullDescription;
    // 条件：配置项描述，不为空
    private Boolean whereIsNotNullDescription;
    // 条件：配置项描述，为空字符串
    private Boolean whereIsEmptyDescription;
    // 条件：配置项描述，不为空字符串
    private Boolean whereIsNotEmptyDescription;
    // 条件：配置项描述，大于
    private String whereGtDescription;
    // 条件：配置项描述，大于等于
    private String whereGteDescription;
    // 条件：配置项描述，小于
    private String whereLtDescription;
    // 条件：配置项描述，小于等于
    private String whereLteDescription;
    // 条件：配置项描述，模糊查询
    private String whereLikeDescription;
    // 条件：配置项描述，开始范围
    private String whereStartDescription;
    // 条件：配置项描述，结束范围
    private String whereEndDescription;
    // 条件：数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。，等于
    private Integer whereDataType;
    // 条件：数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。，在列表中
    private List<Integer> whereInDataTypes;
    // 条件：数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。，在列表中，or连接
    private List<Integer> whereInOrDataTypes;
    // 排除条件：数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。
    private Integer whereNotDataType;
    // 条件：数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。，不在列表中
    private List<Integer> whereNotInDataTypes;
    // 条件：数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。，为空
    private Boolean whereIsNullDataType;
    // 条件：数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。，不为空
    private Boolean whereIsNotNullDataType;
    // 条件：数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。，为空字符串
    private Boolean whereIsEmptyDataType;
    // 条件：数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。，不为空字符串
    private Boolean whereIsNotEmptyDataType;
    // 条件：数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。，大于
    private Integer whereGtDataType;
    // 条件：数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。，大于等于
    private Integer whereGteDataType;
    // 条件：数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。，小于
    private Integer whereLtDataType;
    // 条件：数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。，小于等于
    private Integer whereLteDataType;
    // 条件：数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。，开始范围
    private Integer whereStartDataType;
    // 条件：数据类型。1：字符串，2：数字，3：json，4：图片，5：富文本。，结束范围
    private Integer whereEndDataType;
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
    // 条件：排序值，等于
    private Integer whereSort;
    // 条件：排序值，在列表中
    private List<Integer> whereInSorts;
    // 条件：排序值，在列表中，or连接
    private List<Integer> whereInOrSorts;
    // 排除条件：排序值
    private Integer whereNotSort;
    // 条件：排序值，不在列表中
    private List<Integer> whereNotInSorts;
    // 条件：排序值，为空
    private Boolean whereIsNullSort;
    // 条件：排序值，不为空
    private Boolean whereIsNotNullSort;
    // 条件：排序值，为空字符串
    private Boolean whereIsEmptySort;
    // 条件：排序值，不为空字符串
    private Boolean whereIsNotEmptySort;
    // 条件：排序值，大于
    private Integer whereGtSort;
    // 条件：排序值，大于等于
    private Integer whereGteSort;
    // 条件：排序值，小于
    private Integer whereLtSort;
    // 条件：排序值，小于等于
    private Integer whereLteSort;
    // 条件：排序值，开始范围
    private Integer whereStartSort;
    // 条件：排序值，结束范围
    private Integer whereEndSort;
    // 条件：日期时间选择器类型，等于
    private String wherePicker;
    // 条件：日期时间选择器类型，在列表中
    private List<String> whereInPickers;
    // 条件：日期时间选择器类型，在列表中，or连接
    private List<String> whereInOrPickers;
    // 排除条件：日期时间选择器类型
    private String whereNotPicker;
    // 条件：日期时间选择器类型，不在列表中
    private List<String> whereNotInPickers;
    // 条件：日期时间选择器类型，为空
    private Boolean whereIsNullPicker;
    // 条件：日期时间选择器类型，不为空
    private Boolean whereIsNotNullPicker;
    // 条件：日期时间选择器类型，为空字符串
    private Boolean whereIsEmptyPicker;
    // 条件：日期时间选择器类型，不为空字符串
    private Boolean whereIsNotEmptyPicker;
    // 条件：日期时间选择器类型，大于
    private String whereGtPicker;
    // 条件：日期时间选择器类型，大于等于
    private String whereGtePicker;
    // 条件：日期时间选择器类型，小于
    private String whereLtPicker;
    // 条件：日期时间选择器类型，小于等于
    private String whereLtePicker;
    // 条件：日期时间选择器类型，模糊查询
    private String whereLikePicker;
    // 条件：日期时间选择器类型，开始范围
    private String whereStartPicker;
    // 条件：日期时间选择器类型，结束范围
    private String whereEndPicker;
    // 条件：组件数据，等于
    private String whereComponentData;
    // 条件：组件数据，在列表中
    private List<String> whereInComponentDatas;
    // 条件：组件数据，在列表中，or连接
    private List<String> whereInOrComponentDatas;
    // 排除条件：组件数据
    private String whereNotComponentData;
    // 条件：组件数据，不在列表中
    private List<String> whereNotInComponentDatas;
    // 条件：组件数据，为空
    private Boolean whereIsNullComponentData;
    // 条件：组件数据，不为空
    private Boolean whereIsNotNullComponentData;
    // 条件：组件数据，为空字符串
    private Boolean whereIsEmptyComponentData;
    // 条件：组件数据，不为空字符串
    private Boolean whereIsNotEmptyComponentData;
    // 条件：组件数据，大于
    private String whereGtComponentData;
    // 条件：组件数据，大于等于
    private String whereGteComponentData;
    // 条件：组件数据，小于
    private String whereLtComponentData;
    // 条件：组件数据，小于等于
    private String whereLteComponentData;
    // 条件：组件数据，模糊查询
    private String whereLikeComponentData;
    // 条件：组件数据，开始范围
    private String whereStartComponentData;
    // 条件：组件数据，结束范围
    private String whereEndComponentData;
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
    // 条件：创建用户，等于
    private Integer whereCreatedUserId;
    // 条件：创建用户，在列表中
    private List<Integer> whereInCreatedUserIds;
    // 条件：创建用户，在列表中，or连接
    private List<Integer> whereInOrCreatedUserIds;
    // 排除条件：创建用户
    private Integer whereNotCreatedUserId;
    // 条件：创建用户，不在列表中
    private List<Integer> whereNotInCreatedUserIds;
    // 条件：创建用户，为空
    private Boolean whereIsNullCreatedUserId;
    // 条件：创建用户，不为空
    private Boolean whereIsNotNullCreatedUserId;
    // 条件：创建用户，为空字符串
    private Boolean whereIsEmptyCreatedUserId;
    // 条件：创建用户，不为空字符串
    private Boolean whereIsNotEmptyCreatedUserId;
    // 条件：创建用户，大于
    private Integer whereGtCreatedUserId;
    // 条件：创建用户，大于等于
    private Integer whereGteCreatedUserId;
    // 条件：创建用户，小于
    private Integer whereLtCreatedUserId;
    // 条件：创建用户，小于等于
    private Integer whereLteCreatedUserId;
    // 条件：创建用户，开始范围
    private Integer whereStartCreatedUserId;
    // 条件：创建用户，结束范围
    private Integer whereEndCreatedUserId;
    // 条件：最后更新时间，等于
    private String whereUpdatedAt;
    // 条件：最后更新时间，在列表中
    private List<String> whereInUpdatedAts;
    // 条件：最后更新时间，在列表中，or连接
    private List<String> whereInOrUpdatedAts;
    // 排除条件：最后更新时间
    private String whereNotUpdatedAt;
    // 条件：最后更新时间，不在列表中
    private List<String> whereNotInUpdatedAts;
    // 条件：最后更新时间，为空
    private Boolean whereIsNullUpdatedAt;
    // 条件：最后更新时间，不为空
    private Boolean whereIsNotNullUpdatedAt;
    // 条件：最后更新时间，为空字符串
    private Boolean whereIsEmptyUpdatedAt;
    // 条件：最后更新时间，不为空字符串
    private Boolean whereIsNotEmptyUpdatedAt;
    // 条件：最后更新时间，大于
    private String whereGtUpdatedAt;
    // 条件：最后更新时间，大于等于
    private String whereGteUpdatedAt;
    // 条件：最后更新时间，小于
    private String whereLtUpdatedAt;
    // 条件：最后更新时间，小于等于
    private String whereLteUpdatedAt;
    // 条件：最后更新时间，开始范围
    private String whereStartUpdatedAt;
    // 条件：最后更新时间，结束范围
    private String whereEndUpdatedAt;
    // 条件：更新用户，等于
    private Integer whereUpdatedUserId;
    // 条件：更新用户，在列表中
    private List<Integer> whereInUpdatedUserIds;
    // 条件：更新用户，在列表中，or连接
    private List<Integer> whereInOrUpdatedUserIds;
    // 排除条件：更新用户
    private Integer whereNotUpdatedUserId;
    // 条件：更新用户，不在列表中
    private List<Integer> whereNotInUpdatedUserIds;
    // 条件：更新用户，为空
    private Boolean whereIsNullUpdatedUserId;
    // 条件：更新用户，不为空
    private Boolean whereIsNotNullUpdatedUserId;
    // 条件：更新用户，为空字符串
    private Boolean whereIsEmptyUpdatedUserId;
    // 条件：更新用户，不为空字符串
    private Boolean whereIsNotEmptyUpdatedUserId;
    // 条件：更新用户，大于
    private Integer whereGtUpdatedUserId;
    // 条件：更新用户，大于等于
    private Integer whereGteUpdatedUserId;
    // 条件：更新用户，小于
    private Integer whereLtUpdatedUserId;
    // 条件：更新用户，小于等于
    private Integer whereLteUpdatedUserId;
    // 条件：更新用户，开始范围
    private Integer whereStartUpdatedUserId;
    // 条件：更新用户，结束范围
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
    public TbBasicConfigPo addGroupByField(String field) {
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
    public TbBasicConfigPo setGroupByFields(List<String> groupByFields) {
        this.groupByFields = groupByFields;
        return this;
    }
}
