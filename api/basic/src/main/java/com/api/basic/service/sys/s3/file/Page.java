package com.api.basic.service.sys.s3.file;

import com.api.basic.data.dto.sys.s3.file.PageDto;
import com.api.basic.data.vo.sys.s3.FileInfoVo;
import com.api.basic.service.sys.s3.S3ClientUtil;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.base.data.vo.BasePageVo;
import com.api.common.exception.ServerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CommonPrefix;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 查看文件列表服务
 */
@Service("BasicS3FilePageServiceImpl")
public class Page extends AbstractService {

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
    public void check(PageDto dto) {
        if (dto.getPage() == null || dto.getPage() < 1) {
            dto.setPage(1);
        }
        if (dto.getSize() == null || dto.getSize() < 1 || dto.getSize() > 100) {
            dto.setSize(20);
        }
    }

    /**
     * 查看文件列表服务
     *
     * @param dto 请求参数
     * @return 文件列表
     */
    public Result<?> service(PageDto dto) {
        String prefix = dto.getPrefix();
        String filename = dto.getFilename();
        Integer page = dto.getPage();
        Integer size = dto.getSize();
        Boolean recursive = dto.getRecursive();

        try {
            // 1. 初始化 S3 客户端
            S3Client s3 = S3ClientUtil.createS3Client(selfS3Endpoint, selfS3AccessKey, selfS3SecretKey);

            // 2. 构建请求
            ListObjectsV2Request.Builder requestBuilder = ListObjectsV2Request.builder()
                    .bucket(selfS3Bucket)
                    .prefix(prefix)
                    .maxKeys(page * size);

            if (!recursive) {
                requestBuilder.delimiter("/");
            }

            ListObjectsV2Request request = requestBuilder.build();
            ListObjectsV2Response response = s3.listObjectsV2(request);

            // 3. 处理结果
            List<FileInfoVo> fileList = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // 添加文件夹
            for (CommonPrefix commonPrefix : response.commonPrefixes()) {
                FileInfoVo folderVo = new FileInfoVo();
                String prefixStr = commonPrefix.prefix();
                folderVo.setKey(prefixStr);
                
                // 提取文件夹名称，处理边界情况
                int lastSlashIndex = prefixStr.lastIndexOf("/");
                int endIndex = prefixStr.length() - 1;
                int startIndex = lastSlashIndex + 1;
                
                // 确保起始索引不大于结束索引
                String folderName = "";
                if (startIndex < endIndex) {
                    folderName = prefixStr.substring(startIndex, endIndex);
                } else if (startIndex == endIndex) {
                    // 处理根目录下的文件夹，如 "test/" 这种情况
                    folderName = prefixStr.substring(0, endIndex);
                } else {
                    // 处理特殊情况，如只有 "/" 或空字符串
                    folderName = prefixStr;
                }
                
                folderVo.setName(folderName);
                folderVo.setSize(0);
                folderVo.setContentType("application/x-directory");
                folderVo.setLastModified(sdf.format(new Date()));
                folderVo.setEtag("");
                folderVo.setFolder(true);
                folderVo.setUrl(accessDomain + "/" + selfS3Bucket + "/" + commonPrefix);
                
                // 文件名过滤
                if (filename == null || filename.isEmpty() || folderName.contains(filename)) {
                    fileList.add(folderVo);
                }
            }

            // 添加文件
            for (S3Object s3Object : response.contents()) {
                // 跳过前缀本身（如果是文件夹）
                if (s3Object.key().equals(prefix)) {
                    continue;
                }

                FileInfoVo fileVo = new FileInfoVo();
                fileVo.setKey(s3Object.key());
                String fileName = s3Object.key().substring(s3Object.key().lastIndexOf("/") + 1);
                fileVo.setName(fileName);
                fileVo.setSize(s3Object.size());
                // 根据文件扩展名猜测MIME类型
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
                fileVo.setContentType(contentType);
                fileVo.setLastModified(sdf.format(new Date(s3Object.lastModified().toEpochMilli())));
                fileVo.setEtag(s3Object.eTag());
                fileVo.setFolder(false);
                fileVo.setUrl(accessDomain + "/" + selfS3Bucket + "/" + s3Object.key());
                
                // 文件名过滤
                if (filename == null || filename.isEmpty() || fileName.contains(filename)) {
                    fileList.add(fileVo);
                }
            }

            // 4. 分页处理
            int startIndex = (page - 1) * size;
            int endIndex = Math.min(startIndex + size, fileList.size());
            List<FileInfoVo> pageList = fileList.subList(startIndex, endIndex);

            // 5. 关闭 S3 客户端
            s3.close();

            // 6. 封装分页结果
            BasePageVo<FileInfoVo> basePageVo = new BasePageVo<>();
            basePageVo.setList(pageList);
            basePageVo.setTotal(fileList.size());

            return Result.ok(basePageVo);
        } catch (Exception e) {
            throw new ServerException("查询文件列表失败", e);
        }
    }
}