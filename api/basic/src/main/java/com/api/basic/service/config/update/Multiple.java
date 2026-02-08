package com.api.basic.service.config.update;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import com.api.basic.dao.TbBasicConfigDao;
import com.api.basic.data.dto.config.update.MultipleDto;
import com.api.basic.data.po.TbBasicConfigPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.config.DatabasePropertySourceLoader;
import com.api.common.exception.ServerException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.Map;

/**
 * 更新多个配置
 */
@Service("BasicConfigUpdateMultipleService")
@RequiredArgsConstructor
public class Multiple extends AbstractService {

    private final TbBasicConfigDao tbBasicConfigDao;
    private final DatabasePropertySourceLoader propertySourceLoader;

    /**
     * 参数检查
     */
    public void check() {
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(MultipleDto dto) {
        String configs = dto.getConfigs();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 将 JSON 字符串解析为 List<Map<String, Object>>
            Map<String, Object> configMap = objectMapper.readValue(configs, new TypeReference<>() {});
            Log.get().debug("解析结果：{}", configMap);
            // 遍历每个配置项并更新数据库
            for (Map.Entry<String, Object> config : configMap.entrySet()) {
                // 判断 config.getValue() 的类型是否是数组
                if (config.getValue() instanceof Map || config.getValue() instanceof Array) {
                    tbBasicConfigDao.update(TbBasicConfigPo.builder()
                            .whereName(config.getKey())
                            .value(JSONUtil.toJsonStr(config.getValue()))
                            .updatedUserId(StpUtil.getLoginIdAsInt())
                            .build());
                } else {
                    tbBasicConfigDao.update(TbBasicConfigPo.builder()
                            .whereName(config.getKey())
                            .value(config.getValue().toString())
                            .updatedUserId(StpUtil.getLoginIdAsInt())
                            .build());
                }

            }
            
            // 刷新数据库配置属性源
            refreshDatabaseProperties();
        } catch (Exception e) {
            throw new ServerException("JSON解析失败");
        }
        return Result.ok("配置更新成功");
    }
    
    /**
     * 刷新数据库配置属性源
     */
    private void refreshDatabaseProperties() {
        try {
            propertySourceLoader.refreshProperties();
        } catch (Exception e) {
            // 记录日志但不中断操作
            e.printStackTrace();
        }
    }
}
