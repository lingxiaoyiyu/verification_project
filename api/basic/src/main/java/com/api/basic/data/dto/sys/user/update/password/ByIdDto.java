package com.api.basic.data.dto.sys.user.update.password;


import com.api.common.base.data.dto.BaseUpdateDto;
import com.api.common.validate.dateFormat.ValidDateFormat;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理员修改指定用户的密码相关接口请求用DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ByIdDto extends BaseUpdateDto {

    // 用户ID
    @NotNull(message = "{validation.user.id.notNull}")
    @Min(value = 1, message = "{validation.user.id.min}")
    private Integer id;

    @NotBlank(message = "{validation.user.password.notBlank}")
    @Size(min = 6, max = 20, message = "{validation.user.password.length}")
    private String password;

    // 更新时间
    @Nullable
    @ValidDateFormat(message = "{validation.user.updatedAt.format}")
    private String updatedAt;
}
