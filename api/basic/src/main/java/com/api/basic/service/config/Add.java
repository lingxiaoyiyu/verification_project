package com.api.basic.service.config;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicConfigDao;
import com.api.basic.data.dto.config.AddDto;
import com.api.basic.data.entity.TbBasicConfig;
import com.api.basic.data.po.TbBasicConfigPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.po.BasePageSortList;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 添加配置项
 */
@Service("BasicConfigAddServiceImpl")
@RequiredArgsConstructor
public class Add extends AbstractService {

    private final TbBasicConfigDao tbBasicConfigDao;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(AddDto dto) {
        dto.setName(StrUtil.trim(dto.getName()));
        dto.setDescription(StrUtil.trim(dto.getDescription()));

        if (tbBasicConfigDao.cnt(TbBasicConfigPo.builder().whereName(dto.getName()).build()) != 0) {
            throw new ServerException("配置名称已存在");
        }
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(AddDto dto) {
        // 构建配置添加对象
        TbBasicConfig entity = handleAddData(dto);
        if (tbBasicConfigDao.add(entity) == 0) {
            throw new ServerException("配置添加失败");
        }
        return Result.ok("配置添加成功", Map.of("id", entity.getId()));

    }

    /**
     * 处理要添加的数据
     *
     * @param dto DTO对象
     * @return 处理后的数据
     */
    private TbBasicConfig handleAddData(AddDto dto) {
        TbBasicConfig addEntity = new TbBasicConfig();
        BeanUtils.copyProperties(dto, addEntity);
        TbBasicConfig maxSort = tbBasicConfigDao.get(TbBasicConfigPo.builder().sortList(new BasePageSortList().addSort("sort", "desc").getSortList()).build());
        addEntity.setSort(ObjUtil.isEmpty(maxSort) ? 1 : maxSort.getSort() + 1);
        addEntity.setCreatedUserId(StpUtil.getLoginIdAsInt());
        addEntity.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        return addEntity;
    }
}
