package com.api.basic.service.sys.region;

import com.api.basic.dao.TbBasicSysRegionDao;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.dto.sys.region.GetDto;
import com.api.basic.data.entity.TbBasicSysRegion;
import com.api.basic.data.po.TbBasicSysRegionPo;
import com.api.basic.data.vo.sys.menu.GetVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.enums.IsDeleteEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 获取行政区域详情
 *
 * @author 裴金伟
 */
@Service("BasicSysRegionGetServiceImpl")
@RequiredArgsConstructor
public class Get extends AbstractService {

    private final TbBasicSysRegionDao tbBasicSysRegionDao;
    protected final TbBasicSysUserDao tbBasicSysUserDao;

    /**
     * 参数检查
     */
    public void check(GetDto dto) {
    }

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    public Result<?> service(GetDto dto) {
        TbBasicSysRegion entity = tbBasicSysRegionDao.get(TbBasicSysRegionPo.builder().whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).whereId(dto.getId()).build());
        GetVo vo = new GetVo();
        BeanUtils.copyProperties(entity, vo);

        vo.setCreatedUserName(tbBasicSysUserDao.getById(entity.getCreatedUserId()).getUsername());
        vo.setUpdatedUserName(tbBasicSysUserDao.getById(entity.getUpdatedUserId()).getUsername());
        return Result.ok(vo);
    }

}
