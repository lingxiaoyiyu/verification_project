package com.api.basic.data.dto.sys.s3.file;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 单独上传文件请求DTO
 */
@Data
public class AddDto {
    @NotBlank(message = "{validation.s3.file.key.notBlank}")
    private String key;
    private MultipartFile file;
}