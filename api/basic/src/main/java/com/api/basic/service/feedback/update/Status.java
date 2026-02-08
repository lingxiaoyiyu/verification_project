package com.api.basic.service.feedback.update;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicFeedbackDao;
import com.api.basic.data.dto.sys.feedback.update.StatusDto;
import com.api.basic.data.entity.TbBasicFeedback;
import com.api.basic.data.po.TbBasicFeedbackPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 修改问题反馈状态信息
 *
 * @author 裴金伟
 */
@Service("BasicFeedbackUpdateStatusServiceImpl")
@RequiredArgsConstructor
public class Status extends AbstractService {

    private final TbBasicFeedbackDao tbBasicFeedbackDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(StatusDto dto) {
        TbBasicFeedback entity = tbBasicFeedbackDao.get(TbBasicFeedbackPo.builder()
                                                            .whereId(dto.getId())
                                                            .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
                                                            .build());

        if (entity == null) {
            throw new ServerException("问题反馈不存在");
        }

        if (StrUtil.isNotBlank(dto.getUpdatedAt()) && !dto.getUpdatedAt().equals(entity.getUpdatedAt())) {
           throw new ServerException("问题反馈信息已被其他用户修改");
        }
        if (tbBasicFeedbackDao.cnt(TbBasicFeedbackPo.builder()
             .whereIsDelete(IsDeleteEnum.NO_DELETE.getCode())
            .whereNotId(dto.getId())
            .whereId(dto.getId())
            .build()) != 0) {
            throw new ServerException("ID已存在");
        }
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(StatusDto dto) {
        if (tbBasicFeedbackDao.update(handlUpdateData(dto)) != 1) {
            throw new ServerException("更新失败");
        }

        return Result.ok("更新成功", Map.of("updatedAt", tbBasicFeedbackDao.getFieldById(dto.getId(),  TbBasicFeedback::getUpdatedAt).orElse("")));
    }

    /**
    * 处理要更新的数据
    *
    * @param dto 请求参数
    * @return 处理后的数据
    */
    protected TbBasicFeedbackPo handlUpdateData(StatusDto dto){
        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        po.setWhereId(dto.getId());
        po.setStatus(dto.getStatus());
        po.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        return po;
    }
}
