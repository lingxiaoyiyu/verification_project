package com.api.basic.service.sys.department;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysDepartmentDao;
import com.api.basic.data.dto.sys.department.UpdateDto;
import com.api.basic.data.entity.TbBasicSysDepartment;
import com.api.basic.data.po.TbBasicSysDepartmentPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 更新部门
 */
@Service("BasicSysDepartmentUpdateService")
@RequiredArgsConstructor
public class Update extends AbstractService {

    private final TbBasicSysDepartmentDao tbBasicSysDepartmentDao;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(UpdateDto dto) {
        TbBasicSysDepartment entity = tbBasicSysDepartmentDao.get(TbBasicSysDepartmentPo.builder().whereId(dto.getId()).whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).build());

        if (entity == null) {
            throw new ServerException("部门不存在");
        }

        if (StrUtil.isNotBlank(dto.getUpdatedAt()) && !dto.getUpdatedAt().equals(entity.getUpdatedAt())) {
            throw new ServerException("部门信息已被其他用户修改");
        }

        if (tbBasicSysDepartmentDao.cnt(TbBasicSysDepartmentPo.builder().whereName(dto.getName()).whereNotId(dto.getId()).build()) > 0) {
            throw new ServerException("部门名称已存在");
        }

        if (dto.getParentId() != null && dto.getParentId() > 0 && tbBasicSysDepartmentDao.cnt(TbBasicSysDepartmentPo.builder().whereId(dto.getParentId()).build()) == 0) {
            throw new ServerException("上级部门不存在");
        }
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(UpdateDto dto) {
        if (tbBasicSysDepartmentDao.update(handlUpdateData(dto)) != 1) {
            throw new ServerException("部门更新失败");
        }

        Map<String, String> data = new HashMap<>();
        data.put("updatedAt", tbBasicSysDepartmentDao.getFieldById(dto.getId(), TbBasicSysDepartment::getUpdatedAt).orElse(""));
        return Result.ok("部门更新成功", data);
    }

    /**
     * 处理要更新的数据
     *
     * @param dto DTO对象
     * @return 处理后的数据
     */
    protected TbBasicSysDepartmentPo handlUpdateData(UpdateDto dto) {
        TbBasicSysDepartmentPo po = new TbBasicSysDepartmentPo();
        po.setWhereId(dto.getId());
        BeanUtils.copyProperties(dto, po);
        if (dto.getParentId() == null || dto.getParentId() == 0) {
            po.setParentId(0);
        } else {
            po.setParentId(dto.getParentId());
        }
        po.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        return po;
    }
}
