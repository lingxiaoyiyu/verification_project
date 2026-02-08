package com.api.basic.service.maintenance.log.operation;

import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.data.dto.maintenance.log.operation.PageDto;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.basic.service.maintenance.log.runtime.AbstractRuntime;
import com.api.common.base.Result;
import com.api.common.base.data.vo.BasePageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 查询操作日志列表
 *
 * @author 裴金伟
 * @date 2025-02-22
 */
@Service("BasicMaintenanceLogOperationPageServiceImpl")
@RequiredArgsConstructor
public class Page extends AbstractRuntime {

    private final TbBasicSysUserDao tbBasicSysUserDao;

    /**
     * 参数检查
     */
    public void check(PageDto dto) {
        dto.setUsername(StrUtil.trim(dto.getUsername()));
        dto.setPhoneNumber(StrUtil.trim(dto.getPhoneNumber()));
        dto.setRealName(StrUtil.trim(dto.getRealName()));
        if (StrUtil.isNotBlank(dto.getStartTime())) {
            dto.setStartTime(StrUtil.trim(dto.getStartTime()) + ":00:00");
        }
        if (StrUtil.isNotBlank(dto.getEndTime())) {
            dto.setEndTime(StrUtil.trim(dto.getEndTime()) + ":00:00");
        }
    }

    /**
     * 业务主体
     *
     * @return 处理结果
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
                    dto.setUids(userList.stream().map(TbBasicSysUser::getId).collect(Collectors.toList())); ;
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
                    List<LogEntry> fileEntries = lines
                            .map(this::parseLogEntry)
                            .filter(Objects::nonNull)
                            .filter(entry -> matches(dto.getOperationType(), dto.getUids(), entry))
                            .toList();

                    allEntries.addAll(fileEntries);
                } catch (Exception e) {
                    // 记录错误但继续处理其他文件
                    e.printStackTrace();
                }
            }

            // 4. 按时间倒序排序
            allEntries.sort(Comparator.comparing(LogEntry::getTimestamp).reversed());

            // 5. 分页处理
            int total = allEntries.size();
            if (total == 0) {
                return Result.ok(new ArrayList<>());
            }

            int fromIndex = (page - 1) * pageSize;

            List<LogEntry> pageEntries = new ArrayList<>();
            BasePageVo<com.api.basic.data.vo.maintenance.log.runtime.PageItemVo> basePageVo = new BasePageVo<>();
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
            List<com.api.basic.data.vo.maintenance.log.runtime.PageItemVo> voList = pageEntries.stream()
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


    /**
     * 检查日志条目是否匹配查询条件
     */
    protected boolean matches(String operationType, List<Integer> uids, AbstractRuntime.LogEntry entry) {

        if (StrUtil.isBlank(entry.getUri())) {
            return false;
        } else {
            if (entry.getUri().startsWith("/maintenance/log") || entry.getUri().startsWith("/favicon.ico")) {
                return false;
            }
        }

        // 用户过滤
        if (uids != null &&!uids.isEmpty()) {
            // 判断dto.getUids()中是否包含 entry.getUid()
            if (entry.getUid() == null || !uids.contains(entry.getUid())) {
                return false;
            }
        }
        // 过滤OPTIONS
        if ("OPTIONS".equals(entry.getMethod())) {
            return false;
        }

        // 操作类型过滤

        if (entry.getMessage().startsWith("【operationType="))  {
            if(StrUtil.isNotBlank(operationType)) {
                if (entry.getMessage().startsWith("【operationType="+operationType+"】")) {
                    entry.setMessage(entry.getMessage().replace("【operationType="+operationType+"】", ""));
                } else {
                    return false;
                }
            } else {
                entry.setMessage(entry.getMessage().replace("【operationType=[^】]*】", ""));
            }
        } else {
            return false;
        }

        return true;
    }
}
