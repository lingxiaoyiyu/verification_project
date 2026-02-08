package com.api.basic.controller.sys;

import com.api.common.base.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * S3存储管理相关接口
 */
@RestController
@RequestMapping("/basic/s3/file")
public class S3FileController {

    @Resource(name = "BasicS3FileAddServiceImpl")
    private com.api.basic.service.sys.s3.file.Add addService;

    @Resource(name = "BasicS3FileDeleteServiceImpl")
    private com.api.basic.service.sys.s3.file.Delete deleteService;
    @Resource(name = "BasicS3FileBatchDeleteServiceImpl")
    private com.api.basic.service.sys.s3.file.BatchDelete batchDeleteService;
    @Resource(name = "BasicS3FileGetServiceImpl")
    private com.api.basic.service.sys.s3.file.Get getService;
    @Resource(name = "BasicS3FilePageServiceImpl")
    private com.api.basic.service.sys.s3.file.Page pageService;
    @Resource(name = "BasicS3FolderAddServiceImpl")
    private com.api.basic.service.sys.s3.folder.Add folderAddService;
    @Resource(name = "BasicS3FolderDeleteServiceImpl")
    private com.api.basic.service.sys.s3.folder.Delete folderDeleteService;

    /**
     * 单独上传文件
     *
     * @param file 文件
     * @param key 文件路径
     * @return 上传结果
     */
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<?> add(@RequestParam("file") MultipartFile file, @RequestParam("key") String key) {
        // 如果转换失败，使用原始key
        com.api.basic.data.dto.sys.s3.file.AddDto dto = new com.api.basic.data.dto.sys.s3.file.AddDto();
        dto.setFile(file);
        dto.setKey(key);

        addService.check(dto);
        return addService.service(dto);
    }



    /**
     * 删除文件
     *
     * @param dto 请求参数
     * @return 删除结果
     */
    @PostMapping("/delete")
    public Result<?> delete(@RequestBody @Valid com.api.basic.data.dto.sys.s3.file.DeleteDto dto) {
        deleteService.check(dto);
        return deleteService.service(dto);
    }

    /**
     * 批量删除文件
     *
     * @param dto 请求参数
     * @return 删除结果
     */
    @PostMapping("/batchDelete")
    public Result<?> batchDelete(@RequestBody @Valid com.api.basic.data.dto.sys.s3.file.BatchDeleteDto dto) {
        batchDeleteService.check(dto);
        return batchDeleteService.service(dto);
    }

    /**
     * 获取文件信息
     *
     * @param dto 请求参数
     * @return 文件信息
     */
    @PostMapping("/get")
    public Result<?> get(@RequestBody @Valid com.api.basic.data.dto.sys.s3.file.GetDto dto) {
        getService.check(dto);
        return getService.service(dto);
    }

    /**
     * 查看文件列表
     *
     * @param dto 请求参数
     * @return 文件列表
     */
    @PostMapping("/page")
    public Result<?> page(@RequestBody @Valid com.api.basic.data.dto.sys.s3.file.PageDto dto) {
        pageService.check(dto);
        return pageService.service(dto);
    }

    /**
     * 创建文件夹
     *
     * @param dto 请求参数
     * @return 创建结果
     */
    @PostMapping("/folder/add")
    public Result<?> createFolder(@RequestBody @Valid com.api.basic.data.dto.sys.s3.folder.AddDto dto) {
        folderAddService.check(dto);
        return folderAddService.service(dto);
    }

    /**
     * 删除文件夹
     *
     * @param dto 请求参数
     * @return 删除结果
     */
    @PostMapping("/folder/delete")
    public Result<?> deleteFolder(@RequestBody @Valid com.api.basic.data.dto.sys.s3.folder.DeleteDto dto) {
        folderDeleteService.check(dto);
        return folderDeleteService.service(dto);
    }
}