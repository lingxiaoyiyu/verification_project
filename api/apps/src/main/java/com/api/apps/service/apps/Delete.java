package com.api.apps.service.apps;

import com.api.apps.dao.TbAppsDao;
import com.api.apps.dao.TbAppsVersionDao;
import com.api.apps.data.dto.apps.DeleteDto;
import com.api.apps.data.entity.TbApps;
import com.api.apps.data.po.TbAppsPo;
import com.api.apps.data.po.TbAppsVersionPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 删除应用
 *
 * @author 裴金伟
 */
@Service("AppsDeleteServiceImpl")
@RequiredArgsConstructor
public class Delete extends AbstractService {

    private final TbAppsDao tbAppsDao;
    private final TbAppsVersionDao tbAppsVersionDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(DeleteDto dto) {
        TbApps entity = tbAppsDao.get(TbAppsPo.builder().whereId(dto.getId()).build());
        if (entity == null) {
            throw new ServerException("信息不存在");
        }
        if (entity.getIsEntranceMp() == 1) {
            throw new ServerException("入口小程序不能删除");
        }
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(DeleteDto dto) {
        if (tbAppsDao.delete(TbAppsPo.builder().whereId(dto.getId()).build()) == 0) {
            throw new ServerException("删除失败");
        } else {
            tbAppsVersionDao.delete(TbAppsVersionPo.builder().whereAppId(dto.getId()).build());
        }
        return Result.ok("删除成功");
    }
}
