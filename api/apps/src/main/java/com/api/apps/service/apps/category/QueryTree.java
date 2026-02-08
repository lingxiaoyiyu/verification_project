package com.api.apps.service.apps.category;

import com.api.apps.dao.TbAppsCategoryDao;
import com.api.apps.data.entity.TbAppsCategory;
import com.api.apps.data.po.TbAppsCategoryPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.vo.BaseSelectIntVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询分类列表
 *
 * @author 裴金伟
 */
@Service("AppsCategoryQueryTreeServiceImpl")
@RequiredArgsConstructor
public class QueryTree extends AbstractService {

    private final TbAppsCategoryDao tbAppsCategoryDao;

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
        List<TbAppsCategory> entityList = tbAppsCategoryDao.query(TbAppsCategoryPo.builder().whereIsShow(1).build());
        List<BaseSelectIntVo> voList = new ArrayList<>();
        if (entityList != null) {
            entityList.forEach(entity -> {
                BaseSelectIntVo baseSelectIntVo = new BaseSelectIntVo();
                baseSelectIntVo.setValue(entity.getId());
                baseSelectIntVo.setLabel(entity.getName());
                voList.add(baseSelectIntVo);
            });
        }

        Map<String, List<BaseSelectIntVo>> data = new HashMap<>();
        data.put("list", voList);
        return Result.ok(data);
    }
}
