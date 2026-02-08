package com.api.apps.service.apps;

import com.api.apps.dao.TbAppsCategoryDao;
import com.api.apps.dao.TbAppsDao;
import com.api.apps.dao.TbAppsVersionDao;
import com.api.apps.data.dto.apps.PageDto;
import com.api.apps.data.entity.TbApps;
import com.api.apps.data.entity.TbAppsCategory;
import com.api.apps.data.entity.TbAppsVersion;
import com.api.apps.data.po.TbAppsCategoryPo;
import com.api.apps.data.po.TbAppsPo;
import com.api.apps.data.po.TbAppsVersionPo;
import com.api.apps.data.vo.apps.PageItemVo;
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
 * 查询小程序列表分页
 *
 * @author 裴金伟
 */
@Service("AppsPageServiceImpl")
@RequiredArgsConstructor
public class Page extends AbstractService {

    private final TbAppsDao tbAppsDao;
    private final TbAppsCategoryDao  tbAppsCategoryDao;
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
        TbAppsPo queryPo = handleQueryData(dto);
        List<TbApps> entityList = tbAppsDao.query(queryPo);
        List<PageItemVo> voList = new ArrayList<>();
        if (entityList != null) {
            // 获取id列表
            List<String> idList = entityList.stream().map(TbApps::getId).toList();
            // 获取分类ID列表，并去重
            List<Integer> categoryIdList = entityList.stream().map(TbApps::getCategoryId).distinct().toList();
            // 获取分类ID和分类名称对应值的Map
            Map<Integer, String> categoryNameMap = tbAppsCategoryDao.query(TbAppsCategoryPo.builder().whereInIds(categoryIdList).build())
                                                    .stream()
                                                    .collect(Collectors.toMap(TbAppsCategory::getId, TbAppsCategory::getName));

            // status = 2 : 测试， status = 3 : 正式
            // 获取应用ID和测试版本信息对应值的Map
            Map<String, String> testVersionInfoMap = tbAppsVersionDao.query(TbAppsVersionPo.builder().whereInAppIds(idList).whereStatus(2).build())
                                                        .stream()
                                                        .collect(Collectors.toMap(TbAppsVersion::getAppId, TbAppsVersion::getVersionName));
            // 获取应用ID和正式版本信息对应值的Map
            Map<String, String> onlineVersionInfoMap = tbAppsVersionDao.query(TbAppsVersionPo.builder().whereInAppIds(idList).whereStatus(3).build())
                                                        .stream()
                                                        .collect(Collectors.toMap(TbAppsVersion::getAppId, TbAppsVersion::getVersionName));
            entityList.forEach(entity -> {
                PageItemVo vo = convertToVo(entity);
                vo.setCategoryName(categoryNameMap.get(entity.getCategoryId()));
                vo.setTestVersion(testVersionInfoMap.get(entity.getId()));
                vo.setOnlineVersion(onlineVersionInfoMap.get(entity.getId()));
                voList.add(vo);
            });
        }

        BasePageVo<PageItemVo> basePageVo = new BasePageVo<>();
        basePageVo.setList(voList);
        basePageVo.setTotal(tbAppsDao.cnt(queryPo)); // 统计总数
        return Result.ok(basePageVo);
    }

    /**
     * 处理要查询的条件
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected TbAppsPo handleQueryData(PageDto dto){
        TbAppsPo po = new TbAppsPo();
        BeanUtils.copyProperties(dto, po);
        po.setWhereType(dto.getType());
        po.setWhereLikeUniAppid(dto.getUniAppid());
        po.setWhereCategoryId(dto.getCategoryId());
        po.setWhereLikeName(dto.getName());
        po.setSortList(new BasePageSortList().addSort("sort", "desc").getSortList());
        return po;
    }

    /**
     * entity转换成vo
     *
     * @param entity 转换前的Entity对象
     * @return 转换后的vo对象
     */
    private PageItemVo convertToVo(TbApps entity){
        PageItemVo vo = new PageItemVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
