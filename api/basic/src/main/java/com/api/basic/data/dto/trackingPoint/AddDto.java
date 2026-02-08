package com.api.basic.data.dto.trackingPoint;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 添加埋点记录接口请求用DTO
 *
 * @author 裴金伟
 */
@Data
public class AddDto {

    // 页面
    private String page;
    // 标识
    private String tag;
}
