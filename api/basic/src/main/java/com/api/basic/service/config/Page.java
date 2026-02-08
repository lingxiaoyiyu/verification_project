package com.api.basic.service.config;

import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicConfigDao;
import com.api.basic.dao.TbBasicConfigGroupDao;
import com.api.basic.data.dto.config.PageDto;
import com.api.basic.data.entity.TbBasicConfig;
import com.api.basic.data.entity.TbBasicConfigGroup;
import com.api.basic.data.po.TbBasicConfigGroupPo;
import com.api.basic.data.po.TbBasicConfigPo;
import com.api.basic.data.vo.config.ItemVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.po.BasePageSortList;
import com.api.common.base.data.vo.BasePageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 获取配置项列表-分页
 *
 * @author 裴金伟
 */
@Service("BasicConfigPageServiceImpl")
@RequiredArgsConstructor
public class Page extends AbstractService {

    private final TbBasicConfigDao tbBasicConfigDao;
    private final TbBasicConfigGroupDao tbBasicConfigGroupDao;

    /**
     * 参数检查
     */
    public void check(PageDto dto) {
    }

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    public Result<?> service(PageDto dto) {
        TbBasicConfigPo queryPo = handleQueryData(dto);
        List<TbBasicConfig> entityList = tbBasicConfigDao.query(queryPo);
        List<ItemVo> voList = new ArrayList<>();
        if (entityList != null) {
            Map<Integer, String> groupMap = tbBasicConfigGroupDao.query(TbBasicConfigGroupPo.builder().whereInIds(entityList.stream().map(TbBasicConfig::getGroupId).distinct().toList()).build())
                    .stream()
                    .collect(Collectors.toMap(TbBasicConfigGroup::getId, TbBasicConfigGroup::getName));
            entityList.forEach(entity -> {
                ItemVo vo = convertToVo(entity);
                vo.setGroupName(groupMap.get(entity.getGroupId()));
                voList.add(vo);
            });
        }

        BasePageVo<ItemVo> basePageVo = new BasePageVo<>();
        basePageVo.setList(voList);
        basePageVo.setTotal(tbBasicConfigDao.cnt(queryPo)); // 统计总数
        return Result.ok(basePageVo);
    }

    /**
     * 处理要查询的条件
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected TbBasicConfigPo handleQueryData(PageDto dto) {
        TbBasicConfigPo po = new TbBasicConfigPo();
        BeanUtils.copyProperties(dto, po);
        po.setWhereGroupId(dto.getGroupId());

        po.setPage(dto.getPage(), dto.getPageSize());
        po.setSortList(new BasePageSortList().addSort(StrUtil.blankToDefault(dto.getSortFiled(), "sort"), StrUtil.blankToDefault(dto.getSortType(), "asc"), "tbc").getSortList());
        return po;
    }

    /**
     * entity转换成vo
     *
     * @param entity 转换前的Entity对象
     * @return 转换后的vo对象
     */
    private ItemVo convertToVo(TbBasicConfig entity) {
        ItemVo vo = new ItemVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
