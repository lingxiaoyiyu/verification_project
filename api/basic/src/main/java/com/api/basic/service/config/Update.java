package com.api.basic.service.config;

import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicConfigDao;
import com.api.basic.data.dto.config.UpdateDto;
import com.api.basic.data.entity.TbBasicConfig;
import com.api.basic.data.po.TbBasicConfigPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 更新配置
 */
@Service("BasicConfigUpdateService")
@RequiredArgsConstructor
public class Update extends AbstractService {

    private final TbBasicConfigDao tbBasicConfigDao;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(UpdateDto dto) {
        TbBasicConfig entity = tbBasicConfigDao.get(TbBasicConfigPo.builder().whereId(dto.getId()).build());

        if (entity == null) {
            throw new ServerException("配置不存在");
        }

        if (StrUtil.isNotBlank(dto.getUpdatedAt()) && !dto.getUpdatedAt().equals(entity.getUpdatedAt())) {
            throw new ServerException("配置信息已被其他用户修改");
        }

        if (tbBasicConfigDao.cnt(TbBasicConfigPo.builder().whereNotId(dto.getId()).whereName(dto.getName()).build()) != 0) {
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
    public Result<?> service(UpdateDto dto) {
        if (tbBasicConfigDao.update(TbBasicConfigPo.builder()
                .whereId(dto.getId())
                .groupId(dto.getGroupId())
                .name(dto.getName())
                .description(dto.getDescription())
                .dataType(dto.getDataType())
                .status(dto.getStatus())
                .title(dto.getTitle())
                .componentData(dto.getComponentData())
                .build()) != 1) {
            throw new ServerException("配置更新失败");
        }
        return Result.ok("配置更新成功", Map.of("updatedAt", tbBasicConfigDao.getFieldById(dto.getId(), TbBasicConfig::getUpdatedAt).orElse("")));
    }
}
