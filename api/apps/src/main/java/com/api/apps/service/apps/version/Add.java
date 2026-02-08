package com.api.apps.service.apps.version;


import cn.dev33.satoken.stp.StpUtil;
import com.api.apps.dao.TbAppsVersionDao;
import com.api.apps.data.dto.apps.version.AddDto;
import com.api.apps.data.entity.TbAppsVersion;
import com.api.apps.data.po.TbAppsVersionPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 添加应用版本
 *
 * @author 裴金伟
 */
@Service("AppsVersionAddServiceImpl")
@RequiredArgsConstructor
public class Add extends AbstractService {

    private final TbAppsVersionDao tbAppsVersionDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(AddDto dto) {
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(AddDto dto) {
        if(dto.getStatus() == 2) {
            tbAppsVersionDao.update(TbAppsVersionPo.builder().whereAppId(dto.getAppId()).whereStatus(2).status(1).build());
        } else if (dto.getStatus() == 3) {
            tbAppsVersionDao.update(TbAppsVersionPo.builder().whereAppId(dto.getAppId()).whereStatus(3).status(1).build());
        }
        // 构建添加对象
        TbAppsVersion entity = handleAddData(dto);
        if (tbAppsVersionDao.add(entity) == 0) {
            throw new ServerException("添加失败");
        }
        return Result.ok("添加成功", Map.of("id", entity.getId()));
}

    /**
     * 处理要添加的数据
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected TbAppsVersion handleAddData(AddDto dto){
        TbAppsVersion entity = new TbAppsVersion();
        BeanUtils.copyProperties(dto, entity);
        entity.setCreatedUserId(StpUtil.getLoginIdAsInt());
        entity.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        return entity;
    }
}
