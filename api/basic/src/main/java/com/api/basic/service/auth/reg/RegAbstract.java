package com.api.basic.service.auth.reg;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.utils.FunctionUtil;
import com.api.common.utils.RedisCache;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class RegAbstract extends AbstractService {

    @Resource
    protected TbBasicSysUserDao tbBasicSysUserDao;
    @Resource
    protected RedisCache redisCache;
    @Value("${config.result.access-token-name:accessToken}")
    private String accessTokenName;

    /**
     * 注册用户
     *
     * @param source 注册来源
     * @return 新注册用户uid
     */
    public Integer registerUser(int source) {
        String username = FunctionUtil.randomStr(8, true, true, true); // 生成随机用户名
        String password = DigestUtil.md5Hex("123456"); // 默认密码
        String inviteCode = FunctionUtil.randomStr(6, true, true, true); // 生成邀请码
        //  添加用户
        TbBasicSysUser addEntity = new TbBasicSysUser();
        addEntity.setUsername(username);
        addEntity.setPassword(password);
        addEntity.setInviteCode(inviteCode);
        addEntity.setSource(source);
        addEntity.setCreatedUserId(0);
        addEntity.setUpdatedUserId(0);
        addEntity.setWujieUnionid(FunctionUtil.randomStr(32, true, true, true)); // 生成无界unionid
        if (tbBasicSysUserDao.add(addEntity) == 1) {
            return addEntity.getId();
        } else {
            return null;
        }
    }

    /**
     * 登录成功
     *
     * @param user 用户信息entity
     * @return 登录成功结果
     */
    protected Result<?> registerSuccess(TbBasicSysUser user) {

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", String.valueOf(user.getId()));
        userInfo.put("username", user.getUsername());
        userInfo.put("password", user.getPassword());
        // 生成token
        StpUtil.login(user.getId());
        return Result.ok("注册成功", Map.of(accessTokenName, StpUtil.getTokenValue()));
    }
}
