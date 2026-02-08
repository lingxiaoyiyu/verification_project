package com.api.basic.service.sys.user.online;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.SaTerminalInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.api.basic.data.dto.sys.user.online.PageDto;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.vo.sys.user.online.ItemVo;
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
@Service("BasicBasicSysUserOnlinePageServiceImpl")
@RequiredArgsConstructor
public class Page extends AbstractService {

    /**
     * 业务逻辑处理
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(PageDto dto) {
        List<ItemVo> userVoList = new ArrayList<>();
        int start = (dto.getPage() - 1) * dto.getPageSize();
        int end = start + dto.getPageSize();
        List<String> sessionIdList = StpUtil.searchSessionId("", start, end, false);
        for (String sessionId : sessionIdList) {
            SaSession session = StpUtil.getSessionBySessionId(sessionId);
            ItemVo vo = convertToVo(session);
            userVoList.add(vo);
        }

        BasePageVo<ItemVo> basePageVo = new BasePageVo<>();
        basePageVo.setList(userVoList);
        basePageVo.setTotal(StpUtil.searchSessionId("", 0, -1, false).size()); // 统计总数
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
        vo.setId((Integer) session.getLoginId());
        vo.setAvatar(FunctionUtil.handleAvatar(userInfo.getAvatar()));
        vo.setUsername(StrUtil.blankToDefault(userInfo.getUsername(), ""));
        vo.setNickName(StrUtil.blankToDefault(userInfo.getNickName(), ""));
        vo.setRealName(StrUtil.blankToDefault(userInfo.getRealName(), ""));
        vo.setPhoneNumber(StrUtil.blankToDefault(userInfo.getPhoneNumber(), ""));
        vo.setEmail(StrUtil.blankToDefault(userInfo.getEmail(), ""));
        vo.setStatus(userInfo.getStatus());
        vo.setDepartmentName(StrUtil.blankToDefault(session.getString("departmentName"), ""));
        vo.setRoleNames(JSONUtil.toList(session.getString("roleNames"), String.class));
        vo.setSessionId(session.getId());

        List<SaTerminalInfo> terminalList = StpUtil.getTerminalListByLoginId(session.getLoginId());
        // 获取DeviceType，并去重
        List<String> clientForms = terminalList.stream().map(SaTerminalInfo::getDeviceType).distinct().toList();
        vo.setClientForms(clientForms);

        long lastActiveAt = 0L;
        for (SaTerminalInfo ter : terminalList) {
            if (StpUtil.stpLogic.getTokenLastActiveTime(ter.getTokenValue()) > lastActiveAt) {
                lastActiveAt = StpUtil.stpLogic.getTokenLastActiveTime(ter.getTokenValue());
            }
        }
        if (lastActiveAt > 0) {
            vo.setLastActiveAt(DateUtil.format(new Date(lastActiveAt), "yyyy-MM-dd HH:mm:ss"));
        }
        return vo;
    }
}
