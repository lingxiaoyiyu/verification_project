package com.api.apps.service.apps.get;

import cn.hutool.core.util.ObjUtil;
import com.api.apps.dao.TbAppsDao;
import com.api.apps.dao.TbAppsVersionDao;
import com.api.apps.data.dto.apps.get.MpInfoDto;
import com.api.apps.data.entity.TbApps;
import com.api.apps.data.entity.TbAppsVersion;
import com.api.apps.data.po.TbAppsPo;
import com.api.apps.data.po.TbAppsVersionPo;
import com.api.apps.data.vo.apps.get.MpInfoVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 获取指定小程序详细信息
 *
 * @author 裴金伟
 */
@Service("AppGetMpInfoServiceImpl")
@RequiredArgsConstructor
public class MpInfo extends AbstractService {

    protected final TbAppsDao tbAppsDao;
    protected final TbAppsVersionDao tbAppsVersionDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(MpInfoDto dto) {
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    public Result<?> service(MpInfoDto dto) {

        TbApps entity = tbAppsDao.get(TbAppsPo.builder().whereUniAppid(dto.getUniAppid()).build());
        MpInfoVo vo = new MpInfoVo();
        if (ObjUtil.isNotNull(entity)) {
            vo.setLogo(entity.getImg());
            TbAppsVersion version = tbAppsVersionDao.get(TbAppsVersionPo.builder().whereAppId(entity.getId()).whereStatus(3).build());
            if (ObjUtil.isNotNull(version))
                BeanUtils.copyProperties(version, vo);
        }
        return Result.ok(vo);
    }
}
