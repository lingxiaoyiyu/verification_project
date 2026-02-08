package com.api.basic.service.sys.menu;

import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysMenuDao;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.dto.sys.menu.QueryTreeDto;
import com.api.basic.data.entity.TbBasicSysMenu;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.po.TbBasicSysMenuPo;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.basic.data.vo.sys.menu.ItemVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.vo.BasePageVo;
import com.api.common.enums.IsDeleteEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 查询菜单列表
 */
@Service("BasicSysMenuQueryServiceImpl")
@RequiredArgsConstructor
public class Query extends AbstractService {

    private final TbBasicSysMenuDao tbBasicSysMenuDao;
    private final TbBasicSysUserDao tbBasicSysUserDao;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(QueryTreeDto dto) {
        dto.setTitle(StrUtil.trim(dto.getTitle()));
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    public Result<?> service(QueryTreeDto dto) {
        List<TbBasicSysMenu> menuEntityList = tbBasicSysMenuDao.query(handleQueryData(dto));
        List<ItemVo> voList = new ArrayList<>();
        if (menuEntityList != null) {
            Map<Integer, String> userIdToUsernameMap = getUserIdToUsernameMap(menuEntityList);
            for (TbBasicSysMenu entity : menuEntityList) {
                ItemVo vo = convertToVo(entity);
                vo.setCreatedUserName(userIdToUsernameMap.get(entity.getCreatedUserId()));
                vo.setUpdatedUserName(userIdToUsernameMap.get(entity.getUpdatedUserId()));
                voList.add(vo);
            }
        }
        //voList,根据order进行升序排列
        voList.sort(Comparator.comparing(ItemVo::getOrder));
        BasePageVo<ItemVo> basePageVo = new BasePageVo<>();
        basePageVo.setList(voList);
        return Result.ok(basePageVo);
    }

    /**
     * 处理要查询的条件
     *
     * @param dto DTO对象
     * @return 处理后的数据
     */
    private TbBasicSysMenuPo handleQueryData(QueryTreeDto dto) {
        TbBasicSysMenuPo po = new TbBasicSysMenuPo();
        po.setWhereLikeName(dto.getName());
        po.setWhereLikeTitle(dto.getTitle());
        po.setWhereType(dto.getType());
        po.setWhereStatus(dto.getStatus());
        po.setWhereHideInMenu(dto.getIsShow());
        po.setWhereKeepalive(dto.getKeepalive());
        po.setWhereIsLink(dto.getIsLink());
        po.setWhereHideChildrenInMenu(dto.getHideChildren());
        po.setWhereIsDelete(IsDeleteEnum.NO_DELETE.getCode());
        return po;
    }

    /**
     * entity转换成vo
     *
     * @param entity 转换前的Entity对象
     * @return 展缓后的vo对象
     */
    private ItemVo convertToVo(TbBasicSysMenu entity) {
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
    private Map<Integer, String> getUserIdToUsernameMap(List<TbBasicSysMenu> entityList) {
        Set<Integer> uniqueUserIdSet = new HashSet<>();
        for (TbBasicSysMenu entity : entityList) {
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
