package com.api.basic.service.config.update;

import cn.dev33.satoken.stp.StpUtil;
import com.api.basic.dao.TbBasicConfigDao;
import com.api.basic.data.dto.config.update.StatusDto;
import com.api.basic.data.entity.TbBasicConfig;
import com.api.basic.data.po.TbBasicConfigPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 更新状态
 *
 * @author 裴金伟
 */
@Service("BasicConfigUpdateStatusServiceImpl")
@RequiredArgsConstructor
public class Status extends AbstractService {

    private final TbBasicConfigDao tbBasicConfigDao;

    /**
     * 参数检查
     */
    public void check(StatusDto dto) {
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
    public Result<?> service(StatusDto dto) {
        if (tbBasicConfigDao.update(TbBasicConfigPo.builder()
                .whereId(dto.getId())
                .status(dto.getStatus())
                .updatedUserId(StpUtil.getLoginIdAsInt())
                .build()) != 1) {
            throw new ServerException("更新失败");
        }
        return Result.ok("更新成功", Map.of("updatedAt", tbBasicConfigDao.getFieldById(dto.getId(), TbBasicConfig::getUpdatedAt).orElse("")));
    }


    /**
     * 处理要更新的数据
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected TbBasicConfigPo handlUpdateData(StatusDto dto){
        TbBasicConfigPo po = new TbBasicConfigPo();
        BeanUtils.copyProperties(dto, po);
        po.setWhereId(dto.getId());
        po.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        return po;
    }
}
