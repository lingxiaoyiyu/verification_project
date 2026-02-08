package com.api.basic.service.sys.department;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysDepartmentDao;
import com.api.basic.data.dto.sys.department.AddDto;
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

import java.util.Map;
import java.util.Optional;

/**
 * 添加部门
 */
@Service("BasicSysDepartmentAddService")
@RequiredArgsConstructor
public class Add extends AbstractService {

    private final TbBasicSysDepartmentDao tbBasicSysDepartmentDao;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(AddDto dto) {
        dto.setName(StrUtil.trim(dto.getName()));
        TbBasicSysDepartmentPo departmentPo = new TbBasicSysDepartmentPo();
        departmentPo.setWhereName(dto.getName());
        departmentPo.setWhereIsDelete(IsDeleteEnum.NO_DELETE.getCode());
        if (tbBasicSysDepartmentDao.cnt(departmentPo) > 0) {
            throw new ServerException("部门名称已存在");
        } else if (dto.getParentId() != null && dto.getParentId() > 0) {
            departmentPo.setWhereName(null);
            departmentPo.setWhereId(dto.getParentId());
            if (tbBasicSysDepartmentDao.cnt(departmentPo) == 0) {
                throw new ServerException("上级部门不存在");
            }
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
        TbBasicSysDepartment entity = handleAddData(dto);
        if (tbBasicSysDepartmentDao.add(entity) == 0) {
            throw new ServerException("部门添加失败");
        }
        return Result.ok("部门添加成功", Map.of("id", entity.getId()));
    }

    /**
     * 处理要添加的数据
     *
     * @param dto DTO对象
     * @return 处理后的数据
     */
    protected TbBasicSysDepartment handleAddData(AddDto dto) {
        TbBasicSysDepartment entity = new TbBasicSysDepartment();
        BeanUtils.copyProperties(dto, entity);
        if (dto.getParentId() == null || dto.getParentId() == 0) {
            entity.setParentId(0);
        } else {
            entity.setParentId(dto.getParentId());
        }

        TbBasicSysDepartmentPo po = new TbBasicSysDepartmentPo();
        po.setWhereParentId(dto.getParentId());
        po.setWhereIsDelete(IsDeleteEnum.NO_DELETE.getCode());
        po.setSortList(new BasePageSortList().addSort("sort", "DESC", "tbsd").getSortList());
        entity.setSort(Optional.ofNullable(tbBasicSysDepartmentDao.get(po)).map(TbBasicSysDepartment::getSort).orElse(0) + 1);

        entity.setCreatedUserId(StpUtil.getLoginIdAsInt());
        entity.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        return entity;
    }
}
