package com.api.basic.data.dto.sys.feedback;

import lombok.Data;

import java.util.List;

/**
 * 添加问题反馈信息接口请求用DTO
 *
 * @author 裴金伟
 */
@Data
public class AddDto {

    // 反馈类型。bug：Bug反馈，suggestion：功能建议，question：使用咨询，other：其他
    private String type;
    // 反馈内容
    private String content;
    // 反馈图片（JSON数组格式存储图片URL）
    private List<String> images;
    // 联系方式
    private String contact;
    // 应用版本
    private String appVersion;
    // 设备信息
    private String deviceInfo;
}
