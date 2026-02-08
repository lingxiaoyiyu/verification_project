package com.api.basic.service.sys.user;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysMultipleDao;
import com.api.basic.dao.TbBasicSysRoleDao;
import com.api.basic.dao.TbBasicSysUserRoleRelationDao;
import com.api.basic.data.dto.sys.user.PageDto;
import com.api.basic.data.entity.TbBasicSysMultiple;
import com.api.basic.data.entity.TbBasicSysRole;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.entity.TbBasicSysUserRoleRelation;
import com.api.basic.data.po.TbBasicSysMultiplePo;
import com.api.basic.data.po.TbBasicSysRolePo;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.basic.data.po.TbBasicSysUserRoleRelationPo;
import com.api.basic.data.vo.sys.user.ItemVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.po.BasePageSortList;
import com.api.common.base.data.vo.BasePageVo;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.enums.StatusEnum;
import com.api.common.utils.FunctionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取用户列表-分页
 */
@Service("BasicSysUserPageServiceImpl")
@RequiredArgsConstructor
public class Page extends AbstractService {

    private final TbBasicSysUserRoleRelationDao tbBasicSysUserRoleRelationDao;
    private final TbBasicSysRoleDao tbBasicSysRoleDao;
    private final TbBasicSysMultipleDao tbBasicSysMultipleDao;

    /**
     * 业务逻辑处理
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(PageDto dto) {

        // 构建查询条件
        TbBasicSysMultiplePo queryPo = handleQueryData(dto);
        // 查询列表
        List<TbBasicSysMultiple> userEntityList = tbBasicSysMultipleDao.query(queryPo);
        List<ItemVo> userVoList = new ArrayList<>();
        if (userEntityList != null) {
            Map<Integer, List<String>> userIdToRoleMap = getUserIdToRoleMap(userEntityList);
            for (TbBasicSysMultiple entity : userEntityList) {
                ItemVo vo = convertToVo(entity);
                vo.setRoleNames(userIdToRoleMap.get(entity.getTbBasicSysUser().getId()));
                userVoList.add(vo);
            }
        }

        BasePageVo<ItemVo> basePageVo = new BasePageVo<>();
        basePageVo.setList(userVoList);
        basePageVo.setTotal(tbBasicSysMultipleDao.cnt(queryPo)); // 统计总数
        return Result.ok(basePageVo);
    }

    /**
     * 处理要查询的条件
     *
     * @param dto DTO对象
     * @return 处理后的数据
     */
    private TbBasicSysMultiplePo handleQueryData(PageDto dto) {
        TbBasicSysUserPo userPo = new TbBasicSysUserPo();
        userPo.setWhereLikeUsername(dto.getUsername());
        userPo.setWhereLikeRealName(dto.getRealName());
        userPo.setWhereLikePhoneNumber(dto.getPhoneNumber());
        userPo.setWhereLikeEmail(dto.getEmail());
        userPo.setWhereLikeNickName(dto.getNickName());
        userPo.setWhereStatus(dto.getStatus());
        userPo.setWhereInIds(dto.getUserIds());

        TbBasicSysUserRoleRelationPo userRoleRelationPo = new TbBasicSysUserRoleRelationPo();
        userRoleRelationPo.setWhereInRoleIds(dto.getRoleIds() == null ? (new ArrayList<>()) : dto.getRoleIds());

        List<Integer> inOrIsDeletes = new ArrayList<>();
        inOrIsDeletes.add(IsDeleteEnum.NO_DELETE.getCode());
        inOrIsDeletes.add(null);
        userRoleRelationPo.setWhereInOrIsDeletes(inOrIsDeletes);

        TbBasicSysMultiplePo po = new TbBasicSysMultiplePo();
        po.setTbBasicSysUserPo(userPo);
        po.setTbBasicSysUserRoleRelationPo(userRoleRelationPo);
        po.setPage(dto.getPage(), dto.getPageSize());
        po.setSortList(new BasePageSortList().addSort(StrUtil.blankToDefault(dto.getSortFiled(), "id"), dto.getSortType(), "tbsu").getSortList());
        return po;
    }

    /**
     * entity转换成vo
     *
     * @param entity 转换前的Entity对象
     * @return 展缓后的vo对象
     */
    private ItemVo convertToVo(TbBasicSysMultiple entity) {
        ItemVo vo = new ItemVo();
        BeanUtils.copyProperties(entity.getTbBasicSysUser(), vo);
        vo.setAvatar(FunctionUtil.handleAvatar(entity.getTbBasicSysUser().getAvatar()));
        vo.setNickName(StrUtil.blankToDefault(entity.getTbBasicSysUser().getNickName(), ""));
        vo.setRealName(StrUtil.blankToDefault(entity.getTbBasicSysUser().getRealName(), ""));
        vo.setPhoneNumber(StrUtil.blankToDefault(entity.getTbBasicSysUser().getPhoneNumber(), ""));
        vo.setDepartmentName("");
        vo.setIsLogin(StpUtil.isLogin(entity.getTbBasicSysUser().getId()));
        return vo;
    }

    /**
     * 获取用户ID到关联的角角色名的映射
     *
     * @param entityList 角色实体列表
     * @return 用户ID到角色名的映射
     */
    private Map<Integer, List<String>> getUserIdToRoleMap(List<TbBasicSysMultiple> entityList) {
        // 从entityList中获取用户ID列表
        if (entityList == null || entityList.isEmpty()) {
            return new HashMap<>();
        }
        List<TbBasicSysUser> userList = entityList.stream().map(TbBasicSysMultiple::getTbBasicSysUser).toList();
        if (userList.isEmpty()) {
            return new HashMap<>();
        }
        List<Integer> userIdList = userList.stream().map(TbBasicSysUser::getId).toList(); // 用户ID列表
        List<TbBasicSysUserRoleRelation> userRoles = tbBasicSysUserRoleRelationDao.query(TbBasicSysUserRoleRelationPo.builder().whereInUserIds(userIdList).whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).build());
        List<TbBasicSysRole> roles = tbBasicSysRoleDao.query(TbBasicSysRolePo.builder().whereStatus(StatusEnum.ENABLE.getCode()).whereIsDelete(IsDeleteEnum.NO_DELETE.getCode()).build());

        // 角色ID到角色名的映射
        Map<Integer, String> roleIdToNameMap = new HashMap<>();
        for (TbBasicSysRole role : roles) {
            roleIdToNameMap.put(role.getId(), role.getName());
        }

        // 用户和角色名的映射
        Map<Integer, List<String>> userToRoleNameMap = new HashMap<>();
        for (TbBasicSysUserRoleRelation userRole : userRoles) {
            // 从roleIdToNameMap中找到对应roleId的角色名
            String roleName = roleIdToNameMap.get(userRole.getRoleId());
            if (roleName != null) {
                // 使用computeIfAbsent获取或创建列表，并添加角色名
                userToRoleNameMap.computeIfAbsent(userRole.getUserId(), k -> new ArrayList<>()).add(roleName);
            }
        }
        return userToRoleNameMap;
    }
}
