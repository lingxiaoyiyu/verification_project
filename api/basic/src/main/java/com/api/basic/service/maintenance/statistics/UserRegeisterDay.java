package com.api.basic.service.maintenance.statistics;

import com.api.basic.dao.TbBasicStatisticsUserTotalDao;
import com.api.basic.data.dto.maintenance.statistics.UserRegeisterDayDto;
import com.api.basic.data.entity.TbBasicStatisticsUserTotal;
import com.api.basic.data.po.TbBasicStatisticsUserTotalPo;
import com.api.basic.data.vo.maintenance.statistics.UserRegeisterDayItemVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.vo.BasePageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询每日用户注册统计列表分页
 *
 * @author 裴金伟
 */
@Service("MaintenanceStatisticsUserRegeisterDayServiceImpl")
@RequiredArgsConstructor
public class UserRegeisterDay extends AbstractService {

    private final TbBasicStatisticsUserTotalDao tbBasicStatisticsUserTotalDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(UserRegeisterDayDto dto) {
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    public Result<?> service(UserRegeisterDayDto dto) {
        TbBasicStatisticsUserTotalPo queryPo = handleQueryData(dto);
        List<TbBasicStatisticsUserTotal> entityList = tbBasicStatisticsUserTotalDao.query(queryPo);
        List<UserRegeisterDayItemVo> voList = new ArrayList<>();
        if (entityList != null) {
            entityList.forEach(entity -> {
                voList.add(convertToVo(entity));
            });
        }

        BasePageVo<UserRegeisterDayItemVo> basePageVo = new BasePageVo<>();
        basePageVo.setList(voList);
        basePageVo.setTotal(tbBasicStatisticsUserTotalDao.cnt(queryPo)); // 统计总数
        return Result.ok(basePageVo);
    }

    /**
     * 处理要查询的条件
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected TbBasicStatisticsUserTotalPo handleQueryData(UserRegeisterDayDto dto){
        TbBasicStatisticsUserTotalPo po = new TbBasicStatisticsUserTotalPo();
        po.setWhereStartDay(dto.getStartDay());
        po.setWhereEndDay(dto.getEndDay());
        return po;
    }

    /**
     * entity转换成vo
     *
     * @param entity 转换前的Entity对象
     * @return 转换后的vo对象
     */
    private UserRegeisterDayItemVo convertToVo(TbBasicStatisticsUserTotal entity){
        UserRegeisterDayItemVo vo = new UserRegeisterDayItemVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
