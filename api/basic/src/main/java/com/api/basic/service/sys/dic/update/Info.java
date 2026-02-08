package com.api.basic.service.sys.dic.update;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysDictionaryDao;
import com.api.basic.data.dto.sys.dic.update.InfoDto;
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
 * 修改字典组状态
 *
 * @author 裴金伟
 */
@Service("BasicDicUpdateInfoServiceImpl")
@RequiredArgsConstructor
public class Info extends AbstractService {

    private final TbBasicSysDictionaryDao tbBasicSysDictionaryDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(InfoDto dto) {
        TbBasicSysDictionary entity = tbBasicSysDictionaryDao.get(TbBasicSysDictionaryPo.builder().whereId(dto.getId()).build());

        if (entity == null) {
            throw new ServerException("字典不存在");
        }

        if (StrUtil.isNotBlank(dto.getUpdatedAt()) && !dto.getUpdatedAt().equals(entity.getUpdatedAt())) {
            throw new ServerException("用户信息已被其他用户修改");
        }

        if (tbBasicSysDictionaryDao.cnt(TbBasicSysDictionaryPo.builder()
            .whereNotId(dto.getId())
            .whereNotIdentifying(dto.getIdentifying())
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
    public Result<?> service(InfoDto dto) {
        if (tbBasicSysDictionaryDao.update(handlUpdateData(dto)) != 1) {
            throw new ServerException("更新失败");
        }

        return Result.ok("更新成功", Map.of("updatedAt", tbBasicSysDictionaryDao.getFieldById(dto.getId(), TbBasicSysDictionary::getUpdatedAt).orElse("")));
    }

    /**
    * 处理要更新的数据
    *
    * @param dto 请求参数
    * @return 处理后的数据
    */
    protected TbBasicSysDictionaryPo handlUpdateData(InfoDto dto){
        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
        po.setWhereId(dto.getId());
        po.setParentId(dto.getParentId());
        po.setName(dto.getName());
        po.setIdentifying(dto.getIdentifying());
        po.setDescription(dto.getDescription());
        po.setStatus(dto.getStatus());
        po.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        return po;
    }
}
