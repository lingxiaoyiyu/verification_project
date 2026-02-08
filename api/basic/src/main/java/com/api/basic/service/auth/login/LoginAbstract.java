package com.api.basic.service.auth.login;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.LogFactory;
import com.api.basic.dao.TbBasicSysDepartmentDao;
import com.api.basic.dao.TbBasicSysRoleDao;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.dao.TbBasicSysUserRoleRelationDao;
import com.api.basic.data.entity.TbBasicSysDepartment;
import com.api.basic.data.entity.TbBasicSysRole;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.entity.TbBasicSysUserRoleRelation;
import com.api.basic.data.po.TbBasicSysRolePo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.utils.FunctionUtil;
import com.api.common.utils.RedisCache;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class LoginAbstract extends AbstractService {

    @Resource
    protected TbBasicSysUserDao tbBasicSysUserDao;
    @Resource
    protected TbBasicSysDepartmentDao tbBasicSysDepartmentDao;
    @Resource
    protected TbBasicSysUserRoleRelationDao tbBasicSysUserRoleRelationDao;
    @Resource
    protected TbBasicSysRoleDao tbBasicSysRoleDao;
    @Resource
    protected RedisCache redisCache;
    @Value("${config.result.access-token-name:accessToken}")
    private String accessTokenName;
    @Resource
    protected ApplicationEventPublisher eventPublisher;

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
    protected Result<?> loginSuccess(TbBasicSysUser user, HttpServletRequest request, String deviceType) {
        MDC.put("uid", user.getId().toString());
        if (StrUtil.isBlank(deviceType)) {
            deviceType = "other";
        }

        StpUtil.login(user.getId(), deviceType);
        StpUtil.getSession(true).set("userInfo", JSONUtil.toJsonStr(user));
        if (ObjUtil.isNotEmpty(user.getDepartmentId())) {
            StpUtil.getSession(true).set("departmentName", tbBasicSysDepartmentDao.getFieldById(user.getDepartmentId(), TbBasicSysDepartment::getName)); // 部门名称
        }

        List<String> roleNames = new ArrayList<>();
        List<Integer> roleIds = tbBasicSysUserRoleRelationDao.queryByUserId(user.getId()).stream().map(TbBasicSysUserRoleRelation::getRoleId).toList();
        if (ObjUtil.isNotEmpty(roleIds)) {
            roleNames = tbBasicSysRoleDao.query(TbBasicSysRolePo.builder().whereInIds(roleIds).build()).stream().map(TbBasicSysRole::getName).toList();
        }
        StpUtil.getSession(true).set("roleNames", JSONUtil.toJsonStr(roleNames));

        LogFactory.get().info("【operationType=login】用户{}：{}登录成功", user.getId(), user.getUsername());
        return Result.ok("登录成功", Map.of(accessTokenName, StpUtil.getTokenValue()));
    }
}
