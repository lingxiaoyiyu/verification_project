package com.api.basic.data.vo.sys.region;

import lombok.Data;

/**
 * 分页查询行政区域相关接口响应用VO
 */
@Data
public class ItemVo {

    // 行政区域ID
    private Integer id;
    // 行政区域名称
    private String name;
    // 行政区域编号
    private Long code;
    // 上级行政区域编码
    private Long parentCode;
    // 创建时间
    private String createdAt;
    // 创建者
    private String createdUserName;
    // 最后更新时间
    private String updatedAt;
    // 最后更新者
    private String updatedUserName;
    // 是否有子节点
    private Boolean hasChild;
}
