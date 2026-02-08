package com.api.basic.data.dto.sys.s3.folder;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 创建文件夹请求DTO
 */
@Data
public class AddDto {
    @NotBlank(message = "{validation.s3.folder.path.notBlank}")
    private String path;
}