package com.api.basic.service.sys.user.get;

import cn.dev33.satoken.stp.StpUtil;
import com.api.basic.dao.TbBasicSysRoleDao;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.dao.TbBasicSysUserRoleRelationDao;
import com.api.basic.data.vo.sys.user.GetVo;
import com.api.common.base.Result;
import org.springframework.stereotype.Service;

/**
 * 获取当前登录用户详细信息
 */
@Service("BasicSysUserGetCurrentServiceImpl")
public class Current extends AbstractDetail {

    public Current(TbBasicSysUserRoleRelationDao userRoleDao, TbBasicSysUserDao tbBasicSysUserDao, TbBasicSysUserRoleRelationDao tbBasicSysUserRoleRelationDao, TbBasicSysRoleDao tbBasicSysRoleDao) {
        super(userRoleDao, tbBasicSysUserDao, tbBasicSysUserRoleRelationDao, tbBasicSysRoleDao);
    }

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    public Result<?> service() {
        GetVo vo = getDetailVo(StpUtil.getLoginIdAsInt());
        return Result.ok(vo);
    }
}
