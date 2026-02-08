package com.api.basic.service.config;

import com.api.basic.dao.TbBasicConfigDao;
import com.api.basic.data.dto.config.DeleteDto;
import com.api.basic.data.entity.TbBasicConfig;
import com.api.basic.data.po.TbBasicConfigPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 删除配置项
 */
@Service("BasicConfigDeleteServiceImpl")
@RequiredArgsConstructor
public class Delete extends AbstractService {

    private final TbBasicConfigDao tbBasicConfigDao;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(DeleteDto dto) {
        TbBasicConfig entity = tbBasicConfigDao.get(TbBasicConfigPo.builder().whereId(dto.getId()).build());

        if (entity == null) {
            throw new ServerException("配置不存在");
        }
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(DeleteDto dto) {
        if (tbBasicConfigDao.delete(TbBasicConfigPo.builder().whereId(dto.getId()).build()) != 1) {
            throw new ServerException("配置删除失败");
        }
        return Result.ok("配置删除成功");
    }
}
