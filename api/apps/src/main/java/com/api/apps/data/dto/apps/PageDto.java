package com.api.apps.data.dto.apps;

import com.api.common.base.data.dto.BasePageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 小程序接口请求用DTO
 *
 * @author 裴金伟
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class PageDto extends BasePageDto {

    // 应用类型。1：宿主APP，2：小程序，3：网站。4：第三方应用。
    private Integer type;
    // uniapp_id
    private String uniAppid;
    // 分类ID
    private Integer categoryId;
    // 应用名称
    private String name;
}
