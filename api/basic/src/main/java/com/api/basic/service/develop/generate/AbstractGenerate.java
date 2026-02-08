package com.api.basic.service.develop.generate;

import cn.hutool.core.util.StrUtil;
import com.api.basic.common.JavaFileModifier;
import com.api.basic.common.JavaFileReader;
import com.api.basic.dao.ColumnDao;
import com.api.basic.dao.TableDao;
import com.api.basic.data.dto.develop.generate.ApiCodeDto;
import com.api.basic.data.dto.develop.generate.ColumnItemDto;
import com.api.basic.data.entity.Column;
import com.api.basic.data.po.ColumnPo;
import com.api.common.base.AbstractService;
import com.api.common.base.data.po.BasePageSortList;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RequiredArgsConstructor
public class AbstractGenerate extends AbstractService {

    protected final Configuration freemarkerConfig;
    protected final TableDao tableDao;
    protected final ColumnDao columnDao;

    protected String author = "裴金伟";
    protected String projectRoot = System.getProperty("user.dir");
    protected String javaDirPath;
    protected String databaseName; // 数据库名称

    private static final Map<String, String> mysqlToJavaTypeMap = new HashMap<>();

    static {
        mysqlToJavaTypeMap.put("int", "Integer");
        mysqlToJavaTypeMap.put("tinyint", "Integer");
        mysqlToJavaTypeMap.put("smallint", "Integer");
        mysqlToJavaTypeMap.put("mediumint", "Integer");
        mysqlToJavaTypeMap.put("bigint", "Long");
        mysqlToJavaTypeMap.put("float", "Float");
        mysqlToJavaTypeMap.put("double", "Double");
        mysqlToJavaTypeMap.put("decimal", "BigDecimal");
        mysqlToJavaTypeMap.put("char", "String");
        mysqlToJavaTypeMap.put("varchar", "String");
        mysqlToJavaTypeMap.put("text", "String");
        mysqlToJavaTypeMap.put("longtext", "String");
        mysqlToJavaTypeMap.put("date", "String");
        mysqlToJavaTypeMap.put("datetime", "String");
        mysqlToJavaTypeMap.put("timestamp", "String");
        mysqlToJavaTypeMap.put("time", "String");
        mysqlToJavaTypeMap.put("json", "String");
        mysqlToJavaTypeMap.put("boolean", "Boolean");
    }

    /**
     * 生成Entity文件
     *
     * @param moduleName   模块名
     * @param tableName    表名
     * @param tableComment 表注释
     * @param tableColumns 表字段列表
     * @throws IOException IO异常
     */
    protected void generateEntityFile(String moduleName, String tableName, String tableComment, List<ColumnItemDto> tableColumns) throws Exception {
        String handleTableName = handleStr(tableName);
        String fileName = StrUtil.upperFirst(handleTableName) + ".java";
        String packagePath = "." + moduleName + ".";
        String dirPath = javaDirPath + StrUtil.replace(packagePath, ".", "\\") + "data\\entity\\";
        HashMap<String, Object> model = new HashMap<>();
        model.put("moduleName", moduleName);
        model.put("handleTableName", handleTableName);
        model.put("tableAnnotation", tableComment);
        model.put("author", author);
        model.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        model.put("tableColumns", tableColumns);

        writerFile("Entity.ftl", model, dirPath, fileName);
        // 生成字段常量类
        generateEntityFieldsFile(moduleName, tableName, tableComment, tableColumns);
    }
    
    /**
     * 生成Entity字段常量文件
     *
     * @param moduleName   模块名
     * @param tableName    表名
     * @param tableComment 表注释
     * @param tableColumns 表字段列表
     * @throws Exception 异常
     */
    protected void generateEntityFieldsFile(String moduleName, String tableName, String tableComment, List<ColumnItemDto> tableColumns) throws Exception {
        String handleTableName = handleStr(tableName);
        String fileName = StrUtil.upperFirst(handleTableName) + "Fields.java";
        String packagePath = "." + moduleName + ".";
        String dirPath = javaDirPath + StrUtil.replace(packagePath, ".", "\\") + "data\\entity\\fields\\";
        HashMap<String, Object> model = new HashMap<>();
        model.put("moduleName", moduleName);
        model.put("handleTableName", handleTableName);
        model.put("tableAnnotation", tableComment);
        model.put("author", author);
        model.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        model.put("tableColumns", tableColumns);

        writerFile("EntityFields.ftl", model, dirPath, fileName);
    }

    /**
     * 生成Po文件
     *
     * @param moduleName   模块名
     * @param tableName    表名
     * @param tableComment 表注释
     * @param tableColumns 表字段列表
     * @throws IOException IO异常
     */
    protected void generatePoFile(String moduleName, String tableName, String tableComment, List<ColumnItemDto> tableColumns) throws Exception {
        String handleTableName = handleStr(tableName);
        String fileName = StrUtil.upperFirst(handleTableName) + "Po.java";
        String packagePath = "." + moduleName + ".";
        String dirPath = javaDirPath + StrUtil.replace(packagePath, ".", "\\") + "data\\po\\";
        HashMap<String, Object> model = new HashMap<>();
        model.put("moduleName", moduleName);
        model.put("handleTableName", handleTableName);
        model.put("tableAnnotation", tableComment);
        model.put("author", author);
        model.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        model.put("tableColumns", tableColumns);

        writerFile("Po.ftl", model, dirPath, fileName);
    }

    /**
     * 生成Mapper-java文件
     *
     * @param moduleName   模块名
     * @param tableName    表名
     * @param tableComment 表注释
     * @param tableColumns 表字段列表
     * @throws IOException IO异常
     */
    protected void generateMapperJavaFile(String moduleName, String tableName, String tableComment, List<ColumnItemDto> tableColumns) throws Exception {
        String handleTableName = handleStr(tableName);
        String fileName = StrUtil.upperFirst(handleTableName) + "Mapper.java";
        String packagePath = "." + moduleName + ".";
        String dirPath = javaDirPath + StrUtil.replace(packagePath, ".", "\\") + "mapper\\";
        HashMap<String, Object> model = new HashMap<>();
        model.put("moduleName", moduleName);
        model.put("handleTableName", handleTableName);
        model.put("tableAnnotation", tableComment);
        model.put("author", author);
        model.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        model.put("tableColumns", tableColumns);

        writerFile("MapperJava.ftl", model, dirPath, fileName);
    }

    /**
     * 生成MDao文件
     *
     * @param moduleName   模块名
     * @param tableName    表名
     * @param tableComment 表注释
     * @param tableColumns 表字段列表
     * @throws IOException IO异常
     */
    protected void generateDaoFile(String moduleName, String tableName, String tableComment, List<ColumnItemDto> tableColumns) throws Exception {
        String handleTableName = handleStr(tableName);
        String fileName = StrUtil.upperFirst(handleTableName) + "Dao.java";
        String packagePath = "." + moduleName + ".";
        String dirPath = javaDirPath + StrUtil.replace(packagePath, ".", "\\") + "dao\\";
        HashMap<String, Object> model = new HashMap<>();
        model.put("moduleName", moduleName);
        model.put("handleTableName", handleTableName);
        model.put("tableAnnotation", tableComment);
        model.put("author", author);
        model.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        model.put("tableColumns", tableColumns);
        writerFile("Dao.ftl", model, dirPath, fileName);
    }

    /**
     * 生成Mapper-xml文件
     *
     * @param moduleName   模块名
     * @param tableName    表名
     * @param tableComment 表注释
     * @param tableColumns 表字段列表
     * @throws IOException IO异常
     */
    protected void generateMapperXmlFile(String moduleName, String tableName, String tableComment, List<ColumnItemDto> tableColumns) throws Exception {
        String dirPath = projectRoot + "\\" + moduleName + "\\src\\main\\resources\\mapper\\";
        String handleTableName = handleStr(tableName);
        String fileName = StrUtil.upperFirst(handleTableName) + "Mapper.xml";
        HashMap<String, Object> model = new HashMap<>();
        model.put("moduleName", moduleName);
        model.put("handleTableName", handleTableName);
        model.put("tableAnnotation", tableComment);
        model.put("author", author);
        model.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        model.put("tableName", tableName);
        model.put("tableColumns", tableColumns);
        // 提取tableName的每一部分的首字母，组成别名
        String[] parts = tableName.split("_");
        StringBuilder alias = new StringBuilder();
        for (String part : parts) {
            alias.append(part.charAt(0));
        }
        model.put("alias", alias.toString());

        writerFile("MapperXml.ftl", model, dirPath, fileName);
    }

    /**
     * 生成Dto代码
     *
     * @param dto DTO对象
     */
    protected void generateDtoFile(ApiCodeDto dto) throws TemplateException, IOException {
        String fileName = StrUtil.upperFirst(dto.getApiName()) + "Dto.java";

        String apiPath = "/".equals(dto.getApiPath()) ? dto.getSubModuleName() + "/" : dto.getSubModuleName() + dto.getApiPath();
        String dirPath = javaDirPath + "\\" + dto.getModuleName() + "\\data\\dto\\" +apiPath + "\\";
        String packagePath = StrUtil.replace(apiPath, "/", ".");
        packagePath = packagePath.endsWith(".") ? packagePath.substring(0, packagePath.length() - 1) : packagePath;

        HashMap<String, Object> model = new HashMap<>();
        model.put("moduleName", dto.getModuleName());
        model.put("apiComment", dto.getApiComment());
        model.put("packagePath", packagePath);
        model.put("apiName", dto.getApiName());
        model.put("subModuleAnnotation", dto.getSubModuleAnnotation());
        model.put("author", author);
        model.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        model.put("fields", dto.getDtoFields());

        if ("page".equals(StrUtil.lowerFirst(dto.getApiType()))) {
            writerFile("Dto/Page.ftl", model, dirPath, fileName);
        } else {
            writerFile("Dto/Dto.ftl", model, dirPath, fileName);
        }
    }

    /**
     * 生成Vo代码
     *
     * @param dto DTO对象
     */
    protected void generateVoFile(ApiCodeDto dto) throws TemplateException, IOException {
        String apiName = dto.getApiName();
        if ("page".equals(StrUtil.lowerFirst(dto.getApiType())) || "query".equals(StrUtil.lowerFirst(dto.getApiType()))) {
            apiName += "Item";
        }
        String fileName = StrUtil.upperFirst(apiName) + "Vo.java";

        String apiPath = "/".equals(dto.getApiPath()) ? dto.getSubModuleName() + "/" : dto.getSubModuleName() + dto.getApiPath();
        String dirPath = javaDirPath + "\\" + dto.getModuleName() + "\\data\\vo\\" +apiPath + "\\";
        String packagePath = StrUtil.replace(apiPath, "/", ".");
        packagePath = packagePath.endsWith(".") ? packagePath.substring(0, packagePath.length() - 1) : packagePath;

        HashMap<String, Object> model = new HashMap<>();
        model.put("moduleName", dto.getModuleName());
        model.put("apiComment", dto.getApiComment());
        model.put("packagePath", packagePath);
        model.put("apiName", apiName);
        model.put("subModuleAnnotation", dto.getSubModuleAnnotation());
        model.put("author", author);
        model.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        model.put("fields", dto.getVoFields());

        writerFile("Vo/Vo.ftl", model, dirPath, fileName);
    }

    /**
     * 生成Service代码
     *
     * @param dto DTO对象
     */
    protected void generateServiceFile(ApiCodeDto dto, List<ColumnItemDto> tableColumns) throws TemplateException, IOException {
        String fileName = StrUtil.upperFirst(dto.getApiName()) + ".java";

        String apiPath = "/".equals(dto.getApiPath()) ? dto.getSubModuleName() + "/" : dto.getSubModuleName() + dto.getApiPath();
        String dirPath = javaDirPath + "\\" + dto.getModuleName() + "\\service\\" +apiPath + "\\";
        String packagePath = StrUtil.replace(apiPath, "/", ".");
        packagePath = packagePath.endsWith(".") ? packagePath.substring(0, packagePath.length() - 1) : packagePath;

        HashMap<String, Object> model = new HashMap<>();
        model.put("moduleName", dto.getModuleName());
        model.put("apiComment", dto.getApiComment());
        model.put("packagePath", packagePath);
        model.put("apiName", dto.getApiName());
        model.put("subModuleAnnotation", dto.getSubModuleAnnotation());
        model.put("tableName", handleStr(dto.getTableName()));
        model.put("author", author);
        model.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        model.put("tableColumns", tableColumns);
        model.put("dtoFields", dto.getDtoFields());
        model.put("voFields", dto.getVoFields());
        model.put("apiType", dto.getApiType());

        String serviceName = StrUtil.upperFirst(handleStr(StrUtil.replace(apiPath, "/", "_"))) + StrUtil.upperFirst(dto.getApiName());
        model.put("serviceName", serviceName);

        switch (StrUtil.lowerFirst(dto.getApiType())) {
            case "add": {
                writerFile("Service/Add.ftl", model, dirPath, fileName);
                break;
            }
            case "delete":
            case "remove": {
                writerFile("Service/Remove.ftl", model, dirPath, fileName);
                break;
            }
            case "update": {
                writerFile("Service/Update.ftl", model, dirPath, fileName);
                break;
            }
            case "get": {
                writerFile("Service/Get.ftl", model, dirPath, fileName);
                break;
            }
            case "query": {
                writerFile("Service/Query.ftl", model, dirPath, fileName);
                break;
            }
            case "page": {
                writerFile("Service/Page.ftl", model, dirPath, fileName);
                break;
            }
            default: {
                writerFile("Service/Query.ftl", model, dirPath, fileName);
                break;
            }
        }

        addControllerMethod(dto);
    }

    /**
     * 在controller中添加代码
     *
     * @param dto DTO对象
     */
    private void addControllerMethod(ApiCodeDto dto) {
        try {
            List<String> subModuleNames = StrUtil.split(dto.getSubModuleName(), "/");
            // 最后一个元素，首字母改为大写
            subModuleNames.set(subModuleNames.size() - 1, StrUtil.upperFirst(subModuleNames.get(subModuleNames.size() - 1)));
            String controllerPath = javaDirPath  + "\\" + dto.getModuleName() + "\\controller\\"+ StrUtil.join("\\", subModuleNames) +"Controller.java";
            // 去除文件名，判断路径是否存在
            String controllerName = StrUtil.upperFirst(subModuleNames.getLast()) + "Controller.java";
            String tempControllerPath = controllerPath.substring(0, controllerPath.length() - controllerName.length());
            if (!Files.exists(Paths.get(tempControllerPath))) {
                Files.createDirectories(Paths.get(tempControllerPath));
            }
            if(!Files.exists(Paths.get(controllerPath))) {
                try (FileWriter fileWriter = new FileWriter(controllerPath)) {
                    String controllerClass =
                            "package com.api."+dto.getModuleName()+".controller"+( subModuleNames.size() > 1 ? "." + StrUtil.join(".", subModuleNames.subList(0, subModuleNames.size() - 1)) : "")+";\n" +
                                    "\n" +
                                    "import com.api.common.base.Result;\n" +
                                    "import jakarta.annotation.Resource;\n" +
                                    "import jakarta.validation.Valid;\n" +
                                    "import org.springframework.web.bind.annotation.PostMapping;\n" +
                                    "import org.springframework.web.bind.annotation.RequestBody;\n" +
                                    "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                                    "import org.springframework.web.bind.annotation.RestController;\n" +
                                    "\n" +
                                    "/**\n" +
                                    " * "+dto.getSubModuleAnnotation()+"相关接口\n" +
                                    " */\n" +
                                    "@RestController\n" +
                                    "@RequestMapping(\"/"+dto.getModuleName() + "/"+dto.getSubModuleName()+"\")\n" +
                                    "public class " + StrUtil.upperFirst(subModuleNames.getLast()) + "Controller {\n" +
                                    "\n" +
                                    "}\n";
                    fileWriter.write(controllerClass);
                }
            }

            String apiPath = "/".equals(dto.getApiPath()) ? dto.getSubModuleName() : dto.getSubModuleName() + dto.getApiPath();

            // 1. 读取原始文件
            List<String> lines = JavaFileReader.readJavaFile(controllerPath);

            // 2. 添加新的import语句
            String dtoPackage = "com.api." + dto.getModuleName() + ".data.dto." + StrUtil.replace(apiPath, "/", ".") + "."+ StrUtil.upperFirst(dto.getApiName()) +"Dto";
            String serviceName = StrUtil.upperFirst( dto.getModuleName()) + StrUtil.upperFirst(handleStr(StrUtil.replace(apiPath, "/", "_"))) + StrUtil.upperFirst(dto.getApiName());
            String servicePackage = "com.api." + dto.getModuleName() + ".service." + StrUtil.replace(apiPath, "/", ".") + "."+ StrUtil.upperFirst(dto.getApiName());
//            lines = JavaFileModifier.addImport(lines, "import " + dtoPackage + ";");
//            lines = JavaFileModifier.addImport(lines, "import " + servicePackage + ";");

            // 3. 添加新的@Resource字段
            List<String> apiPaths = StrUtil.split(dto.getApiPath(), "/");
            apiPaths.add(dto.getApiName());
            // 从第二个元素开始，首字母改为大写
            for (int i = 1; i < apiPaths.size(); i++) {
                apiPaths.set(i, StrUtil.upperFirst(apiPaths.get(i)));
            }

            String serviceClassName = StrUtil.lowerFirst(StrUtil.join("", apiPaths));
            lines = JavaFileModifier.addResourceField(lines, subModuleNames.getLast() + "Controller", servicePackage, serviceClassName, serviceName);

            // 4. 添加新的方法
            String newMethod =
                    "\n    // "+dto.getApiComment()+"\n" +
                            "    @PostMapping(\""+(dto.getApiPath().equals("/") ? "" : dto.getApiPath())+"/"+dto.getApiName()+"\")\n" +
                            "    public Result<?> "+dto.getApiName()+"(@RequestBody @Valid " + dtoPackage + " dto) {\n" +
                            "        "+serviceClassName+".check(dto);\n" +
                            "        return "+serviceClassName+".service(dto);\n" +
                            "    }";
            lines = JavaFileModifier.addMethod(lines, serviceClassName, newMethod);

            // 5. 写回文件
            Files.write(Paths.get(controllerPath), lines, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成文件
     *
     * @param templateName 模板文件名
     * @param fileName     文件名
     * @param dirPath      文件路径
     * @throws IOException IO一次
     */
    private void writerFile(String templateName, HashMap<String, Object> model, String dirPath, String fileName) throws IOException, TemplateException {
        Template template = freemarkerConfig.getTemplate(templateName);
        StringWriter writer = new StringWriter();
        template.process(model, writer);
        String generatedContent = writer.toString();

        // 转义 #\{ 为 #{
        generatedContent = generatedContent.replace("#\\{", "#{");
        generatedContent = generatedContent.replace(" >= ", " &gt;= ");
        generatedContent = generatedContent.replace(" <= ", " &lt;= ");

        // 判断dirPath是否存在，不存在则创建
        File dir = new File(dirPath);
        if (!dir.exists()) {
            boolean mkdirsSuccess = dir.mkdirs();
            if (!mkdirsSuccess) {
                throw new RuntimeException("无法创建目录: " + dirPath);
            }
        }

        String filePath = dirPath + fileName;

        // 将生成的内容写入文件
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(generatedContent);
        }
    }

    /**
     * 处理表名
     *
     * @param underscoreStr 原始表名
     * @return 处理后的表名
     */
    public String handleStr(String underscoreStr) {
        if (StrUtil.isBlank(underscoreStr)) {
            return "";
        }
        StringBuilder camelCaseStr = new StringBuilder();
        boolean nextUpperCase = false;
        for (char c : underscoreStr.toCharArray()) {
            if (c == '_') {
                nextUpperCase = true;
            } else {
                if (nextUpperCase) {
                    camelCaseStr.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    camelCaseStr.append(c);
                }
            }
        }
        return camelCaseStr.toString();
    }

    /**
     * 获取表结构
     *
     * @param tableNames 表名列表
     */
    protected Map<String, List<ColumnItemDto>> getColumns(List<String> tableNames) {
        List<Column> columnList = columnDao.query(ColumnPo.builder()
                .whereTableSchema(databaseName)
                .whereInTableNames(tableNames)
                .sortList(new BasePageSortList().addSort("ordinal_position").getSortList())
                .build());
        Map<String, List<ColumnItemDto>> tableColumnMap = new HashMap<>();
        for (Column column : columnList) {
            ColumnItemDto columnItemDto = new ColumnItemDto();
            BeanUtils.copyProperties(column, columnItemDto);
            columnItemDto.setDataType(mysqlToJavaTypeMap.get(column.getDataType()));
            columnItemDto.setValueName(handleStr(column.getColumnName()));
            columnItemDto.setColumnComment(Optional.of(column.getColumnComment()).map(comment -> comment.replaceAll("[\\r\\n]+", "")).orElse(""));
            columnItemDto.setColumnDefault(column.getColumnDefault());
            List<String> whereTypes = new ArrayList<>();
            whereTypes.add("equal");
            whereTypes.add("in");
            whereTypes.add("inOr");
            whereTypes.add("not");
            whereTypes.add("notIn");
            whereTypes.add("isNull");
            whereTypes.add("isNotNull");
            whereTypes.add("isEmpty");
            whereTypes.add("isNotEmpty");
            whereTypes.add("gt"); // >
            whereTypes.add("gte"); // >=
            whereTypes.add("lt"); // <
            whereTypes.add("lte"); // <=
            if ("String".equals(columnItemDto.getDataType()) && !List.of("date", "datetime", "timestamp", "time").contains(column.getDataType())) {
                whereTypes.add("like");
            }
            if (List.of("String","Integer", "Long", "Float", "Double", "BigDecimal").contains(columnItemDto.getDataType())
                    || List.of("date", "datetime", "timestamp", "time").contains(column.getDataType())) {
                whereTypes.add("start");
                whereTypes.add("end");
            }
            columnItemDto.setWhereTypes(whereTypes);
            tableColumnMap.computeIfAbsent(column.getTableName(), k -> new ArrayList<>()).add(columnItemDto);
        }
        return tableColumnMap;
    }
}
