package com.api.basic.data.dto.sys.menu;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询菜单树相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryTreeDto extends BaseDto {

    // 名称
    private String name;
    // 标题
    private String title;
    // 类型
    private Integer type;
    // 状态
    private Integer status;
    // 是否显示
    private Integer isShow;
    // 是否缓存
    private Integer keepalive;
    // 是否外链
    private Integer isLink;
    // 是否隐藏子菜单
    private Integer hideChildren;
}
