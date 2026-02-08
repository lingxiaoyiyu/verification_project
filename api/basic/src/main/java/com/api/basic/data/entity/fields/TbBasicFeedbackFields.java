package com.api.basic.data.entity.fields;

/**
 * 问题反馈表字段常量类
 */
public class TbBasicFeedbackFields {
    /**
     * ID
     */
    public static final String ID = "id";
    /**
     * 反馈类型。1：Bug反馈，2：功能建议，3：使用咨询，4：其他
     */
    public static final String TYPE = "type";
    /**
     * 反馈内容
     */
    public static final String CONTENT = "content";
    /**
     * 反馈图片（JSON数组格式存储图片URL）
     */
    public static final String IMAGES = "images";
    /**
     * 联系方式
     */
    public static final String CONTACT = "contact";
    /**
     * 处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝
     */
    public static final String STATUS = "status";
    /**
     * 是否已删除。1：未删除，2：已删除
     */
    public static final String ISDELETE = "isDelete";
    /**
     * 设备信息（JSON格式存储设备型号、系统版本等）
     */
    public static final String DEVICEINFO = "deviceInfo";
    /**
     * 应用版本号
     */
    public static final String APPVERSION = "appVersion";
    /**
     * 回复内容
     */
    public static final String REPLYCONTENT = "replyContent";
    /**
     * 回复时间
     */
    public static final String REPLYAT = "replyAt";
    /**
     * 创建时间
     */
    public static final String CREATEDAT = "createdAt";
    /**
     * 创建者用户ID（可为空，允许匿名反馈）
     */
    public static final String CREATEDUSERID = "createdUserId";
    /**
     * 更新时间
     */
    public static final String UPDATEDAT = "updatedAt";
    /**
     * 更新者用户ID
     */
    public static final String UPDATEDUSERID = "updatedUserId";
}