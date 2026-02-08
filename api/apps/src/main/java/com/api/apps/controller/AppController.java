package com.api.apps.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.api.apps.data.dto.apps.AddDto;
import com.api.apps.data.dto.apps.DeleteDto;
import com.api.apps.data.dto.apps.PageDto;
import com.api.apps.data.dto.apps.get.EntranceMpDto;
import com.api.apps.data.dto.apps.get.MpInfoDto;
import com.api.apps.data.dto.apps.query.MpListDto;
import com.api.apps.data.dto.apps.upload.HBWgtDto;
import com.api.apps.service.apps.Add;
import com.api.apps.service.apps.Delete;
import com.api.apps.service.apps.Page;
import com.api.apps.service.apps.get.EntranceMp;
import com.api.apps.service.apps.get.MpInfo;
import com.api.apps.service.apps.query.MpList;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用相关接口
 */
@RestController
@RequestMapping("/apps")
public class AppController {

    @Resource(name = "AppsAddServiceImpl")
    private Add add;
    @Resource(name = "AppsDeleteServiceImpl")
    private Delete delete;
    @Resource(name = "AppsUpdateInfoServiceImpl")
    private com.api.apps.service.apps.update.Info updateInfo;
    @Resource(name = "AppsUpdateStatusServiceImpl")
    private com.api.apps.service.apps.update.Status updateStatus;
    @Resource(name = "AppsPageServiceImpl")
    private Page page;
    @Resource(name = "GetEntranceMpServiceImpl")
    private EntranceMp getEntranceMp;
    @Resource(name = "AppGetMpInfoServiceImpl")
    private MpInfo getMpInfo;
    @Resource(name = "AppQueryMpListServiceImpl")
    private MpList queryMpList;
    @Resource(name = "AppsUpdateEntranceMpServiceImpl")
    private com.api.apps.service.apps.update.EntranceMp updateEntranceMp;
    @Resource(name = "AppsCategoryQueryTreeServiceImpl")
    private com.api.apps.service.apps.category.QueryTree queryCategory;
    @Resource(name = "AppsUploadHBWgtServiceImpl")
    private com.api.apps.service.apps.upload.HBWgt uploadHBWgt;
    @Resource(name = "AppsVersionPageServiceImpl")
    private com.api.apps.service.apps.version.Page versionPage;
    @Resource(name = "AppsVersionUpdateStatusServiceImpl")
    private com.api.apps.service.apps.version.update.Status updateVersionStatus;
    @Resource(name = "AppsVersionAddServiceImpl")
    private com.api.apps.service.apps.version.Add addVersion;
    @Resource(name = "AppsGetHostNewsServiceImpl")
    private com.api.apps.service.apps.get.HostNews hostNews;

    /**
     * 添加应用
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody @Valid AddDto dto) {
        add.check(dto);
        return add.service(dto);
    }

    /**
     * 删除应用
     */
    @PostMapping("/delete")
    public Result<?> delete(@RequestBody @Valid DeleteDto dto) {
        delete.check(dto);
        return delete.service(dto);
    }

    /**
     * 更新应用
     */
    @PostMapping("/update/info")
    public Result<?> updateInfo(@RequestBody @Valid com.api.apps.data.dto.apps.update.InfoDto dto) {
        updateInfo.check(dto);
        return updateInfo.service(dto);
    }

    /**
     * 更新应用状态
     */
    @PostMapping("/update/status")
    public Result<?> updateStatus(@RequestBody @Valid com.api.apps.data.dto.apps.update.StatusDto dto) {
        updateStatus.check(dto);
        return updateStatus.service(dto);
    }

    /**
     * 获取分页列表
     */
    @PostMapping("/page")
    public Result<?> page(@RequestBody @Valid PageDto dto) {
        page.check(dto);
        return page.service(dto);
    }

    /**
     * 获取入口小程序信息
     */
    @PostMapping("/get/entranceMp")
    @SaIgnore
    public Result<?> getEntranceMp(@RequestBody @Valid EntranceMpDto dto) {
        getEntranceMp.check(dto);
        return getEntranceMp.service(dto);
    }

    /**
     * 获取指定小程序信息
     */
    @PostMapping("/get/mpInfo")
    @SaIgnore
    public Result<?> getMpInfo(@RequestBody @Valid MpInfoDto dto) {
        getMpInfo.check(dto);
        return getMpInfo.service(dto);
    }

    /**
     * 查询小程序列表分页
     */
    @PostMapping("/query/mpList")
    @SaIgnore
    public Result<?> queryMpList(@RequestBody @Valid MpListDto dto) {
        queryMpList.check(dto);
        return queryMpList.service(dto);
    }

    /**
     * 设置入口小程序
     */
    @PostMapping("/update/entranceMp")
    public Result<?> updateEntranceMp(@RequestBody @Valid com.api.apps.data.dto.apps.update.EntranceMpDto dto) {
        updateEntranceMp.check(dto);
        return updateEntranceMp.service(dto);
    }

    /**
     * 获取宿主最新版本
     */
    @PostMapping("/get/hostNews")
    @SaIgnore
    public Result<?> hostNews() {
        hostNews.check();
        return hostNews.service();
    }

    /**
     * 获取分类列表
     */
    @PostMapping("/category/query")
    public Result<?> queryCategory() {
        queryCategory.check();
        return queryCategory.service();
    }

    /**
     * 上传小程序安装包
     */
    @PostMapping("/upload/hbWgt")
    @SaIgnore
    public Result<?> uploadHBWgt(@RequestBody @Valid HBWgtDto dto) {
        uploadHBWgt.check(dto);
        return uploadHBWgt.service(dto);
    }

    /**
     * 获取版本列表
     */
    @PostMapping("/version/page")
    public Result<?> versionPage(@RequestBody @Valid com.api.apps.data.dto.apps.version.PageDto dto) {
        versionPage.check(dto);
        return versionPage.service(dto);
    }

    /**
     * 更新版本状态
     */
    @PostMapping("/version/update/status")
    public Result<?> versionUpdateStatus(@RequestBody @Valid com.api.apps.data.dto.apps.version.update.StatusDto dto) {
        updateVersionStatus.check(dto);
        return updateVersionStatus.service(dto);
    }

    /**
     * 添加应用版本
     */
    @PostMapping("/version/add")
    public Result<?> addVersion(@RequestBody @Valid com.api.apps.data.dto.apps.version.AddDto dto) {
        addVersion.check(dto);
        return addVersion.service(dto);
    }
}
