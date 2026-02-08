package com.api.apps.service.apps;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.api.apps.dao.TbAppsDao;
import com.api.apps.data.dto.apps.AddDto;
import com.api.apps.data.entity.TbApps;
import com.api.apps.data.po.TbAppsPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import com.api.common.utils.FunctionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 添加应用
 *
 * @author 裴金伟
 */
@Service("AppsAddServiceImpl")
@RequiredArgsConstructor
public class Add extends AbstractService {

    private final TbAppsDao tbAppsDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(AddDto dto) {
        if (tbAppsDao.cnt(TbAppsPo.builder().whereAppSecret(dto.getAppSecret()).build()) == 1) {
            throw new ServerException("应用密钥已存在");
        }
        if (tbAppsDao.cnt(TbAppsPo.builder().whereName(dto.getName()).build()) == 1) {
            throw new ServerException("应用名称已存在");
        }
        if (tbAppsDao.cnt(TbAppsPo.builder().whereUniAppid(dto.getUniAppid()).build()) == 1) {
            throw new ServerException("应用uniappid已存在");
        }
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
        TbApps entity = handleAddData(dto);
        if (tbAppsDao.add(entity) == 0) {
            throw new ServerException("添加失败");
        }
        return Result.ok("添加成功");
}

    /**
     * 处理要添加的数据
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected TbApps handleAddData(AddDto dto){
        TbApps entity = new TbApps();
        BeanUtils.copyProperties(dto, entity);
        entity.setId("WJ_"+ FunctionUtil.randomStr(10, true, true, true));
        entity.setAccessRoleIds(JSONUtil.toJsonStr(dto.getAccessRoleIds()));
        entity.setCreatedUserId(StpUtil.getLoginIdAsInt());
        entity.setUpdatedUserId(StpUtil.getLoginIdAsInt());
        return entity;
    }
}
