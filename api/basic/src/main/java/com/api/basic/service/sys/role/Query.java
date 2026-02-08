package com.api.basic.service.sys.role;

import com.api.basic.dao.TbBasicSysRoleDao;
import com.api.basic.data.entity.TbBasicSysRole;
import com.api.basic.data.po.TbBasicSysRolePo;
import com.api.basic.data.vo.sys.role.ItemVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.enums.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询角色列表
 */
@Service("BasicSysRoleQueryServiceImpl")
@RequiredArgsConstructor
public class Query extends AbstractService {

    private final TbBasicSysRoleDao tbBasicSysRoleDao;

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    public Result<?> service() {
        // 构建查询条件
        TbBasicSysRolePo queryPo = handleQueryData();
        // 查询列表
        List<TbBasicSysRole> roleEntityList = tbBasicSysRoleDao.query(queryPo);
        List<ItemVo> roleVoList = new ArrayList<>();
        if (roleEntityList != null) {
            for (TbBasicSysRole entity : roleEntityList) {
                roleVoList.add(convertToVo(entity));
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("list", roleVoList);
        return Result.ok(map);
    }

    /**
     * 处理要查询的条件
     *
     * @return 处理后的数据
     */
    private TbBasicSysRolePo handleQueryData() {
        TbBasicSysRolePo po = new TbBasicSysRolePo();
        po.setWhereIsDelete(IsDeleteEnum.NO_DELETE.getCode());
        po.setWhereStatus(StatusEnum.ENABLE.getCode());
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
