package com.api.basic.service.sys.department;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysDepartmentDao;
import com.api.basic.data.dto.sys.department.RemoveDto;
import com.api.basic.data.entity.TbBasicSysDepartment;
import com.api.basic.data.po.TbBasicSysDepartmentPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 删除部门
 */
@Service("BasicSysDepartmentRemoveService")
@RequiredArgsConstructor
public class Remove extends AbstractService {

    private final TbBasicSysDepartmentDao tbBasicSysDepartmentDao;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(RemoveDto dto) {
        TbBasicSysDepartment entity = tbBasicSysDepartmentDao.get(TbBasicSysDepartmentPo.builder().whereId(dto.getId()).whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).build());

        if (entity == null) {
            throw new ServerException("部门不存在");
        }

        if (StrUtil.isNotBlank(dto.getUpdatedAt()) && !dto.getUpdatedAt().equals(entity.getUpdatedAt())) {
            throw new ServerException("部门信息已被其他用户修改");
        }
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(RemoveDto dto) {
        TbBasicSysDepartmentPo tbBasicSysDepartmentPo = new TbBasicSysDepartmentPo();
        tbBasicSysDepartmentPo.setIsDelete(IsDeleteEnum.DELETED.getCode());
        tbBasicSysDepartmentPo.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        tbBasicSysDepartmentPo.setWhereId(dto.getId());
        if (tbBasicSysDepartmentDao.update(tbBasicSysDepartmentPo) != 1) {
            throw new ServerException("部门删除失败");
        }
        return Result.ok("部门删除成功");
    }
}
