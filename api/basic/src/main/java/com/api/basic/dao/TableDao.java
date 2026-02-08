package com.api.basic.dao;

import com.api.basic.data.entity.Table;
import com.api.basic.data.po.TablePo;
import com.api.basic.mapper.TableMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库表Dao
 */
@Component
@RequiredArgsConstructor
public class TableDao {

    private final TableMapper mapper;
    private final DataSource dataSource;

    //***************************************************获取 START******************************************************

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<Table> query(TablePo po) {
        return mapper.query(po);
    }

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    public Integer cnt(TablePo po) {
        return mapper.cnt(po);
    }

    /**
     * 查询单条数据
     *
     * @param po 查询条件
     * @return 查询结果
     */
    public Table get(TablePo po) {
        List<Table> list = mapper.query(po);
        return list.isEmpty() ? null : list.getFirst();
    }

    public String getDatabaseName() throws SQLException {
        Connection connection = null;
        String databaseName = null;
        try {
            connection = dataSource.getConnection();
            databaseName = connection.getCatalog();
            connection.close();
        } catch (SQLException e) {

        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return databaseName;
    }
    //***************************************************获取 END********************************************************
}
