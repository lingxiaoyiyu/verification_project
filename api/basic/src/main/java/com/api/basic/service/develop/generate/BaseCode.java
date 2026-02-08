package com.api.basic.service.develop.generate;

import com.api.basic.dao.ColumnDao;
import com.api.basic.dao.TableDao;
import com.api.basic.data.dto.develop.generate.BaseCodeDto;
import com.api.basic.data.dto.develop.generate.ColumnItemDto;
import com.api.basic.data.entity.Table;
import com.api.basic.data.po.TablePo;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import freemarker.template.Configuration;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 基础代码生成器
 */
@Service("BasicGenerateBaseCodeServiceImpl")
public class BaseCode extends AbstractGenerate {

    public BaseCode(Configuration freemarkerConfig, TableDao tableDao, ColumnDao columnDao) {
        super(freemarkerConfig, tableDao, columnDao);
    }

    /**
     * 参数校验
     *
     * @param dto DTO对象
     */
    public void check(BaseCodeDto dto) {
        if (!(new File(projectRoot + "\\" + dto.getModuleName() + "\\").exists())) {
            throw new ServerException("模块不存在");
        }
        String javaDirPath = projectRoot + "\\" + dto.getModuleName() + "\\src\\main\\java\\com\\api";
        File javaDir = new File(javaDirPath);
        if (!javaDir.exists()) {
            throw new ServerException("模块内目录结构不完成。完整目录结构：【\\src\\main\\java\\com\\api】");
        }
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    public Result<?> service(BaseCodeDto dto) {
        javaDirPath = projectRoot + "\\" + dto.getModuleName() + "\\src\\main\\java\\com\\api";
        try {
            databaseName = tableDao.getDatabaseName();
            List<Table> tables = tableDao.query(TablePo.builder()
                    .whereTableSchema(databaseName)
                    .whereInTableNames(dto.getTableNameList())
                    .build());
            Map<String, String> tableCommentMap = tables.stream()
                    .collect(Collectors.toMap(Table::getTableName, Table::getTableComment));

            Map<String, List<ColumnItemDto>> tableColumMap = getColumns(dto.getTableNameList());
            for (String tableName : dto.getTableNameList()) {
                String moduleName = dto.getModuleName();
                String tableComment = tableCommentMap.get(tableName);
                List<ColumnItemDto> tableColumns = tableColumMap.get(tableName);
                generateEntityFile(moduleName, tableName, tableComment, tableColumns); //  生成Entity文件
                generatePoFile(moduleName, tableName, tableComment, tableColumns); // 生成Po文件
                generateMapperJavaFile(moduleName, tableName, tableComment, tableColumns); // 生成Mapper-java文件
                generateDaoFile(moduleName, tableName, tableComment, tableColumns); // 生成Dao文件
                generateMapperXmlFile(moduleName, tableName, tableComment, tableColumns); // 生成Mapper-xml文件
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok();
    }
}
