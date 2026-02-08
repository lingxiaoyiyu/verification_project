package com.api.basic.listener;

import cn.hutool.core.date.DateUtil;
import com.api.basic.dao.TbBasicStatisticsUserTotalDao;
import com.api.basic.dao.TbBasicStatisticsUserTotalHourDao;
import com.api.basic.data.entity.TbBasicStatisticsUserTotal;
import com.api.basic.data.entity.TbBasicStatisticsUserTotalHour;
import com.api.basic.data.po.TbBasicStatisticsUserTotalHourPo;
import com.api.basic.data.po.TbBasicStatisticsUserTotalPo;
import com.api.basic.event.UserRegisterEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

// 用户注册监听
@Component
@RequiredArgsConstructor
public class UserRegeisterListener implements ApplicationListener<UserRegisterEvent> {

    private final TbBasicStatisticsUserTotalDao tbBasicStatisticsUserTotalDao;
    private final TbBasicStatisticsUserTotalHourDao tbBasicStatisticsUserTotalHourDao;

    @Override
    @Async
    public void onApplicationEvent(UserRegisterEvent event) {
        Integer userId = event.getEntity().getUserId();
        String dateHour = DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH");
        String dateDay = DateUtil.format(DateUtil.date(), "yyyy-MM-dd");

        // 用户每日注册统计
        TbBasicStatisticsUserTotalPo totalPo = new TbBasicStatisticsUserTotalPo();
        totalPo.setWhereDay(dateDay);
        if(tbBasicStatisticsUserTotalDao.cnt(TbBasicStatisticsUserTotalPo.builder().day(dateDay).build()) == 0) {
            tbBasicStatisticsUserTotalDao.add(TbBasicStatisticsUserTotal.builder().day(dateDay).build());
        }
        Integer totalCnt = tbBasicStatisticsUserTotalDao.getFieldByDay(dateDay, TbBasicStatisticsUserTotal::getCnt).orElse(0);
        tbBasicStatisticsUserTotalDao.update(TbBasicStatisticsUserTotalPo.builder().whereDay(dateDay).cnt(totalCnt + 1).build());

        // 每小时注册统计
        TbBasicStatisticsUserTotalHourPo totalHourPo = new TbBasicStatisticsUserTotalHourPo();
        totalHourPo.setWhereDateHour(dateHour);
        if(tbBasicStatisticsUserTotalHourDao.cnt(totalHourPo) == 0) {
            tbBasicStatisticsUserTotalHourDao.add(TbBasicStatisticsUserTotalHour.builder().dateHour(dateDay).build());
        }
        Integer totalHourCnt = tbBasicStatisticsUserTotalHourDao.getFieldByDateHour(dateHour, TbBasicStatisticsUserTotalHour::getCnt).orElse(0);
        tbBasicStatisticsUserTotalHourDao.update(TbBasicStatisticsUserTotalHourPo.builder().whereDateHour(dateHour).cnt(totalHourCnt + 1).build());
    }
}

