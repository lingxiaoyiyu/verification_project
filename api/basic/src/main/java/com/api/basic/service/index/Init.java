package com.api.basic.service.index;

import cn.hutool.log.LogFactory;
import com.api.basic.dao.TbBasicSysRegionDao;
import com.api.basic.data.entity.TbBasicSysRegion;
import com.api.basic.data.po.TbBasicSysRegionPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.enums.IsDeleteEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 如初初始化
 */
@Service("IndexInitServiceImpl")
@RequiredArgsConstructor
public class Init extends AbstractService {

    private final TbBasicSysRegionDao tbBasicSysRegionDao;

    /**
     * 业务逻辑处理
     *
     * @return 处理结果
     */
    public Result<?> service() {
        handleRegionData();
        return Result.ok();
    }

    // 处理行政区域数据
    private void handleRegionData(){
        List<TbBasicSysRegion> regionList = tbBasicSysRegionDao.query(TbBasicSysRegionPo.builder()
                .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
                .whereLevel(4)
                .build());

        for (TbBasicSysRegion region : regionList) {
            setSort(region.getCode());
        }
    }

    private void setSort(Long parentCode) {
        // 获取allRegionList中parentId = parentId的数据
        List<TbBasicSysRegion> regionList = tbBasicSysRegionDao.query(TbBasicSysRegionPo.builder().whereParentCode(parentCode).build());
        if (!regionList.isEmpty()) {
            LogFactory.get().info("parentCode：" + parentCode + "，regionList.size：" + regionList.size());
            Integer sort = 0;
            for (TbBasicSysRegion region : regionList) {
                sort++;
                if(region.getSort() > 100) {
                    LogFactory.get().info("code：" + region.getCode() + "，sort：" + sort);
                    tbBasicSysRegionDao.update(TbBasicSysRegionPo.builder().whereCode(region.getCode()).sort(sort).build());
                }
//                setSort(region.getCode());
            }
        }
    }
}
