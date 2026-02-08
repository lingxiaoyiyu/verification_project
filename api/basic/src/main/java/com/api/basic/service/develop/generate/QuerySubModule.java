package com.api.basic.service.develop.generate;

import cn.hutool.core.util.StrUtil;
import com.api.basic.data.dto.develop.generate.QuerySubModuleDto;
import com.api.basic.data.vo.develop.generate.QuerySubModuleItemVo;
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
@Service("BasicDevelopGenerateQuerySubModuleServiceImpl")
@RequiredArgsConstructor
public class QuerySubModule {

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(QuerySubModuleDto dto) {
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    public Result<?> service(QuerySubModuleDto dto) {
        String projectRoot = System.getProperty("user.dir");
        // controller目录
        String controllerDir = projectRoot + "\\" + dto.getModuleName() + "\\src\\main\\java\\com\\api\\"+dto.getModuleName()+"\\controller\\";
        List<QuerySubModuleItemVo> voList = new ArrayList<>();
        // 读取controller目录，将所有文件名作为子模块名，如果有多级目录，将目录用“/”拼接到子模块命前面
        // 递归读取controller目录
        readControllerFiles(controllerDir, "", voList);
        return Result.ok(Map.of("list", voList));
    }

    /**
     * 递归读取控制器文件
     * @param currentDir 当前目录路径
     * @param prefix 目录前缀
     * @param voList 结果列表
     */
    private void readControllerFiles(String currentDir, String prefix, List<QuerySubModuleItemVo> voList) {
        java.io.File dir = new java.io.File(currentDir);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }

        java.io.File[] files = dir.listFiles();
        if (files == null) {
            return;
        }

        for (java.io.File file : files) {
            if (file.isDirectory()) {
                // 递归处理子目录
                String newPrefix = prefix.isEmpty() ? file.getName() : prefix + "/" + file.getName();
                readControllerFiles(file.getAbsolutePath(), newPrefix, voList);
            } else if (file.getName().endsWith(".java")) {
                QuerySubModuleItemVo vo = new QuerySubModuleItemVo();
                String fileName = file.getName().substring(0, file.getName().length() - 5);
                fileName = StrUtil.replace(fileName, "Controller", "");
                fileName = StrUtil.lowerFirst(fileName);
                vo.setSubModuleName(prefix.isEmpty() ? fileName : prefix + "/" + fileName);
                voList.add(vo);
            }
        }
    }
}
