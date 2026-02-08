package com.api.basic.service.sys.menu.query;

import com.api.basic.dao.TbBasicSysMenuDao;
import com.api.basic.data.dto.sys.menu.QueryChildrenDto;
import com.api.basic.data.entity.TbBasicSysMenu;
import com.api.basic.data.po.TbBasicSysMenuPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.vo.BaseSelectIntVo;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.enums.StatusEnum;
import com.api.common.enums.YesOrNoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取指定菜单的显示状态的页面子菜单
 */
@Service("BasicSysMenuQueryChildrenServiceImpl")
@RequiredArgsConstructor
public class Children extends AbstractService {

    private final TbBasicSysMenuDao tbBasicSysMenuDao;

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    public Result<?> service(QueryChildrenDto dto) {
        List<TbBasicSysMenu> menuEntityList = tbBasicSysMenuDao.query(handleQueryData(dto));
        List<BaseSelectIntVo> datas = new ArrayList<>();
        for (TbBasicSysMenu entity : menuEntityList) {
            if (entity.getHideInMenu() == YesOrNoEnum.NO.getCode() && entity.getIsDelete() == IsDeleteEnum.NO_DELETE.getCode()) { // 显示未删除
                BaseSelectIntVo vo = new BaseSelectIntVo();
                vo.setValue(entity.getId());
                vo.setLabel(entity.getTitle());
                datas.add(vo);
            }
        }
        return Result.ok(datas);
    }

    /**
     * 处理要查询的条件
     *
     * @param dto DTO对象
     * @return 处理后的数据
     */
    private TbBasicSysMenuPo handleQueryData(QueryChildrenDto dto) {
        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereParentId(dto.getId());
        po.setWhereStatus(StatusEnum.ENABLE.getCode());
        po.setWhereHideInMenu(YesOrNoEnum.YES.getCode());
        po.setWhereIsDelete(IsDeleteEnum.NO_DELETE.getCode());
        return po;
    }
}
