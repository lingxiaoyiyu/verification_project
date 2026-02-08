package com.api.basic.service.config.update;

import cn.dev33.satoken.stp.StpUtil;
import com.api.basic.dao.TbBasicConfigDao;
import com.api.basic.data.dto.sys.menu.update.SortDto;
import com.api.basic.data.entity.TbBasicConfig;
import com.api.basic.data.po.TbBasicConfigPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.po.BasePageSortList;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 更新配置项排序
 *
 * @author 裴金伟
 * @date 2025-02-19
 */
@Service("BasicConfigUpdateSortServiceImpl")
@RequiredArgsConstructor
public class Sort extends AbstractService {

    private final TbBasicConfigDao tbBasicConfigDao;

    /**
     * 参数检查
     */
    public void check(SortDto dto) {
        TbBasicConfig entity = tbBasicConfigDao.get(TbBasicConfigPo.builder().whereId(dto.getId()).build());

        if (entity == null) {
            throw new ServerException("配置不存在");
        }
    }

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(SortDto dto) {
        // 获取需要调整的配置项
        TbBasicConfig targetConfig = tbBasicConfigDao.get(TbBasicConfigPo.builder().whereId(dto.getTargetId()).build());
        if (targetConfig == null) {
            throw new ServerException("目标配置项不存在");
        }

        // 获取需要移动的配置项
        TbBasicConfig sourceConfig = tbBasicConfigDao.get(TbBasicConfigPo.builder().whereId(dto.getId()).build());
        if (sourceConfig == null) {
            throw new ServerException("要移动的配置项不存在");
        }

        Integer startSort = 0;
        // 根据 direction 调整指定菜单在列表中的位置
        if ("top".equalsIgnoreCase(dto.getDirection())) { // 调整到目标配置项上面
            startSort = targetConfig.getSort();
        } else if ("bottom".equalsIgnoreCase(dto.getDirection())) { // 调整到目标配置项下面
            startSort = targetConfig.getSort() + 1;
        } else {
            throw new ServerException("无效的方向参数");
        }
        List<TbBasicConfig> configList = tbBasicConfigDao.query(TbBasicConfigPo.builder().whereStartSort(startSort).sortList(new BasePageSortList().addSort("sort", "asc").getSortList()).build());
        tbBasicConfigDao.update(TbBasicConfigPo.builder()
                .whereId(sourceConfig.getId())
                .updatedUserId(StpUtil.getLoginIdAsInt())
                .sort(startSort).build());
        for (TbBasicConfig config : configList) {
            if (config.getId().equals(sourceConfig.getId())) {
                continue;
            }
            tbBasicConfigDao.update(TbBasicConfigPo.builder().whereId(config.getId()).sort(++startSort).build());
        }

        resetSort();

        return Result.ok("配置更新成功", Map.of("updatedAt", tbBasicConfigDao.getFieldById(dto.getId(), TbBasicConfig::getUpdatedAt).orElse("")));
    }

    private void resetSort(){
        int startSort = 0;
        List<TbBasicConfig> configList = tbBasicConfigDao.query(TbBasicConfigPo.builder().sortList(new BasePageSortList().addSort("sort", "asc").getSortList()).build());
        for (TbBasicConfig config : configList) {
            tbBasicConfigDao.update(TbBasicConfigPo.builder().whereId(config.getId()).sort(++startSort).build());
        }
    }
}
