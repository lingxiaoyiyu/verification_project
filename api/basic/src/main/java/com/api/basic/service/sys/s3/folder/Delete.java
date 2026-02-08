package com.api.basic.service.sys.s3.folder;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.api.basic.data.dto.sys.s3.folder.DeleteDto;
import com.api.basic.service.sys.s3.S3ClientUtil;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.util.ArrayList;
import java.util.List;

@Service("BasicS3FolderDeleteServiceImpl")
public class Delete extends AbstractService {

    private static final Log log = LogFactory.get();

    @Value("${selfS3.endpoint}")
    private String selfS3Endpoint;
    @Value("${selfS3.access_key}")
    private String selfS3AccessKey;
    @Value("${selfS3.secret_key}")
    private String selfS3SecretKey;
    @Value("${selfS3.bucket}")
    private String selfS3Bucket;

    public void check(DeleteDto dto) {
        if (dto.getPath() == null || dto.getPath().trim().isEmpty()) {
            throw new ServerException("文件夹路径不能为空");
        }
    }

    public Result<?> service(DeleteDto dto) {
        String path = dto.getPath();
        S3Client s3 = null;

        try {
            s3 = S3ClientUtil.createS3Client(selfS3Endpoint, selfS3AccessKey, selfS3SecretKey);

            // 标准化路径格式（确保以/结尾）
            if (!path.endsWith("/")) {
                path += "/";
            }
            log.info("开始删除文件夹及所有内容: {}", path);

            // 删除文件夹及其所有内容
            deleteFolderRecursively(s3, path);

            log.info("文件夹删除成功: {}", path);
            return Result.ok("文件夹删除成功");
        } catch (Exception e) {
            log.error("文件夹删除失败: {}", path, e);
            throw new ServerException("文件夹删除失败", e);
        } finally {
            if (s3 != null) {
                s3.close();
            }
        }
    }

    /**
     * 递归删除文件夹及其所有内容（包括显式文件夹对象）
     */
    private void deleteFolderRecursively(S3Client s3, String folderPath) {
        List<ObjectIdentifier> allObjects = new ArrayList<>();
        List<String> foldersToCheck = new ArrayList<>();
        foldersToCheck.add(folderPath); // 添加当前文件夹路径

        String continuationToken = null;

        // 1. 收集所有对象（包括子文件夹中的对象）
        do {
            ListObjectsV2Request request = ListObjectsV2Request.builder()
                    .bucket(selfS3Bucket)
                    .prefix(folderPath)
                    .continuationToken(continuationToken)
                    .build();

            ListObjectsV2Response response = s3.listObjectsV2(request);

            // 添加所有对象到删除列表
            response.contents().forEach(obj ->
                    allObjects.add(ObjectIdentifier.builder().key(obj.key()).build())
            );

            // 收集发现的子文件夹路径
            response.commonPrefixes().forEach(cp ->
                    foldersToCheck.add(cp.prefix())
            );

            continuationToken = response.nextContinuationToken();
        } while (continuationToken != null);

        // 2. 批量删除所有对象
        if (!allObjects.isEmpty()) {
            log.info("发现 {} 个对象待删除", allObjects.size());
            deleteInBatches(s3, allObjects);
        }

        // 3. 递归删除子文件夹
        for (String folder : foldersToCheck) {
            if (!folder.equals(folderPath)) { // 避免无限递归
                deleteFolderRecursively(s3, folder);
            }
        }

        // 4. 显式删除文件夹对象本身（多种可能形式）
        deleteFolderObject(s3, folderPath);          // 原始路径（带/）
        deleteFolderObject(s3, folderPath.substring(0, folderPath.length() - 1)); // 去掉/
        deleteFolderObject(s3, folderPath + "_$folder$"); // 某些系统使用的特殊标记
    }

    /**
     * 批量删除对象（每批最多1000个）
     */
    private void deleteInBatches(S3Client s3, List<ObjectIdentifier> objects) {
        int batchSize = 1000;
        for (int i = 0; i < objects.size(); i += batchSize) {
            int end = Math.min(i + batchSize, objects.size());
            List<ObjectIdentifier> batch = objects.subList(i, end);

            DeleteObjectsRequest request = DeleteObjectsRequest.builder()
                    .bucket(selfS3Bucket)
                    .delete(software.amazon.awssdk.services.s3.model.Delete.builder().objects(batch).build())
                    .build();

            s3.deleteObjects(request);
            log.info("已删除批次: {}-{}/{}", i, end, objects.size());
        }
    }

    /**
     * 尝试删除文件夹对象（忽略不存在的错误）
     */
    private void deleteFolderObject(S3Client s3, String key) {
        try {
            DeleteObjectRequest request = DeleteObjectRequest.builder()
                    .bucket(selfS3Bucket)
                    .key(key)
                    .build();

            s3.deleteObject(request);
            log.info("已删除文件夹对象: {}", key);
        } catch (Exception e) {
            // 404 Not Found 是正常的，其他错误记录日志
            if (!(e instanceof S3Exception && ((S3Exception) e).statusCode() == 404)) {
                log.warn("删除文件夹对象失败: {} - {}", key, e.getMessage());
            }
        }
    }
}