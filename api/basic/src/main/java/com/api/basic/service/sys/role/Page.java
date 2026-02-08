package com.api.basic.service.sys.role;

import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysRoleDao;
import com.api.basic.data.dto.sys.role.PageDto;
import com.api.basic.data.entity.TbBasicSysRole;
import com.api.basic.data.po.TbBasicSysRolePo;
import com.api.basic.data.vo.sys.role.ItemVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.po.BasePageSortList;
import com.api.common.base.data.vo.BasePageVo;
import com.api.common.enums.IsDeleteEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询角色列表-分页
 */
@Service("BasicSysRolePageServiceImpl")
@RequiredArgsConstructor
public class Page extends AbstractService {

    private final TbBasicSysRoleDao tbBasicSysRoleDao;

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    public Result<?> service(PageDto dto) {
        // 构建查询条件
        TbBasicSysRolePo queryPo = handleQueryData(dto);
        // 查询列表
        List<TbBasicSysRole> roleEntityList = tbBasicSysRoleDao.query(queryPo);
        List<ItemVo> roleVoList = new ArrayList<>();
        if (roleEntityList != null) {
            for (TbBasicSysRole entity : roleEntityList) {
                ItemVo vo = convertToVo(entity);
                roleVoList.add(vo);
            }
        }
        BasePageVo<ItemVo> basePageVo = new BasePageVo<>();
        basePageVo.setList(roleVoList);
        basePageVo.setTotal(tbBasicSysRoleDao.cnt(queryPo)); // 统计总数
        return Result.ok(basePageVo);
    }

    /**
     * 处理要查询的条件
     *
     * @param dto DTO对象
     * @return 处理后的数据
     */
    private TbBasicSysRolePo handleQueryData(PageDto dto) {
        TbBasicSysRolePo po = new TbBasicSysRolePo();
        po.setWhereLikeName(dto.getName());
        po.setWhereStatus(dto.getStatus());
        po.setWhereLikeIdentifying(dto.getIdentifying());
        po.setWhereIsDelete(IsDeleteEnum.NO_DELETE.getCode());
        po.setPage(dto.getPage(), dto.getPageSize());
        po.setSortList(new BasePageSortList().addSort(StrUtil.blankToDefault(dto.getSortFiled(), "id"), dto.getSortType(), "tbsr").getSortList());
        return po;
    }

    /**
     * entity转换成vo
     *
     * @param entity 转换前的Entity对象
     * @return 展缓后的vo对象
     */
    private ItemVo convertToVo(TbBasicSysRole entity) {
        ItemVo vo = new ItemVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
