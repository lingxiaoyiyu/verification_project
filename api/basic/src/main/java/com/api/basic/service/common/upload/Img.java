package com.api.basic.service.common.upload;

import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import com.api.common.utils.FunctionUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * 上传图片
 */
@Service("UploadImgServiceImpl")
public class Img extends AbstractUpload {

    @Value("${config.upload.img_max_size:20971520}")
    private long maxFileSize;

    /**
     * 参数检查
     */
    public void check(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ServerException("文件为空");
        }

        // 获取文件扩展名
        String extension = getExtension(Objects.requireNonNull(file.getOriginalFilename()));

        if (!Objects.requireNonNull(file.getContentType()).startsWith("image/")) { // 如果是图片，进行压缩处理
            throw new ServerException("文件类型不正确，只允许上传图片");
        }

        // 使用Set的contains方法检查文件扩展名是否在允许的列表中
        if (!ALLOWED_EXTENSIONS_IMG.contains(extension)) {
            throw new ServerException("文件类型不正确，只允许上传图片");
        }

        long fileSize = file.getSize();
        if (fileSize > maxFileSize) {
            throw new ServerException("文件大小超过" + FunctionUtil.File(maxFileSize) + "限制");
        }
    }

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    public Result<?> service(MultipartFile file) {
        return uploadFile(file);
    }
}
