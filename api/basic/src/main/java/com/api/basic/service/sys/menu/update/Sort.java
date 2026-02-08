package com.api.basic.service.sys.menu.update;

import cn.dev33.satoken.stp.StpUtil;
import com.api.basic.dao.TbBasicSysMenuDao;
import com.api.basic.data.dto.sys.menu.update.SortDto;
import com.api.basic.data.entity.TbBasicSysMenu;
import com.api.basic.data.po.TbBasicSysMenuPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.po.BasePageSortList;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 更新菜单排序
 *
 * @author 裴金伟
 * @date 2025-02-19
 */
@Service("BasicSysMenuUpdateSortServiceImpl")
@RequiredArgsConstructor
public class Sort extends AbstractService {

    private final TbBasicSysMenuDao tbBasicSysMenuDao;

    /**
     * 参数检查
     */
    public void check(SortDto dto) {
        TbBasicSysMenu entity = tbBasicSysMenuDao.get(TbBasicSysMenuPo.builder().whereId(dto.getId()).whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).build());

        if (entity == null) {
            throw new ServerException("菜单信息不存在");
        }
    }

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(SortDto dto) {
        // 获取需要调整的菜单
        TbBasicSysMenuPo targetMenuPo = new TbBasicSysMenuPo();
        targetMenuPo.setWhereId(dto.getTargetId());
        TbBasicSysMenu targetMenu = tbBasicSysMenuDao.get(targetMenuPo);
        if (targetMenu == null) {
            throw new ServerException("目标菜单不存在");
        }

        // 获取需要移动的菜单
        TbBasicSysMenuPo menuToMovePo = new TbBasicSysMenuPo();
        menuToMovePo.setWhereId(dto.getId());
        TbBasicSysMenu menuToMove = tbBasicSysMenuDao.get(menuToMovePo);
        if (menuToMove == null) {
            throw new ServerException("要移动的菜单不存在");
        }

        // 获取所有同级菜单
        TbBasicSysMenuPo siblingMenusPo = new TbBasicSysMenuPo();
        siblingMenusPo.setWhereParentId(menuToMove.getParentId());
        siblingMenusPo.setWhereIsDelete(IsDeleteEnum.NO_DELETE.getCode());
        siblingMenusPo.setSortList(new BasePageSortList().addSort("order").getSortList());
        List<TbBasicSysMenu> siblingMenus = tbBasicSysMenuDao.query(siblingMenusPo);

        // 找到要移动的菜单和目标菜单在列表中的索引
        int targetIndex = -1;
        int menuToMoveIndex = -1;
        for (int i = 0; i < siblingMenus.size(); i++) {
            if (siblingMenus.get(i).getId().equals(targetMenu.getId())) {
                targetIndex = i;
            }
            if (siblingMenus.get(i).getId().equals(menuToMove.getId())) {
                menuToMoveIndex = i;
            }
        }

        if (targetIndex == -1 || menuToMoveIndex == -1) {
            throw new ServerException("菜单索引错误");
        }

        // 根据 direction 调整指定菜单在列表中的位置
        if ("top".equalsIgnoreCase(dto.getDirection())) {
            if (menuToMoveIndex > 0) {
                // 交换位置
                TbBasicSysMenu temp = siblingMenus.get(menuToMoveIndex);
                siblingMenus.set(menuToMoveIndex, siblingMenus.get(menuToMoveIndex - 1));
                siblingMenus.set(menuToMoveIndex - 1, temp);
            }
        } else if ("bottom".equalsIgnoreCase(dto.getDirection())) {
            if (menuToMoveIndex < siblingMenus.size() - 1) {
                // 交换位置
                TbBasicSysMenu temp = siblingMenus.get(menuToMoveIndex);
                siblingMenus.set(menuToMoveIndex, siblingMenus.get(menuToMoveIndex + 1));
                siblingMenus.set(menuToMoveIndex + 1, temp);
            }
        } else {
            throw new ServerException("无效的方向参数");
        }

        // 重新排列同级菜单的排序值，从 1 开始
        int currentOrder = 1;
        for (TbBasicSysMenu siblingMenu : siblingMenus) {
            siblingMenu.setOrder(currentOrder++);
        }

        // 更新数据库中的排序值
        for (TbBasicSysMenu siblingMenu : siblingMenus) {
            TbBasicSysMenuPo updatePo = new TbBasicSysMenuPo();
            BeanUtils.copyProperties(siblingMenu, updatePo);
            updatePo.setWhereId(siblingMenu.getId());
            updatePo.setUpdatedUserId(StpUtil.getLoginIdAsInt());
            if (tbBasicSysMenuDao.update(updatePo) != 1) {
                throw new ServerException("更新失败");
            }
        }

        Map<String, String> data = new HashMap<>();
        data.put("updatedAt", tbBasicSysMenuDao.getFieldById(dto.getId(), TbBasicSysMenu::getUpdatedAt).orElse(""));

        return Result.ok("更新成功", data);
    }
}
