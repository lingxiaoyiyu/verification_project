package com.api.basic.data.dto.sys.menu;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取指定菜单详情接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetDto extends BaseDto {

    // 菜单ID
    private Integer id;
}
