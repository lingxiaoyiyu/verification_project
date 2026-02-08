package com.api.basic.service.sys.dic;

import cn.dev33.satoken.stp.StpUtil;
import com.api.basic.dao.TbBasicSysDictionaryDao;
import com.api.basic.data.dto.sys.dic.AddDto;
import com.api.basic.data.entity.TbBasicSysDictionary;
import com.api.basic.data.po.TbBasicSysDictionaryPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 添加字典组信息
 *
 * @author 裴金伟
 */
@Service("BasicDicAddServiceImpl")
@RequiredArgsConstructor
public class Add extends AbstractService {

    private final TbBasicSysDictionaryDao tbBasicSysDictionaryDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(AddDto dto) {
        if (tbBasicSysDictionaryDao.cnt(TbBasicSysDictionaryPo.builder()
            .whereIdentifying(dto.getIdentifying())
            .build()) != 0) {
            throw new ServerException("字典标识已存在");
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
        TbBasicSysDictionary entity = handleAddData(dto);
        if (tbBasicSysDictionaryDao.add(entity) == 0) {
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
    protected TbBasicSysDictionary handleAddData(AddDto dto){
        TbBasicSysDictionary entity = new TbBasicSysDictionary();
        entity.setParentId(dto.getParentId());
        entity.setName(dto.getName());
        entity.setIdentifying(dto.getIdentifying());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setCreatedUserId(StpUtil.getLoginIdAsInt());
        entity.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        return entity;
    }
}
