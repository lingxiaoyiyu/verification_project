package com.api.basic.service.sys.region;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysRegionDao;
import com.api.basic.data.dto.sys.region.UpdateDto;
import com.api.basic.data.entity.TbBasicSysRegion;
import com.api.basic.data.po.TbBasicSysRegionPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 更新行政区域
 */
@Service("BasicSysRegionUpdateService")
@RequiredArgsConstructor
public class Update extends AbstractService {

    private final TbBasicSysRegionDao tbBasicSysRegionDao;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(UpdateDto dto) {
        TbBasicSysRegion entity = tbBasicSysRegionDao.get(TbBasicSysRegionPo.builder().whereId(dto.getId()).whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).build());

        if (entity == null) {
            throw new ServerException("行政区域不存在");
        }

        if (StrUtil.isNotBlank(dto.getUpdatedAt()) && !dto.getUpdatedAt().equals(entity.getUpdatedAt())) {
            throw new ServerException("行政区域信息已被其他用户修改");
        }

        if (tbBasicSysRegionDao.cnt(TbBasicSysRegionPo.builder().whereName(dto.getName()).whereNotId(dto.getId()).build()) > 0) {
            throw new ServerException("行政区域名称已存在");
        }

        if (dto.getParentCode() != null && dto.getParentCode() > 0 && tbBasicSysRegionDao.cnt(TbBasicSysRegionPo.builder().whereCode(dto.getParentCode()).build()) == 0) {
            throw new ServerException("上级行政区域不存在");
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
        if (tbBasicSysRegionDao.update(handlUpdateData(dto)) != 1) {
            throw new ServerException("行政区域更新失败");
        }

        Map<String, String> data = new HashMap<>();
        data.put("updatedAt", tbBasicSysRegionDao.getFieldById(dto.getId(), TbBasicSysRegion::getUpdatedAt).orElse(""));
        return Result.ok("行政区域更新成功", data);
    }

    /**
     * 处理要更新的数据
     *
     * @param dto DTO对象
     * @return 处理后的数据
     */
    protected TbBasicSysRegionPo handlUpdateData(UpdateDto dto) {
        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereId(dto.getId());
        BeanUtils.copyProperties(dto, po);
        if (dto.getParentCode() == null || dto.getParentCode() == 0) {
            po.setParentCode(0L);
        } else {
            po.setParentCode(dto.getParentCode());
        }
        po.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        return po;
    }
}
