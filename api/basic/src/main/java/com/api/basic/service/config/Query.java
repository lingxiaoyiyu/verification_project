package com.api.basic.service.config;

import cn.hutool.core.collection.CollUtil;
import com.api.basic.dao.TbBasicConfigDao;
import com.api.basic.data.dto.config.QueryDto;
import com.api.basic.data.entity.TbBasicConfig;
import com.api.basic.data.po.TbBasicConfigPo;
import com.api.basic.data.vo.config.ItemVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.po.BasePageSortList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 查询配置项列表
 */
@Service("BasicConfigQueryServiceImpl")
@RequiredArgsConstructor
public class Query extends AbstractService {

    private final TbBasicConfigDao tbBasicConfigDao;

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    public Result<?> service(QueryDto dto) {
        // 构建查询条件
        TbBasicConfigPo queryPo = handleQueryData(dto);
        // 查询列表
        List<TbBasicConfig> configList = tbBasicConfigDao.query(queryPo);
        List<ItemVo> itemVoList = new ArrayList<>();
        if (CollUtil.isNotEmpty(configList)) {
            for (TbBasicConfig entity : configList) {
                itemVoList.add(convertToVo(entity));
            }
        }
        return Result.ok("处理成功", Map.of("list", itemVoList));
    }

    /**
     * 处理要查询的条件
     *
     * @param dto DTO对象
     * @return 处理后的数据
     */
    private TbBasicConfigPo handleQueryData(QueryDto dto) {
        TbBasicConfigPo po = new TbBasicConfigPo();
        po.setWhereGroupId(dto.getGroupId());
        po.setWhereStatus(dto.getStatus());
        po.setSortList(new BasePageSortList().addSort("sort", "ASC").getSortList());
        return po;
    }

    /**
     * entity转换成vo
     *
     * @param entity 转换前的Entity对象
     * @return 展缓后的vo对象
     */
    private ItemVo convertToVo(TbBasicConfig entity) {
        ItemVo vo = new ItemVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
