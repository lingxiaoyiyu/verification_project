package com.api.basic.data.vo.sys.s3;

import lombok.Data;

/**
 * 文件信息响应VO
 */
@Data
public class FileInfoVo {
    private String key;
    private String name;
    private long size;
    private String contentType;
    private String lastModified;
    private String etag;
    private boolean folder;
    private String url;
}