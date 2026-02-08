package com.api.basic.dao;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.data.entity.TbBasicSysMenu;
import com.api.basic.data.po.TbBasicSysMenuPo;
import com.api.basic.mapper.TbBasicSysMenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

/**
 * 菜单信息表Dao
 *
 * @author 裴金伟
 */
@Component
@RequiredArgsConstructor
public class TbBasicSysMenuDao {

    private final TbBasicSysMenuMapper mapper;

    //***************************************************添加 START******************************************************
    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbBasicSysMenu entity) {
        if (entity == null) {
            return 0;
        }
        return mapper.add(entity);
    }
    //***************************************************添加 END********************************************************

    //***************************************************删除 START******************************************************
    /**
     * 删除信息
     *
     * @param po 要删除的信息
     * @return 受影响的行数，即删除的数据数量
     */
    public Integer delete(TbBasicSysMenuPo po) {
        if (po == null) {
            return 0;
        }
        return mapper.delete(po);
    }
    //***************************************************删除 END********************************************************


    //***************************************************更新 START******************************************************
    /**
     * 更新信息
     *
     * @param po 要更新的信息
     * @return 受影响的行数，即更新的数据数量
     */
    public Integer update(TbBasicSysMenuPo po) {
        if (po == null) {
            return 0;
        }
        return mapper.update(po);
    }
    //***************************************************更新 END********************************************************

    //***************************************************获取 START******************************************************
    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<TbBasicSysMenu> query(TbBasicSysMenuPo po) {
        if (po == null) {
            return null;
        }
        return mapper.query(po);
    }

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    public Integer cnt(TbBasicSysMenuPo po) {
        if (po == null) {
            return 0;
        }
        return mapper.cnt(po);
    }

    /**
     * 查询单条数据
     *
     * @param po 查询条件
     * @return 查询结果
     */
    public TbBasicSysMenu get(TbBasicSysMenuPo po) {
        if (po == null) {
            return null;
        }
        return mapper.query(po).stream().findFirst().orElse(null);
    }

    /**
     * 根据ID查询单条数据
     *
     * @param id ID
     * @return 查询结果
     */
    public TbBasicSysMenu getById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereId(id);
        return get(po);
    }

    /**
     * 根据ID查询多条数据
     *
     * @param id ID
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryById(Integer id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereId(id);
        return query(po);
    }

    /**
     * 根据ID获取指定字段的值
     *
     * @param id ID
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldById(Integer id, Function<TbBasicSysMenu, T> fieldMapper) {
        if (ObjectUtil.isNull(id)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getById(id)).map(fieldMapper);
   }

    /**
     * 根据父级id查询单条数据
     *
     * @param parentId 父级id
     * @return 查询结果
     */
    public TbBasicSysMenu getByParentId(Integer parentId) {
        if (ObjectUtil.isNull(parentId)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereParentId(parentId);
        return get(po);
    }

    /**
     * 根据父级id查询多条数据
     *
     * @param parentId 父级id
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByParentId(Integer parentId) {
        if (ObjectUtil.isNull(parentId)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereParentId(parentId);
        return query(po);
    }

    /**
     * 根据父级id获取指定字段的值
     *
     * @param parentId 父级id
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByParentId(Integer parentId, Function<TbBasicSysMenu, T> fieldMapper) {
        if (ObjectUtil.isNull(parentId)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByParentId(parentId)).map(fieldMapper);
   }

    /**
     * 根据类型。 1：目录，2：页面，3：权限。查询单条数据
     *
     * @param type 类型。 1：目录，2：页面，3：权限。
     * @return 查询结果
     */
    public TbBasicSysMenu getByType(Integer type) {
        if (ObjectUtil.isNull(type)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereType(type);
        return get(po);
    }

    /**
     * 根据类型。 1：目录，2：页面，3：权限。查询多条数据
     *
     * @param type 类型。 1：目录，2：页面，3：权限。
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByType(Integer type) {
        if (ObjectUtil.isNull(type)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereType(type);
        return query(po);
    }

    /**
     * 根据类型。 1：目录，2：页面，3：权限。获取指定字段的值
     *
     * @param type 类型。 1：目录，2：页面，3：权限。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByType(Integer type, Function<TbBasicSysMenu, T> fieldMapper) {
        if (ObjectUtil.isNull(type)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByType(type)).map(fieldMapper);
   }

    /**
     * 根据菜单名查询单条数据
     *
     * @param name 菜单名
     * @return 查询结果
     */
    public TbBasicSysMenu getByName(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereName(name);
        return get(po);
    }

    /**
     * 根据菜单名查询多条数据
     *
     * @param name 菜单名
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByName(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereName(name);
        return query(po);
    }

    /**
     * 根据菜单名获取指定字段的值
     *
     * @param name 菜单名
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByName(String name, Function<TbBasicSysMenu, T> fieldMapper) {
        if (StrUtil.isBlank(name)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByName(name)).map(fieldMapper);
   }

    /**
     * 根据路由地址查询单条数据
     *
     * @param path 路由地址
     * @return 查询结果
     */
    public TbBasicSysMenu getByPath(String path) {
        if (StrUtil.isBlank(path)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWherePath(path);
        return get(po);
    }

    /**
     * 根据路由地址查询多条数据
     *
     * @param path 路由地址
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByPath(String path) {
        if (StrUtil.isBlank(path)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWherePath(path);
        return query(po);
    }

    /**
     * 根据路由地址获取指定字段的值
     *
     * @param path 路由地址
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByPath(String path, Function<TbBasicSysMenu, T> fieldMapper) {
        if (StrUtil.isBlank(path)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByPath(path)).map(fieldMapper);
   }

    /**
     * 根据组件地址查询单条数据
     *
     * @param component 组件地址
     * @return 查询结果
     */
    public TbBasicSysMenu getByComponent(String component) {
        if (StrUtil.isBlank(component)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereComponent(component);
        return get(po);
    }

    /**
     * 根据组件地址查询多条数据
     *
     * @param component 组件地址
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByComponent(String component) {
        if (StrUtil.isBlank(component)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereComponent(component);
        return query(po);
    }

    /**
     * 根据组件地址获取指定字段的值
     *
     * @param component 组件地址
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByComponent(String component, Function<TbBasicSysMenu, T> fieldMapper) {
        if (StrUtil.isBlank(component)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByComponent(component)).map(fieldMapper);
   }

    /**
     * 根据权限标识查询单条数据
     *
     * @param identifying 权限标识
     * @return 查询结果
     */
    public TbBasicSysMenu getByIdentifying(String identifying) {
        if (StrUtil.isBlank(identifying)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereIdentifying(identifying);
        return get(po);
    }

    /**
     * 根据权限标识查询多条数据
     *
     * @param identifying 权限标识
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByIdentifying(String identifying) {
        if (StrUtil.isBlank(identifying)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereIdentifying(identifying);
        return query(po);
    }

    /**
     * 根据权限标识获取指定字段的值
     *
     * @param identifying 权限标识
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByIdentifying(String identifying, Function<TbBasicSysMenu, T> fieldMapper) {
        if (StrUtil.isBlank(identifying)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByIdentifying(identifying)).map(fieldMapper);
   }

    /**
     * 根据状态。1：正常，2：禁用。查询单条数据
     *
     * @param status 状态。1：正常，2：禁用。
     * @return 查询结果
     */
    public TbBasicSysMenu getByStatus(Integer status) {
        if (ObjectUtil.isNull(status)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereStatus(status);
        return get(po);
    }

    /**
     * 根据状态。1：正常，2：禁用。查询多条数据
     *
     * @param status 状态。1：正常，2：禁用。
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByStatus(Integer status) {
        if (ObjectUtil.isNull(status)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereStatus(status);
        return query(po);
    }

    /**
     * 根据状态。1：正常，2：禁用。获取指定字段的值
     *
     * @param status 状态。1：正常，2：禁用。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByStatus(Integer status, Function<TbBasicSysMenu, T> fieldMapper) {
        if (ObjectUtil.isNull(status)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByStatus(status)).map(fieldMapper);
   }

    /**
     * 根据删除状态。1：未删除，2：已删除。查询单条数据
     *
     * @param isDelete 删除状态。1：未删除，2：已删除。
     * @return 查询结果
     */
    public TbBasicSysMenu getByIsDelete(Integer isDelete) {
        if (ObjectUtil.isNull(isDelete)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereIsDelete(isDelete);
        return get(po);
    }

    /**
     * 根据删除状态。1：未删除，2：已删除。查询多条数据
     *
     * @param isDelete 删除状态。1：未删除，2：已删除。
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByIsDelete(Integer isDelete) {
        if (ObjectUtil.isNull(isDelete)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereIsDelete(isDelete);
        return query(po);
    }

    /**
     * 根据删除状态。1：未删除，2：已删除。获取指定字段的值
     *
     * @param isDelete 删除状态。1：未删除，2：已删除。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByIsDelete(Integer isDelete, Function<TbBasicSysMenu, T> fieldMapper) {
        if (ObjectUtil.isNull(isDelete)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByIsDelete(isDelete)).map(fieldMapper);
   }

    /**
     * 根据菜单标题查询单条数据
     *
     * @param title 菜单标题
     * @return 查询结果
     */
    public TbBasicSysMenu getByTitle(String title) {
        if (StrUtil.isBlank(title)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereTitle(title);
        return get(po);
    }

    /**
     * 根据菜单标题查询多条数据
     *
     * @param title 菜单标题
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByTitle(String title) {
        if (StrUtil.isBlank(title)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereTitle(title);
        return query(po);
    }

    /**
     * 根据菜单标题获取指定字段的值
     *
     * @param title 菜单标题
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByTitle(String title, Function<TbBasicSysMenu, T> fieldMapper) {
        if (StrUtil.isBlank(title)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByTitle(title)).map(fieldMapper);
   }

    /**
     * 根据图标查询单条数据
     *
     * @param icon 图标
     * @return 查询结果
     */
    public TbBasicSysMenu getByIcon(String icon) {
        if (StrUtil.isBlank(icon)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereIcon(icon);
        return get(po);
    }

    /**
     * 根据图标查询多条数据
     *
     * @param icon 图标
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByIcon(String icon) {
        if (StrUtil.isBlank(icon)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereIcon(icon);
        return query(po);
    }

    /**
     * 根据图标获取指定字段的值
     *
     * @param icon 图标
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByIcon(String icon, Function<TbBasicSysMenu, T> fieldMapper) {
        if (StrUtil.isBlank(icon)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByIcon(icon)).map(fieldMapper);
   }

    /**
     * 根据用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。查询单条数据
     *
     * @param activeIcon 用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。
     * @return 查询结果
     */
    public TbBasicSysMenu getByActiveIcon(String activeIcon) {
        if (StrUtil.isBlank(activeIcon)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereActiveIcon(activeIcon);
        return get(po);
    }

    /**
     * 根据用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。查询多条数据
     *
     * @param activeIcon 用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByActiveIcon(String activeIcon) {
        if (StrUtil.isBlank(activeIcon)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereActiveIcon(activeIcon);
        return query(po);
    }

    /**
     * 根据用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。获取指定字段的值
     *
     * @param activeIcon 用于配置页面的激活图标，会在菜单中显示。一般会配合图标库使用，如果是http链接，会自动加载图片。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByActiveIcon(String activeIcon, Function<TbBasicSysMenu, T> fieldMapper) {
        if (StrUtil.isBlank(activeIcon)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByActiveIcon(activeIcon)).map(fieldMapper);
   }

    /**
     * 根据用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。查询单条数据
     *
     * @param order 用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。
     * @return 查询结果
     */
    public TbBasicSysMenu getByOrder(Integer order) {
        if (ObjectUtil.isNull(order)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereOrder(order);
        return get(po);
    }

    /**
     * 根据用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。查询多条数据
     *
     * @param order 用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByOrder(Integer order) {
        if (ObjectUtil.isNull(order)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereOrder(order);
        return query(po);
    }

    /**
     * 根据用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。获取指定字段的值
     *
     * @param order 用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByOrder(Integer order, Function<TbBasicSysMenu, T> fieldMapper) {
        if (ObjectUtil.isNull(order)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByOrder(order)).map(fieldMapper);
   }

    /**
     * 根据用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。查询单条数据
     *
     * @param keepalive 用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。
     * @return 查询结果
     */
    public TbBasicSysMenu getByKeepalive(Integer keepalive) {
        if (ObjectUtil.isNull(keepalive)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereKeepalive(keepalive);
        return get(po);
    }

    /**
     * 根据用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。查询多条数据
     *
     * @param keepalive 用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByKeepalive(Integer keepalive) {
        if (ObjectUtil.isNull(keepalive)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereKeepalive(keepalive);
        return query(po);
    }

    /**
     * 根据用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。获取指定字段的值
     *
     * @param keepalive 用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByKeepalive(Integer keepalive, Function<TbBasicSysMenu, T> fieldMapper) {
        if (ObjectUtil.isNull(keepalive)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByKeepalive(keepalive)).map(fieldMapper);
   }

    /**
     * 根据用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。查询单条数据
     *
     * @param hideInMenu 用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。
     * @return 查询结果
     */
    public TbBasicSysMenu getByHideInMenu(Integer hideInMenu) {
        if (ObjectUtil.isNull(hideInMenu)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereHideInMenu(hideInMenu);
        return get(po);
    }

    /**
     * 根据用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。查询多条数据
     *
     * @param hideInMenu 用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByHideInMenu(Integer hideInMenu) {
        if (ObjectUtil.isNull(hideInMenu)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereHideInMenu(hideInMenu);
        return query(po);
    }

    /**
     * 根据用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。获取指定字段的值
     *
     * @param hideInMenu 用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByHideInMenu(Integer hideInMenu, Function<TbBasicSysMenu, T> fieldMapper) {
        if (ObjectUtil.isNull(hideInMenu)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByHideInMenu(hideInMenu)).map(fieldMapper);
   }

    /**
     * 根据用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。查询单条数据
     *
     * @param hideInTab 用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。
     * @return 查询结果
     */
    public TbBasicSysMenu getByHideInTab(Integer hideInTab) {
        if (ObjectUtil.isNull(hideInTab)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereHideInTab(hideInTab);
        return get(po);
    }

    /**
     * 根据用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。查询多条数据
     *
     * @param hideInTab 用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByHideInTab(Integer hideInTab) {
        if (ObjectUtil.isNull(hideInTab)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereHideInTab(hideInTab);
        return query(po);
    }

    /**
     * 根据用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。获取指定字段的值
     *
     * @param hideInTab 用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByHideInTab(Integer hideInTab, Function<TbBasicSysMenu, T> fieldMapper) {
        if (ObjectUtil.isNull(hideInTab)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByHideInTab(hideInTab)).map(fieldMapper);
   }

    /**
     * 根据用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。查询单条数据
     *
     * @param hideChildrenInMenu 用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。
     * @return 查询结果
     */
    public TbBasicSysMenu getByHideChildrenInMenu(Integer hideChildrenInMenu) {
        if (ObjectUtil.isNull(hideChildrenInMenu)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereHideChildrenInMenu(hideChildrenInMenu);
        return get(po);
    }

    /**
     * 根据用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。查询多条数据
     *
     * @param hideChildrenInMenu 用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByHideChildrenInMenu(Integer hideChildrenInMenu) {
        if (ObjectUtil.isNull(hideChildrenInMenu)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereHideChildrenInMenu(hideChildrenInMenu);
        return query(po);
    }

    /**
     * 根据用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。获取指定字段的值
     *
     * @param hideChildrenInMenu 用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByHideChildrenInMenu(Integer hideChildrenInMenu, Function<TbBasicSysMenu, T> fieldMapper) {
        if (ObjectUtil.isNull(hideChildrenInMenu)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByHideChildrenInMenu(hideChildrenInMenu)).map(fieldMapper);
   }

    /**
     * 根据用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。查询单条数据
     *
     * @param authority 用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。
     * @return 查询结果
     */
    public TbBasicSysMenu getByAuthority(String authority) {
        if (StrUtil.isBlank(authority)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereAuthority(authority);
        return get(po);
    }

    /**
     * 根据用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。查询多条数据
     *
     * @param authority 用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByAuthority(String authority) {
        if (StrUtil.isBlank(authority)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereAuthority(authority);
        return query(po);
    }

    /**
     * 根据用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。获取指定字段的值
     *
     * @param authority 用于配置页面的权限，只有拥有对应权限的用户才能访问页面，不配置则不需要权限。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByAuthority(String authority, Function<TbBasicSysMenu, T> fieldMapper) {
        if (StrUtil.isBlank(authority)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByAuthority(authority)).map(fieldMapper);
   }

    /**
     * 根据用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。查询单条数据
     *
     * @param activeId 用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。
     * @return 查询结果
     */
    public TbBasicSysMenu getByActiveId(Integer activeId) {
        if (ObjectUtil.isNull(activeId)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereActiveId(activeId);
        return get(po);
    }

    /**
     * 根据用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。查询多条数据
     *
     * @param activeId 用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByActiveId(Integer activeId) {
        if (ObjectUtil.isNull(activeId)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereActiveId(activeId);
        return query(po);
    }

    /**
     * 根据用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。获取指定字段的值
     *
     * @param activeId 用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByActiveId(Integer activeId, Function<TbBasicSysMenu, T> fieldMapper) {
        if (ObjectUtil.isNull(activeId)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByActiveId(activeId)).map(fieldMapper);
   }

    /**
     * 根据用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否查询单条数据
     *
     * @param affixTab 用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否
     * @return 查询结果
     */
    public TbBasicSysMenu getByAffixTab(Integer affixTab) {
        if (ObjectUtil.isNull(affixTab)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereAffixTab(affixTab);
        return get(po);
    }

    /**
     * 根据用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否查询多条数据
     *
     * @param affixTab 用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByAffixTab(Integer affixTab) {
        if (ObjectUtil.isNull(affixTab)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereAffixTab(affixTab);
        return query(po);
    }

    /**
     * 根据用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否获取指定字段的值
     *
     * @param affixTab 用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByAffixTab(Integer affixTab, Function<TbBasicSysMenu, T> fieldMapper) {
        if (ObjectUtil.isNull(affixTab)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByAffixTab(affixTab)).map(fieldMapper);
   }

    /**
     * 根据用于配置页面固定标签页的排序, 采用升序排序。查询单条数据
     *
     * @param affixTabOrder 用于配置页面固定标签页的排序, 采用升序排序。
     * @return 查询结果
     */
    public TbBasicSysMenu getByAffixTabOrder(Integer affixTabOrder) {
        if (ObjectUtil.isNull(affixTabOrder)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereAffixTabOrder(affixTabOrder);
        return get(po);
    }

    /**
     * 根据用于配置页面固定标签页的排序, 采用升序排序。查询多条数据
     *
     * @param affixTabOrder 用于配置页面固定标签页的排序, 采用升序排序。
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByAffixTabOrder(Integer affixTabOrder) {
        if (ObjectUtil.isNull(affixTabOrder)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereAffixTabOrder(affixTabOrder);
        return query(po);
    }

    /**
     * 根据用于配置页面固定标签页的排序, 采用升序排序。获取指定字段的值
     *
     * @param affixTabOrder 用于配置页面固定标签页的排序, 采用升序排序。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByAffixTabOrder(Integer affixTabOrder, Function<TbBasicSysMenu, T> fieldMapper) {
        if (ObjectUtil.isNull(affixTabOrder)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByAffixTabOrder(affixTabOrder)).map(fieldMapper);
   }

    /**
     * 根据用于配置页面是否忽略权限，直接可以访问。1：是，2：否查询单条数据
     *
     * @param ignoreAccess 用于配置页面是否忽略权限，直接可以访问。1：是，2：否
     * @return 查询结果
     */
    public TbBasicSysMenu getByIgnoreAccess(Integer ignoreAccess) {
        if (ObjectUtil.isNull(ignoreAccess)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereIgnoreAccess(ignoreAccess);
        return get(po);
    }

    /**
     * 根据用于配置页面是否忽略权限，直接可以访问。1：是，2：否查询多条数据
     *
     * @param ignoreAccess 用于配置页面是否忽略权限，直接可以访问。1：是，2：否
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByIgnoreAccess(Integer ignoreAccess) {
        if (ObjectUtil.isNull(ignoreAccess)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereIgnoreAccess(ignoreAccess);
        return query(po);
    }

    /**
     * 根据用于配置页面是否忽略权限，直接可以访问。1：是，2：否获取指定字段的值
     *
     * @param ignoreAccess 用于配置页面是否忽略权限，直接可以访问。1：是，2：否
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByIgnoreAccess(Integer ignoreAccess, Function<TbBasicSysMenu, T> fieldMapper) {
        if (ObjectUtil.isNull(ignoreAccess)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByIgnoreAccess(ignoreAccess)).map(fieldMapper);
   }

    /**
     * 根据用于配置是否是内嵌页面的iframe地址查询单条数据
     *
     * @param isIframe 用于配置是否是内嵌页面的iframe地址
     * @return 查询结果
     */
    public TbBasicSysMenu getByIsIframe(Integer isIframe) {
        if (ObjectUtil.isNull(isIframe)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereIsIframe(isIframe);
        return get(po);
    }

    /**
     * 根据用于配置是否是内嵌页面的iframe地址查询多条数据
     *
     * @param isIframe 用于配置是否是内嵌页面的iframe地址
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByIsIframe(Integer isIframe) {
        if (ObjectUtil.isNull(isIframe)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereIsIframe(isIframe);
        return query(po);
    }

    /**
     * 根据用于配置是否是内嵌页面的iframe地址获取指定字段的值
     *
     * @param isIframe 用于配置是否是内嵌页面的iframe地址
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByIsIframe(Integer isIframe, Function<TbBasicSysMenu, T> fieldMapper) {
        if (ObjectUtil.isNull(isIframe)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByIsIframe(isIframe)).map(fieldMapper);
   }

    /**
     * 根据用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。查询单条数据
     *
     * @param iframeSrc 用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。
     * @return 查询结果
     */
    public TbBasicSysMenu getByIframeSrc(String iframeSrc) {
        if (StrUtil.isBlank(iframeSrc)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereIframeSrc(iframeSrc);
        return get(po);
    }

    /**
     * 根据用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。查询多条数据
     *
     * @param iframeSrc 用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByIframeSrc(String iframeSrc) {
        if (StrUtil.isBlank(iframeSrc)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereIframeSrc(iframeSrc);
        return query(po);
    }

    /**
     * 根据用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。获取指定字段的值
     *
     * @param iframeSrc 用于配置内嵌页面的 iframe 地址，设置后会在当前页面内嵌对应的页面。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByIframeSrc(String iframeSrc, Function<TbBasicSysMenu, T> fieldMapper) {
        if (StrUtil.isBlank(iframeSrc)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByIframeSrc(iframeSrc)).map(fieldMapper);
   }

    /**
     * 根据用于配置是否是外链跳转路径。1：是，2：否查询单条数据
     *
     * @param isLink 用于配置是否是外链跳转路径。1：是，2：否
     * @return 查询结果
     */
    public TbBasicSysMenu getByIsLink(Integer isLink) {
        if (ObjectUtil.isNull(isLink)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereIsLink(isLink);
        return get(po);
    }

    /**
     * 根据用于配置是否是外链跳转路径。1：是，2：否查询多条数据
     *
     * @param isLink 用于配置是否是外链跳转路径。1：是，2：否
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByIsLink(Integer isLink) {
        if (ObjectUtil.isNull(isLink)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereIsLink(isLink);
        return query(po);
    }

    /**
     * 根据用于配置是否是外链跳转路径。1：是，2：否获取指定字段的值
     *
     * @param isLink 用于配置是否是外链跳转路径。1：是，2：否
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByIsLink(Integer isLink, Function<TbBasicSysMenu, T> fieldMapper) {
        if (ObjectUtil.isNull(isLink)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByIsLink(isLink)).map(fieldMapper);
   }

    /**
     * 根据用于配置外链跳转路径，会在新窗口打开。查询单条数据
     *
     * @param link 用于配置外链跳转路径，会在新窗口打开。
     * @return 查询结果
     */
    public TbBasicSysMenu getByLink(String link) {
        if (StrUtil.isBlank(link)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereLink(link);
        return get(po);
    }

    /**
     * 根据用于配置外链跳转路径，会在新窗口打开。查询多条数据
     *
     * @param link 用于配置外链跳转路径，会在新窗口打开。
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByLink(String link) {
        if (StrUtil.isBlank(link)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereLink(link);
        return query(po);
    }

    /**
     * 根据用于配置外链跳转路径，会在新窗口打开。获取指定字段的值
     *
     * @param link 用于配置外链跳转路径，会在新窗口打开。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByLink(String link, Function<TbBasicSysMenu, T> fieldMapper) {
        if (StrUtil.isBlank(link)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByLink(link)).map(fieldMapper);
   }

    /**
     * 根据用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。查询单条数据
     *
     * @param maxNumOfOpenTab 用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。
     * @return 查询结果
     */
    public TbBasicSysMenu getByMaxNumOfOpenTab(Integer maxNumOfOpenTab) {
        if (ObjectUtil.isNull(maxNumOfOpenTab)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereMaxNumOfOpenTab(maxNumOfOpenTab);
        return get(po);
    }

    /**
     * 根据用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。查询多条数据
     *
     * @param maxNumOfOpenTab 用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByMaxNumOfOpenTab(Integer maxNumOfOpenTab) {
        if (ObjectUtil.isNull(maxNumOfOpenTab)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereMaxNumOfOpenTab(maxNumOfOpenTab);
        return query(po);
    }

    /**
     * 根据用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。获取指定字段的值
     *
     * @param maxNumOfOpenTab 用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByMaxNumOfOpenTab(Integer maxNumOfOpenTab, Function<TbBasicSysMenu, T> fieldMapper) {
        if (ObjectUtil.isNull(maxNumOfOpenTab)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByMaxNumOfOpenTab(maxNumOfOpenTab)).map(fieldMapper);
   }

    /**
     * 根据用于配置页面是否在新窗口中打开。1：是，2：否查询单条数据
     *
     * @param openInNewWindow 用于配置页面是否在新窗口中打开。1：是，2：否
     * @return 查询结果
     */
    public TbBasicSysMenu getByOpenInNewWindow(Integer openInNewWindow) {
        if (ObjectUtil.isNull(openInNewWindow)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereOpenInNewWindow(openInNewWindow);
        return get(po);
    }

    /**
     * 根据用于配置页面是否在新窗口中打开。1：是，2：否查询多条数据
     *
     * @param openInNewWindow 用于配置页面是否在新窗口中打开。1：是，2：否
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByOpenInNewWindow(Integer openInNewWindow) {
        if (ObjectUtil.isNull(openInNewWindow)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereOpenInNewWindow(openInNewWindow);
        return query(po);
    }

    /**
     * 根据用于配置页面是否在新窗口中打开。1：是，2：否获取指定字段的值
     *
     * @param openInNewWindow 用于配置页面是否在新窗口中打开。1：是，2：否
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByOpenInNewWindow(Integer openInNewWindow, Function<TbBasicSysMenu, T> fieldMapper) {
        if (ObjectUtil.isNull(openInNewWindow)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByOpenInNewWindow(openInNewWindow)).map(fieldMapper);
   }

    /**
     * 根据用于配置页面的菜单参数，会在菜单中传递给页面。查询单条数据
     *
     * @param query 用于配置页面的菜单参数，会在菜单中传递给页面。
     * @return 查询结果
     */
    public TbBasicSysMenu getByQuery(String query) {
        if (StrUtil.isBlank(query)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereQuery(query);
        return get(po);
    }

    /**
     * 根据用于配置页面的菜单参数，会在菜单中传递给页面。查询多条数据
     *
     * @param query 用于配置页面的菜单参数，会在菜单中传递给页面。
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByQuery(String query) {
        if (StrUtil.isBlank(query)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereQuery(query);
        return query(po);
    }

    /**
     * 根据用于配置页面的菜单参数，会在菜单中传递给页面。获取指定字段的值
     *
     * @param query 用于配置页面的菜单参数，会在菜单中传递给页面。
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByQuery(String query, Function<TbBasicSysMenu, T> fieldMapper) {
        if (StrUtil.isBlank(query)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByQuery(query)).map(fieldMapper);
   }

    /**
     * 根据是否在管理后台首页导航中显示。1：是，2：否查询单条数据
     *
     * @param showInAdminHome 是否在管理后台首页导航中显示。1：是，2：否
     * @return 查询结果
     */
    public TbBasicSysMenu getByShowInAdminHome(Integer showInAdminHome) {
        if (ObjectUtil.isNull(showInAdminHome)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereShowInAdminHome(showInAdminHome);
        return get(po);
    }

    /**
     * 根据是否在管理后台首页导航中显示。1：是，2：否查询多条数据
     *
     * @param showInAdminHome 是否在管理后台首页导航中显示。1：是，2：否
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByShowInAdminHome(Integer showInAdminHome) {
        if (ObjectUtil.isNull(showInAdminHome)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereShowInAdminHome(showInAdminHome);
        return query(po);
    }

    /**
     * 根据是否在管理后台首页导航中显示。1：是，2：否获取指定字段的值
     *
     * @param showInAdminHome 是否在管理后台首页导航中显示。1：是，2：否
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByShowInAdminHome(Integer showInAdminHome, Function<TbBasicSysMenu, T> fieldMapper) {
        if (ObjectUtil.isNull(showInAdminHome)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByShowInAdminHome(showInAdminHome)).map(fieldMapper);
   }

    /**
     * 根据创建时间查询单条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public TbBasicSysMenu getByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereCreatedAt(createdAt);
        return get(po);
    }

    /**
     * 根据创建时间查询多条数据
     *
     * @param createdAt 创建时间
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByCreatedAt(String createdAt) {
        if (StrUtil.isBlank(createdAt)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereCreatedAt(createdAt);
        return query(po);
    }

    /**
     * 根据创建时间获取指定字段的值
     *
     * @param createdAt 创建时间
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByCreatedAt(String createdAt, Function<TbBasicSysMenu, T> fieldMapper) {
        if (StrUtil.isBlank(createdAt)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByCreatedAt(createdAt)).map(fieldMapper);
   }

    /**
     * 根据创建者查询单条数据
     *
     * @param createdUserId 创建者
     * @return 查询结果
     */
    public TbBasicSysMenu getByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereCreatedUserId(createdUserId);
        return get(po);
    }

    /**
     * 根据创建者查询多条数据
     *
     * @param createdUserId 创建者
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByCreatedUserId(Integer createdUserId) {
        if (ObjectUtil.isNull(createdUserId)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereCreatedUserId(createdUserId);
        return query(po);
    }

    /**
     * 根据创建者获取指定字段的值
     *
     * @param createdUserId 创建者
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByCreatedUserId(Integer createdUserId, Function<TbBasicSysMenu, T> fieldMapper) {
        if (ObjectUtil.isNull(createdUserId)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByCreatedUserId(createdUserId)).map(fieldMapper);
   }

    /**
     * 根据更新时间查询单条数据
     *
     * @param updatedAt 更新时间
     * @return 查询结果
     */
    public TbBasicSysMenu getByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereUpdatedAt(updatedAt);
        return get(po);
    }

    /**
     * 根据更新时间查询多条数据
     *
     * @param updatedAt 更新时间
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByUpdatedAt(String updatedAt) {
        if (StrUtil.isBlank(updatedAt)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereUpdatedAt(updatedAt);
        return query(po);
    }

    /**
     * 根据更新时间获取指定字段的值
     *
     * @param updatedAt 更新时间
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByUpdatedAt(String updatedAt, Function<TbBasicSysMenu, T> fieldMapper) {
        if (StrUtil.isBlank(updatedAt)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByUpdatedAt(updatedAt)).map(fieldMapper);
   }

    /**
     * 根据最后更新者查询单条数据
     *
     * @param updatedUserId 最后更新者
     * @return 查询结果
     */
    public TbBasicSysMenu getByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereUpdatedUserId(updatedUserId);
        return get(po);
    }

    /**
     * 根据最后更新者查询多条数据
     *
     * @param updatedUserId 最后更新者
     * @return 查询结果
     */
    public List<TbBasicSysMenu> queryByUpdatedUserId(Integer updatedUserId) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return null;
        }

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereUpdatedUserId(updatedUserId);
        return query(po);
    }

    /**
     * 根据最后更新者获取指定字段的值
     *
     * @param updatedUserId 最后更新者
     * @param fieldMapper 字段映射函数
     * @param <T> 字段类型
     * @return 字段值的 Optional
     */
    public <T> Optional<T> getFieldByUpdatedUserId(Integer updatedUserId, Function<TbBasicSysMenu, T> fieldMapper) {
        if (ObjectUtil.isNull(updatedUserId)) {
            return Optional.empty();
        }

        return Optional.ofNullable(getByUpdatedUserId(updatedUserId)).map(fieldMapper);
   }

    //***************************************************获取 END********************************************************

    //***************************************************聚合操作 START******************************************************
    /**
     * 统计ID的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumId(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumId(po);
    }

    /**
     * 统计ID的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxId(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxId(po);
    }

    /**
     * 统计ID的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minId(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minId(po);
    }

    /**
     * 统计ID的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgId(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgId(po);
    }
    /**
     * 统计父级id的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumParentId(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumParentId(po);
    }

    /**
     * 统计父级id的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxParentId(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxParentId(po);
    }

    /**
     * 统计父级id的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minParentId(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minParentId(po);
    }

    /**
     * 统计父级id的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgParentId(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgParentId(po);
    }
    /**
     * 统计类型。 1：目录，2：页面，3：权限。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumType(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumType(po);
    }

    /**
     * 统计类型。 1：目录，2：页面，3：权限。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxType(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxType(po);
    }

    /**
     * 统计类型。 1：目录，2：页面，3：权限。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minType(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minType(po);
    }

    /**
     * 统计类型。 1：目录，2：页面，3：权限。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgType(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgType(po);
    }
    /**
     * 统计状态。1：正常，2：禁用。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumStatus(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumStatus(po);
    }

    /**
     * 统计状态。1：正常，2：禁用。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxStatus(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxStatus(po);
    }

    /**
     * 统计状态。1：正常，2：禁用。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minStatus(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minStatus(po);
    }

    /**
     * 统计状态。1：正常，2：禁用。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgStatus(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgStatus(po);
    }
    /**
     * 统计删除状态。1：未删除，2：已删除。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumIsDelete(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumIsDelete(po);
    }

    /**
     * 统计删除状态。1：未删除，2：已删除。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxIsDelete(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxIsDelete(po);
    }

    /**
     * 统计删除状态。1：未删除，2：已删除。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minIsDelete(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minIsDelete(po);
    }

    /**
     * 统计删除状态。1：未删除，2：已删除。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgIsDelete(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgIsDelete(po);
    }
    /**
     * 统计用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumOrder(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumOrder(po);
    }

    /**
     * 统计用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxOrder(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxOrder(po);
    }

    /**
     * 统计用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minOrder(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minOrder(po);
    }

    /**
     * 统计用于配置页面的排序，用于路由在菜单中的排序。排序仅针对一级菜单有效，二级菜单的排序需要在对应的一级菜单中按代码顺序设置。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgOrder(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgOrder(po);
    }
    /**
     * 统计用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumKeepalive(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumKeepalive(po);
    }

    /**
     * 统计用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxKeepalive(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxKeepalive(po);
    }

    /**
     * 统计用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minKeepalive(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minKeepalive(po);
    }

    /**
     * 统计用于配置页面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效。1：是，2：否。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgKeepalive(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgKeepalive(po);
    }
    /**
     * 统计用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumHideInMenu(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumHideInMenu(po);
    }

    /**
     * 统计用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxHideInMenu(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxHideInMenu(po);
    }

    /**
     * 统计用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minHideInMenu(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minHideInMenu(po);
    }

    /**
     * 统计用于配置页面是否在菜单中隐藏，隐藏后页面不会在菜单中显示。1：隐藏，2：显示。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgHideInMenu(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgHideInMenu(po);
    }
    /**
     * 统计用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumHideInTab(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumHideInTab(po);
    }

    /**
     * 统计用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxHideInTab(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxHideInTab(po);
    }

    /**
     * 统计用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minHideInTab(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minHideInTab(po);
    }

    /**
     * 统计用于配置页面是否在标签页中隐藏，隐藏后页面不会在标签页中显示。1：隐藏，2：显示。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgHideInTab(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgHideInTab(po);
    }
    /**
     * 统计用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumHideChildrenInMenu(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumHideChildrenInMenu(po);
    }

    /**
     * 统计用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxHideChildrenInMenu(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxHideChildrenInMenu(po);
    }

    /**
     * 统计用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minHideChildrenInMenu(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minHideChildrenInMenu(po);
    }

    /**
     * 统计用于配置页面的子页面是否在菜单中隐藏，隐藏后子页面不会在菜单中显示。。1：是，2：否。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgHideChildrenInMenu(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgHideChildrenInMenu(po);
    }
    /**
     * 统计用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumActiveId(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumActiveId(po);
    }

    /**
     * 统计用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxActiveId(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxActiveId(po);
    }

    /**
     * 统计用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minActiveId(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minActiveId(po);
    }

    /**
     * 统计用于配置当前激活的菜单，有时候页面没有显示在菜单内，需要激活父级菜单时使用。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgActiveId(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgActiveId(po);
    }
    /**
     * 统计用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumAffixTab(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumAffixTab(po);
    }

    /**
     * 统计用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxAffixTab(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxAffixTab(po);
    }

    /**
     * 统计用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minAffixTab(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minAffixTab(po);
    }

    /**
     * 统计用于配置页面是否固定标签页，固定后页面不可关闭。1：是，2：否的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgAffixTab(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgAffixTab(po);
    }
    /**
     * 统计用于配置页面固定标签页的排序, 采用升序排序。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumAffixTabOrder(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumAffixTabOrder(po);
    }

    /**
     * 统计用于配置页面固定标签页的排序, 采用升序排序。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxAffixTabOrder(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxAffixTabOrder(po);
    }

    /**
     * 统计用于配置页面固定标签页的排序, 采用升序排序。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minAffixTabOrder(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minAffixTabOrder(po);
    }

    /**
     * 统计用于配置页面固定标签页的排序, 采用升序排序。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgAffixTabOrder(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgAffixTabOrder(po);
    }
    /**
     * 统计用于配置页面是否忽略权限，直接可以访问。1：是，2：否的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumIgnoreAccess(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumIgnoreAccess(po);
    }

    /**
     * 统计用于配置页面是否忽略权限，直接可以访问。1：是，2：否的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxIgnoreAccess(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxIgnoreAccess(po);
    }

    /**
     * 统计用于配置页面是否忽略权限，直接可以访问。1：是，2：否的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minIgnoreAccess(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minIgnoreAccess(po);
    }

    /**
     * 统计用于配置页面是否忽略权限，直接可以访问。1：是，2：否的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgIgnoreAccess(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgIgnoreAccess(po);
    }
    /**
     * 统计用于配置是否是内嵌页面的iframe地址的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumIsIframe(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumIsIframe(po);
    }

    /**
     * 统计用于配置是否是内嵌页面的iframe地址的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxIsIframe(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxIsIframe(po);
    }

    /**
     * 统计用于配置是否是内嵌页面的iframe地址的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minIsIframe(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minIsIframe(po);
    }

    /**
     * 统计用于配置是否是内嵌页面的iframe地址的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgIsIframe(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgIsIframe(po);
    }
    /**
     * 统计用于配置是否是外链跳转路径。1：是，2：否的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumIsLink(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumIsLink(po);
    }

    /**
     * 统计用于配置是否是外链跳转路径。1：是，2：否的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxIsLink(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxIsLink(po);
    }

    /**
     * 统计用于配置是否是外链跳转路径。1：是，2：否的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minIsLink(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minIsLink(po);
    }

    /**
     * 统计用于配置是否是外链跳转路径。1：是，2：否的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgIsLink(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgIsLink(po);
    }
    /**
     * 统计用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumMaxNumOfOpenTab(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumMaxNumOfOpenTab(po);
    }

    /**
     * 统计用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxMaxNumOfOpenTab(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxMaxNumOfOpenTab(po);
    }

    /**
     * 统计用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minMaxNumOfOpenTab(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minMaxNumOfOpenTab(po);
    }

    /**
     * 统计用于配置标签页最大打开数量，设置后会在打开新标签页时自动关闭最早打开的标签页(仅在打开同名标签页时生效)。的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgMaxNumOfOpenTab(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgMaxNumOfOpenTab(po);
    }
    /**
     * 统计用于配置页面是否在新窗口中打开。1：是，2：否的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumOpenInNewWindow(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumOpenInNewWindow(po);
    }

    /**
     * 统计用于配置页面是否在新窗口中打开。1：是，2：否的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxOpenInNewWindow(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxOpenInNewWindow(po);
    }

    /**
     * 统计用于配置页面是否在新窗口中打开。1：是，2：否的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minOpenInNewWindow(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minOpenInNewWindow(po);
    }

    /**
     * 统计用于配置页面是否在新窗口中打开。1：是，2：否的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgOpenInNewWindow(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgOpenInNewWindow(po);
    }
    /**
     * 统计是否在管理后台首页导航中显示。1：是，2：否的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumShowInAdminHome(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumShowInAdminHome(po);
    }

    /**
     * 统计是否在管理后台首页导航中显示。1：是，2：否的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxShowInAdminHome(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxShowInAdminHome(po);
    }

    /**
     * 统计是否在管理后台首页导航中显示。1：是，2：否的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minShowInAdminHome(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minShowInAdminHome(po);
    }

    /**
     * 统计是否在管理后台首页导航中显示。1：是，2：否的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgShowInAdminHome(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgShowInAdminHome(po);
    }
    /**
     * 统计创建者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumCreatedUserId(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumCreatedUserId(po);
    }

    /**
     * 统计创建者的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxCreatedUserId(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxCreatedUserId(po);
    }

    /**
     * 统计创建者的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minCreatedUserId(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minCreatedUserId(po);
    }

    /**
     * 统计创建者的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgCreatedUserId(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgCreatedUserId(po);
    }
    /**
     * 统计最后更新者的总和
     *
     * @param po 查询条件
     * @return 总和结果
     */
    public BigDecimal sumUpdatedUserId(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.sumUpdatedUserId(po);
    }

    /**
     * 统计最后更新者的最大值
     *
     * @param po 查询条件
     * @return 最大值结果
     */
    public BigDecimal maxUpdatedUserId(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.maxUpdatedUserId(po);
    }

    /**
     * 统计最后更新者的最小值
     *
     * @param po 查询条件
     * @return 最小值结果
     */
    public BigDecimal minUpdatedUserId(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.minUpdatedUserId(po);
    }

    /**
     * 统计最后更新者的平均值
     *
     * @param po 查询条件
     * @return 平均值结果
     */
    public BigDecimal avgUpdatedUserId(TbBasicSysMenuPo po) {
        if (po == null) {
            return BigDecimal.ZERO;
        }
        return mapper.avgUpdatedUserId(po);
    }
    //***************************************************聚合操作 END********************************************************
}