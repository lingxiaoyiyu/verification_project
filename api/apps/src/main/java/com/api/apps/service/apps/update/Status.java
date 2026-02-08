package com.api.apps.service.apps.update;

import cn.dev33.satoken.stp.StpUtil;
import com.api.apps.dao.TbAppsDao;
import com.api.apps.data.dto.apps.update.StatusDto;
import com.api.apps.data.entity.TbApps;
import com.api.apps.data.po.TbAppsPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 更新应用状态
 *
 * @author 裴金伟
 */
@Service("AppsUpdateStatusServiceImpl")
@RequiredArgsConstructor
public class Status extends AbstractService {

    private final TbAppsDao tbAppsDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(StatusDto dto) {


    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(StatusDto dto) {

        if (tbAppsDao.update(handlUpdateData(dto)) != 1) {
            throw new ServerException("更新失败");
        }

        return Result.ok("更新成功", Map.of("updatedAt", tbAppsDao.getFieldById(dto.getId(),  TbApps::getUpdatedAt).orElse("")));
    }

    /**
    * 处理要更新的数据
    *
    * @param dto 请求参数
    * @return 处理后的数据
    */
    protected TbAppsPo handlUpdateData(StatusDto dto){
        TbAppsPo po = new TbAppsPo();
        BeanUtils.copyProperties(dto, po);
        po.setWhereId(dto.getId());
        po.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        return po;
    }
}
