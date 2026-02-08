package com.api.basic.service.sys.dic;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysDictionaryDao;
import com.api.basic.data.dto.sys.dic.RemoveDto;
import com.api.basic.data.entity.TbBasicSysDictionary;
import com.api.basic.data.po.TbBasicSysDictionaryPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 删除字典组信息
 *
 * @author 裴金伟
 */
@Service("BasicDicRemoveServiceImpl")
@RequiredArgsConstructor
public class Remove extends AbstractService {

    private final TbBasicSysDictionaryDao tbBasicSysDictionaryDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(RemoveDto dto) {
        TbBasicSysDictionary entity = tbBasicSysDictionaryDao.get(TbBasicSysDictionaryPo.builder().whereId(dto.getId()).build());

        if (entity == null) {
            throw new ServerException("字典不存在");
        }

        if (StrUtil.isNotBlank(dto.getUpdatedAt()) && !dto.getUpdatedAt().equals(entity.getUpdatedAt())) {
            throw new ServerException("用户信息已被其他用户修改");
        }
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(RemoveDto dto) {
        // 构建删除对象
        TbBasicSysDictionaryPo po = handleRemoveData(dto);
        if (tbBasicSysDictionaryDao.update(po) == 0) {
            throw new ServerException("删除失败");
        }
        return Result.ok("删除成功");
    }

    /**
     * 处理要删除的数据
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected TbBasicSysDictionaryPo handleRemoveData(RemoveDto dto){
        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
        po.setWhereId(dto.getId());
        po.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        return po;
    }
}
