package com.api.basic.service.sys.s3.file;

import com.api.basic.data.dto.sys.s3.file.GetDto;
import com.api.basic.data.vo.sys.s3.FileInfoVo;
import com.api.basic.service.sys.s3.S3ClientUtil;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectAttributesRequest;
import software.amazon.awssdk.services.s3.model.GetObjectAttributesResponse;
import software.amazon.awssdk.services.s3.model.ObjectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取文件信息服务
 */
@Service("BasicS3FileGetServiceImpl")
public class Get extends AbstractService {

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
    public void check(GetDto dto) {
        if (dto.getKey() == null || dto.getKey().trim().isEmpty()) {
            throw new ServerException("文件路径不能为空");
        }
    }

    /**
     * 获取文件信息服务
     *
     * @param dto 请求参数
     * @return 文件信息
     */
    public Result<?> service(GetDto dto) {
        String key = dto.getKey();

        try {
            // 1. 初始化 S3 客户端
            S3Client s3 = S3ClientUtil.createS3Client(selfS3Endpoint, selfS3AccessKey, selfS3SecretKey);

            // 2. 获取文件属性
        GetObjectAttributesRequest request = GetObjectAttributesRequest.builder()
                .bucket(selfS3Bucket)
                .key(key)
                .objectAttributes(ObjectAttributes.OBJECT_SIZE)
                .build();

        GetObjectAttributesResponse response = s3.getObjectAttributes(request);

        // 3. 构建文件信息
        FileInfoVo fileInfoVo = new FileInfoVo();
        fileInfoVo.setKey(key);
        fileInfoVo.setName(key.substring(key.lastIndexOf("/") + 1));
        fileInfoVo.setSize(response.objectSize());
        
        // 根据文件扩展名猜测MIME类型
        String fileName = fileInfoVo.getName();
        int dotIndex = fileName.lastIndexOf('.');
        String contentType = "application/octet-stream";
        if (dotIndex > 0) {
            String extension = fileName.substring(dotIndex + 1).toLowerCase();
            // 简单的MIME类型映射
            switch (extension) {
                case "jpg":
                case "jpeg":
                    contentType = "image/jpeg";
                    break;
                case "png":
                    contentType = "image/png";
                    break;
                case "gif":
                    contentType = "image/gif";
                    break;
                case "pdf":
                    contentType = "application/pdf";
                    break;
                case "txt":
                    contentType = "text/plain";
                    break;
                case "html":
                case "htm":
                    contentType = "text/html";
                    break;
                case "css":
                    contentType = "text/css";
                    break;
                case "js":
                    contentType = "application/javascript";
                    break;
                case "json":
                    contentType = "application/json";
                    break;
                case "xml":
                    contentType = "application/xml";
                    break;
                case "zip":
                    contentType = "application/zip";
                    break;
                case "rar":
                    contentType = "application/x-rar-compressed";
                    break;
                case "mp3":
                    contentType = "audio/mpeg";
                    break;
                case "mp4":
                    contentType = "video/mp4";
                    break;
                case "doc":
                    contentType = "application/msword";
                    break;
                case "docx":
                    contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
                    break;
                case "xls":
                    contentType = "application/vnd.ms-excel";
                    break;
                case "xlsx":
                    contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                    break;
                case "ppt":
                    contentType = "application/vnd.ms-powerpoint";
                    break;
                case "pptx":
                    contentType = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
                    break;
                default:
                    contentType = "application/octet-stream";
            }
        }
        fileInfoVo.setContentType(contentType);
        
        fileInfoVo.setLastModified(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(response.lastModified().toEpochMilli())));
        fileInfoVo.setEtag(response.eTag());
        fileInfoVo.setFolder(false);
        fileInfoVo.setUrl(accessDomain + "/" + selfS3Bucket + "/" + key);

            // 4. 关闭 S3 客户端
            s3.close();

            return Result.ok("获取文件信息成功", fileInfoVo);
        } catch (Exception e) {
            throw new ServerException("获取文件信息失败", e);
        }
    }
}