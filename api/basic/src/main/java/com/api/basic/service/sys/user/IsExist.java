package com.api.basic.service.sys.user;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.dto.sys.user.IsExistDto;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 判断用户信息属性是否存在
 */
@Service("BasicSysUserIsExistServiceImpl")
@RequiredArgsConstructor
public class IsExist extends AbstractService {

    private final TbBasicSysUserDao tbBasicSysUserDao;

    /**
     * 请求参数检查
     *
     * @param dto DTO对象
     */
    public void check(IsExistDto dto) {
        dto.setUsername(StrUtil.trim(dto.getUsername()));
        dto.setNickname(StrUtil.trim(dto.getNickname()));
        dto.setPhoneNumber(StrUtil.trim(dto.getPhoneNumber()));
    }

    /**
     * 业务逻辑处理
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    public Result<?> service(IsExistDto dto) {
        if (StrUtil.isNotBlank(dto.getUsername()) && tbBasicSysUserDao.cnt(TbBasicSysUserPo.builder().whereNotId(StpUtil.getLoginIdAsInt()).whereUsername(dto.getUsername()).build()) != 0) {
            throw new ServerException("用户名已存在");
        }

        if (StrUtil.isNotBlank(dto.getNickname()) && tbBasicSysUserDao.cnt(TbBasicSysUserPo.builder().whereNotId(StpUtil.getLoginIdAsInt()).whereNickName(dto.getNickname()).build()) != 0) {
            throw new ServerException("昵称已存在");
        }

        if (StrUtil.isNotBlank(dto.getPhoneNumber()) && tbBasicSysUserDao.cnt(TbBasicSysUserPo.builder().whereNotId(StpUtil.getLoginIdAsInt()).wherePhoneNumber(dto.getPhoneNumber()).build()) != 0) {
            throw new ServerException("手机号已存在");
        }
        return Result.ok();
    }
}
