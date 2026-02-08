package com.api.basic.service.config.group;


import cn.dev33.satoken.stp.StpUtil;
import com.api.basic.dao.TbBasicConfigGroupDao;
import com.api.basic.data.dto.config.group.AddDto;
import com.api.basic.data.entity.TbBasicConfigGroup;
import com.api.basic.data.po.TbBasicConfigGroupPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 添加配置组
 *
 * @author 裴金伟
 */
@Service("BasicConfigGroupAddServiceImpl")
@RequiredArgsConstructor
public class Add extends AbstractService {

    private final TbBasicConfigGroupDao tbBasicConfigGroupDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(AddDto dto) {
        if (tbBasicConfigGroupDao.cnt(TbBasicConfigGroupPo.builder().whereName(dto.getName()).build()) == 1) {
            throw new ServerException("配置组名称已存在");
        }

        if (tbBasicConfigGroupDao.cnt(TbBasicConfigGroupPo.builder().whereValue(dto.getValue()).build()) == 1) {
            throw new ServerException("配置组值已存在");
        }
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(AddDto dto) {
        // 构建添加对象
        TbBasicConfigGroup entity = handleAddData(dto);
        if (tbBasicConfigGroupDao.add(entity) == 0) {
            throw new ServerException("添加失败");
        }
        return Result.ok("添加成功", Map.of("id", entity.getId()));
    }

    /**
     * 处理要添加的数据
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected TbBasicConfigGroup handleAddData(AddDto dto){
        TbBasicConfigGroup entity = new TbBasicConfigGroup();
        BeanUtils.copyProperties(dto, entity);
        entity.setCreatedUserId(StpUtil.getLoginIdAsInt());
        return entity;
    }
}
