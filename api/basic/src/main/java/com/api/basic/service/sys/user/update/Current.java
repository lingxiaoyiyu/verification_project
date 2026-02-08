package com.api.basic.service.sys.user.update;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.dto.sys.user.update.info.CurrentDto;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import com.api.common.utils.FunctionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 更新当前登录用户的信息
 */
@Service("BasicSysUserUpdateCurrentServiceImpl")
@RequiredArgsConstructor
public class Current extends AbstractService {

    private final TbBasicSysUserDao tbBasicSysUserDao;

    /**
     * 请求参数检查
     *
     * @param dto DTO对象
     */
    public void check(CurrentDto dto) {
        if (StrUtil.isNotBlank(dto.getPhoneNumber()) && tbBasicSysUserDao.cnt(TbBasicSysUserPo.builder().whereNotId(StpUtil.getLoginIdAsInt()).wherePhoneNumber(dto.getPhoneNumber()).build()) != 0) {
            throw new ServerException(FunctionUtil.getI18nString("service.user.phoneNumber.exists"));
        }

        if (StrUtil.isNotBlank(dto.getNickName()) && tbBasicSysUserDao.cnt(TbBasicSysUserPo.builder().whereNotId(StpUtil.getLoginIdAsInt()).whereNickName(dto.getNickName()).build()) != 0) {
            throw new ServerException(FunctionUtil.getI18nString("service.user.nickName.exists"));
        }

        if (StrUtil.isNotBlank(dto.getEmail()) && tbBasicSysUserDao.cnt(TbBasicSysUserPo.builder().whereNotId(StpUtil.getLoginIdAsInt()).whereEmail(dto.getEmail()).build()) != 0) {
            throw new ServerException(FunctionUtil.getI18nString("service.user.email.exists"));
        }
    }

    /**
     * 业务逻辑处理
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(CurrentDto dto) {
        TbBasicSysUserPo userPo = handleUpdateData(dto);
        if (tbBasicSysUserDao.update(userPo) != 1) {
            throw new ServerException(FunctionUtil.getI18nString("service.user.info.updateFailed"));
        }
        return Result.ok(FunctionUtil.getI18nString("service.user.info.updateSuccess"));
    }

    /**
     * 处理要更新的数据
     *
     * @return 处理后的数据
     */
    private TbBasicSysUserPo handleUpdateData(CurrentDto dto) {
        TbBasicSysUserPo updatePo = new TbBasicSysUserPo();
        BeanUtils.copyProperties(dto, updatePo);
        updatePo.setWhereId(StpUtil.getLoginIdAsInt());
        if (StrUtil.isNotBlank(dto.getAvatar())) {
            updatePo.setAvatar(FunctionUtil.handleAvatar(dto.getAvatar()));
        }
        updatePo.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        return updatePo;
    }
}
