package com.api.basic.service.sys.dic.item.update;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysDictionaryItemDao;
import com.api.basic.data.dto.sys.dic.item.update.StatusDto;
import com.api.basic.data.entity.TbBasicSysDictionaryItem;
import com.api.basic.data.po.TbBasicSysDictionaryItemPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 修改字典项信息
 *
 * @author 裴金伟
 */
@Service("BasicDicItemUpdateStatusServiceImpl")
@RequiredArgsConstructor
public class Status extends AbstractService {

    private final TbBasicSysDictionaryItemDao tbBasicSysDictionaryItemDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(StatusDto dto) {
        TbBasicSysDictionaryItem entity = tbBasicSysDictionaryItemDao.get(TbBasicSysDictionaryItemPo.builder().whereId(dto.getId()).build());

        if (entity == null) {
            throw new ServerException("字典项不存在");
        }

        if (StrUtil.isNotBlank(dto.getUpdatedAt()) && !dto.getUpdatedAt().equals(entity.getUpdatedAt())) {
            throw new ServerException("字典项已被其他用户修改");
        }
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(StatusDto dto) {
        if (tbBasicSysDictionaryItemDao.update(handlUpdateData(dto)) != 1) {
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
    protected TbBasicSysDictionaryItemPo handlUpdateData(StatusDto dto){
        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereId(dto.getId());
        po.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        return po;
    }
}
