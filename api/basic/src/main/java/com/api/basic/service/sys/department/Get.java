package com.api.basic.service.sys.department;

import com.api.basic.dao.TbBasicSysDepartmentDao;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.dto.sys.department.GetDto;
import com.api.basic.data.entity.TbBasicSysDepartment;
import com.api.basic.data.po.TbBasicSysDepartmentPo;
import com.api.basic.data.vo.sys.menu.GetVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.enums.IsDeleteEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 获取部门详情
 *
 * @author 裴金伟
 */
@Service("BasicSysDepartmentGetServiceImpl")
@RequiredArgsConstructor
public class Get extends AbstractService {

    private final TbBasicSysDepartmentDao tbBasicSysDepartmentDao;
    protected final TbBasicSysUserDao tbBasicSysUserDao;

    /**
     * 参数检查
     */
    public void check(GetDto dto) {
    }

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    public Result<?> service(GetDto dto) {
        TbBasicSysDepartment entity = tbBasicSysDepartmentDao.get(TbBasicSysDepartmentPo.builder().whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).whereId(dto.getId()).build());
        GetVo vo = new GetVo();
        BeanUtils.copyProperties(entity, vo);

        vo.setCreatedUserName(tbBasicSysUserDao.getById(entity.getCreatedUserId()).getUsername());
        vo.setUpdatedUserName(tbBasicSysUserDao.getById(entity.getUpdatedUserId()).getUsername());
        return Result.ok(vo);
    }

}
