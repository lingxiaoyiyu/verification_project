package com.api.basic.service.config.group;

import com.api.basic.dao.TbBasicConfigGroupDao;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.dto.config.group.GetDto;
import com.api.basic.data.entity.TbBasicConfigGroup;
import com.api.basic.data.po.TbBasicConfigGroupPo;
import com.api.basic.data.vo.config.group.GetVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 获取配置组详情
 *
 * @author 裴金伟
 */
@Service("BasicConfigGroupGetServiceImpl")
@RequiredArgsConstructor
public class Get extends AbstractService {

    private final TbBasicConfigGroupDao tbBasicConfigGroupDao;
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
        TbBasicConfigGroup entity = tbBasicConfigGroupDao.get(TbBasicConfigGroupPo.builder().whereId(dto.getId()).build());
        GetVo vo = new GetVo();
        BeanUtils.copyProperties(entity, vo);
        vo.setCreatedUserName(tbBasicSysUserDao.getById(entity.getCreatedUserId()).getUsername());
        return Result.ok(vo);
    }
}
