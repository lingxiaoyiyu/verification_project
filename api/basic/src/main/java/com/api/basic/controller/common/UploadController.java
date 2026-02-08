package com.api.basic.controller.common;

import cn.dev33.satoken.annotation.SaIgnore;
import com.api.basic.service.common.upload.File;
import com.api.basic.service.common.upload.Img;
import com.api.common.base.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 验证码管理相关接口
 */
@RestController
@RequestMapping("/basic/common/upload")
public class UploadController {

    @Resource(name = "UploadImgServiceImpl")
    private Img img;
    @Resource(name = "UploadFileServiceImpl")
    private File file;

    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    @PostMapping("/img")
    @SaIgnore
    public Result<?> img(@RequestParam("file") MultipartFile file) {
        img.check(file);
        return img.service(file);
    }

    /**
     * 上传文件
     */
    @PostMapping("/file")
    @SaIgnore
    public Result<?> file(@RequestParam("file") MultipartFile files) {
        file.check(files);
        return this.file.service(files);
    }
}
