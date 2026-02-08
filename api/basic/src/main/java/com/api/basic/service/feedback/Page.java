package com.api.basic.service.feedback;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.api.basic.dao.TbBasicFeedbackDao;
import com.api.basic.data.dto.sys.feedback.PageDto;
import com.api.basic.data.entity.TbBasicFeedback;
import com.api.basic.data.po.TbBasicFeedbackPo;
import com.api.basic.data.vo.sys.feedback.PageItemVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.po.BasePageSortList;
import com.api.common.base.data.vo.BasePageVo;
import com.api.common.enums.IsDeleteEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取问题反馈列表-分页
 *
 * @author 裴金伟
 */
@Service("BasicFeedbackPageServiceImpl")
@RequiredArgsConstructor
public class Page extends AbstractService {

    private final TbBasicFeedbackDao tbBasicFeedbackDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(PageDto dto) {
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    public Result<?> service(PageDto dto) {
        TbBasicFeedbackPo queryPo = handleQueryData(dto);
        List<TbBasicFeedback> entityList = tbBasicFeedbackDao.query(queryPo);
        List<PageItemVo> voList = new ArrayList<>();
        if (entityList != null) {

            entityList.forEach(entity -> {
                PageItemVo vo = convertToVo(entity);
                voList.add(vo);
            });
        }

        BasePageVo<PageItemVo> basePageVo = new BasePageVo<>();
        basePageVo.setList(voList);
        basePageVo.setTotal(tbBasicFeedbackDao.cnt(queryPo)); // 统计总数
        return Result.ok(basePageVo);
    }

    /**
     * 处理要查询的条件
     *
     * @param dto 请求参数
     * @return 处理后的数据
     */
    protected TbBasicFeedbackPo handleQueryData(PageDto dto){
        TbBasicFeedbackPo po = new TbBasicFeedbackPo();
        BeanUtils.copyProperties(dto, po);
        po.setWhereType(dto.getType());
        po.setWhereLikeContent(dto.getContent());
        po.setWhereContact(dto.getContact());
        po.setWhereStatus(dto.getStatus());
        po.setWhereIsDelete(IsDeleteEnum.NO_DELETE.getCode());
        if(dto.getIsCurrentUser() != null && dto.getIsCurrentUser() == 1) {
            po.setWhereCreatedUserId(StpUtil.getLoginIdAsInt());
        }

        po.setPage(dto.getPage(), dto.getPageSize());
        po.setSortList(new BasePageSortList().addSort(StrUtil.isBlank(dto.getSortFiled()) ? "id" : dto.getSortFiled(),
        StrUtil.isBlank(dto.getSortType()) ? "DESC" : dto.getSortType()).getSortList());
        return po;
    }

    /**
     * entity转换成vo
     *
     * @param entity 转换前的Entity对象
     * @return 转换后的vo对象
     */
    private PageItemVo convertToVo(TbBasicFeedback entity){
        PageItemVo vo = new PageItemVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
