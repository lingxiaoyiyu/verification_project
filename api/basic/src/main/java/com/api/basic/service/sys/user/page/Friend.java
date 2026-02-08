package com.api.basic.service.sys.user.page;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.dto.sys.user.page.FriendDto;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.basic.data.vo.sys.user.page.FriendtemVo;
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
 * 获取好友列表
 *
 * @author system
 */
@Service("BasicSysUserPageFriendServiceImpl")
@RequiredArgsConstructor
public class Friend extends AbstractService {

    private final TbBasicSysUserDao tbBasicSysUserDao;
    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(FriendDto dto) {
        // 参数校验逻辑
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    public Result<?> service(FriendDto dto) {
        // 获取当前登录用户ID
        Integer currentUserId = StpUtil.getLoginIdAsInt();
        
        // TODO: 实现好友列表查询逻辑
        // 这里需要根据实际的数据库表结构来实现
        // 示例代码如下：
        
        java.util.List<FriendtemVo> voList = new ArrayList<>();
        
        // 示例数据（实际应从数据库查询）
         TbBasicSysUserPo queryPo = handleQueryData(dto, currentUserId);
         List<TbBasicSysUser> entityList = tbBasicSysUserDao.query(queryPo);
         if (entityList != null) {
             entityList.forEach(entity -> {
                 FriendtemVo vo = convertToVo(entity);
                 voList.add(vo);
             });
         }
        
        BasePageVo<FriendtemVo> basePageVo = new BasePageVo<>();
        basePageVo.setList(voList);
        basePageVo.setTotal(0); // TODO: 从数据库获取总数
        
        return Result.ok(basePageVo);
    }

    /**
     * 处理要查询的条件
     *
     * @param dto 请求参数
     * @param currentUserId 当前用户ID
     * @return 处理后的数据
     */
     protected TbBasicSysUserPo handleQueryData(FriendDto dto, Integer currentUserId) {
         TbBasicSysUserPo po = new TbBasicSysUserPo();
         po.setWhereInviter(currentUserId);

         po.setPage(dto.getPage(), dto.getPageSize());
         po.setSortList(new BasePageSortList()
             .addSort(StrUtil.isBlank(dto.getSortFiled()) ? "id" : dto.getSortFiled(),
                      StrUtil.isBlank(dto.getSortType()) ? "DESC" : dto.getSortType())
             .getSortList());
         return po;
     }

    /**
     * entity转换成vo
     *
     * @param entity 转换前的Entity对象
     * @return 转换后的vo对象
     */
     private FriendtemVo convertToVo(TbBasicSysUser entity) {
         FriendtemVo vo = new FriendtemVo();
         BeanUtils.copyProperties(entity, vo);
         return vo;
     }
}
