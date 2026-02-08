package com.api.basic.service.sys.s3.folder;

import com.api.basic.data.dto.sys.s3.folder.AddDto;
import com.api.basic.service.sys.s3.S3ClientUtil;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

/**
 * 创建文件夹服务
 */
@Service("BasicS3FolderAddServiceImpl")
public class Add extends AbstractService {

    @Value("${selfS3.endpoint}")
    private String selfS3Endpoint;
    @Value("${selfS3.access_key}")
    private String selfS3AccessKey;
    @Value("${selfS3.secret_key}")
    private String selfS3SecretKey;
    @Value("${selfS3.bucket}")
    private String selfS3Bucket;

    /**
     * 参数校验
     *
     * @param dto 请求参数
     */
    public void check(AddDto dto) {
        if (dto.getPath() == null || dto.getPath().trim().isEmpty()) {
            throw new ServerException("文件夹路径不能为空");
        }
    }

    /**
     * 创建文件夹服务
     *
     * @param dto 请求参数
     * @return 创建结果
     */
    public Result<?> service(AddDto dto) {
        String path = dto.getPath();

        try {
            // 1. 初始化 S3 客户端
            S3Client s3 = S3ClientUtil.createS3Client(selfS3Endpoint, selfS3AccessKey, selfS3SecretKey);

            // 2. 确保路径以斜杠结尾
            if (!path.endsWith("/")) {
                path += "/";
            }

            // 3. 创建文件夹（上传一个空对象，key以斜杠结尾）
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(selfS3Bucket)
                    .key(path)
                    .contentType("application/x-directory")
                    .build();

            s3.putObject(request, RequestBody.fromBytes(new byte[0]));

            // 4. 关闭 S3 客户端
            s3.close();

            return Result.ok("文件夹创建成功");
        } catch (Exception e) {
            throw new ServerException("文件夹创建失败", e);
        }
    }
}