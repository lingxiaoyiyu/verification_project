package com.api.basic.service.sys.user.get;

import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysRoleDao;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.dao.TbBasicSysUserRoleRelationDao;
import com.api.basic.data.dto.sys.user.get.ByIdDto;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import org.springframework.stereotype.Service;

/**
 * 获取指定用户详细信息
 */
@Service("BasicSysUserGetByIdServiceImpl")
public class ById extends AbstractDetail {

    public ById(TbBasicSysUserRoleRelationDao userRoleDao, TbBasicSysUserDao tbBasicSysUserDao, TbBasicSysUserRoleRelationDao tbBasicSysUserRoleRelationDao, TbBasicSysRoleDao tbBasicSysRoleDao) {
        super(userRoleDao, tbBasicSysUserDao, tbBasicSysUserRoleRelationDao, tbBasicSysRoleDao);
    }

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(ByIdDto dto) {
        if (StrUtil.isBlank(tbBasicSysUserDao.getFieldById(dto.getId(), TbBasicSysUser::getUpdatedAt).orElse(""))) {
            throw new ServerException("用户不存在");
        }
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    public Result<?> service(ByIdDto dto) {
        return Result.ok(getDetailVo(dto.getId()));
    }
}
