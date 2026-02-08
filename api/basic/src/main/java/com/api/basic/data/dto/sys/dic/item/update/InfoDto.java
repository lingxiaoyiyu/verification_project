package com.api.basic.data.dto.sys.dic.item.update;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 修改字典项信息接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InfoDto extends BaseDto {

    // ID
    private Integer id;
    // 字典标识
    private String dicIdentifying;
    // 字典项名称
    private String name;
    // 字典项值
    private String value;
    // 字典项标识
    private String identifying;
    // 排序ID
    private Integer sort;
    // 描述
    private String description;
    // 背景色
    private String backgroundColor;
    // 字体颜色
    private String color;
    // Tag预设状态
    private String tagStatus;
    // 状态。1：启用，2：禁用
    private Integer status;
    // 更新时间
    private String updatedAt;
}
