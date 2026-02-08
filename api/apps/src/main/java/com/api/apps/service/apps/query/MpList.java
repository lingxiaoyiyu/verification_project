package com.api.apps.service.apps.query;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.api.apps.dao.TbAppsCategoryDao;
import com.api.apps.dao.TbAppsDao;
import com.api.apps.dao.TbAppsVersionDao;
import com.api.apps.data.dto.apps.query.MpListDto;
import com.api.apps.data.entity.TbApps;
import com.api.apps.data.entity.TbAppsCategory;
import com.api.apps.data.entity.TbAppsVersion;
import com.api.apps.data.po.TbAppsCategoryPo;
import com.api.apps.data.po.TbAppsPo;
import com.api.apps.data.po.TbAppsVersionPo;
import com.api.apps.data.vo.apps.query.MpListItemVo;
import com.api.basic.dao.TbBasicSysUserRoleRelationDao;
import com.api.basic.data.entity.TbBasicSysUserRoleRelation;
import com.api.basic.data.po.TbBasicSysUserRoleRelationPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.po.BasePageSortList;
import com.api.common.enums.IsDeleteEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 查询小程序列表分页
 *
 * @author 裴金伟
 */
@Service("AppQueryMpListServiceImpl")
@RequiredArgsConstructor
public class MpList extends AbstractService {

    private final TbAppsDao tbAppsDao;
    private final TbAppsCategoryDao tbAppsCategoryDao;
    private final TbAppsVersionDao tbAppsVersionDao;
    private final TbBasicSysUserRoleRelationDao tbBasicSysUserRoleRelationDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(MpListDto dto) {
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    public Result<?> service(MpListDto dto) {
        List<TbApps> openList = tbAppsDao.query(TbAppsPo.builder().whereStatus(1).whereType(2).whereIsEntranceMp(2).whereAccessType(1).build()); // 上架、公开的小程序。
        if (StpUtil.isLogin()) { // 已登录
            List<TbApps> limitList = tbAppsDao.query(TbAppsPo.builder().whereStatus(1).whereType(2).whereIsEntranceMp(2).whereAccessType(2).build()); // 上架、受限的小程序
            if (limitList != null) {
                Integer userId = StpUtil.getLoginIdAsInt();
                // 获取用户角色ID
                List<Integer> userRoleIds = tbBasicSysUserRoleRelationDao.query(TbBasicSysUserRoleRelationPo.builder()
                        .whereUserId(userId)
                        .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
                .build())
                        .stream()
                        .map(TbBasicSysUserRoleRelation::getRoleId)
                        .toList();
                limitList.forEach(entity -> {
                    if (StrUtil.isNotBlank(entity.getAccessUserIds()) && JSONUtil.parseArray(entity.getAccessUserIds()).contains(userId)) {
                        openList.add(entity);
                    } else if (StrUtil.isNotBlank(entity.getAccessRoleIds()) && JSONUtil.parseArray(entity.getAccessRoleIds()).contains(userRoleIds)) {
                        openList.add(entity);
                    }
                });
            }
        }

        List<TbAppsCategory> categoryList = tbAppsCategoryDao.query(TbAppsCategoryPo.builder().whereIsShow(1).sortList(new BasePageSortList().addSort("sort", "desc").getSortList()).build());
        List<Integer> categoryIds = categoryList.stream().map(TbAppsCategory::getId).toList();

        List<Map<String, Object>> voList = new ArrayList<>();
        if (openList != null) {
            // 获取appid
            List<String> appIds = openList.stream().map(TbApps::getId).toList();
            List<TbAppsVersion> versionList = tbAppsVersionDao.query(TbAppsVersionPo.builder().whereInAppIds(appIds).whereStatus(3).build());

            // 对openList按照sort字段降序排列
            openList.sort((o1, o2) -> o2.getSort() - o1.getSort());

            // 先构建未排序的 voList
            Map<String, List<MpListItemVo>> tempVoList = new HashMap<>();
            openList.forEach(entity -> {
                if (categoryIds.contains(entity.getCategoryId())) {
                    // 修复1：检查Optional是否存在值
                    Optional<TbAppsCategory> categoryOpt = categoryList.stream()
                            .filter(category -> category.getId().equals(entity.getCategoryId()))
                            .findFirst();

                    if (categoryOpt.isPresent()) {
                        String cateName = categoryOpt.get().getName();
                        // 将convertToVo(entity)放到voList中以cateName为key的list中
                        if (!tempVoList.containsKey(cateName)) {
                            tempVoList.put(cateName, new ArrayList<>());
                        }
                        MpListItemVo itemVo =convertToVo(entity);
                        if (versionList != null && !versionList.isEmpty()) {
                            Optional<TbAppsVersion> versionOpt = versionList.stream()
                                    .filter(version -> version.getAppId().equals(entity.getId()))
                                    .findFirst();

                            if (versionOpt.isPresent()) {
                                itemVo.setVersionCode(versionOpt.get().getVersionCode());
                                itemVo.setUrl(versionOpt.get().getUrl());
                            }
                        }
                        tempVoList.get(cateName).add(itemVo);
                    }
                }
            });
            // 按照 categoryList 的顺序将 tempVoList 插入到 voList（LinkedHashMap）中
            for (TbAppsCategory category : categoryList) {
                String cateName = category.getName();
                if (tempVoList.containsKey(cateName)) {
                    voList.add(Map.of("cateName", cateName, "list", tempVoList.get(cateName)));
                }
            }
        }
        // 将 voList 中的数据按照 categoryList中的sort升序排列
        return Result.ok(Map.of("list", voList));
    }

    /**
     * entity转换成vo
     *
     * @param entity 转换前的Entity对象
     * @return 转换后的vo对象
     */
    private MpListItemVo convertToVo(TbApps entity){
        MpListItemVo vo = new MpListItemVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
