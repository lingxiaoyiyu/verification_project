package com.api.apps.service.apps.version;

import com.api.apps.dao.TbAppsVersionDao;
import com.api.apps.data.dto.apps.version.PageDto;
import com.api.apps.data.entity.TbAppsVersion;
import com.api.apps.data.po.TbAppsVersionPo;
import com.api.apps.data.vo.apps.version.PageItemVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.po.BasePageSortList;
import com.api.common.base.data.vo.BasePageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询应用版本列表分页
 *
 * @author 裴金伟
 */
@Service("AppsVersionPageServiceImpl")
@RequiredArgsConstructor
public class Page extends AbstractService {

    private final TbAppsVersionDao tbAppsVersionDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(PageDto dto) {
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    public Result<?> service(PageDto dto) {
        TbAppsVersionPo queryPo = handleQueryData(dto);
        List<TbAppsVersion> entityList = tbAppsVersionDao.query(queryPo);
        List<PageItemVo> voList = new ArrayList<>();
        if (entityList != null) {
            entityList.forEach(entity -> {
                voList.add(convertToVo(entity));
            });
        }

        BasePageVo<PageItemVo> basePageVo = new BasePageVo<>();
        basePageVo.setList(voList);
        basePageVo.setTotal(tbAppsVersionDao.cnt(queryPo)); // 统计总数
        return Result.ok(basePageVo);
    }

    /**
     * 处理要查询的条件
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected TbAppsVersionPo handleQueryData(PageDto dto){
        TbAppsVersionPo po = new TbAppsVersionPo();
        BeanUtils.copyProperties(dto, po);
        po.setWhereAppId(dto.getAppId());
        po.setWhereVersionCode(dto.getVersionCode());
        po.setSortList(new BasePageSortList().addSort("id", "desc").getSortList());
        return po;
    }

    /**
     * entity转换成vo
     *
     * @param entity 转换前的Entity对象
     * @return 转换后的vo对象
     */
    private PageItemVo convertToVo(TbAppsVersion entity){
        PageItemVo vo = new PageItemVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
