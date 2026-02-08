package com.api.basic.service.common.upload;

import cn.hutool.core.date.DateUtil;
import com.aliyun.oss.OSS;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import com.api.common.utils.UUID;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AbstractUpload extends AbstractService {

    protected static final Set<String> ALLOWED_EXTENSIONS_IMG =
            Stream.of("jpg", "jpeg", "png", "gif", "bmp", "svg")
                    .collect(Collectors.toSet());

    private static final String UPLOAD_DIR = "uploads/";
    @Value("${config.api_domain}")
    private String uploadFileDomain;
    @Value("${config.upload.storage_type:local}")
    private String uploadStorageType;
    @Autowired
    private OSS ossClient;
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.bucket-name}")
    private String bucketName;
    @Value("${config.upload.oss_root_dir}")
    private String ossRootDir;
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

    protected Result<?> uploadFile(MultipartFile file) {
        try {
            // 增强文件名的唯一性
            String uniqueFileName = UUID.randomUUID() + "." + getExtension(Objects.requireNonNull(file.getOriginalFilename()));

            String url = "";
            switch (uploadStorageType) {
                case "local":
                    url = local(file, uniqueFileName);
                    break;
                case "aliyunOSS":
                    url = oss(file, uniqueFileName);
                    break;
                case "selfOSS":
                    url = selfOSS(file, uniqueFileName);
                    break;
                default:
                    throw new ServerException("上传失败，请检查配置");
            }

            Map<String, String> datas = new HashMap<>();
            datas.put("url", url);
            datas.put("fileName", uniqueFileName);
            datas.put("fileInfoId", url);
            datas.put("fullUrl", url);
            return Result.ok("上传成功", datas);
        } catch (IOException e) {
            throw new ServerException("上传失败", e);
        }
    }

    /**
     * 本地上传
     *
     * @param file 上传的文件
     * @param uniqueFileName 文件名
     * @return 上传后的url
     * @throws IOException IO异常
     */
    private String local(MultipartFile file, String uniqueFileName) throws IOException {
        String currentDate = DateUtil.format(DateUtil.date(), "yyyyMMdd");
        Path uploadDirPath = Paths.get(UPLOAD_DIR + currentDate + "/");
        Files.createDirectories(uploadDirPath); // 确保目录创建的原子性
        Path path = uploadDirPath.resolve(uniqueFileName); // 拼接文件路径

        // 判断file是不是图片
        if (Objects.requireNonNull(file.getContentType()).startsWith("image/")) { // 如果是图片，进行压缩处理
            // 获取文件扩展名
            String extension = getExtension(Objects.requireNonNull(file.getOriginalFilename()));
            if("svg".equals(extension)) {
                // 如果不是图片，直接写入文件
                Files.write(path, file.getBytes());
            } else {
                // 使用Thumbnailator进行压缩
                Thumbnails.of(file.getInputStream())
                        .scale(1f)
                        .outputQuality(0.8f)
                        .toFile(path.toFile());
            }
        } else {
            // 如果不是图片，直接写入文件
            Files.write(path, file.getBytes());
        }

        return uploadFileDomain + "/" + uploadDirPath.toString().replace("\\", "/") + "/" + uniqueFileName;
    }

    /**
     * 阿里云上传
     *
     * @param file 上传的文件
     * @param uniqueFileName 文件名
     * @return 上传后的url
     */
    private String oss(MultipartFile file, String uniqueFileName) throws IOException {
        String currentDate = DateUtil.format(DateUtil.date(), "yyyyMMdd");
        String objectName = ossRootDir + "/" + currentDate + "/" + uniqueFileName;
        // 上传到OSS
        ossClient.putObject(new com.aliyun.oss.model.PutObjectRequest(
                bucketName,
                objectName,
                file.getInputStream()
        ));

        // 构建OSS上的URL
        return "https://" + bucketName + "." + endpoint + "/" + objectName;
    }

    /**
     * 上传自建对象存储平台
     *
     * @param file 上传的文件
     * @param uniqueFileName 文件名
     * @return 上传后的url
     */
    private String selfOSS(MultipartFile file, String uniqueFileName) throws IOException {
        String currentDate = DateUtil.format(DateUtil.date(), "yyyyMMdd");
        // 1. 初始化 S3 客户端
        S3Client s3 = S3Client.builder()
                .endpointOverride(URI.create(selfS3Endpoint)) // RustFS 地址
                .region(Region.US_EAST_1) // 可写死，RustFS 不校验 region
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(selfS3AccessKey, selfS3SecretKey)
                        )
                )
                .forcePathStyle(true) // 关键配置！RustFS 需启用 Path-Style
                .build();

        // 2. 构造对象键（Key）
        String key = currentDate + "/" + uniqueFileName;

        // 3. 上传文件（使用 MultipartFile 的输入流）
        try (InputStream inputStream = file.getInputStream()) {
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(selfS3Bucket)
                    .key(key)
                    .contentType(file.getContentType())
                    .build();

            PutObjectResponse response = s3.putObject(request, RequestBody.fromInputStream(inputStream, file.getSize()));

            // 4. 生成文件 URL
            String fileUrl = accessDomain + "/" + selfS3Bucket + "/" + key;
            return fileUrl;
        }
    }

    /**
     * 辅助方法：获取文件扩展名
     *
     * @param fileName 文件名
     * @return 扩展名
     */
    protected String getExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex >= 0) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }
}
