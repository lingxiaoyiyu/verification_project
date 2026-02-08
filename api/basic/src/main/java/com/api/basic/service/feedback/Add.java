package com.api.basic.service.feedback;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.api.basic.dao.TbBasicFeedbackDao;
import com.api.basic.data.dto.sys.feedback.AddDto;
import com.api.basic.data.entity.TbBasicFeedback;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 添加问题反馈信息
 *
 * @author 裴金伟
 */
@Service("BasicFeedbackAddServiceImpl")
@RequiredArgsConstructor
public class Add extends AbstractService {

    private final TbBasicFeedbackDao tbBasicFeedbackDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(AddDto dto) {
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(AddDto dto) {
        // 构建添加对象
        TbBasicFeedback entity = handleAddData(dto);
        if (tbBasicFeedbackDao.add(entity) == 0) {
            throw new ServerException("添加失败");
        }
        return Result.ok("添加成功", Map.of("id", entity.getId()));
    }

    /**
     * 处理要添加的数据
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected TbBasicFeedback handleAddData(AddDto dto){
        TbBasicFeedback entity = new TbBasicFeedback();
        BeanUtils.copyProperties(dto, entity);
        entity.setCreatedUserId(StpUtil.getLoginIdAsInt());
        entity.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        entity.setImages(JSONUtil.toJsonStr(dto.getImages()));
        return entity;
    }
}
