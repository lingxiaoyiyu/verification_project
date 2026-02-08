package com.api.basic.service.sys.menu;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysMenuDao;
import com.api.basic.data.dto.sys.menu.RemoveMenuDto;
import com.api.basic.data.entity.TbBasicSysMenu;
import com.api.basic.data.po.TbBasicSysMenuPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 删除菜单
 */
@Service("BasicSysMenuRemoveServiceImpl")
@RequiredArgsConstructor
public class Remove extends AbstractService {

    private final TbBasicSysMenuDao tbBasicSysMenuDao;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(RemoveMenuDto dto) {
        TbBasicSysMenu entity = tbBasicSysMenuDao.get(TbBasicSysMenuPo.builder().whereId(dto.getId()).whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).build());

        if (entity == null) {
            throw new ServerException("菜单信息不存在");
        }

        if (StrUtil.isNotBlank(dto.getUpdatedAt()) && !entity.getUpdatedAt().equals(dto.getUpdatedAt())) {
            throw new ServerException("菜单信息已被其他用户修改");
        }

        if (dto.getId() == 1 || dto.getId() == 2) {
            throw new ServerException("该菜单不允许删除");
        }

        TbBasicSysMenuPo queryPo = new TbBasicSysMenuPo();
        queryPo.setWhereIsDelete(IsDeleteEnum.NO_DELETE.getCode());
        queryPo.setWhereParentId(dto.getId());
        if (tbBasicSysMenuDao.cnt(queryPo) > 0) {
            throw new ServerException("该菜单下存在子菜单，请先删除子菜单");
        }
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(RemoveMenuDto dto) {
        TbBasicSysMenuPo removePo = new TbBasicSysMenuPo();
        removePo.setWhereId(dto.getId());
        removePo.setIsDelete(IsDeleteEnum.DELETED.getCode());
        removePo.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        if (tbBasicSysMenuDao.update(removePo) != 1) {
            throw new ServerException("菜单删除失败");
        }
        return Result.ok("菜单删除成功");
    }
}
