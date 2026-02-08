package com.api.basic.service.feedback;

import com.api.basic.dao.TbBasicFeedbackDao;
import com.api.basic.data.dto.sys.feedback.GetDto;
import com.api.basic.data.entity.TbBasicFeedback;
import com.api.basic.data.po.TbBasicFeedbackPo;
import com.api.basic.data.vo.sys.feedback.GetVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.enums.IsDeleteEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 获取指定问题反馈信息
 *
 * @author 裴金伟
 */
@Service("BasicFeedbackGetServiceImpl")
@RequiredArgsConstructor
public class Get extends AbstractService {

    protected final TbBasicFeedbackDao tbBasicFeedbackDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(GetDto dto) {
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    public Result<?> service(GetDto dto) {
        TbBasicFeedback entity = tbBasicFeedbackDao.get(TbBasicFeedbackPo.builder()
            .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
            .whereId(dto.getId())
            .build());
        GetVo vo = new GetVo();
        if (entity != null) {
            BeanUtils.copyProperties(entity, vo);
        }

        return Result.ok(vo);
    }
}
