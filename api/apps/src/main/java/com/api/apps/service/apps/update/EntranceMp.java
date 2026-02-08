package com.api.apps.service.apps.update;

import cn.dev33.satoken.stp.StpUtil;
import com.api.apps.dao.TbAppsDao;
import com.api.apps.data.dto.apps.update.EntranceMpDto;
import com.api.apps.data.entity.TbApps;
import com.api.apps.data.po.TbAppsPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

/**
 * 更新设置入口小程序
 *
 * @author 裴金伟
 */
@Service("AppsUpdateEntranceMpServiceImpl")
@RequiredArgsConstructor
public class EntranceMp extends AbstractService {

    private final TbAppsDao tbAppsDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(EntranceMpDto dto) {

        Optional.ofNullable(tbAppsDao.get(TbAppsPo.builder().whereId(dto.getId()).build()))
                .map(TbApps::getType)
                .ifPresent(type -> {
                   if (type != 2) {
                       throw new ServerException("该小程序不是小程序");
                   }
                });
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(EntranceMpDto dto) {
        tbAppsDao.update(TbAppsPo.builder().whereType(2).isEntranceMp(2).build());
        if (tbAppsDao.update(TbAppsPo.builder().whereId(dto.getId()).updatedUserId(StpUtil.getLoginIdAsInt()).isEntranceMp(1).build())!= 1) {
            throw new ServerException("更新失败");
        }
        return Result.ok("更新成功", Map.of("updatedAt", tbAppsDao.getFieldById(dto.getId(),  TbApps::getUpdatedAt).orElse("")));
    }
}
