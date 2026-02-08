package com.api.basic.service.sys.s3;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

/**
 * S3客户端工具类
 */
public class S3ClientUtil {

    /**
     * 创建S3客户端
     *
     * @param endpoint  S3服务端点
     * @param accessKey 访问密钥
     * @param secretKey 秘密密钥
     * @return S3客户端
     */
    public static S3Client createS3Client(String endpoint, String accessKey, String secretKey) {
        return S3Client.builder()
                .endpointOverride(URI.create(endpoint))
                .region(Region.US_EAST_1) // 可写死，RustFS 不校验 region
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(accessKey, secretKey)
                        )
                )
                .forcePathStyle(true) // 关键配置！RustFS 需启用 Path-Style
                .build();
    }
}