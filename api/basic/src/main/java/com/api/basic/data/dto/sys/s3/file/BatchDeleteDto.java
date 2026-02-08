package com.api.basic.data.dto.sys.s3.file;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * 批量删除文件请求DTO
 */
@Data
public class BatchDeleteDto {
    @NotEmpty(message = "{validation.s3.file.keys.notEmpty}")
    private List<String> keys;
}