package com.api.basic.mapper;

import com.api.basic.data.entity.TbBasicSysMenu;
import com.api.basic.data.po.TbBasicSysMenuPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.math.BigDecimal;

/**
 * 菜单信息表Mapper
 *
 * @author 裴金伟
 */
@Repository
@Mapper
public interface TbBasicSysMenuMapper {

    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicSysMenu entity);

    /**
     * 删除信息
     *
     * @param po 要删除的信息
     * @return 受影响的行数，即删除的数据数量
     */
    public Integer delete(TbBasicSysMenuPo po);

    /**
     * 更新信息
     *
     * @param po 要更新的信息
     * @return 受影响的行数，即更新的数据数量
     */
    public Integer update(TbBasicSysMenuPo po);

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<TbBasicSysMenu> query(TbBasicSysMenuPo po);

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    public Integer cnt(TbBasicSysMenuPo po);

    /**
     * 统计ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumId(TbBasicSysMenuPo po);

    /**
     * 统计ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxId(TbBasicSysMenuPo po);

    /**
     * 统计ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minId(TbBasicSysMenuPo po);

    /**
     * 统计ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgId(TbBasicSysMenuPo po);
    /**
     * 统计父级id的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumParentId(TbBasicSysMenuPo po);

    /**
     * 统计父级id的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxParentId(TbBasicSysMenuPo po);

    /**
     * 统计父级id的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minParentId(TbBasicSysMenuPo po);

    /**
     * 统计父级id的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgParentId(TbBasicSysMenuPo po);
    /**
     * 统计类型。 1：目录，2：页面，3：权限。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumType(TbBasicSysMenuPo po);

    /**
     * 统计类型。 1：目录，2：页面，3：权限。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxType(TbBasicSysMenuPo po);

    /**
     * 统计类型。 1：目录，2：页面，3：权限。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minType(TbBasicSysMenuPo po);

    /**
     * 统计类型。 1：目录，2：页面，3：权限。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgType(TbBasicSysMenuPo po);
    /**
     * 统计状态。1：正常，2：禁用。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumStatus(TbBasicSysMenuPo po);

    /**
     * 统计状态。1：正常，2：禁用。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxStatus(TbBasicSysMenuPo po);

    /**
     * 统计状态。1：正常，2：禁用。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minStatus(TbBasicSysMenuPo po);

    /**
     * 统计状态。1：正常，2：禁用。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgStatus(TbBasicSysMenuPo po);
    /**
     * 统计删除状态。1：未删除，2：已删除。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumIsDelete(TbBasicSysMenuPo po);

    /**
     * 统计删除状态。1：未删除，2：已删除。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxIsDelete(TbBasicSysMenuPo po);

    /**
     * 统计删除状态。1：未删除，2：已删除。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minIsDelete(TbBasicSysMenuPo po);

    /**
     * 统计删除状态。1：未删除，2：已删除。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgIsDelete(TbBasicSysMenuPo po);
    /**
     * 统计用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumOrder(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxOrder(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minOrder(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgOrder(TbBasicSysMenuPo po);
    /**
     * 统计用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumKeepalive(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxKeepalive(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minKeepalive(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgKeepalive(TbBasicSysMenuPo po);
    /**
     * 统计用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumHideInMenu(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxHideInMenu(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minHideInMenu(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgHideInMenu(TbBasicSysMenuPo po);
    /**
     * 统计用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumHideInTab(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxHideInTab(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minHideInTab(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgHideInTab(TbBasicSysMenuPo po);
    /**
     * 统计用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumHideChildrenInMenu(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxHideChildrenInMenu(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minHideChildrenInMenu(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgHideChildrenInMenu(TbBasicSysMenuPo po);
    /**
     * 统计用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumActiveId(TbBasicSysMenuPo po);

    /**
     * 统计用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxActiveId(TbBasicSysMenuPo po);

    /**
     * 统计用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minActiveId(TbBasicSysMenuPo po);

    /**
     * 统计用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgActiveId(TbBasicSysMenuPo po);
    /**
     * 统计用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumAffixTab(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxAffixTab(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minAffixTab(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgAffixTab(TbBasicSysMenuPo po);
    /**
     * 统计用于配置页面固定标签页的排序, 采用升序排序。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumAffixTabOrder(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面固定标签页的排序, 采用升序排序。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxAffixTabOrder(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面固定标签页的排序, 采用升序排序。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minAffixTabOrder(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面固定标签页的排序, 采用升序排序。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgAffixTabOrder(TbBasicSysMenuPo po);
    /**
     * 统计用于配置页面是否忽略权限，直接可以访问。1：是，2：否的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumIgnoreAccess(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面是否忽略权限，直接可以访问。1：是，2：否的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxIgnoreAccess(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面是否忽略权限，直接可以访问。1：是，2：否的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minIgnoreAccess(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面是否忽略权限，直接可以访问。1：是，2：否的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgIgnoreAccess(TbBasicSysMenuPo po);
    /**
     * 统计用于配置是否是内嵌页面的iframe地址的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumIsIframe(TbBasicSysMenuPo po);

    /**
     * 统计用于配置是否是内嵌页面的iframe地址的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxIsIframe(TbBasicSysMenuPo po);

    /**
     * 统计用于配置是否是内嵌页面的iframe地址的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minIsIframe(TbBasicSysMenuPo po);

    /**
     * 统计用于配置是否是内嵌页面的iframe地址的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgIsIframe(TbBasicSysMenuPo po);
    /**
     * 统计用于配置是否是外链跳转路径。1：是，2：否的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumIsLink(TbBasicSysMenuPo po);

    /**
     * 统计用于配置是否是外链跳转路径。1：是，2：否的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxIsLink(TbBasicSysMenuPo po);

    /**
     * 统计用于配置是否是外链跳转路径。1：是，2：否的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minIsLink(TbBasicSysMenuPo po);

    /**
     * 统计用于配置是否是外链跳转路径。1：是，2：否的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgIsLink(TbBasicSysMenuPo po);
    /**
     * 统计用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumMaxNumOfOpenTab(TbBasicSysMenuPo po);

    /**
     * 统计用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxMaxNumOfOpenTab(TbBasicSysMenuPo po);

    /**
     * 统计用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minMaxNumOfOpenTab(TbBasicSysMenuPo po);

    /**
     * 统计用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgMaxNumOfOpenTab(TbBasicSysMenuPo po);
    /**
     * 统计用于配置页面是否在新窗口中打开。1：是，2：否的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumOpenInNewWindow(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面是否在新窗口中打开。1：是，2：否的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxOpenInNewWindow(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面是否在新窗口中打开。1：是，2：否的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minOpenInNewWindow(TbBasicSysMenuPo po);

    /**
     * 统计用于配置页面是否在新窗口中打开。1：是，2：否的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgOpenInNewWindow(TbBasicSysMenuPo po);
    /**
     * 统计是否在管理后台首页导航中显示。1：是，2：否的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumShowInAdminHome(TbBasicSysMenuPo po);

    /**
     * 统计是否在管理后台首页导航中显示。1：是，2：否的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxShowInAdminHome(TbBasicSysMenuPo po);

    /**
     * 统计是否在管理后台首页导航中显示。1：是，2：否的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minShowInAdminHome(TbBasicSysMenuPo po);

    /**
     * 统计是否在管理后台首页导航中显示。1：是，2：否的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgShowInAdminHome(TbBasicSysMenuPo po);
    /**
     * 统计创建者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumCreatedUserId(TbBasicSysMenuPo po);

    /**
     * 统计创建者的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxCreatedUserId(TbBasicSysMenuPo po);

    /**
     * 统计创建者的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minCreatedUserId(TbBasicSysMenuPo po);

    /**
     * 统计创建者的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgCreatedUserId(TbBasicSysMenuPo po);
    /**
     * 统计最后更新者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    BigDecimal sumUpdatedUserId(TbBasicSysMenuPo po);

    /**
     * 统计最后更新者的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    BigDecimal maxUpdatedUserId(TbBasicSysMenuPo po);

    /**
     * 统计最后更新者的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    BigDecimal minUpdatedUserId(TbBasicSysMenuPo po);

    /**
     * 统计最后更新者的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    BigDecimal avgUpdatedUserId(TbBasicSysMenuPo po);
}
