package com.api.basic.service.sys.region.update;

import cn.dev33.satoken.stp.StpUtil;
import com.api.basic.dao.TbBasicSysRegionDao;
import com.api.basic.data.dto.sys.region.update.SortDto;
import com.api.basic.data.entity.TbBasicSysRegion;
import com.api.basic.data.po.TbBasicSysRegionPo;
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
 * 更新行政区域排序
 *
 * @author 裴金伟
 * @date 2025-10-16
 */
@Service("BasicSysRegionUpdateSortService")
@RequiredArgsConstructor
public class Sort extends AbstractService {

    private final TbBasicSysRegionDao tbBasicSysRegionDao;

    /**
     * 参数检查
     */
    public void check(SortDto dto) {
        TbBasicSysRegion entity = tbBasicSysRegionDao.get(TbBasicSysRegionPo.builder().whereId(dto.getId()).whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).build());

        if (entity == null) {
            throw new ServerException("行政区域信息不存在");
        }
    }

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(SortDto dto) {
        // 获取需要调整的行政区域
        TbBasicSysRegionPo targetRegionPo = new TbBasicSysRegionPo();
        targetRegionPo.setWhereId(dto.getTargetId());
        TbBasicSysRegion targetRegion = tbBasicSysRegionDao.get(targetRegionPo);
        if (targetRegion == null) {
            throw new ServerException("目标行政区域不存在");
        }

        // 获取需要移动的行政区域
        TbBasicSysRegionPo regionToMovePo = new TbBasicSysRegionPo();
        regionToMovePo.setWhereId(dto.getId());
        TbBasicSysRegion regionToMove = tbBasicSysRegionDao.get(regionToMovePo);
        if (regionToMove == null) {
            throw new ServerException("要行政区域的菜单不存在");
        }

        // 获取所有同级行政区域
        TbBasicSysRegionPo siblingMenusPo = new TbBasicSysRegionPo();
        siblingMenusPo.setWhereParentCode(regionToMove.getParentCode());
        siblingMenusPo.setWhereIsDelete(IsDeleteEnum.NO_DELETE.getCode());
        siblingMenusPo.setSortList(new BasePageSortList().addSort("sort").getSortList());
        List<TbBasicSysRegion> siblingMenus = tbBasicSysRegionDao.query(siblingMenusPo);

        // 找到要移动的菜单和目标菜单在列表中的索引
        int targetIndex = -1;
        int menuToMoveIndex = -1;
        for (int i = 0; i < siblingMenus.size(); i++) {
            if (siblingMenus.get(i).getId().equals(targetRegion.getId())) {
                targetIndex = i;
            }
            if (siblingMenus.get(i).getId().equals(regionToMove.getId())) {
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
                TbBasicSysRegion temp = siblingMenus.get(menuToMoveIndex);
                siblingMenus.set(menuToMoveIndex, siblingMenus.get(menuToMoveIndex - 1));
                siblingMenus.set(menuToMoveIndex - 1, temp);
            }
        } else if ("bottom".equalsIgnoreCase(dto.getDirection())) {
            if (menuToMoveIndex < siblingMenus.size() - 1) {
                // 交换位置
                TbBasicSysRegion temp = siblingMenus.get(menuToMoveIndex);
                siblingMenus.set(menuToMoveIndex, siblingMenus.get(menuToMoveIndex + 1));
                siblingMenus.set(menuToMoveIndex + 1, temp);
            }
        } else {
            throw new ServerException("无效的方向参数");
        }

        // 重新排列同级菜单的排序值，从 1 开始
        int currentOrder = 1;
        for (TbBasicSysRegion siblingRegion : siblingMenus) {
            siblingRegion.setSort(currentOrder++);
        }

        // 更新数据库中的排序值
        for (TbBasicSysRegion siblingRegion : siblingMenus) {
            TbBasicSysRegionPo updatePo = new TbBasicSysRegionPo();
            BeanUtils.copyProperties(siblingRegion, updatePo);
            updatePo.setWhereId(siblingRegion.getId());
            updatePo.setUpdatedUserId(StpUtil.getLoginIdAsInt());
            if (tbBasicSysRegionDao.update(updatePo) != 1) {
                throw new ServerException("更新失败");
            }
        }

        Map<String, String> data = new HashMap<>();
        data.put("updatedAt", tbBasicSysRegionDao.getFieldById(dto.getId(), TbBasicSysRegion::getUpdatedAt).orElse(""));

        return Result.ok("更新成功", data);
    }
}
