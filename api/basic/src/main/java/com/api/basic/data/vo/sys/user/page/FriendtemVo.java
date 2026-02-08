package com.api.basic.data.vo.sys.user.page;

import lombok.Data;

/**
 * 好友列表项VO
 *
 * @author system
 */
@Data
public class FriendtemVo {

    // 好友ID
    private Integer id;

    // 用户名
    private String username;
    
    // 昵称
    private String nickName;
    
    // 头像
    private String avatar;

    // 真实姓名
    private String realName;
    
    // 好友状态。1：正常，2：已删除
    private Integer status;
    
    // 添加时间
    private String createdAt;
}
