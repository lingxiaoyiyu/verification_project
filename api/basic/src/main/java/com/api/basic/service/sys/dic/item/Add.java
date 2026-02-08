package com.api.basic.service.sys.dic.item;

import cn.dev33.satoken.stp.StpUtil;
import com.api.basic.dao.TbBasicSysDictionaryItemDao;
import com.api.basic.data.dto.sys.dic.item.AddDto;
import com.api.basic.data.entity.TbBasicSysDictionaryItem;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 添加字典项信息
 *
 * @author 裴金伟
 */
@Service("BasicDicItemAddServiceImpl")
@RequiredArgsConstructor
public class Add extends AbstractService {

    private final TbBasicSysDictionaryItemDao tbBasicSysDictionaryItemDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(AddDto dto) {
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
        TbBasicSysDictionaryItem entity = handleAddData(dto);
        if (tbBasicSysDictionaryItemDao.add(entity) == 0) {
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
    protected TbBasicSysDictionaryItem handleAddData(AddDto dto){
        TbBasicSysDictionaryItem entity = new TbBasicSysDictionaryItem();
        entity.setDicIdentifying(dto.getDicIdentifying());
        entity.setName(dto.getName());
        entity.setValue(dto.getValue());
        entity.setIdentifying(dto.getIdentifying());
        entity.setDescription(dto.getDescription());
        entity.setBackgroundColor(dto.getBackgroundColor());
        entity.setStatus(dto.getStatus());
        entity.setCreatedUserId(StpUtil.getLoginIdAsInt());
        entity.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        return entity;
    }
}
