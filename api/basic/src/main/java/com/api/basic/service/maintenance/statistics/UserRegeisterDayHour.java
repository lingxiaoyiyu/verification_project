package com.api.basic.service.maintenance.statistics;

import com.api.basic.dao.TbBasicStatisticsUserTotalHourDao;
import com.api.basic.data.dto.maintenance.statistics.UserRegeisterDayHourDto;
import com.api.basic.data.entity.TbBasicStatisticsUserTotalHour;
import com.api.basic.data.po.TbBasicStatisticsUserTotalHourPo;
import com.api.basic.data.vo.maintenance.statistics.UserRegeisterDayHourItemVo;
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
 * 查询每日用户注册统计列表
 *
 * @author 裴金伟
 */
@Service("MaintenanceStatisticsUserRegeisterDayHourServiceImpl")
@RequiredArgsConstructor
public class UserRegeisterDayHour extends AbstractService {

    private final TbBasicStatisticsUserTotalHourDao tbBasicStatisticsUserTotalHourDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(UserRegeisterDayHourDto dto) {
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    public Result<?> service(UserRegeisterDayHourDto dto) {
        TbBasicStatisticsUserTotalHourPo queryPo = handleQueryData(dto);
        List<TbBasicStatisticsUserTotalHour> entityList = tbBasicStatisticsUserTotalHourDao.query(queryPo);
        List<UserRegeisterDayHourItemVo> voList = new ArrayList<>();
        if (entityList != null) {
            entityList.forEach(entity -> {
                voList.add(convertToVo(entity));
            });
        }

        Map<String, List<UserRegeisterDayHourItemVo>> data = new HashMap<>();
        data.put("list", voList);
        return Result.ok(data);
    }

    /**
     * 处理要查询的条件
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected TbBasicStatisticsUserTotalHourPo handleQueryData(UserRegeisterDayHourDto dto){
        TbBasicStatisticsUserTotalHourPo po = new TbBasicStatisticsUserTotalHourPo();
        BeanUtils.copyProperties(dto, po);
        po.setWhereStartDateHour(dto.getStartDayHour());
        po.setWhereEndDateHour(dto.getEndDayHour());
        return po;
    }


    /**
     * entity转换成vo
     *
     * @param entity 转换前的Entity对象
     * @return 转换后的vo对象
     */
    private UserRegeisterDayHourItemVo convertToVo(TbBasicStatisticsUserTotalHour entity){
        UserRegeisterDayHourItemVo vo = new UserRegeisterDayHourItemVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
