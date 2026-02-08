package com.api.basic.data.dto.sys.dic;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 添加字典组信息接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AddDto extends BaseDto {

    // 父节点ID
    private Integer parentId;
    // 字典名称
    private String name;
    // 字典标识
    private String identifying;
    // 描述
    private String description;
    // 状态。1：启用，2：禁用
    private Integer status;
}
