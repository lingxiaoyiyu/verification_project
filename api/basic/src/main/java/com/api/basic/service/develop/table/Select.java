package com.api.basic.service.develop.table;

import com.api.basic.dao.TableDao;
import com.api.basic.data.entity.Table;
import com.api.basic.data.po.TablePo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.vo.BaseSelectStrVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询数据库表列表（下拉列表用）
 */
@Service("BasicDevelopTableSelectServiceImpl")
@RequiredArgsConstructor
public class Select extends AbstractService {

    @Autowired
    private DataSource dataSource;
    private final TableDao tableDao;

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    public Result<?> service() throws SQLException {
        TablePo po = handleQueryData();
        List<Table> tableList = tableDao.query(po);
        List<BaseSelectStrVo> voList = tableList.stream().map(entity -> {
            BaseSelectStrVo vo = new BaseSelectStrVo();
            vo.setValue(entity.getTableName());
            vo.setLabel(entity.getTableName());
            return vo;
        }).toList();

        Map<String, List<BaseSelectStrVo>> data = new HashMap<>();
        data.put("list", voList);
        return Result.ok(data);
    }

    /**
     * 处理要查询的条件
     *
     * @return 处理后的数据
     */
    private TablePo handleQueryData() throws SQLException {
        TablePo po = new TablePo();
        po.setWhereTableSchema(dataSource.getConnection().getCatalog());
        return po;
    }
}
