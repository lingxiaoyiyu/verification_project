package com.api.apps.service.apps.get;

import cn.hutool.core.util.ObjUtil;
import com.api.apps.dao.TbAppsDao;
import com.api.apps.dao.TbAppsVersionDao;
import com.api.apps.data.dto.apps.get.EntranceMpDto;
import com.api.apps.data.entity.TbApps;
import com.api.apps.data.entity.TbAppsVersion;
import com.api.apps.data.po.TbAppsPo;
import com.api.apps.data.po.TbAppsVersionPo;
import com.api.apps.data.vo.apps.get.EntranceMpVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 获取指定获取入口小程序信息详细信息
 *
 * @author 裴金伟
 */
@Service("GetEntranceMpServiceImpl")
@RequiredArgsConstructor
public class EntranceMp extends AbstractService {

    protected final TbAppsDao tbAppsDao;
    protected final TbAppsVersionDao tbAppsVersionDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(EntranceMpDto dto) {
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    public Result<?> service(EntranceMpDto dto) {
        TbApps entity = tbAppsDao.get(TbAppsPo.builder().whereIsEntranceMp(1).build());
        EntranceMpVo vo = new EntranceMpVo();
        if (ObjUtil.isNotNull(entity)) {
            vo.setEntranceUniAppid(entity.getUniAppid());
            vo.setLogo(entity.getImg());

            TbAppsVersion tbAppsVersion = tbAppsVersionDao.get(TbAppsVersionPo.builder().whereAppId(entity.getId()).whereStatus(3).build());
            if (ObjUtil.isNotNull(tbAppsVersion)) {
                vo.setVersionCode(tbAppsVersion.getVersionCode());
                vo.setUrl(tbAppsVersion.getUrl());
            }
        }
        return Result.ok(vo);
    }
}
