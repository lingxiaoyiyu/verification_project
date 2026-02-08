package com.api.common.base.data.dto;

import com.api.common.validate.dateFormat.ValidDateFormat;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询基类Dto
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseUpdateDto extends BaseDto{

    // 更新日期
    @Nullable
    @ValidDateFormat(message = "最后更新日期格式错误")
    public String updatedAt;
}
