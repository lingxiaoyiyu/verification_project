package com.api.basic.data.po;

import com.api.basic.data.entity.TbBasicWorkbenchHeader;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 工作台头部项目信息表条件实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicWorkbenchHeaderPo extends TbBasicWorkbenchHeader {

    public TbBasicWorkbenchHeaderPo(){
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
    // 条件：工作台头部项目key，等于
    private String whereKey;
    // 条件：工作台头部项目key，在列表中
    private List<String> whereInKeys;
    // 条件：工作台头部项目key，在列表中，or连接
    private List<String> whereInOrKeys;
    // 排除条件：工作台头部项目key
    private String whereNotKey;
    // 条件：工作台头部项目key，不在列表中
    private List<String> whereNotInKeys;
    // 条件：工作台头部项目key，为空
    private Boolean whereIsNullKey;
    // 条件：工作台头部项目key，不为空
    private Boolean whereIsNotNullKey;
    // 条件：工作台头部项目key，为空字符串
    private Boolean whereIsEmptyKey;
    // 条件：工作台头部项目key，不为空字符串
    private Boolean whereIsNotEmptyKey;
    // 条件：工作台头部项目key，大于
    private String whereGtKey;
    // 条件：工作台头部项目key，大于等于
    private String whereGteKey;
    // 条件：工作台头部项目key，小于
    private String whereLtKey;
    // 条件：工作台头部项目key，小于等于
    private String whereLteKey;
    // 条件：工作台头部项目key，模糊查询
    private String whereLikeKey;
    // 条件：工作台头部项目key，开始范围
    private String whereStartKey;
    // 条件：工作台头部项目key，结束范围
    private String whereEndKey;
    // 条件：标题，等于
    private String whereTitle;
    // 条件：标题，在列表中
    private List<String> whereInTitles;
    // 条件：标题，在列表中，or连接
    private List<String> whereInOrTitles;
    // 排除条件：标题
    private String whereNotTitle;
    // 条件：标题，不在列表中
    private List<String> whereNotInTitles;
    // 条件：标题，为空
    private Boolean whereIsNullTitle;
    // 条件：标题，不为空
    private Boolean whereIsNotNullTitle;
    // 条件：标题，为空字符串
    private Boolean whereIsEmptyTitle;
    // 条件：标题，不为空字符串
    private Boolean whereIsNotEmptyTitle;
    // 条件：标题，大于
    private String whereGtTitle;
    // 条件：标题，大于等于
    private String whereGteTitle;
    // 条件：标题，小于
    private String whereLtTitle;
    // 条件：标题，小于等于
    private String whereLteTitle;
    // 条件：标题，模糊查询
    private String whereLikeTitle;
    // 条件：标题，开始范围
    private String whereStartTitle;
    // 条件：标题，结束范围
    private String whereEndTitle;
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
    public TbBasicWorkbenchHeaderPo addGroupByField(String field) {
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
    public TbBasicWorkbenchHeaderPo setGroupByFields(List<String> groupByFields) {
        this.groupByFields = groupByFields;
        return this;
    }
}
