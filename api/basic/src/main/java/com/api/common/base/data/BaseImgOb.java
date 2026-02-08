package com.api.common.base.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 图片传输通用对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseImgOb {

    private String uid;
    private String name;
    private String status;
    private String url;
}
