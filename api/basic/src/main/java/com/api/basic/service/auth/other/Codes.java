package com.api.basic.service.auth.other;

import com.api.basic.dao.TbBasicSysMenuDao;
import com.api.basic.dao.TbBasicSysRoleDao;
import com.api.basic.dao.TbBasicSysRoleMenuRelationDao;
import com.api.basic.dao.TbBasicSysUserRoleRelationDao;
import com.api.basic.data.entity.TbBasicSysMenu;
import com.api.basic.data.enums.MenuTypeEnum;
import com.api.common.base.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 获取用户权限码
 */
@Service("BasicAuthOtherCodesServiceImpl")
public class Codes extends AbstractMenu {

    public Codes(TbBasicSysUserRoleRelationDao tbBasicSysUserRoleRelationDao, TbBasicSysRoleMenuRelationDao tbBasicSysRoleMenuRelationDao, TbBasicSysMenuDao tbBasicSysMenuDao, TbBasicSysRoleDao tbBasicSysRoleDao) {
        super(tbBasicSysUserRoleRelationDao, tbBasicSysRoleMenuRelationDao, tbBasicSysMenuDao, tbBasicSysRoleDao);
    }

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    public Result<?> service() {
        // 从roleEnableMenu中获取权限值
        return Result.ok(getUserMenus(List.of(MenuTypeEnum.PERMISSION.getCode())).stream().map(TbBasicSysMenu::getIdentifying).toList());
    }
}
