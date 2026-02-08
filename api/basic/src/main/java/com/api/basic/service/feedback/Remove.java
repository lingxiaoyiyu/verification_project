package com.api.basic.service.feedback;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicFeedbackDao;
import com.api.basic.data.dto.sys.feedback.RemoveDto;
import com.api.basic.data.entity.TbBasicFeedback;
import com.api.basic.data.po.TbBasicFeedbackPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.enums.IsDeleteEnum;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 删除问题反馈信息
 *
 * @author 裴金伟
 */
@Service("BasicFeedbackRemoveServiceImpl")
@RequiredArgsConstructor
public class Remove extends AbstractService {

    private final TbBasicFeedbackDao tbBasicFeedbackDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(RemoveDto dto) {
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
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(RemoveDto dto) {
        // 构建删除对象
        TbBasicFeedbackPo po = handleRemoveData(dto);
        if (tbBasicFeedbackDao.update(po) == 0) {
            throw new ServerException("删除失败");
        }
        return Result.ok("删除成功");
    }

    /**
     * 处理要删除的数据
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected TbBasicFeedbackPo handleRemoveData(RemoveDto dto){
        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
                    po.setWhereId(dto.getId());
        po.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        po.setIsDelete(IsDeleteEnum.DELETED.getCode());
        return po;
    }
}
