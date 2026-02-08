package com.api.apps.data.dto.apps.get;

import com.api.common.base.data.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 小程序接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MpInfoDto extends BaseDto {

    @NotBlank(message = "{validation.apps.mp.appid.notBlank}")
    private String uniAppid;
}
