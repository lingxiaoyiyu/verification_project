package com.api.basic.service.sys.s3.file;

import com.api.basic.data.dto.sys.s3.file.DeleteDto;
import com.api.basic.service.sys.s3.S3ClientUtil;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;

/**
 * 删除文件服务
 */
@Service("BasicS3FileDeleteServiceImpl")
public class Delete extends AbstractService {

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
    public void check(DeleteDto dto) {
        if (dto.getKey() == null || dto.getKey().trim().isEmpty()) {
            throw new ServerException("文件路径不能为空");
        }
    }

    /**
     * 删除文件服务
     *
     * @param dto 请求参数
     * @return 删除结果
     */
    public Result<?> service(DeleteDto dto) {
        String key = dto.getKey();

        try {
            // 1. 初始化 S3 客户端
            S3Client s3 = S3ClientUtil.createS3Client(selfS3Endpoint, selfS3AccessKey, selfS3SecretKey);

            // 2. 删除文件
            DeleteObjectRequest request = DeleteObjectRequest.builder()
                    .bucket(selfS3Bucket)
                    .key(key)
                    .build();

            DeleteObjectResponse response = s3.deleteObject(request);

            // 3. 关闭 S3 客户端
            s3.close();

            return Result.ok("文件删除成功");
        } catch (Exception e) {
            throw new ServerException("文件删除失败", e);
        }
    }
}