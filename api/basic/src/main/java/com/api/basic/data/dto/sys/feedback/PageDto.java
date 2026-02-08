package com.api.basic.data.dto.sys.feedback;

import com.api.common.base.data.dto.BasePageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取问题反馈列表-分页接口请求用DTO
 *
 * @author 裴金伟
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class PageDto extends BasePageDto {

    // 反馈类型。bug：Bug反馈，suggestion：功能建议，question：使用咨询，other：其他
    private Integer type;
    // 反馈内容
    private String content;
    // 联系方式
    private String contact;
    // 处理状态。1：待处理，2：处理中，3：已解决，4：已拒绝
    private Integer status;
    // 是否当前用户 1:当前用户，其他非当前用户
    private Integer isCurrentUser;
}
