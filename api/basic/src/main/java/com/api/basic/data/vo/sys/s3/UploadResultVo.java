package com.api.basic.data.vo.sys.s3;

import lombok.Data;

/**
 * 上传结果响应VO
 */
@Data
public class UploadResultVo {
    private String key;
    private String name;
    private long size;
    private String url;
    private String etag;
}