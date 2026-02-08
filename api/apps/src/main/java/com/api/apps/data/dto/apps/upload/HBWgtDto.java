package com.api.apps.data.dto.apps.upload;

import com.api.common.base.data.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 上传小程序接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HBWgtDto extends BaseDto {

    // uniappid
    @NotBlank(message = "{validation.apps.upload.uniAppId.notBlank}")
    private String uniAppId;
    // 版本号
    @NotNull(message = "{validation.apps.upload.versionCode.notNull}")
    private Integer versionCode;
    // 版本名称
    @NotBlank(message = "{validation.apps.upload.versionName.notBlank}")
    private String versionName;
    // 版本描述
    @NotBlank(message = "{validation.apps.upload.versionDesc.notBlank}")
    private String versionDesc;
    // 文件
    @NotNull(message = "{validation.apps.upload.file.notNull}")
    private String file;
}
