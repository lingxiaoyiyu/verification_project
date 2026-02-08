package com.api.basic.service.sys.region;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysRegionDao;
import com.api.basic.data.dto.sys.region.AddDto;
import com.api.basic.data.entity.TbBasicSysRegion;
import com.api.basic.data.po.TbBasicSysRegionPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.po.BasePageSortList;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

/**
 * 添加行政区域
 */
@Service("BasicSysRegionAddService")
@RequiredArgsConstructor
public class Add extends AbstractService {

    private final TbBasicSysRegionDao tbBasicSysRegionDao;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(AddDto dto) {
        dto.setName(StrUtil.trim(dto.getName()));
        TbBasicSysRegionPo regionPo = new TbBasicSysRegionPo();
        regionPo.setWhereName(dto.getName());
        regionPo.setWhereIsDelete(IsDeleteEnum.NO_DELETE.getCode());
        if (tbBasicSysRegionDao.cnt(regionPo) > 0) {
            throw new ServerException("行政区域名称已存在");
        } else if (dto.getParentCode() != null && dto.getParentCode() > 0) {
            regionPo.setWhereName(null);
            regionPo.setWhereCode(dto.getParentCode());
            if (tbBasicSysRegionDao.cnt(regionPo) == 0) {
                throw new ServerException("上级行政区域不存在");
            }
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
        TbBasicSysRegion entity = handleAddData(dto);
        if (tbBasicSysRegionDao.add(entity) == 0) {
            throw new ServerException("行政区域添加失败");
        }
        return Result.ok("行政区域添加成功", Map.of("id", entity.getId()));
    }

    /**
     * 处理要添加的数据
     *
     * @param dto DTO对象
     * @return 处理后的数据
     */
    protected TbBasicSysRegion handleAddData(AddDto dto) {
        TbBasicSysRegion entity = new TbBasicSysRegion();
        BeanUtils.copyProperties(dto, entity);
        if (dto.getParentCode() == null) {
            entity.setParentCode(0L);
        }

        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereParentCode(dto.getParentCode());
        po.setWhereIsDelete(IsDeleteEnum.NO_DELETE.getCode());
        po.setSortList(new BasePageSortList().addSort("sort", "DESC", "tbr").getSortList());
        entity.setSort(Optional.ofNullable(tbBasicSysRegionDao.get(po)).map(TbBasicSysRegion::getSort).orElse(0) + 1);

        entity.setCreatedUserId(StpUtil.getLoginIdAsInt());
        entity.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        return entity;
    }
}
