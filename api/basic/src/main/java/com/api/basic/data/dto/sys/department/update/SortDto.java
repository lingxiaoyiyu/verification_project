package com.api.basic.data.dto.sys.department.update;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 修改部门排序接口请求用DTO
 *
 * @author 裴金伟
 * @date 2025-02-19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SortDto extends BaseDto {

    // 相对目标ID方向。up：上方，down：下方
    private String direction;
    // 目标ID
    private Integer targetId;
    // 菜单ID
    private Integer id;
}
