package com.api.basic.service.sys.dic;

import com.api.basic.dao.TbBasicSysDictionaryDao;
import com.api.basic.data.dto.sys.dic.QueryDto;
import com.api.basic.data.entity.TbBasicSysDictionary;
import com.api.basic.data.po.TbBasicSysDictionaryPo;
import com.api.basic.data.vo.sys.dic.QueryItemVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 获取字典组列表
 *
 * @author 裴金伟
 */
@Service("BasicDicQueryServiceImpl")
@RequiredArgsConstructor
public class Query extends AbstractService {

    private final TbBasicSysDictionaryDao tbBasicSysDictionaryDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(QueryDto dto) {
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    public Result<?> service(QueryDto dto) {
        TbBasicSysDictionaryPo queryPo = handleQueryData(dto);
        List<TbBasicSysDictionary> entityList = tbBasicSysDictionaryDao.query(queryPo);
        List<QueryItemVo> voList = new ArrayList<>();
        if (entityList != null) {
            entityList.forEach(entity -> {
                QueryItemVo vo = convertToVo(entity);
                voList.add(vo);
            });
        }

        return Result.ok(Map.of("list", voList));
    }

    /**
     * 处理要查询的条件
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected TbBasicSysDictionaryPo handleQueryData(QueryDto dto){
        TbBasicSysDictionaryPo po = new TbBasicSysDictionaryPo();
        BeanUtils.copyProperties(dto, po);
        po.setWhereName(dto.getName());
        po.setWhereIdentifying(dto.getIdentifying());
        po.setWhereDescription(dto.getDescription());
        po.setWhereStatus(dto.getStatus());
        return po;
    }


    /**
     * entity转换成vo
     *
     * @param entity 转换前的Entity对象
     * @return 转换后的vo对象
     */
    private QueryItemVo convertToVo(TbBasicSysDictionary entity){
        QueryItemVo vo = new QueryItemVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
