package com.api.basic.service.sys.region;

import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysRegionDao;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.dto.sys.region.QueryDto;
import com.api.basic.data.entity.TbBasicSysRegion;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.po.TbBasicSysRegionPo;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.basic.data.vo.sys.region.ItemVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.po.BasePageSortList;
import com.api.common.enums.IsDeleteEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 查询行政区域列表
 */
@Service("BasicSysRegionQueryService")
@RequiredArgsConstructor
public class Query extends AbstractService {

    private final TbBasicSysRegionDao tbBasicSysRegionDao;
    private final TbBasicSysUserDao tbBasicSysUserDao;

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    public Result<?> service(QueryDto dto) {
        List<ItemVo> datas = new ArrayList<>();
        List<TbBasicSysRegion> regionEntityList = tbBasicSysRegionDao.query(handleQueryData(dto));
        if (regionEntityList != null) {
            Map<Integer, String> userIdToUsernameMap = getUserIdToUsernameMap(regionEntityList);
            // 获取行政区域编码，判断这些行政区域编码有没有下级
            List<Long> parentCodeList = regionEntityList.stream().map(TbBasicSysRegion::getCode).toList();
            List<TbBasicSysRegion> childRegionEntityList = tbBasicSysRegionDao.query(TbBasicSysRegionPo.builder().whereInParentCodes(parentCodeList).build());
            // 判断行政区域编码有没有下级
            Map<Long, Boolean> hasChildMap = childRegionEntityList.stream().collect(
                    HashMap::new,
                    (map, entity) -> map.put(entity.getParentCode(), true),
                    HashMap::putAll
            );
            for (TbBasicSysRegion entity : regionEntityList) {
                ItemVo itemVo = convertToVo(entity);
                itemVo.setHasChild(hasChildMap.get(entity.getCode()));
                itemVo.setUpdatedUserName(userIdToUsernameMap.get(entity.getUpdatedUserId()));
                itemVo.setCreatedUserName(userIdToUsernameMap.get(entity.getCreatedUserId()));
                datas.add(itemVo);
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("list", datas);
        return Result.ok(result);
    }

    /**
     * 处理要查询的条件
     *
     * @param dto DTO对象
     * @return 处理后的数据
     */
    protected TbBasicSysRegionPo handleQueryData(QueryDto dto) {
        TbBasicSysRegionPo po = new TbBasicSysRegionPo();
        po.setWhereLikeName(StrUtil.trim(dto.getName()));
        po.setWhereIsDelete(IsDeleteEnum.NO_DELETE.getCode());
        po.setWhereParentCode(dto.getParentCode());
        po.setWhereCode(dto.getCode());
        po.setSortList(new BasePageSortList().addSort("sort", "ASC", "tbsr").getSortList());
        return po;
    }

    /**
     * entity转换成vo
     *
     * @param entity 转换前的Entity对象
     * @return 展缓后的vo对象
     */
    private ItemVo convertToVo(TbBasicSysRegion entity) {
        ItemVo vo = new ItemVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    /**
     * 获取用户ID到用户名的映射
     *
     * @param entityList 实体列表
     * @return 映射数
     */
    private Map<Integer, String> getUserIdToUsernameMap(List<TbBasicSysRegion> entityList) {
        Set<Integer> uniqueUserIdSet = new HashSet<>();
        for (TbBasicSysRegion entity : entityList) {
            uniqueUserIdSet.add(entity.getCreatedUserId());
            uniqueUserIdSet.add(entity.getUpdatedUserId());
        }
        List<Integer> userIdList = new ArrayList<>(uniqueUserIdSet);

        TbBasicSysUserPo userPo = new TbBasicSysUserPo();
        userPo.setWhereInIds(userIdList);
        List<TbBasicSysUser> userEntityList = tbBasicSysUserDao.query(userPo);
        // 创建一个 Map 来存储用户 ID 和用户名的映射
        Map<Integer, String> userIdToUsernameMap = new HashMap<>();
        for (TbBasicSysUser user : userEntityList) {
            userIdToUsernameMap.put(user.getId(), user.getUsername());
        }
        return userIdToUsernameMap;
    }
}
