package com.api.basic.service.sys.s3.file;

import com.api.basic.data.dto.sys.s3.file.AddDto;
import com.api.basic.data.vo.sys.s3.UploadResultVo;
import com.api.basic.service.sys.s3.S3ClientUtil;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

/**
 * 单独上传文件服务
 */
@Service("BasicS3FileAddServiceImpl")
public class Add extends AbstractService {

    @Value("${selfS3.endpoint}")
    private String selfS3Endpoint;
    @Value("${selfS3.access_key}")
    private String selfS3AccessKey;
    @Value("${selfS3.secret_key}")
    private String selfS3SecretKey;
    @Value("${selfS3.bucket}")
    private String selfS3Bucket;
    @Value("${selfS3.access_domain}")
    private String accessDomain;

    /**
     * 参数校验
     *
     * @param dto 请求参数
     */
    public void check(AddDto dto) {
        if (dto.getFile() == null || dto.getFile().isEmpty()) {
            throw new ServerException("文件不能为空");
        }
        if (dto.getKey() == null || dto.getKey().trim().isEmpty()) {
            throw new ServerException("文件路径不能为空");
        }
    }

    /**
     * 单独上传文件服务
     *
     * @param dto 请求参数
     * @return 上传结果
     */
    public Result<?> service(AddDto dto) {
        MultipartFile file = dto.getFile();
        String key = dto.getKey();
        try {
            key = new String(key.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new ServerException("文件路径编码错误", e);
        }

        try {
            // 1. 初始化 S3 客户端
            S3Client s3 = S3ClientUtil.createS3Client(selfS3Endpoint, selfS3AccessKey, selfS3SecretKey);

            // 2. 上传文件，Spring MVC已经自动处理了中文参数，无需再次解码
            try (InputStream inputStream = file.getInputStream()) {
                PutObjectRequest request = PutObjectRequest.builder()
                        .bucket(selfS3Bucket)
                        .key(key)
                        .contentType(file.getContentType())
                        .build();

                PutObjectResponse response = s3.putObject(request, RequestBody.fromInputStream(inputStream, file.getSize()));

                // 3. 生成文件 URL
                String fileUrl = accessDomain + "/" + selfS3Bucket + "/" + key;

                // 4. 构建响应结果
                UploadResultVo result = new UploadResultVo();
                result.setKey(key);
                result.setName(Objects.requireNonNull(file.getOriginalFilename()));
                result.setSize(file.getSize());
                result.setUrl(fileUrl);
                result.setEtag(response.eTag());

                return Result.ok("上传成功", result);
            } finally {
                // 关闭 S3 客户端
                if (s3 != null) {
                    s3.close();
                }
            }
        } catch (IOException e) {
            throw new ServerException("文件上传失败", e);
        }
    }
}