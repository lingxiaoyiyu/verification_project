package com.api.basic.service.sys.dic.item;

import cn.dev33.satoken.stp.StpUtil;
import com.api.basic.dao.TbBasicSysDictionaryItemDao;
import com.api.basic.data.dto.sys.dic.item.DeleteDto;
import com.api.basic.data.entity.TbBasicSysDictionaryItem;
import com.api.basic.data.po.TbBasicSysDictionaryItemPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 删除字典项信息
 *
 * @author 裴金伟
 */
@Service("BasicDicItemDeleteServiceImpl")
@RequiredArgsConstructor
public class Delete extends AbstractService {

    private final TbBasicSysDictionaryItemDao tbBasicSysDictionaryItemDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(DeleteDto dto) {
        TbBasicSysDictionaryItem entity = tbBasicSysDictionaryItemDao.get(TbBasicSysDictionaryItemPo.builder().whereId(dto.getId()).build());

        if (entity == null) {
            throw new ServerException("字典项不存在");
        }
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(DeleteDto dto) {
        // 构建删除对象
        TbBasicSysDictionaryItemPo po = handleRemoveData(dto);
        if (tbBasicSysDictionaryItemDao.delete(po) == 0) {
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
    protected TbBasicSysDictionaryItemPo handleRemoveData(DeleteDto dto){
        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        po.setWhereId(dto.getId());
        po.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        return po;
    }
}
