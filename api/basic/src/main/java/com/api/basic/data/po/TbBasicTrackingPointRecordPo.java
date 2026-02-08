package com.api.basic.data.po;

import com.api.basic.data.entity.TbBasicTrackingPointRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * 埋点记录条件实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicTrackingPointRecordPo extends TbBasicTrackingPointRecord {

    public TbBasicTrackingPointRecordPo(){
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
    // 条件：ID，开始范围
    private Integer whereStartId;
    // 条件：ID，结束范围
    private Integer whereEndId;
    // 条件：页面，等于
    private String wherePage;
    // 条件：页面，在列表中
    private List<String> whereInPages;
    // 条件：页面，在列表中，or连接
    private List<String> whereInOrPages;
    // 排除条件：页面
    private String whereNotPage;
    // 条件：页面，不在列表中
    private List<String> whereNotInPages;
    // 条件：页面，模糊查询
    private String whereLikePage;
    // 条件：页面，开始范围
    private String whereStartPage;
    // 条件：页面，结束范围
    private String whereEndPage;
    // 条件：标识，等于
    private String whereTag;
    // 条件：标识，在列表中
    private List<String> whereInTags;
    // 条件：标识，在列表中，or连接
    private List<String> whereInOrTags;
    // 排除条件：标识
    private String whereNotTag;
    // 条件：标识，不在列表中
    private List<String> whereNotInTags;
    // 条件：标识，模糊查询
    private String whereLikeTag;
    // 条件：标识，开始范围
    private String whereStartTag;
    // 条件：标识，结束范围
    private String whereEndTag;
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
    // 条件：创建时间，开始范围
    private String whereStartCreatedAt;
    // 条件：创建时间，结束范围
    private String whereEndCreatedAt;
    // 条件：访问UA，等于
    private String whereUA;
    // 条件：访问UA，在列表中
    private List<String> whereInUAs;
    // 条件：访问UA，在列表中，or连接
    private List<String> whereInOrUAs;
    // 排除条件：访问UA
    private String whereNotUA;
    // 条件：访问UA，不在列表中
    private List<String> whereNotInUAs;
    // 条件：访问UA，模糊查询
    private String whereLikeUA;
    // 条件：访问UA，开始范围
    private String whereStartUA;
    // 条件：访问UA，结束范围
    private String whereEndUA;
    // 条件：访问IP，等于
    private String whereIp;
    // 条件：访问IP，在列表中
    private List<String> whereInIps;
    // 条件：访问IP，在列表中，or连接
    private List<String> whereInOrIps;
    // 排除条件：访问IP
    private String whereNotIp;
    // 条件：访问IP，不在列表中
    private List<String> whereNotInIps;
    // 条件：访问IP，模糊查询
    private String whereLikeIp;
    // 条件：访问IP，开始范围
    private String whereStartIp;
    // 条件：访问IP，结束范围
    private String whereEndIp;
}
