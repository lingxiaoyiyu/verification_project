package com.api.basic.service.trackingPoint;

import com.api.basic.data.dto.trackingPoint.AddDto;
import com.api.basic.data.entity.TbBasicTrackingPointRecord;
import com.api.basic.dao.TbBasicTrackingPointRecordDao;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import com.api.common.utils.FunctionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

/**
 * 添加埋点记录
 *
 * @author 裴金伟
 */
@Service("BasicTrackingPointAddServiceImpl")
@RequiredArgsConstructor
public class Add extends AbstractService {

    private final TbBasicTrackingPointRecordDao tbBasicTrackingPointRecordDao;

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
        TbBasicTrackingPointRecord entity = handleAddData(dto);
        if (tbBasicTrackingPointRecordDao.add(entity) == 0) {
            throw new ServerException(FunctionUtil.getI18nString("service.trackingPoint.addFailed"));
        }
        return Result.ok(FunctionUtil.getI18nString("service.trackingPoint.addSuccess"), Map.of("id", entity.getId()));
    }

    /**
     * 处理要添加的数据
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected TbBasicTrackingPointRecord handleAddData(AddDto dto){
        TbBasicTrackingPointRecord entity = new TbBasicTrackingPointRecord();
        entity.setPage(dto.getPage());
        entity.setTag(dto.getTag());
        entity.setIp(FunctionUtil.getIpAddress());
        entity.setUA(FunctionUtil.getUserAgent());
        return entity;
    }
}
