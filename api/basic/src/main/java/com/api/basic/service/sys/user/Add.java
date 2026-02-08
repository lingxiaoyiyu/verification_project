package com.api.basic.service.sys.user;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.api.basic.dao.TbBasicSysRoleDao;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.dao.TbBasicSysUserRoleRelationDao;
import com.api.basic.data.dto.sys.user.AddDto;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.entity.TbBasicSysUserRoleRelation;
import com.api.basic.data.enums.UserSourceEnum;
import com.api.basic.data.po.TbBasicSysRolePo;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.basic.event.UserRegisterEvent;
import com.api.basic.event.entity.UserRegisterEntity;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.exception.ServerException;
import com.api.common.utils.FunctionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 添加用户
 */
@Service("BasicSysUserAddServiceImpl")
@RequiredArgsConstructor
public class Add extends AbstractService {

    private final TbBasicSysUserDao tbBasicSysUserDao;
    private final TbBasicSysUserRoleRelationDao userRoleDao;
    private final TbBasicSysRoleDao tbBasicSysRoleDao;
    private final ApplicationEventPublisher eventPublisher;

    /**
     * 请求参数检查
     *
     * @param dto DTO对象
     */
    public void check(AddDto dto) {
        dto.setUsername(StrUtil.trim(dto.getUsername()));
        dto.setPassword(StrUtil.trim(dto.getPassword()));
        dto.setPhoneNumber(StrUtil.trim(dto.getPhoneNumber()));
        dto.setRealName(StrUtil.trim(dto.getRealName()));
        dto.setNickName(StrUtil.trim(dto.getNickName()));
        dto.setEmail(StrUtil.trim(dto.getEmail()));
        dto.setRemark(StrUtil.trim(dto.getRemark()));

        if (tbBasicSysUserDao.cnt(TbBasicSysUserPo.builder().whereNotId(StpUtil.getLoginIdAsInt()).whereUsername(dto.getUsername()).build()) > 0) {
            throw new ServerException("用户名已存在");
        }

        if (StrUtil.isNotBlank(dto.getPhoneNumber())) {
            if (tbBasicSysUserDao.cnt(TbBasicSysUserPo.builder().whereNotId(StpUtil.getLoginIdAsInt()).wherePhoneNumber(dto.getPhoneNumber()).build()) > 0) {
                throw new ServerException("手机号已存在");
            }
        }

        if (dto.getRoleIds().stream().distinct().toList().size() != dto.getRoleIds().size()) {
            throw new ServerException("角色id列表存在重复值");
        }

        dto.getRoleIds().forEach(roleId -> {
            if (tbBasicSysRoleDao.cnt(TbBasicSysRolePo.builder().whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).whereId(roleId).build()) == 0) {
                throw new ServerException("部分角色数据不存在");
            }
        });
    }

    /**
     * 业务逻辑处理
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(AddDto dto) {
        TbBasicSysUser addEntity = handleAddData(dto);
        if (tbBasicSysUserDao.add(addEntity) != 1) {
            throw new ServerException("用户添加失败");
        }

        // 保存用户角色关系记录
        for (Integer roleId : dto.getRoleIds()) {
            if (userRoleDao.add(TbBasicSysUserRoleRelation.builder()
                    .userId(addEntity.getId())
                    .roleId(roleId)
                    .createdUserId(StpUtil.getLoginIdAsInt())
                    .updatedUserId(StpUtil.getLoginIdAsInt())
                    .build()) == 0) {
                throw new ServerException("角色添加失败");
            }
        }
        eventPublisher.publishEvent(new UserRegisterEvent(this, UserRegisterEntity.builder().userId(addEntity.getId()).request(FunctionUtil.getRequest()).build())); // 发布事件
        return Result.ok("用户添加成功", Map.of("id", addEntity.getId()));
    }


    /**
     * 处理要添加的数据
     *
     * @param dto DTO对象
     * @return 处理后的数据
     */
    private TbBasicSysUser handleAddData(AddDto dto) {
        TbBasicSysUser addEntity = new TbBasicSysUser();
        BeanUtils.copyProperties(dto, addEntity);

        addEntity.setPassword(DigestUtil.md5Hex(dto.getPassword()));
        addEntity.setSource(UserSourceEnum.ADMIN_ADDITION.getCode());
        addEntity.setInviteCode(FunctionUtil.randomStr(6, true, true, true));
        addEntity.setCreatedUserId(StpUtil.getLoginIdAsInt());
        addEntity.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        return addEntity;
    }
}
