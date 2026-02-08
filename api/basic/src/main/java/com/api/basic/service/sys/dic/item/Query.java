package com.api.basic.service.sys.dic.item;

import com.api.basic.dao.TbBasicSysDictionaryItemDao;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.dto.sys.dic.item.QueryDto;
import com.api.basic.data.entity.TbBasicSysDictionaryItem;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.po.TbBasicSysDictionaryItemPo;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.basic.data.vo.sys.dic.item.QueryItemVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 获取字典项列表
 *
 * @author 裴金伟
 */
@Service("BasicDicItemQueryServiceImpl")
@RequiredArgsConstructor
public class Query extends AbstractService {

    protected final TbBasicSysUserDao tbBasicSysUserDao;
    private final TbBasicSysDictionaryItemDao tbBasicSysDictionaryItemDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(QueryDto dto) {
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    public Result<?> service(QueryDto dto) {
        TbBasicSysDictionaryItemPo queryPo = handleQueryData(dto);
        List<TbBasicSysDictionaryItem> entityList = tbBasicSysDictionaryItemDao.query(queryPo);
        List<QueryItemVo> voList = new ArrayList<>();
        if (entityList != null) {
            List<Integer> userIds = entityList.stream()
                .flatMap(entity -> Stream.of(
                    entity.getCreatedUserId(),
                    entity.getUpdatedUserId()
                ))
                .distinct()
                .toList();
            Map<Integer, String> userNameMap = tbBasicSysUserDao.query(TbBasicSysUserPo.builder().whereInIds(userIds).build())
                .stream()
                .collect(Collectors.toMap(TbBasicSysUser::getId, TbBasicSysUser::getUsername));
            entityList.forEach(entity -> {
                QueryItemVo vo = convertToVo(entity);
                vo.setCreatedUserName(userNameMap.get(entity.getCreatedUserId()));
                vo.setUpdatedUserName(userNameMap.get(entity.getUpdatedUserId()));
                voList.add(vo);
            });
        }

        return Result.ok(Map.of("list", voList));
    }

    /**
     * 处理要查询的条件
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected TbBasicSysDictionaryItemPo handleQueryData(QueryDto dto){
        TbBasicSysDictionaryItemPo po = new TbBasicSysDictionaryItemPo();
        BeanUtils.copyProperties(dto, po);
        po.setWhereDicIdentifying(dto.getDicIdentifying());
        return po;
    }


    /**
     * entity转换成vo
     *
     * @param entity 转换前的Entity对象
     * @return 转换后的vo对象
     */
    private QueryItemVo convertToVo(TbBasicSysDictionaryItem entity){
        QueryItemVo vo = new QueryItemVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
