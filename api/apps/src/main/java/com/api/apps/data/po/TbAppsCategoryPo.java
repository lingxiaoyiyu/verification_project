package com.api.apps.data.po;

import com.api.apps.data.entity.TbAppsCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 条件实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbAppsCategoryPo extends TbAppsCategory {

    public TbAppsCategoryPo(){
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
    // 条件：，开始范围
    private Integer whereStartId;
    // 条件：，结束范围
    private Integer whereEndId;
    // 条件：父级ID，等于
    private Integer wherePid;
    // 条件：父级ID，在列表中
    private List<Integer> whereInPids;
    // 条件：父级ID，在列表中，or连接
    private List<Integer> whereInOrPids;
    // 排除条件：父级ID
    private Integer whereNotPid;
    // 条件：父级ID，不在列表中
    private List<Integer> whereNotInPids;
    // 条件：父级ID，开始范围
    private Integer whereStartPid;
    // 条件：父级ID，结束范围
    private Integer whereEndPid;
    // 条件：分类名称，等于
    private String whereName;
    // 条件：分类名称，在列表中
    private List<String> whereInNames;
    // 条件：分类名称，在列表中，or连接
    private List<String> whereInOrNames;
    // 排除条件：分类名称
    private String whereNotName;
    // 条件：分类名称，不在列表中
    private List<String> whereNotInNames;
    // 条件：分类名称，模糊查询
    private String whereLikeName;
    // 条件：分类名称，开始范围
    private String whereStartName;
    // 条件：分类名称，结束范围
    private String whereEndName;
    // 条件：分类说明，等于
    private String whereDesc;
    // 条件：分类说明，在列表中
    private List<String> whereInDescs;
    // 条件：分类说明，在列表中，or连接
    private List<String> whereInOrDescs;
    // 排除条件：分类说明
    private String whereNotDesc;
    // 条件：分类说明，不在列表中
    private List<String> whereNotInDescs;
    // 条件：分类说明，模糊查询
    private String whereLikeDesc;
    // 条件：分类说明，开始范围
    private String whereStartDesc;
    // 条件：分类说明，结束范围
    private String whereEndDesc;
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
    // 条件：排序，开始范围
    private Integer whereStartSort;
    // 条件：排序，结束范围
    private Integer whereEndSort;
    // 条件：是否显示。1：显示，2：不显示。，等于
    private Integer whereIsShow;
    // 条件：是否显示。1：显示，2：不显示。，在列表中
    private List<Integer> whereInIsShows;
    // 条件：是否显示。1：显示，2：不显示。，在列表中，or连接
    private List<Integer> whereInOrIsShows;
    // 排除条件：是否显示。1：显示，2：不显示。
    private Integer whereNotIsShow;
    // 条件：是否显示。1：显示，2：不显示。，不在列表中
    private List<Integer> whereNotInIsShows;
    // 条件：是否显示。1：显示，2：不显示。，开始范围
    private Integer whereStartIsShow;
    // 条件：是否显示。1：显示，2：不显示。，结束范围
    private Integer whereEndIsShow;
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
    // 条件：创建用户，开始范围
    private Integer whereStartCreatedUserId;
    // 条件：创建用户，结束范围
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
    // 条件：更新时间，开始范围
    private String whereStartUpdatedAt;
    // 条件：更新时间，结束范围
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
    // 条件：更新用户，开始范围
    private Integer whereStartUpdatedUserId;
    // 条件：更新用户，结束范围
    private Integer whereEndUpdatedUserId;
}
