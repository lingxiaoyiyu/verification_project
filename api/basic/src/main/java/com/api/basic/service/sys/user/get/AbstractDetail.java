package com.api.basic.service.sys.user.get;

import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysRoleDao;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.dao.TbBasicSysUserRoleRelationDao;
import com.api.basic.data.entity.TbBasicSysRole;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.entity.TbBasicSysUserRoleRelation;
import com.api.basic.data.enums.UserSourceEnum;
import com.api.basic.data.po.TbBasicSysRolePo;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.basic.data.po.TbBasicSysUserRoleRelationPo;
import com.api.basic.data.vo.sys.user.GetVo;
import com.api.common.base.AbstractService;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.enums.StatusEnum;
import com.api.common.exception.ServerException;
import com.api.common.utils.FunctionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class AbstractDetail extends AbstractService {

    private final TbBasicSysUserRoleRelationDao userRoleDao;
    protected final TbBasicSysUserDao tbBasicSysUserDao;
    protected final TbBasicSysUserRoleRelationDao tbBasicSysUserRoleRelationDao;
    protected final TbBasicSysRoleDao tbBasicSysRoleDao;

    /**
     * 获取用户详情
     *
     * @param userId 用户ID
     * @return 用户详情
     */
    protected GetVo getDetailVo(Integer userId) {
        TbBasicSysUser user = tbBasicSysUserDao.get(TbBasicSysUserPo.builder().whereId(userId).build());
        if (user == null) {
            throw new ServerException("用户不存在");
        }

        GetVo vo = new GetVo();
        BeanUtils.copyProperties(user, vo);
        vo.setPhoneNumber(StrUtil.blankToDefault(user.getPhoneNumber(), ""));
        vo.setRealName(StrUtil.blankToDefault(user.getRealName(), ""));
        vo.setNickName(StrUtil.blankToDefault(user.getNickName(), ""));
        vo.setAvatar(FunctionUtil.handleAvatar(user.getAvatar()));
        vo.setInviterUsername(Optional.ofNullable(tbBasicSysUserDao.getById(user.getInviter())).map(TbBasicSysUser::getUsername).orElse(""));
        vo.setSourceName(UserSourceEnum.fromCode(user.getSource()).getDescription());
        vo.setIntroduction(StrUtil.blankToDefault(user.getIntroduction(), ""));
        vo.setRemark(StrUtil.blankToDefault(user.getRemark(), ""));
        vo.setEmail(StrUtil.blankToDefault(user.getEmail(), ""));
        vo.setCreatedUserName(Optional.ofNullable(tbBasicSysUserDao.getById(user.getCreatedUserId())).map(TbBasicSysUser::getUsername).orElse(""));
        vo.setUpdatedUserName(Optional.ofNullable(tbBasicSysUserDao.getById(user.getUpdatedUserId())).map(TbBasicSysUser::getUsername).orElse(""));

        List<TbBasicSysUserRoleRelation> userRoles = tbBasicSysUserRoleRelationDao.query(TbBasicSysUserRoleRelationPo.builder().whereUserId(userId).whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).build());
        List<TbBasicSysRole> roles = new ArrayList<>();
        if (userRoles != null && !userRoles.isEmpty()) {
            roles = tbBasicSysRoleDao.query(TbBasicSysRolePo.builder().whereInIds(userRoles.stream().map(TbBasicSysUserRoleRelation::getRoleId).toList()).whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).whereStatus(StatusEnum.ENABLE.getCode()).build());
        }
        vo.setRoleIds(roles.stream().map(TbBasicSysRole::getId).toList());
        vo.setRoleNames(roles.stream().map(TbBasicSysRole::getName).toList());
        vo.setRoles(roles.stream().map(TbBasicSysRole::getIdentifying).toList());
        vo.setHomePath("/");
        vo.setDepartmentName("");
        vo.setWechatUnionid("");
        vo.setWechatOfficialAccountOpenid("");
        vo.setWujieUnionid(user.getWujieUnionid());
        return vo;
    }
}
