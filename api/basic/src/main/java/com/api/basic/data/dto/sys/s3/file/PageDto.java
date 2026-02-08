package com.api.basic.data.dto.sys.s3.file;

import lombok.Data;

/**
 * 查看文件列表请求DTO
 */
@Data
public class PageDto {
    private String prefix;
    private String filename;
    private Integer page = 1;
    private Integer size = 20;
    private Boolean recursive = false;
}