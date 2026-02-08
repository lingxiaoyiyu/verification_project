package com.api.basic.data.dto.sys.s3.file;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 删除文件请求DTO
 */
@Data
public class DeleteDto {
    @NotBlank(message = "{validation.s3.file.key.notBlank}")
    private String key;
}