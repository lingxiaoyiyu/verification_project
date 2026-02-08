package com.api.basic.data.po;

import com.api.basic.data.entity.TbBasicConfigGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 配置分组管理条件实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicConfigGroupPo extends TbBasicConfigGroup {

    public TbBasicConfigGroupPo(){
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
    // 条件：分组名称，等于
    private String whereName;
    // 条件：分组名称，在列表中
    private List<String> whereInNames;
    // 条件：分组名称，在列表中，or连接
    private List<String> whereInOrNames;
    // 排除条件：分组名称
    private String whereNotName;
    // 条件：分组名称，不在列表中
    private List<String> whereNotInNames;
    // 条件：分组名称，为空
    private Boolean whereIsNullName;
    // 条件：分组名称，不为空
    private Boolean whereIsNotNullName;
    // 条件：分组名称，为空字符串
    private Boolean whereIsEmptyName;
    // 条件：分组名称，不为空字符串
    private Boolean whereIsNotEmptyName;
    // 条件：分组名称，大于
    private String whereGtName;
    // 条件：分组名称，大于等于
    private String whereGteName;
    // 条件：分组名称，小于
    private String whereLtName;
    // 条件：分组名称，小于等于
    private String whereLteName;
    // 条件：分组名称，模糊查询
    private String whereLikeName;
    // 条件：分组名称，开始范围
    private String whereStartName;
    // 条件：分组名称，结束范围
    private String whereEndName;
    // 条件：分组value，等于
    private String whereValue;
    // 条件：分组value，在列表中
    private List<String> whereInValues;
    // 条件：分组value，在列表中，or连接
    private List<String> whereInOrValues;
    // 排除条件：分组value
    private String whereNotValue;
    // 条件：分组value，不在列表中
    private List<String> whereNotInValues;
    // 条件：分组value，为空
    private Boolean whereIsNullValue;
    // 条件：分组value，不为空
    private Boolean whereIsNotNullValue;
    // 条件：分组value，为空字符串
    private Boolean whereIsEmptyValue;
    // 条件：分组value，不为空字符串
    private Boolean whereIsNotEmptyValue;
    // 条件：分组value，大于
    private String whereGtValue;
    // 条件：分组value，大于等于
    private String whereGteValue;
    // 条件：分组value，小于
    private String whereLtValue;
    // 条件：分组value，小于等于
    private String whereLteValue;
    // 条件：分组value，模糊查询
    private String whereLikeValue;
    // 条件：分组value，开始范围
    private String whereStartValue;
    // 条件：分组value，结束范围
    private String whereEndValue;
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
    public TbBasicConfigGroupPo addGroupByField(String field) {
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
    public TbBasicConfigGroupPo setGroupByFields(List<String> groupByFields) {
        this.groupByFields = groupByFields;
        return this;
    }
}
