package com.api.basic.service.sys.menu.query;

import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysMenuDao;
import com.api.basic.dao.TbBasicSysRoleDao;
import com.api.basic.dao.TbBasicSysUserRoleRelationDao;
import com.api.basic.data.entity.TbBasicSysMenu;
import com.api.basic.data.po.TbBasicSysMenuPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.vo.BaseSelectTreeVo;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.enums.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 获取树形结构菜单下拉列表数据
 */
@Service("BasicSysMenuQuerySelectTreeServiceImpl")
@RequiredArgsConstructor
public class SelectTree extends AbstractService {

    protected final TbBasicSysUserRoleRelationDao tbBasicSysUserRoleRelationDao;
    protected final TbBasicSysMenuDao tbBasicSysMenuDao;
    protected final TbBasicSysRoleDao tbBasicSysRoleDao;

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    public Result<?> service() {
        List<TbBasicSysMenu> menuEntityList = tbBasicSysMenuDao.query(handleQueryData());
        List<BaseSelectTreeVo> treeList = buildTree(menuEntityList);
        return Result.ok(treeList);
    }

    /**
     * 处理要查询的条件
     *
     * @return 处理后的数据
     */
    private TbBasicSysMenuPo handleQueryData() {
        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereStatus(StatusEnum.ENABLE.getCode());
        po.setWhereIsDelete(IsDeleteEnum.NO_DELETE.getCode());
        return po;
    }

    /**
     * 将菜单列表构建成属性结构
     *
     * @param menuList 菜单列表
     * @return 处理后的菜单树
     */
    protected List<BaseSelectTreeVo> buildTree(List<TbBasicSysMenu> menuList) {
        List<BaseSelectTreeVo> treeList = new ArrayList<>();
        if (menuList != null && !menuList.isEmpty()) {
            for (TbBasicSysMenu entity : menuList) {
                // 将菜单添加到节点树
                treeList = addTree(treeList, entity);
                if (!isExist(treeList, entity)) {
                    treeList.add(transfer(entity));
                }
            }
        }
        return sort(treeList);
    }

    /**
     * 对treeList每一层根据order进行升序排列
     *
     * @param menuList 菜单列表
     * @return 排序后的菜单列表
     */
    protected List<BaseSelectTreeVo> sort(List<BaseSelectTreeVo> menuList) {
        if (menuList != null && !menuList.isEmpty()) {
            // 对当前层进行排序
            menuList.sort(Comparator.comparing(BaseSelectTreeVo::getOrder));

            // 遍历每一项并递归处理子节点
            for (BaseSelectTreeVo menuNode : menuList) {
                // 获取子节点列表
                List<BaseSelectTreeVo> children = menuNode.getChildren();

                // 检查子节点列表是否为null或为空
                if (children != null && !children.isEmpty()) {
                    // 对子节点进行排序
                    children.sort(Comparator.comparing(BaseSelectTreeVo::getOrder));
                    // 递归处理子节点
                    sort(children);
                }
            }
        }
        return menuList;
    }

    /**
     * entity转vo
     *
     * @param menu entity对象
     * @return 转换后的vo对象
     */
    protected BaseSelectTreeVo transfer(TbBasicSysMenu menu) {
        BaseSelectTreeVo vo = new BaseSelectTreeVo();
        vo.setId(menu.getId());
        vo.setTitle(menu.getTitle());
//        vo.setSpread(true);
        vo.setChildren(null);
        vo.setOrder(menu.getOrder());
        vo.setIcon(StrUtil.isBlank(menu.getIcon()) ? "" : menu.getIcon());
        vo.setType(menu.getType());
        return vo;
    }

    /**
     * 将菜单根据parentID添加到节点树中
     *
     * @param treeList 节点数
     * @param menu     菜单
     * @return 节点树
     */
    protected List<BaseSelectTreeVo> addTree(List<BaseSelectTreeVo> treeList, TbBasicSysMenu menu) {
        for (BaseSelectTreeVo value : treeList) {
            List<BaseSelectTreeVo> children = value.getChildren();

            if (value.getId() == menu.getParentId()) {
                if (children == null) {
                    children = new ArrayList<>();
                }
                children.add(transfer(menu));
                value.setChildren(children);
            } else if (value.getChildren() != null) { // 判断是否有子节点
                value.setChildren(addTree(value.getChildren(), menu));
            }
        }
        return treeList;
    }

    /**
     * 判断菜单是否已经在菜单树中
     *
     * @param treeList 节点树
     * @param menu     菜单
     * @return true: 已存在，false：不存在
     */
    protected boolean isExist(List<BaseSelectTreeVo> treeList, TbBasicSysMenu menu) {
        boolean rst = false;
        for (BaseSelectTreeVo menuNode : treeList) {
            if (menuNode.getId() == menu.getId()) {
                rst = true;
            } else if (menuNode.getChildren() != null && !menuNode.getChildren().isEmpty()) {
                rst = isExist(menuNode.getChildren(), menu);
            }
            if (rst) {
                break;
            }
        }
        return rst;
    }
}
