package com.api.basic.service.sys.role;

import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysMenuDao;
import com.api.basic.dao.TbBasicSysRoleDao;
import com.api.basic.dao.TbBasicSysRoleMenuRelationDao;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.dto.sys.role.GetDto;
import com.api.basic.data.entity.TbBasicSysMenu;
import com.api.basic.data.entity.TbBasicSysRole;
import com.api.basic.data.entity.TbBasicSysRoleMenuRelation;
import com.api.basic.data.po.TbBasicSysMenuPo;
import com.api.basic.data.po.TbBasicSysRoleMenuRelationPo;
import com.api.basic.data.po.TbBasicSysRolePo;
import com.api.basic.data.vo.sys.role.GetVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.enums.StatusEnum;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 获取指定角色详细信息
 */
@Service("BasicSysRoleGetServiceImpl")
@RequiredArgsConstructor
public class Get extends AbstractService {

    protected final TbBasicSysUserDao tbBasicSysUserDao;
    protected final TbBasicSysRoleDao tbBasicSysRoleDao;
    protected final TbBasicSysRoleMenuRelationDao tbBasicSysRoleMenuRelationDao;
    protected final TbBasicSysMenuDao tbBasicSysMenuDao;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(GetDto dto) {
        if (StrUtil.isBlank(tbBasicSysRoleDao.getFieldById(dto.getId(), TbBasicSysRole::getUpdatedAt).orElse(""))) {
            throw new ServerException("角色不存在");
        }
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    public Result<?> service(GetDto dto) {
        TbBasicSysRole entity = tbBasicSysRoleDao.get(TbBasicSysRolePo.builder().whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).whereId(dto.getId()).build());
        GetVo vo = new GetVo();
        BeanUtils.copyProperties(entity, vo);

        vo.setCreatedUserName(tbBasicSysUserDao.getById(entity.getCreatedUserId()).getUsername());
        vo.setUpdatedUserName(tbBasicSysUserDao.getById(entity.getUpdatedUserId()).getUsername());
        List<TbBasicSysMenu> menuList = null;
        if (dto.getId() == 1) { // 系统管理员
            menuList = tbBasicSysMenuDao.query(TbBasicSysMenuPo.builder().whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).whereStatus(StatusEnum.ENABLE.getCode()).build());
        } else {
            List<Integer> menuIdList = tbBasicSysRoleMenuRelationDao.query(TbBasicSysRoleMenuRelationPo.builder().whereRoleId(dto.getId()).whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).build()).stream().map(TbBasicSysRoleMenuRelation::getMenuId).toList();
            if (!menuIdList.isEmpty()) {
                menuList = tbBasicSysMenuDao.query(TbBasicSysMenuPo.builder().whereInIds(menuIdList).whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).whereStatus(StatusEnum.ENABLE.getCode()).build());
            }
        }
        if (menuList != null && !menuList.isEmpty()) {
            vo.setMenuIdList(menuList.stream()
                    .map(TbBasicSysMenu::getId)
                    .toList());
            vo.setMenuNames(menuList.stream().map(TbBasicSysMenu::getTitle).toList());
        }

        return Result.ok(vo);
    }
}
