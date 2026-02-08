package com.api.basic.data.entity;

import com.api.common.base.data.po.BasePagePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 用户每日注册统计表实体
 */
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@Data
@SuperBuilder
public class TbBasicStatisticsUserTotal extends BasePagePo {

    public TbBasicStatisticsUserTotal(){
        super();
    }

    // 
    private Integer id;
    // 日期
    private String day;
    // 新增用户数
    private Integer cnt;
    // 更新时间
    private String updatedAt;
}
