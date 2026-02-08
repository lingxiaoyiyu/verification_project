package com.api.basic.data.dto.sys.user;

import com.api.common.base.data.dto.BasePageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 获取用户分页列表相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageDto extends BasePageDto {

    // 用户名
    private String username;
    // 真实姓名
    private String realName;
    // 手机号
    private String phoneNumber;
    // 昵称
    private String nickName;
    // 邮箱
    private String email;
    // 状态
    private Integer status;
    // 角色ID
    private List<Integer> roleIds;
    // 用户ID列
    private List<Integer> userIds;
    // 部门ID
    private Integer departmentId;
}
