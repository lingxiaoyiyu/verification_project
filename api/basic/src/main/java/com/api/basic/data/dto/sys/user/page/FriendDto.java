package com.api.basic.data.dto.sys.user.page;

import com.api.common.base.data.dto.BasePageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取好友列表接口请求用DTO
 *
 * @author system
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FriendDto extends BasePageDto {

    // 好友用户名（模糊查询）
    private String username;
    
    // 好友昵称（模糊查询）
    private String nickname;
    
    // 好友状态。1：正常，2：已删除
    private Integer status;
}
