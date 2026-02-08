package com.api.basic.data.po;

import com.api.basic.data.entity.TbBasicStatisticsUserTotalHour;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户每小时注册统计表条件实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicStatisticsUserTotalHourPo extends TbBasicStatisticsUserTotalHour {

    public TbBasicStatisticsUserTotalHourPo(){
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
    // 条件：日期小时，等于
    private String whereDateHour;
    // 条件：日期小时，在列表中
    private List<String> whereInDateHours;
    // 条件：日期小时，在列表中，or连接
    private List<String> whereInOrDateHours;
    // 排除条件：日期小时
    private String whereNotDateHour;
    // 条件：日期小时，不在列表中
    private List<String> whereNotInDateHours;
    // 条件：日期小时，为空
    private Boolean whereIsNullDateHour;
    // 条件：日期小时，不为空
    private Boolean whereIsNotNullDateHour;
    // 条件：日期小时，为空字符串
    private Boolean whereIsEmptyDateHour;
    // 条件：日期小时，不为空字符串
    private Boolean whereIsNotEmptyDateHour;
    // 条件：日期小时，大于
    private String whereGtDateHour;
    // 条件：日期小时，大于等于
    private String whereGteDateHour;
    // 条件：日期小时，小于
    private String whereLtDateHour;
    // 条件：日期小时，小于等于
    private String whereLteDateHour;
    // 条件：日期小时，模糊查询
    private String whereLikeDateHour;
    // 条件：日期小时，开始范围
    private String whereStartDateHour;
    // 条件：日期小时，结束范围
    private String whereEndDateHour;
    // 条件：用户注册数，等于
    private Integer whereCnt;
    // 条件：用户注册数，在列表中
    private List<Integer> whereInCnts;
    // 条件：用户注册数，在列表中，or连接
    private List<Integer> whereInOrCnts;
    // 排除条件：用户注册数
    private Integer whereNotCnt;
    // 条件：用户注册数，不在列表中
    private List<Integer> whereNotInCnts;
    // 条件：用户注册数，为空
    private Boolean whereIsNullCnt;
    // 条件：用户注册数，不为空
    private Boolean whereIsNotNullCnt;
    // 条件：用户注册数，为空字符串
    private Boolean whereIsEmptyCnt;
    // 条件：用户注册数，不为空字符串
    private Boolean whereIsNotEmptyCnt;
    // 条件：用户注册数，大于
    private Integer whereGtCnt;
    // 条件：用户注册数，大于等于
    private Integer whereGteCnt;
    // 条件：用户注册数，小于
    private Integer whereLtCnt;
    // 条件：用户注册数，小于等于
    private Integer whereLteCnt;
    // 条件：用户注册数，开始范围
    private Integer whereStartCnt;
    // 条件：用户注册数，结束范围
    private Integer whereEndCnt;
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
    public TbBasicStatisticsUserTotalHourPo addGroupByField(String field) {
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
    public TbBasicStatisticsUserTotalHourPo setGroupByFields(List<String> groupByFields) {
        this.groupByFields = groupByFields;
        return this;
    }
}
