package com.api.basic.service.common.upload;

import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import com.api.common.utils.FunctionUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件
 */
@Service("UploadFileServiceImpl")
public class File extends AbstractUpload {

    @Value("${config.upload.file_max_size:52428800}")
    private long maxFileSize;

    /**
     * 参数检查
     */
    public void check(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ServerException("文件为空");
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
