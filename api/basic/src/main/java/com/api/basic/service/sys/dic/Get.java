package com.api.basic.service.sys.dic;

import com.api.basic.dao.TbBasicSysDictionaryDao;
import com.api.basic.data.dto.sys.dic.GetDto;
import com.api.basic.data.entity.TbBasicSysDictionary;
import com.api.basic.data.po.TbBasicSysDictionaryPo;
import com.api.basic.data.vo.sys.dic.GetVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 获取指定字典组信息
 *
 * @author 裴金伟
 */
@Service("BasicDicGetServiceImpl")
@RequiredArgsConstructor
public class Get extends AbstractService {

    protected final TbBasicSysDictionaryDao tbBasicSysDictionaryDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(GetDto dto) {
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    public Result<?> service(GetDto dto) {
        TbBasicSysDictionary entity = tbBasicSysDictionaryDao.get(TbBasicSysDictionaryPo.builder()
            .whereId(dto.getId())
            .build());
        GetVo vo = new GetVo();
        if (entity != null) {
            BeanUtils.copyProperties(entity, vo);
        }

        return Result.ok(vo);
    }
}
