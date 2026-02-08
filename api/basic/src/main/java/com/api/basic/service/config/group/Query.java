package com.api.basic.service.config.group;

import com.api.basic.dao.TbBasicConfigGroupDao;
import com.api.basic.data.entity.TbBasicConfigGroup;
import com.api.basic.data.po.TbBasicConfigGroupPo;
import com.api.basic.data.vo.config.group.QueryItemVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取配置组列表
 *
 * @author 裴金伟
 */
@Service("BasicConfigGroupQueryServiceImpl")
@RequiredArgsConstructor
public class Query extends AbstractService {

    private final TbBasicConfigGroupDao tbBasicConfigGroupDao;

    /**
     * 参数检查
     */
    public void check() {
    }

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    public Result<?> service() {
        List<TbBasicConfigGroup> entityList = tbBasicConfigGroupDao.query(TbBasicConfigGroupPo.builder().build());
        List<QueryItemVo> voList = new ArrayList<>();
        if (entityList != null) {
            entityList.forEach(entity -> {
                voList.add(convertToVo(entity));
            });
        }

        Map<String, List<QueryItemVo>> data = new HashMap<>();
        data.put("list", voList);
        return Result.ok(data);
    }


    /**
     * entity转换成vo
     *
     * @param entity 转换前的Entity对象
     * @return 转换后的vo对象
     */
    private QueryItemVo convertToVo(TbBasicConfigGroup entity){
        QueryItemVo vo = new QueryItemVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
