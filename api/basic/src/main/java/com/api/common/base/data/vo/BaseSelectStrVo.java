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
public class BaseSelectStrVo {

    // VALUE
    private String value;
    // 标题
    private String label;
}
