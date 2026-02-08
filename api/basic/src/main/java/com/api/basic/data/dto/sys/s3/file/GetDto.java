package com.api.basic.data.dto.sys.s3.file;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 获取文件信息请求DTO
 */
@Data
public class GetDto {
    @NotBlank(message = "{validation.s3.file.key.notBlank}")
    private String key;
}