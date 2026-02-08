package com.api.basic.service.config.group;

import com.api.basic.dao.TbBasicConfigDao;
import com.api.basic.dao.TbBasicConfigGroupDao;
import com.api.basic.data.dto.config.group.DeleteDto;
import com.api.basic.data.entity.TbBasicConfigGroup;
import com.api.basic.data.po.TbBasicConfigGroupPo;
import com.api.basic.data.po.TbBasicConfigPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 删除配置组
 *
 * @author 裴金伟
 */
@Service("BasicConfigGroupDeleteServiceImpl")
@RequiredArgsConstructor
public class Delete extends AbstractService {

    private final TbBasicConfigGroupDao tbBasicConfigGroupDao;
    private final TbBasicConfigDao tbBasicConfigDao;

    /**
     * 参数检查
     */
    public void check(DeleteDto dto) {
        TbBasicConfigGroup entity = tbBasicConfigGroupDao.get(TbBasicConfigGroupPo.builder().whereId(dto.getId()).build());

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
    public Result<?> service(DeleteDto dto) {
        if (tbBasicConfigGroupDao.delete(TbBasicConfigGroupPo.builder().whereId(dto.getId()).build()) != 1) {
            throw new ServerException("删除失败");
        }
        tbBasicConfigDao.delete(TbBasicConfigPo.builder().whereGroupId(dto.getId()).build());
        return Result.ok("删除成功");
    }
}
