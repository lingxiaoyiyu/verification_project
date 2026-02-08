package com.api.basic.service.config;

import com.api.basic.dao.TbBasicConfigDao;
import com.api.basic.dao.TbBasicConfigGroupDao;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.dto.config.GetDto;
import com.api.basic.data.entity.TbBasicConfig;
import com.api.basic.data.entity.TbBasicConfigGroup;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.po.TbBasicConfigPo;
import com.api.basic.data.vo.config.GetVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 获取配置项详情
 *
 * @author 裴金伟
 */
@Service("BasicConfigGetServiceImpl")
@RequiredArgsConstructor
public class Get extends AbstractService {

    private final TbBasicConfigDao tbBasicConfigDao;
    private final TbBasicConfigGroupDao tbBasicConfigGroupDao;
    private final TbBasicSysUserDao tbBasicSysUserDao;

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
        GetVo vo = new GetVo();
        TbBasicConfig entity = tbBasicConfigDao.get(TbBasicConfigPo.builder().whereId(dto.getId()).build());
        if (entity != null) {
            BeanUtils.copyProperties(entity, vo);
            vo.setGroupName(tbBasicConfigGroupDao.getFieldById(entity.getGroupId(), TbBasicConfigGroup::getName).orElse(""));
            vo.setCreatedUserName(tbBasicSysUserDao.getFieldById(entity.getCreatedUserId(), TbBasicSysUser::getUsername).orElse(""));
            vo.setUpdatedUserName(tbBasicSysUserDao.getFieldById(entity.getUpdatedUserId(), TbBasicSysUser::getUsername).orElse(""));
        }
        return Result.ok(vo);
    }

    /**
     * 处理要查询的条件
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected TbBasicConfigPo  handleQueryData(GetDto dto){
        TbBasicConfigPo po = new TbBasicConfigPo();
        BeanUtils.copyProperties(dto, po);
        return po;
    }
}
