package com.api.apps.service.apps.update;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.api.apps.dao.TbAppsDao;
import com.api.apps.data.dto.apps.update.InfoDto;
import com.api.apps.data.po.TbAppsPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 更新应用
 *
 * @author 裴金伟
 */
@Service("AppsUpdateInfoServiceImpl")
@RequiredArgsConstructor
public class Info extends AbstractService {

    private final TbAppsDao tbAppsDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(InfoDto dto) {


    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(InfoDto dto) {

        if (tbAppsDao.update(handlUpdateData(dto)) != 1) {
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
    protected TbAppsPo handlUpdateData(InfoDto dto){
        TbAppsPo po = new TbAppsPo();
        BeanUtils.copyProperties(dto, po);
        po.setWhereId(dto.getId());
        po.setAccessRoleIds(JSONUtil.toJsonStr(dto.getAccessRoleIds()));
        po.setAccessUserIds(JSONUtil.toJsonStr(dto.getAccessUserIds()));
        po.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        return po;
    }
}
