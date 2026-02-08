package com.api.apps.data.dto.apps.query;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 小程序接口请求用DTO
 *
 * @author 裴金伟
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class MpListDto extends BaseDto {

    // 是否是入口小程序
    private Integer isEntranceMp;
}
