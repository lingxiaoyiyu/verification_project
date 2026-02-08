package com.api.basic.service.sys.user.update;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.dto.sys.user.update.BindInviteCodeDto;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 绑定邀请码接口
 */
@Service("BasicSysUserUpdateBindInviteCodeServiceImpl")
@RequiredArgsConstructor
public class BindInviteCode extends AbstractService {

    private final TbBasicSysUserDao tbBasicSysUserDao;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(BindInviteCodeDto dto) {
        // 获取当前登录用户
        Integer currentUserId = StpUtil.getLoginIdAsInt();
        TbBasicSysUser currentUser = tbBasicSysUserDao.get(TbBasicSysUserPo.builder().whereId(currentUserId).build());

        if (currentUser == null) {
            throw new ServerException("用户不存在");
        }

        // 检查当前用户是否已经绑定过邀请码
        if (StrUtil.isNotBlank(currentUser.getInviteCode())) {
            throw new ServerException("已绑定邀请码，不能重复绑定");
        }

        // 验证邀请码格式（根据实际需求调整）
        if (StrUtil.isBlank(dto.getInviteCode())) {
            throw new ServerException("邀请码不能为空");
        }

        // 验证邀请码是否有效
         TbBasicSysUser inviter = tbBasicSysUserDao.get(
             TbBasicSysUserPo.builder().whereInviteCode(dto.getInviteCode()).build()
         );
         if (inviter == null) {
             throw new ServerException("邀请码无效");
         }
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(BindInviteCodeDto dto) {
        Integer currentUserId = StpUtil.getLoginIdAsInt();

        TbBasicSysUser inviter = tbBasicSysUserDao.get(
                TbBasicSysUserPo.builder().whereInviteCode(dto.getInviteCode()).build()
        );

        // 更新用户的邀请码
        if (tbBasicSysUserDao.update(TbBasicSysUserPo.builder()
                .whereId(currentUserId)
                .inviter(inviter.getId())
                .updatedUserId(currentUserId)
                .build()) != 1) {
            throw new ServerException("绑定邀请码失败");
        }

        // TODO: 可以在这里添加邀请奖励逻辑
        // 例如：给邀请人增加积分、给被邀请人发放奖励等

        return Result.ok("绑定邀请码成功");
    }
}
