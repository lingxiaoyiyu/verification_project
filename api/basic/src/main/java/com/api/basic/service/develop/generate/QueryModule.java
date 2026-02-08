package com.api.basic.service.develop.generate;

import com.api.basic.common.PomParser;
import com.api.basic.data.dto.develop.generate.QueryModuleDto;
import com.api.basic.data.vo.develop.generate.QueryModuleItemVo;
import com.api.common.base.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 获取模块列表
 *
 * @author 裴金伟
 */
@Service("BasicDevelopGenerateQueryModuleServiceImpl")
@RequiredArgsConstructor
public class QueryModule {

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(QueryModuleDto dto) {
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    public Result<?> service(QueryModuleDto dto) {
        String projectRoot = System.getProperty("user.dir");
        String pomFileDir = projectRoot + "\\pom.xml";
        List<QueryModuleItemVo> voList = new ArrayList<>();
        try {
            PomParser parser = new PomParser();
            List<String> moduleNames = parser.getModulesFromPom(pomFileDir);

            for (String moduleName : moduleNames) {
                if (!moduleName.equals("start")) {
                    QueryModuleItemVo itemVo = new QueryModuleItemVo();
                    itemVo.setModuleName(moduleName); // 假设 QueryModuleItemVo 有 setName 方法
                    voList.add(itemVo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 可以返回错误信息
        }
        return Result.ok(Map.of("list", voList));
    }
}
