package com.api.basic.service.sys.menu;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysMenuDao;
import com.api.basic.data.dto.sys.menu.AddDto;
import com.api.basic.data.entity.TbBasicSysMenu;
import com.api.basic.data.enums.MenuTypeEnum;
import com.api.basic.data.po.TbBasicSysMenuPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.po.BasePageSortList;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.enums.YesOrNoEnum;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

/**
 * 添加菜单
 */
@Service("BasicSysMenuAddServiceImpl")
@RequiredArgsConstructor
public class Add extends AbstractService {

    private final TbBasicSysMenuDao tbBasicSysMenuDao;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(AddDto dto) {
        dto.setTitle(StrUtil.trim(dto.getTitle()));
        dto.setName(StrUtil.trim(dto.getName()));
        dto.setIcon(StrUtil.trim(dto.getIcon()));
        dto.setActiveIcon(StrUtil.trim(dto.getActiveIcon()));
        dto.setIdentifying(StrUtil.trim(dto.getIdentifying()));
        dto.setPath(StrUtil.trim(dto.getPath()));
        dto.setLink(StrUtil.trim(dto.getLink()));
        dto.setIframeSrc(StrUtil.trim(dto.getIframeSrc()));
        dto.setQuery(StrUtil.trim(dto.getQuery()));

        dto.setParentId(dto.getParentId() == null ? 0 : dto.getParentId());

        if (dto.getParentId() > 0) {
            TbBasicSysMenu parentMenu = tbBasicSysMenuDao.get(TbBasicSysMenuPo.builder()
                            .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
                            .whereId(dto.getParentId())
                            .build());

            if (parentMenu == null) {
                throw new ServerException("父节点不存在");
            }

            if (dto.getType() == MenuTypeEnum.PAGE.getCode() && parentMenu.getType() != MenuTypeEnum.DIR.getCode()) {
                throw new ServerException("父节点必须是目录");
            }

            if (dto.getType() == MenuTypeEnum.PERMISSION.getCode() && parentMenu.getType() != MenuTypeEnum.PAGE.getCode()) {
                throw new ServerException("父节点必须是页面");
            }
        }

        if (dto.getType() == MenuTypeEnum.PAGE.getCode() && tbBasicSysMenuDao.cnt(TbBasicSysMenuPo.builder()
                        .whereId(dto.getParentId())
                        .wherePath(dto.getPath())
                        .wherePath(dto.getPath())
                        .build()) > 0) {
            throw new ServerException("路由地址已存在");
        }

        if (dto.getIsLink() == null || dto.getIsLink() == YesOrNoEnum.NO.getCode()) {
            dto.setLink("");
        } else if (dto.getType() == MenuTypeEnum.PAGE.getCode()) {
            if (StrUtil.isBlank(dto.getLink())) {
                throw new ServerException("外链地址不能为空");
            }
        }

        if (dto.getIsIframe() == null || dto.getIsIframe() == YesOrNoEnum.NO.getCode()) {
            dto.setIframeSrc("");
        } else if (dto.getType() == MenuTypeEnum.PAGE.getCode()) {
            if (StrUtil.isBlank(dto.getIframeSrc())) {
                throw new ServerException("iframe地址不能为空");
            }
        }

        if(tbBasicSysMenuDao.cnt(TbBasicSysMenuPo.builder().whereName(dto.getName()).build()) > 0) {
            throw new ServerException("菜单名称已存在");
        }
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(AddDto dto) {
        TbBasicSysMenu addEntity = handleAddData(dto);
        if (tbBasicSysMenuDao.add(addEntity) == 0) {
            throw new ServerException("菜单添加失败");
        }
        return Result.ok("菜单添加成功", Map.of("id", addEntity.getId()));
    }

    /**
     * 处理要添加的数据
     *
     * @param dto DTO对象
     * @return 处理后的数据
     */
    private TbBasicSysMenu handleAddData(AddDto dto) {
        TbBasicSysMenu addEntity = new TbBasicSysMenu();
        BeanUtils.copyProperties(dto, addEntity);
        addEntity.setCreatedUserId(StpUtil.getLoginIdAsInt());
        addEntity.setUpdatedUserId(StpUtil.getLoginIdAsInt());

        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereParentId(dto.getParentId());
        po.setWhereIsDelete(IsDeleteEnum.NO_DELETE.getCode());
        po.setSortList(new BasePageSortList().addSort("order", "DESC", "tbsm").getSortList());
        addEntity.setOrder(Optional.ofNullable(tbBasicSysMenuDao.get(po)).map(TbBasicSysMenu::getOrder).orElse(0) + 1);
        return addEntity;
    }
}
