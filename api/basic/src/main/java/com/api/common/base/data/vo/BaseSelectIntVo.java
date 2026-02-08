package com.api.common.base.data.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 下拉选择列表通用Vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseSelectIntVo {

    // VALUE
    private int value;
    // 标题
    private String label;
}
