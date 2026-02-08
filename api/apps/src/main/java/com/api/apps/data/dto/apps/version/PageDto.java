package com.api.apps.data.dto.apps.version;

import com.api.common.base.data.dto.BasePageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 应用版本接口请求用DTO
 *
 * @author 裴金伟
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class PageDto extends BasePageDto {

    // 应用ID
    private String appId;
    // 版本号
    private Integer versionCode;
}
