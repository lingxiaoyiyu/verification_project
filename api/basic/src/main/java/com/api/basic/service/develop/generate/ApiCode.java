package com.api.basic.service.develop.generate;

import com.api.basic.dao.ColumnDao;
import com.api.basic.dao.TableDao;
import com.api.basic.data.dto.develop.generate.ApiCodeDto;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Api接口代码生成器
 */
@Service("BasicGenerateApiCodeServiceImpl")
public class ApiCode extends AbstractGenerate {

    public ApiCode(Configuration freemarkerConfig, TableDao tableDao, ColumnDao columnDao) {
        super(freemarkerConfig, tableDao, columnDao);
    }

    /**
     * 参数校验
     *
     * @param dto DTO对象
     */
    public void check(ApiCodeDto dto) {
        if (!(new File(projectRoot + "\\" + dto.getModuleName() + "\\").exists())) {
            throw new ServerException("模块不存在");
        }
        String javaDirPath = projectRoot + "\\" + dto.getModuleName() + "\\src\\main\\java\\com\\api";
        File javaDir = new File(javaDirPath);
        if (!javaDir.exists()) {
            throw new ServerException("模块内目录结构不完成。完整目录结构：【\\src\\main\\java\\com\\api】");
        }

        String subModuleName = dto.getSubModuleName();
        // 如果第一个字符是 / ，则删除
        if (subModuleName.startsWith("/")) {
            subModuleName = subModuleName.substring(1);
            dto.setSubModuleName(subModuleName);
        }
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    public Result<?> service(ApiCodeDto dto) {
        javaDirPath = projectRoot + "\\" + dto.getModuleName() + "\\src\\main\\java\\com\\api";
        try {
            databaseName = tableDao.getDatabaseName();
            generateDtoFile(dto);// 生成dto文件
            generateVoFile(dto); // 生成vo
            generateServiceFile(dto, getColumns(List.of(dto.getTableName())).get(dto.getTableName())); // 生成service
        } catch (TemplateException | IOException | SQLException e) {
            throw new RuntimeException(e);
        }

        return Result.ok();
    }
}
