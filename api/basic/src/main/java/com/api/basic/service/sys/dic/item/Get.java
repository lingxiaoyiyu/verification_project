package com.api.basic.service.sys.dic.item;

import com.api.basic.dao.TbBasicSysDictionaryItemDao;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.dto.sys.dic.item.GetDto;
import com.api.basic.data.entity.TbBasicSysDictionaryItem;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.po.TbBasicSysDictionaryItemPo;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.basic.data.vo.sys.dic.item.GetVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 获取指定字典项信息
 *
 * @author 裴金伟
 */
@Service("BasicDicItemGetServiceImpl")
@RequiredArgsConstructor
public class Get extends AbstractService {

    protected final TbBasicSysUserDao tbBasicSysUserDao;
    protected final TbBasicSysDictionaryItemDao tbBasicSysDictionaryItemDao;

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
        TbBasicSysDictionaryItem entity = tbBasicSysDictionaryItemDao.get(TbBasicSysDictionaryItemPo.builder()
            .whereId(dto.getId())
            .build());
        GetVo vo = new GetVo();
        if (entity != null) {
            BeanUtils.copyProperties(entity, vo);
            List<Integer> userIds = Arrays.asList(entity.getCreatedUserId(), entity.getUpdatedUserId());
            Map<Integer, String> userNameMap = tbBasicSysUserDao.query(TbBasicSysUserPo.builder().whereInIds(userIds).build())
                .stream()
                .collect(Collectors.toMap(TbBasicSysUser::getId, TbBasicSysUser::getUsername));
            vo.setCreatedUserName(userNameMap.get(entity.getCreatedUserId()));
            vo.setUpdatedUserName(userNameMap.get(entity.getUpdatedUserId()));
        }

        return Result.ok(vo);
    }
}
