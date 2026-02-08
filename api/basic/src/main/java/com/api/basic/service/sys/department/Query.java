package com.api.basic.service.sys.department;

import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysDepartmentDao;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.dto.sys.department.QueryDto;
import com.api.basic.data.entity.TbBasicSysDepartment;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.po.TbBasicSysDepartmentPo;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.basic.data.vo.sys.department.ItemVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.po.BasePageSortList;
import com.api.common.enums.IsDeleteEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 查询部门列表
 */
@Service("BasicSysDepartmentQueryService")
@RequiredArgsConstructor
public class Query extends AbstractService {

    private final TbBasicSysDepartmentDao tbBasicSysDepartmentDao;
    private final TbBasicSysUserDao tbBasicSysUserDao;

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    public Result<?> service(QueryDto dto) {
        List<ItemVo> datas = new ArrayList<>();
        List<TbBasicSysDepartment> departmentEntityList = tbBasicSysDepartmentDao.query(handleQueryData(dto));
        if (departmentEntityList != null) {
            Map<Integer, String> userIdToUsernameMap = getUserIdToUsernameMap(departmentEntityList);
            for (TbBasicSysDepartment entity : departmentEntityList) {
                ItemVo itemVo = convertToVo(entity);
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
    protected TbBasicSysDepartmentPo handleQueryData(QueryDto dto) {
        TbBasicSysDepartmentPo po = new TbBasicSysDepartmentPo();
        po.setWhereLikeName(StrUtil.trim(dto.getName()));
        po.setWhereIsDelete(IsDeleteEnum.NO_DELETE.getCode());
        po.setSortList(new BasePageSortList().addSort("sort", "ASC", "tbsd").getSortList());
        return po;
    }

    /**
     * entity转换成vo
     *
     * @param entity 转换前的Entity对象
     * @return 展缓后的vo对象
     */
    private ItemVo convertToVo(TbBasicSysDepartment entity) {
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
    private Map<Integer, String> getUserIdToUsernameMap(List<TbBasicSysDepartment> entityList) {
        Set<Integer> uniqueUserIdSet = new HashSet<>();
        for (TbBasicSysDepartment entity : entityList) {
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
