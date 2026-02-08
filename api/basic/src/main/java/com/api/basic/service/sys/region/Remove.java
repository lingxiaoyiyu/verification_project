package com.api.basic.service.sys.region;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysRegionDao;
import com.api.basic.data.dto.sys.region.RemoveDto;
import com.api.basic.data.entity.TbBasicSysRegion;
import com.api.basic.data.po.TbBasicSysRegionPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 删除行政区域
 */
@Service("BasicSysRegionRemoveService")
@RequiredArgsConstructor
public class Remove extends AbstractService {

    private final TbBasicSysRegionDao tbBasicSysRegionDao;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(RemoveDto dto) {
        TbBasicSysRegion entity = tbBasicSysRegionDao.get(TbBasicSysRegionPo.builder().whereId(dto.getId()).whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).build());

        if (entity == null) {
            throw new ServerException("行政区域不存在");
        }

        if (StrUtil.isNotBlank(dto.getUpdatedAt()) && !dto.getUpdatedAt().equals(entity.getUpdatedAt())) {
            throw new ServerException("行政区域信息已被其他用户修改");
        }
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(RemoveDto dto) {
        TbBasicSysRegionPo tbBasicSysRegionPo = new TbBasicSysRegionPo();
        tbBasicSysRegionPo.setIsDelete(IsDeleteEnum.DELETED.getCode());
        tbBasicSysRegionPo.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        tbBasicSysRegionPo.setWhereId(dto.getId());
        if (tbBasicSysRegionDao.update(tbBasicSysRegionPo) != 1) {
            throw new ServerException("行政区域删除失败");
        }
        return Result.ok("行政区域删除成功");
    }
}
