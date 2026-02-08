package com.api.common.base.data.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 下拉选择树通用Vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseSelectTreeVo {

    // id
    private int id;
    // 标题
    private String title;
    // 排序值
    private Integer order;
    // 类型
    private Integer type;
    // 图标
    private String icon;
    // 子节点
    private List<BaseSelectTreeVo> children;
}
