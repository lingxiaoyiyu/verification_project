package com.api.basic.data.dto.sys.feedback;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 创建问题反馈DTO
 */
@Data
public class CreateDto {

    /**
     * 反馈类型。bug：Bug反馈，suggestion：功能建议，question：使用咨询，other：其他
     */
    @NotBlank(message = "{validation.feedback.type.notBlank}")
    private String type;

    /**
     * 反馈内容
     */
    @NotBlank(message = "{validation.feedback.content.notBlank}")
    @Size(min = 10, max = 500, message = "{validation.feedback.content.length}")
    private String content;

    /**
     * 反馈图片（JSON数组格式存储图片URL）
     */
    private String images;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 设备信息（JSON格式存储设备型号、系统版本等）
     */
    private String deviceInfo;

    /**
     * 应用版本号
     */
    private String appVersion;

    /**
     * 用户ID
     */
    private Integer userId;
}