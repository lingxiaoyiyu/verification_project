package com.api.basic.data.dto.sys.user;

import com.api.basic.data.enums.UserStatusEnum;
import com.api.common.base.data.dto.BaseDto;
import com.api.common.validate.enumValue.ValidEnumValue;
import com.api.common.validate.listNotEmpty.ValidListNotEmpty;
import com.api.common.validate.phoneNumber.ValidPhoneNumber;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 添加用户相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AddDto extends BaseDto {

    // 用户名
    @NotBlank(message = "{validation.user.username.notBlank}")
    @Size(min = 3, max = 32, message = "{validation.user.username.length}")
    private String username;

    // 密码
    @NotBlank(message = "{validation.user.password.notBlank}")
    @Size(min = 6, max = 64, message = "{validation.user.password.length}")
    private String password;

    // 手机号
    @Nullable
    @ValidPhoneNumber(message = "{validation.user.phoneNumber.format}", nullable = true)
    private String phoneNumber;

    // 邮箱
    @Nullable
    @Size(max = 64, message = "{validation.user.email.length}")
    @Email(message = "{validation.user.email.format}")
    private String email;

    // 真实姓名
    @Nullable
    @Size(max = 32, message = "{validation.user.realName.length}")
    private String realName;

    // 昵称
    @Nullable
    @Size(max = 32, message = "{validation.user.nickName.length}")
    private String nickName;

    // 角色ID列表
    @ValidListNotEmpty(message = "{validation.user.roleIds.notEmpty}")
    private List<Integer> roleIds;

    // 状态
    @ValidEnumValue(enumClass = UserStatusEnum.class, expectedType = Integer.class, message = "{validation.user.status.enumValue}")
    private Integer status;

    // 备注
    @Nullable
    @Size(max = 255, message = "{validation.user.remark.length}")
    private String remark;

    // 部门ID
    @Nullable
    private Integer departmentId;
}
