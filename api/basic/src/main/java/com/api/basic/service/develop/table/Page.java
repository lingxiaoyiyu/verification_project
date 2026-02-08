package com.api.basic.service.develop.table;

import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TableDao;
import com.api.basic.data.dto.develop.table.PageDto;
import com.api.basic.data.entity.Table;
import com.api.basic.data.po.TablePo;
import com.api.basic.data.vo.develop.table.TableItemVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.po.BasePageSortList;
import com.api.common.base.data.vo.BasePageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * 查询数据库表列表-分页
 */
@Service("BasicDevelopTablePageServiceImpl")
@RequiredArgsConstructor
public class Page extends AbstractService {

    private final TableDao tableDao;
    @Autowired
    private DataSource dataSource;

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    public Result<?> service(PageDto dto) {
        try {
            TablePo po = handleQueryData(dto);
            List<Table> tableList = tableDao.query(po);
            List<TableItemVo> tableVoList = tableList.stream().map(table -> {
                TableItemVo tableVo = new TableItemVo();
                BeanUtils.copyProperties(table, tableVo);
                return tableVo;
            }).toList();

            BasePageVo<TableItemVo> pageVo = new BasePageVo<>();
            pageVo.setList(tableVoList);
            pageVo.setTotal(tableDao.cnt(po));
            return Result.ok(pageVo);
        } catch (SQLException e) {
            return Result.fail("查询失败");
        }
    }

    /**
     * 处理要查询的条件
     *
     * @param dto DTO对象
     * @return 处理后的数据
     */
    private TablePo handleQueryData(PageDto dto) throws SQLException {
        TablePo po = new TablePo();
        po.setWhereTableSchema(dataSource.getConnection().getCatalog());
        po.setWhereLikeTableName(dto.getTableName());
        po.setPage(dto.getPage(), dto.getPageSize());
        po.setSortList(new BasePageSortList().addSort(StrUtil.isBlank(dto.getSortFiled()) ? "table_name" : dto.getSortFiled(), dto.getSortType()).getSortList());
        return po;
    }
}
