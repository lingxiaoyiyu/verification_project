package com.api.basic.service.develop.table;

import com.api.basic.dao.ColumnDao;
import com.api.basic.dao.TableDao;
import com.api.basic.data.dto.develop.table.DetailDto;
import com.api.basic.data.entity.Column;
import com.api.basic.data.po.ColumnPo;
import com.api.basic.data.vo.develop.table.ColumnItemVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.po.BasePageSortList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 查询数据库表结构列表
 */
@Service("BasicDevelopTableDetailServiceImpl")
@RequiredArgsConstructor
public class Detail extends AbstractService {

    private final ColumnDao columnDao;
    private final TableDao tableDao;

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    public Result<?> service(DetailDto dto) {
        try {
            ColumnPo po = handleQueryData(dto);
            List<Column> columnList = columnDao.query(po);

            Map<String, String> mysqlToJavaTypeMap = new HashMap<>();
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

            List<ColumnItemVo> columnItemVoList = columnList.stream()
                    .filter(column -> !"default".equals(column.getColumnName())) // 前置过滤条件
                    .map(column -> {
                        ColumnItemVo columnItemVo = new ColumnItemVo();
                        BeanUtils.copyProperties(column, columnItemVo);
                        columnItemVo.setColumnComment(Optional.of(column.getColumnComment()).map(comment -> comment.replaceAll("[\\r\\n]+", "")).orElse(""));
                        columnItemVo.setDataType(mysqlToJavaTypeMap.get(column.getDataType()));
                        return columnItemVo;
                    })
                    .toList(); // 修复 5：流中已过滤非法值，无需判空

            Map<String, List<ColumnItemVo>> pageVo = new HashMap<>();
            pageVo.put("list", columnItemVoList);
            return Result.ok(pageVo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 处理要查询的条件
     *
     * @param dto DTO对象
     * @return 处理后的数据
     */
    private ColumnPo handleQueryData(DetailDto dto) throws SQLException {
        ColumnPo po = new ColumnPo();
        po.setWhereTableSchema(tableDao.getDatabaseName());
        po.setWhereTableName(dto.getTableName());
        po.setSortList(new BasePageSortList().addSort("ordinal_position").getSortList());
        return po;
    }
}
