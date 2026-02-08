package com.api.basic.data.dto.sys.dic;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 删除字典组信息接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RemoveDto extends BaseDto {

    // ID
    private Integer id;
    // 更新时间
    private String updatedAt;
}
