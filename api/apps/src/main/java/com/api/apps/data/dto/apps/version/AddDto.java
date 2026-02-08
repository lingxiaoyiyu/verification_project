package com.api.apps.data.dto.apps.version;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 应用版本接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AddDto extends BaseDto {

    // 版本描述
    private String desc;
    // 状态。1：下线，2：测试，3：正式上线。
    private Integer status;
    // 下载地址
    private String url;
    // 应用ID
    private String appId;
    // 版本号
    private Integer versionCode;
    // 版本名称
    private String versionName;
}
