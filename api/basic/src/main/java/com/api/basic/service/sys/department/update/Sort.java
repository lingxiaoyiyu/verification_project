package com.api.basic.service.sys.department.update;

import cn.dev33.satoken.stp.StpUtil;
import com.api.basic.dao.TbBasicSysDepartmentDao;
import com.api.basic.data.dto.sys.department.update.SortDto;
import com.api.basic.data.entity.TbBasicSysDepartment;
import com.api.basic.data.po.TbBasicSysDepartmentPo;
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
 * 更新部门排序
 *
 * @author 裴金伟
 * @date 2025-10-16
 */
@Service("BasicSysDepartmentUpdateSortService")
@RequiredArgsConstructor
public class Sort extends AbstractService {

    private final TbBasicSysDepartmentDao tbBasicSysDepartmentDao;

    /**
     * 参数检查
     */
    public void check(SortDto dto) {
        TbBasicSysDepartment entity = tbBasicSysDepartmentDao.get(TbBasicSysDepartmentPo.builder().whereId(dto.getId()).whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).build());

        if (entity == null) {
            throw new ServerException("部门信息不存在");
        }
    }

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(SortDto dto) {
        // 获取需要调整的部门
        TbBasicSysDepartmentPo targetDepartmentPo = new TbBasicSysDepartmentPo();
        targetDepartmentPo.setWhereId(dto.getTargetId());
        TbBasicSysDepartment targetDepartment = tbBasicSysDepartmentDao.get(targetDepartmentPo);
        if (targetDepartment == null) {
            throw new ServerException("目标部门不存在");
        }

        // 获取需要移动的部门
        TbBasicSysDepartmentPo departmentToMovePo = new TbBasicSysDepartmentPo();
        departmentToMovePo.setWhereId(dto.getId());
        TbBasicSysDepartment departmentToMove = tbBasicSysDepartmentDao.get(departmentToMovePo);
        if (departmentToMove == null) {
            throw new ServerException("要部门的菜单不存在");
        }

        // 获取所有同级部门
        TbBasicSysDepartmentPo siblingMenusPo = new TbBasicSysDepartmentPo();
        siblingMenusPo.setWhereParentId(departmentToMove.getParentId());
        siblingMenusPo.setWhereIsDelete(IsDeleteEnum.NO_DELETE.getCode());
        siblingMenusPo.setSortList(new BasePageSortList().addSort("sort").getSortList());
        List<TbBasicSysDepartment> siblingMenus = tbBasicSysDepartmentDao.query(siblingMenusPo);

        // 找到要移动的菜单和目标菜单在列表中的索引
        int targetIndex = -1;
        int menuToMoveIndex = -1;
        for (int i = 0; i < siblingMenus.size(); i++) {
            if (siblingMenus.get(i).getId().equals(targetDepartment.getId())) {
                targetIndex = i;
            }
            if (siblingMenus.get(i).getId().equals(departmentToMove.getId())) {
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
                TbBasicSysDepartment temp = siblingMenus.get(menuToMoveIndex);
                siblingMenus.set(menuToMoveIndex, siblingMenus.get(menuToMoveIndex - 1));
                siblingMenus.set(menuToMoveIndex - 1, temp);
            }
        } else if ("bottom".equalsIgnoreCase(dto.getDirection())) {
            if (menuToMoveIndex < siblingMenus.size() - 1) {
                // 交换位置
                TbBasicSysDepartment temp = siblingMenus.get(menuToMoveIndex);
                siblingMenus.set(menuToMoveIndex, siblingMenus.get(menuToMoveIndex + 1));
                siblingMenus.set(menuToMoveIndex + 1, temp);
            }
        } else {
            throw new ServerException("无效的方向参数");
        }

        // 重新排列同级菜单的排序值，从 1 开始
        int currentOrder = 1;
        for (TbBasicSysDepartment siblingDepartment : siblingMenus) {
            siblingDepartment.setSort(currentOrder++);
        }

        // 更新数据库中的排序值
        for (TbBasicSysDepartment siblingDepartment : siblingMenus) {
            TbBasicSysDepartmentPo updatePo = new TbBasicSysDepartmentPo();
            BeanUtils.copyProperties(siblingDepartment, updatePo);
            updatePo.setWhereId(siblingDepartment.getId());
            updatePo.setUpdatedUserId(StpUtil.getLoginIdAsInt());
            if (tbBasicSysDepartmentDao.update(updatePo) != 1) {
                throw new ServerException("更新失败");
            }
        }

        Map<String, String> data = new HashMap<>();
        data.put("updatedAt", tbBasicSysDepartmentDao.getFieldById(dto.getId(), TbBasicSysDepartment::getUpdatedAt).orElse(""));

        return Result.ok("更新成功", data);
    }
}
