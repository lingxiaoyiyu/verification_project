package com.api.basic.data.dto.sys.dic.item;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 添加字典项信息接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AddDto extends BaseDto {

    // 字典标识
    private String dicIdentifying;
    // 字典项名称
    private String name;
    // 字典项值
    private String value;
    // 字典项标识
    private String identifying;
    // 描述
    private String description;
    // 背景色
    private String backgroundColor;
    // 状态。1：启用，2：禁用
    private Integer status;
}
