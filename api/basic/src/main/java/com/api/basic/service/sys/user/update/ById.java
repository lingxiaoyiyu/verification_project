package com.api.basic.service.sys.user.update;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysRoleDao;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.dao.TbBasicSysUserRoleRelationDao;
import com.api.basic.data.dto.sys.user.update.info.ByIdDto;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.entity.TbBasicSysUserRoleRelation;
import com.api.basic.data.po.TbBasicSysRolePo;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.basic.data.po.TbBasicSysUserRoleRelationPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.exception.ServerException;
import com.api.common.utils.FunctionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 更新指定用户的信息
 */
@Service("BasicSysUserUpdateByIdServiceImpl")
@RequiredArgsConstructor
public class ById extends AbstractService {

    private final TbBasicSysUserDao tbBasicSysUserDao;
    private final TbBasicSysRoleDao tbBasicSysRoleDao;
    private final TbBasicSysUserRoleRelationDao tbBasicSysUserRoleRelationDao;

    /**
     * 请求参数检查
     *
     * @param dto DTO对象
     */
    public void check(ByIdDto dto) {
        TbBasicSysUser entity = tbBasicSysUserDao.get(TbBasicSysUserPo.builder().whereId(dto.getId()).build());

        if (entity == null) {
            throw new ServerException(FunctionUtil.getI18nString("service.user.notExist"));
        }

        if (StrUtil.isNotBlank(dto.getUpdatedAt()) && !dto.getUpdatedAt().equals(entity.getUpdatedAt())) {
            throw new ServerException(FunctionUtil.getI18nString("service.user.infoModified"));
        }

        if (tbBasicSysUserDao.cnt(TbBasicSysUserPo.builder().whereNotId(dto.getId()).whereUsername(dto.getUsername()).build()) != 0) {
            throw new ServerException(FunctionUtil.getI18nString("service.user.username.exists"));
        }

        if (StrUtil.isNotBlank(dto.getPhoneNumber()) && tbBasicSysUserDao.cnt(TbBasicSysUserPo.builder().whereNotId(dto.getId()).wherePhoneNumber(dto.getPhoneNumber()).build()) != 0) {
            throw new ServerException(FunctionUtil.getI18nString("service.user.phoneNumber.exists"));
        }

        if (StrUtil.isNotBlank(dto.getEmail()) && tbBasicSysUserDao.cnt(TbBasicSysUserPo.builder().whereNotId(dto.getId()).whereEmail(dto.getEmail()).build()) != 0) {
            throw new ServerException(FunctionUtil.getI18nString("service.user.email.exists"));
        }

        dto.getRoleIds().forEach(roleId -> {
            if (tbBasicSysRoleDao.cnt(TbBasicSysRolePo.builder().whereId(roleId).build()) == 0) {
                throw new ServerException(FunctionUtil.getI18nString("service.user.role.notExist"));
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
    public Result<?> service(ByIdDto dto) {
        Integer userId = dto.getId();
        Integer userIdOperator = StpUtil.getLoginIdAsInt();
        TbBasicSysUserPo userPo = handleUpdateData(dto);
        if (tbBasicSysUserDao.update(userPo) != 1) {
            throw new ServerException(FunctionUtil.getI18nString("service.user.info.updateFailed"));
        }

        List<TbBasicSysUserRoleRelation> userRoleList = tbBasicSysUserRoleRelationDao.query(TbBasicSysUserRoleRelationPo.builder().whereUserId(userId).whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).build());

        // 对比两个列表，找出需要删除的角色
        userRoleList.forEach(userRole -> {
            if (!dto.getRoleIds().contains(userRole.getRoleId())) {
                if (tbBasicSysUserRoleRelationDao.update(TbBasicSysUserRoleRelationPo.builder()
                        .whereRoleId(userRole.getRoleId())
                        .whereUserId(userId)
                        .isDelete(IsDeleteEnum.DELETED.getCode())
                        .updatedAt(userRole.getUpdatedAt())
                        .updatedUserId(userIdOperator)
                        .build()) != 1) {
                    throw new ServerException(FunctionUtil.getI18nString("service.user.role.relation.deleteFailed"));
                }
            }
        });

        // 对比两个列表，找出需要添加的角色
        dto.getRoleIds().forEach(roleId -> {
            if (userRoleList.stream().noneMatch(userRole -> userRole.getRoleId().equals(roleId))) {
                if (tbBasicSysUserRoleRelationDao.add(TbBasicSysUserRoleRelation.builder()
                        .userId(userId)
                        .roleId(roleId)
                        .createdUserId(userIdOperator)
                        .updatedUserId(userIdOperator)
                        .build()) != 1) {
                    throw new ServerException(FunctionUtil.getI18nString("service.user.role.relation.addFailed"));
                }
            }
        });
        Map<String, String> data = new HashMap<>();
        data.put("updatedAt", tbBasicSysUserDao.getFieldById(dto.getId(), TbBasicSysUser::getUpdatedAt).orElse(""));
        return Result.ok(data);
    }

    /**
     * 处理要更新的数据
     *
     * @return 处理后的数据
     */
    private TbBasicSysUserPo handleUpdateData(ByIdDto dto) {
        TbBasicSysUserPo updatePo = new TbBasicSysUserPo();
        BeanUtils.copyProperties(dto, updatePo);
        updatePo.setWhereId(dto.getId());
        updatePo.setUpdatedUserId(StpUtil.getLoginIdAsInt());

        return updatePo;
    }
}
