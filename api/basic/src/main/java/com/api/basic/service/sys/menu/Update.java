package com.api.basic.service.sys.menu;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysMenuDao;
import com.api.basic.data.dto.sys.menu.update.InfoDto;
import com.api.basic.data.entity.TbBasicSysMenu;
import com.api.basic.data.enums.MenuTypeEnum;
import com.api.basic.data.po.TbBasicSysMenuPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.enums.StatusEnum;
import com.api.common.enums.YesOrNoEnum;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 更新菜单
 */
@Service("BasicSysMenuUpdateServiceImpl")
@RequiredArgsConstructor
public class Update extends AbstractService {

    private final TbBasicSysMenuDao tbBasicSysMenuDao;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(InfoDto dto) {
        dto.setParentId(dto.getParentId() == null ? 0 : dto.getParentId());

        TbBasicSysMenu entity = tbBasicSysMenuDao.get(TbBasicSysMenuPo.builder().whereId(dto.getId()).whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).build());

        if (entity == null) {
            throw new ServerException("菜单不存在");
        }

        if (StrUtil.isNotBlank(dto.getUpdatedAt()) && !entity.getUpdatedAt().equals(dto.getUpdatedAt())) {
            throw new ServerException("菜单信息已被其他用户修改");
        }

        if((dto.getId() == 1 || dto.getId() == 2) && dto.getStatus() == StatusEnum.DISABLED.getCode()) {
            throw new ServerException("该菜单不能禁用");
        }

        if (dto.getParentId() > 0) {
            TbBasicSysMenu parentMenu = tbBasicSysMenuDao.get(TbBasicSysMenuPo.builder().whereId(dto.getParentId()).whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).build());

            if (parentMenu == null) {
                throw new ServerException("父节点数据不存在");
            }

            if (dto.getType() == MenuTypeEnum.PAGE.getCode() && parentMenu.getType() != MenuTypeEnum.DIR.getCode()) {
                throw new ServerException("父节点必须是目录");
            }

            if (dto.getType() == MenuTypeEnum.PERMISSION.getCode() && parentMenu.getType() != MenuTypeEnum.PAGE.getCode()) {
                throw new ServerException("父节点必须是页面");
            }
        }
        if (dto.getType() == MenuTypeEnum.PAGE.getCode() && tbBasicSysMenuDao.cnt(TbBasicSysMenuPo.builder()
                        .whereNotId(dto.getId())
                        .whereParentId(dto.getParentId())
                        .wherePath(dto.getPath())
                        .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
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

        if(tbBasicSysMenuDao.cnt(TbBasicSysMenuPo.builder().whereNotId(dto.getId()).whereName(dto.getName()).build()) > 0) {
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
    public Result<?> service(InfoDto dto) {
        TbBasicSysMenuPo updateEntity = handleUpdateData(dto);
        if (tbBasicSysMenuDao.update(updateEntity) != 1) {
            throw new ServerException("菜单更新失败");
        }

        Map<String, String> data = new HashMap<>();
        data.put("updatedAt", tbBasicSysMenuDao.getFieldById(dto.getId(), TbBasicSysMenu::getUpdatedAt).orElse(""));
        return Result.ok("菜单更新成功", data);
    }

    /**
     * 处理要更新的数据
     *
     * @param dto DTO对象
     * @return 处理后的数据
     */
    private TbBasicSysMenuPo handleUpdateData(InfoDto dto) {
        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        BeanUtils.copyProperties(dto, po);
        po.setWhereId(dto.getId());
        po.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        return po;
    }
}
