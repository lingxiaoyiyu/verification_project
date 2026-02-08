package com.api.basic.data.dto.sys.dic.update;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 修改字典组状态接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InfoDto extends BaseDto {

    // ID
    private Integer id;
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
    // 更新时间
    private String updatedAt;
}
