package com.api.apps.mapper;

import com.api.apps.data.entity.TbAppsVersion;
import com.api.apps.data.po.TbAppsVersionPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 应用版本信息表Mapper
 *
 * @author 裴金伟
 */
@Repository
@Mapper
public interface TbAppsVersionMapper {

    /**
     * 新增
     *
     * @param entity 新增信息
     * @return 新增ID
     */
    public Integer add(TbAppsVersion entity);

    /**
     * 删除信息
     *
     * @param po 要删除的信息
     * @return 受影响的行数，即删除的数据数量
     */
    public Integer delete(TbAppsVersionPo po);

    /**
     * 更新信息
     *
     * @param po 要更新的信息
     * @return 受影响的行数，即更新的数据数量
     */
    public Integer update(TbAppsVersionPo po);

    /**
     * 查询列表
     *
     * @param po 查询条件
     * @return 列表
     */
    public List<TbAppsVersion> query(TbAppsVersionPo po);

    /**
     * 统计数量
     *
     * @param po 统计条件
     * @return 数量
     */
    public Integer cnt(TbAppsVersionPo po);
}
