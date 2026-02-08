package com.api.basic.service.sys.user.online;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.SaTerminalInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.api.basic.data.dto.sys.user.online.TerminalListDto;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.vo.sys.user.ItemVo;
import com.api.basic.data.vo.sys.user.online.TerminalItemVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.vo.BasePageVo;
import com.api.common.utils.FunctionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 获取在线用户列表-分页
 */
@Service("BasicSysUserOnlineTerminalListServiceImpl")
@RequiredArgsConstructor
public class TerminalList extends AbstractService {

    private final SaTokenDao saTokenDao;

    /**
     * 业务逻辑处理
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(TerminalListDto dto) {

        List<TerminalItemVo> userVoList = new ArrayList<>();
        List<SaTerminalInfo> terminalList = StpUtil.getTerminalListByLoginId(dto.getUserId());
        for (SaTerminalInfo ter : terminalList) {
            TerminalItemVo vo = new TerminalItemVo();
            vo.setClientForm(ter.getDeviceType());
            vo.setCreatedAt(DateUtil.format(new Date(ter.getCreateTime()), "yyyy年MM月dd日 HH:mm:ss"));
            vo.setLastActiveAt(DateUtil.format(new Date(StpUtil.stpLogic.getTokenLastActiveTime(ter.getTokenValue())), "yyyy年MM月dd日 HH:mm:ss"));
            vo.setToken(ter.getTokenValue());
            vo.setStatus(Integer.valueOf(saTokenDao.get("Authorization:login:token:" + ter.getTokenValue())));
            userVoList.add(vo);
        }
        BasePageVo<TerminalItemVo> basePageVo = new BasePageVo<>();
        basePageVo.setList(userVoList);
        return Result.ok(basePageVo);
    }


    /**
     * entity转换成vo
     *
     * @param session session对象
     * @return 展缓后的vo对象
     */
    private ItemVo convertToVo(SaSession session) {
        TbBasicSysUser userInfo = JSONUtil.toBean(session.getString("userInfo"), TbBasicSysUser.class);
        ItemVo vo = new ItemVo();
        vo.setAvatar(FunctionUtil.handleAvatar(userInfo.getAvatar()));
        vo.setUsername(StrUtil.blankToDefault(userInfo.getUsername(), ""));
        vo.setNickName(StrUtil.blankToDefault(userInfo.getNickName(), ""));
        vo.setRealName(StrUtil.blankToDefault(userInfo.getRealName(), ""));
        vo.setPhoneNumber(StrUtil.blankToDefault(userInfo.getPhoneNumber(), ""));
        vo.setEmail(StrUtil.blankToDefault(userInfo.getEmail(), ""));
        vo.setStatus(userInfo.getStatus());
        vo.setDepartmentName(StrUtil.blankToDefault(session.getString("departmentName"), ""));
        vo.setRoleNames(JSONUtil.toList(session.getString("roleNames"), String.class));
        return vo;
    }
}
