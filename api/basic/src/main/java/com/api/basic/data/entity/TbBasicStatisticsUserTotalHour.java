package com.api.basic.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 用户每小时注册统计表实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicStatisticsUserTotalHour extends BasePagePo {

    public TbBasicStatisticsUserTotalHour(){
        super();
    }

    // 
    private Integer id;
    // 日期小时
    private String dateHour;
    // 用户注册数
    private Integer cnt;
    // 更新时间
    private String updatedAt;
}
