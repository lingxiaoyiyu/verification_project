package com.api.basic.data.dto.sys.dic.item;

import com.api.common.base.data.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 删除字典项信息接口请求用DTO
 *
 * @author 裴金伟
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DeleteDto extends BaseDto {

    // ID
    private Integer id;
}
