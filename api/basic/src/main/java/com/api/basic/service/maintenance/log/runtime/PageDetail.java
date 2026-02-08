package com.api.basic.service.maintenance.log.runtime;

import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.dto.maintenance.log.runtime.PageDto;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.basic.data.vo.maintenance.log.runtime.PageItemVo;
import com.api.common.base.Result;
import com.api.common.base.data.vo.BasePageVo;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 获取运行日志列表
 */
@Service("BasicMaintenanceLogRuntimePageServiceImpl")
@RequiredArgsConstructor
public class PageDetail extends AbstractRuntime {

    private static final Logger log = LogManager.getLogger(PageDetail.class);
    private final TbBasicSysUserDao tbBasicSysUserDao;

    /**
     * 参数检查
     */
    public void check(PageDto dto) {
        dto.setUsername(StrUtil.trim(dto.getUsername()));
        dto.setPhoneNumber(StrUtil.trim(dto.getPhoneNumber()));
        dto.setRealName(StrUtil.trim(dto.getRealName()));
        dto.setUri(StrUtil.trim(dto.getUri()));
        dto.setRequestId(StrUtil.trim(dto.getRequestId()));
        dto.setIp(StrUtil.trim(dto.getIp()));
        dto.setDomain(StrUtil.trim(dto.getDomain()));
        dto.setLogLevel(StrUtil.trim(dto.getLogLevel()));
        if (StrUtil.isNotBlank(dto.getStartTime())) {
            dto.setStartTime(StrUtil.trim(dto.getStartTime()) + ":00:00");
        }
        if (StrUtil.isNotBlank(dto.getEndTime())) {
            dto.setEndTime(StrUtil.trim(dto.getEndTime()) + ":00:00");
        }
    }

    /**
     * 业务主体
     */
    public Result<?> service(PageDto dto) {

        try {
            if (StrUtil.isNotBlank(dto.getUsername())) {
                List<TbBasicSysUser> userList = tbBasicSysUserDao.query(TbBasicSysUserPo.builder()
                        .whereLikeUsername(dto.getUsername())
                        .whereLikePhoneNumber(dto.getPhoneNumber())
                        .whereLikeRealName(dto.getRealName())
                        .build());
                if(userList != null && !userList.isEmpty()) {
                    dto.setUids(userList.stream().map(TbBasicSysUser::getId).collect(Collectors.toList()));
                }
            }

            // 1. 准备分页参数
            int page = dto.getPage() != null ? dto.getPage() : 1;
            int pageSize = dto.getPageSize() != null ? dto.getPageSize() : 20;

            // 2. 获取时间范围内的日志文件
            List<Path> logFiles = findLogFiles(dto.getStartTime(), dto.getEndTime());

            // 3. 读取并解析日志
            List<LogEntry> allEntries = new ArrayList<>();
            for (Path file : logFiles) {
                try (Stream<String> lines = Files.lines(file, StandardCharsets.UTF_8)) {
                    lines.map(logLine -> parseLogEntry(logLine))
                            .filter(Objects::nonNull)
                            .filter(entry -> matches(dto, entry))
                            .forEach(allEntries::add);
                } catch (Exception e) {
                    // 记录错误但继续处理其他文件
                    e.printStackTrace();
                }
            }

            // 4. 按时间倒序排序
            allEntries.sort(Comparator.comparing(LogEntry::getTimestamp));

            // 5. 分页处理
            int total = allEntries.size();
            if (total == 0) {
                return Result.ok(new ArrayList<>());
            }

            int fromIndex = (page - 1) * pageSize;

            List<LogEntry> pageEntries = new ArrayList<>();
            BasePageVo<PageItemVo> basePageVo = new BasePageVo<>();
            if (fromIndex >= total) {
                // 6. 转换为VO
                pageEntries = allEntries;
            } else {
                int toIndex = Math.min(fromIndex + pageSize, total);
                pageEntries = allEntries.subList(fromIndex, toIndex);
            }

            // 从 pageEntries 中获取 uid 并去重
            List<Integer> uniqueUids = pageEntries.stream()
                    .map(LogEntry::getUid)    // 提取 uid 字段
                    .filter(Objects::nonNull) // 过滤掉 null 值
                    .distinct()               // 去重
                    .collect(Collectors.toList());

            List<TbBasicSysUser> sysUsers = tbBasicSysUserDao.query(TbBasicSysUserPo.builder().whereInIds(uniqueUids).build());

            Map<Integer, String> userMap = sysUsers.stream()
                    .collect(Collectors.toMap(TbBasicSysUser::getId, TbBasicSysUser::getUsername));
            // 6. 转换为VO
            List<PageItemVo> voList = pageEntries.stream()
                    .map(this::convertToPageItemVo)
                    .collect(Collectors.toList());
            // 设置用户名
            voList.forEach(vo -> {
                String username = userMap.get(vo.getUid()); // 假设 vo.getUid() 返回 Integer
                if (StrUtil.isNotBlank(username)) {
                    vo.setUsername(username);
                }
            });
            basePageVo.setList(voList);
            basePageVo.setTotal(total);
            return Result.ok(basePageVo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("查询日志失败: " + e.getMessage());
        }
    }
}
