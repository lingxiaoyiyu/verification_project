package com.api.apps.service.apps.get;

import cn.hutool.core.util.ObjUtil;
import com.api.apps.dao.TbAppsDao;
import com.api.apps.dao.TbAppsVersionDao;
import com.api.apps.data.entity.TbApps;
import com.api.apps.data.entity.TbAppsVersion;
import com.api.apps.data.po.TbAppsPo;
import com.api.apps.data.po.TbAppsVersionPo;
import com.api.apps.data.vo.apps.get.HostNewsVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 获取指定宿主详细信息
 *
 * @author 裴金伟
 */
@Service("AppsGetHostNewsServiceImpl")
@RequiredArgsConstructor
public class HostNews extends AbstractService {

    protected final TbAppsDao tbAppsDao;
    protected final TbAppsVersionDao tbAppsVersionDao;

    /**
     * 参数检查
     */
    public void check() {
    }

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    public Result<?> service() {
        HostNewsVo vo = new HostNewsVo();
        TbApps entity = tbAppsDao.get(TbAppsPo.builder().whereType(1).build());
        if (ObjUtil.isNotNull( entity)) {
            TbAppsVersion version = tbAppsVersionDao.get(TbAppsVersionPo.builder().whereAppId(entity.getId()).whereStatus(3).build());
            if (ObjUtil.isNotNull(version))
                BeanUtils.copyProperties(version, vo);
        }
        return Result.ok(vo);
    }
}
