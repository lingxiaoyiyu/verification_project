package com.api.basic.service.config.group;

import com.api.basic.dao.TbBasicConfigGroupDao;
import com.api.basic.data.dto.config.group.UpdateDto;
import com.api.basic.data.entity.TbBasicConfigGroup;
import com.api.basic.data.po.TbBasicConfigGroupPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 更新配置组
 *
 * @author 裴金伟
 */
@Service("BasicConfigGroupUpdateServiceImpl")
@RequiredArgsConstructor
public class Update extends AbstractService {

    private final TbBasicConfigGroupDao tbBasicConfigGroupDao;

    /**
     * 参数检查
     */
    public void check(UpdateDto dto) {
        TbBasicConfigGroup entity = tbBasicConfigGroupDao.get(TbBasicConfigGroupPo.builder().whereId(dto.getId()).build());

        if (entity == null) {
            throw new ServerException("配置不存在");
        }

        if (tbBasicConfigGroupDao.cnt(TbBasicConfigGroupPo.builder().whereId(dto.getId()).build()) == 0) {
            throw new ServerException("信息不存在");
        }

        if (tbBasicConfigGroupDao.cnt(TbBasicConfigGroupPo.builder().whereNotId(dto.getId()).whereName(dto.getName()).build()) != 0) {
            throw new ServerException("配置组名称已存在");
        }

        if (tbBasicConfigGroupDao.cnt(TbBasicConfigGroupPo.builder().whereNotId(dto.getId()).whereValue(dto.getValue()).build()) != 0) {
            throw new ServerException("配置组值已存在");
        }
    }

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(UpdateDto dto) {
        if (tbBasicConfigGroupDao.update(handlUpdateData(dto)) != 1) {
            throw new ServerException("更新失败");
        }
        return Result.ok("更新成功");
    }


    /**
     * 处理要更新的数据
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected TbBasicConfigGroupPo handlUpdateData(UpdateDto dto){
        TbBasicConfigGroupPo po = new TbBasicConfigGroupPo();
        BeanUtils.copyProperties(dto, po);
        po.setWhereId(dto.getId());
        po.setName(dto.getName());
        po.setValue(dto.getValue());

        return po;
    }
}
