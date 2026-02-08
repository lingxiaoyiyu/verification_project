package com.api.basic.service.sys.dic;

import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicSysDictionaryDao;
import com.api.basic.data.dto.sys.dic.PageDto;
import com.api.basic.data.entity.TbBasicSysDictionary;
import com.api.basic.data.po.TbBasicSysDictionaryPo;
import com.api.basic.data.vo.sys.dic.PageItemVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.po.BasePageSortList;
import com.api.common.base.data.vo.BasePageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取字典组列表-分页
 *
 * @author 裴金伟
 */
@Service("BasicDicPageServiceImpl")
@RequiredArgsConstructor
public class Page extends AbstractService {

    private final TbBasicSysDictionaryDao tbBasicSysDictionaryDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(PageDto dto) {
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    public Result<?> service(PageDto dto) {
        TbBasicSysDictionaryPo queryPo = handleQueryData(dto);
        List<TbBasicSysDictionary> entityList = tbBasicSysDictionaryDao.query(queryPo);
        List<PageItemVo> voList = new ArrayList<>();
        if (entityList != null) {

            entityList.forEach(entity -> {
                PageItemVo vo = convertToVo(entity);
                voList.add(vo);
            });
        }

        BasePageVo<PageItemVo> basePageVo = new BasePageVo<>();
        basePageVo.setList(voList);
        basePageVo.setTotal(tbBasicSysDictionaryDao.cnt(queryPo)); // 统计总数
        return Result.ok(basePageVo);
    }

    /**
     * 处理要查询的条件
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected TbBasicSysDictionaryPo handleQueryData(PageDto dto){
        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
        BeanUtils.copyProperties(dto, po);
        po.setWhereName(dto.getName());
        po.setWhereIdentifying(dto.getIdentifying());
        po.setWhereDescription(dto.getDescription());
        po.setWhereStatus(dto.getStatus());

        po.setPage(dto.getPage(), dto.getPageSize());
        po.setSortList(new BasePageSortList().addSort(StrUtil.isBlank(dto.getSortFiled()) ? "id" : dto.getSortFiled(),
        StrUtil.isBlank(dto.getSortType()) ? "DESC" : dto.getSortType()).getSortList());
        return po;
    }

    /**
     * entity转换成vo
     *
     * @param entity 转换前的Entity对象
     * @return 转换后的vo对象
     */
    private PageItemVo convertToVo(TbBasicSysDictionary entity){
        PageItemVo vo = new PageItemVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
