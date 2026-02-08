package com.api.apps.service.apps.version.update;

import cn.dev33.satoken.stp.StpUtil;
import com.api.apps.dao.TbAppsVersionDao;
import com.api.apps.data.dto.apps.version.update.StatusDto;
import com.api.apps.data.entity.TbAppsVersion;
import com.api.apps.data.po.TbAppsVersionPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 更新应用版本
 *
 * @author 裴金伟
 */
@Service("AppsVersionUpdateStatusServiceImpl")
@RequiredArgsConstructor
public class Status extends AbstractService {

    private final TbAppsVersionDao tbAppsVersionDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(StatusDto dto) {
        if (tbAppsVersionDao.get(TbAppsVersionPo.builder().whereId(dto.getId()).build()) == null) {
            throw new ServerException("信息不存在");
        }
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(StatusDto dto) {

        String appId = tbAppsVersionDao.getFieldById(dto.getId(), TbAppsVersion::getAppId).orElse("");
        if(dto.getStatus() == 2) {
            tbAppsVersionDao.update(TbAppsVersionPo.builder().whereAppId(appId).whereStatus(2).updatedUserId(StpUtil.getLoginIdAsInt()).status(1).build());
        } else if (dto.getStatus() == 3) {
            tbAppsVersionDao.update(TbAppsVersionPo.builder().whereAppId(appId).whereStatus(3).updatedUserId(StpUtil.getLoginIdAsInt()).status(1).build());
        }
        TbAppsVersionPo po =handlUpdateData(dto);
        po.setWhereAppId(appId);
        if (tbAppsVersionDao.update(po) != 1) {
            throw new ServerException("更新失败");
        }

        return Result.ok("更新成功");
    }

    /**
    * 处理要更新的数据
    *
    * @param dto 请求参数
    * @return 处理后的数据
    */
    protected TbAppsVersionPo handlUpdateData(StatusDto dto){
        TbAppsVersionPo po = new TbAppsVersionPo();
        BeanUtils.copyProperties(dto, po);
        po.setWhereId(dto.getId());
        po.setStatus(dto.getStatus());

        return po;
    }
}
