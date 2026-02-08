package com.api.apps.service.apps.upload;

import cn.hutool.core.util.StrUtil;
import com.api.apps.dao.TbAppsDao;
import com.api.apps.dao.TbAppsVersionDao;
import com.api.apps.data.dto.apps.upload.HBWgtDto;
import com.api.apps.data.entity.TbApps;
import com.api.apps.data.entity.TbAppsVersion;
import com.api.apps.data.po.TbAppsVersionPo;
import com.api.basic.service.common.upload.AbstractUpload;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Map;

/**
 * 更新上传小程序
 *
 * @author 裴金伟
 */
@Service("AppsUploadHBWgtServiceImpl")
@RequiredArgsConstructor
public class HBWgt extends AbstractUpload {

    private final TbAppsDao tbAppsDao;
    private final TbAppsVersionDao tbAppsVersionDao;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(HBWgtDto dto) {
        String appid = tbAppsDao.getFieldByUniAppid(dto.getUniAppId(), TbApps::getId).orElse("");
        if (StrUtil.isBlank(appid)) {
            throw new ServerException("小程序不存在");
        }

        if (tbAppsVersionDao.get(TbAppsVersionPo.builder().whereAppId(appid).whereVersionCode(dto.getVersionCode()).build()) != null) {
            throw new ServerException("版本号已存在");
        }

        if (tbAppsVersionDao.get(TbAppsVersionPo.builder().whereAppId(appid).whereVersionName(dto.getVersionName()).build()) != null) {
            throw new ServerException("版本名称已存在");
        }
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    @Transactional(rollbackFor = Throwable.class)
    public Result<?> service(HBWgtDto dto) {
        String base64File = dto.getFile();

        if (base64File == null || !base64File.startsWith("data:")) {
            return Result.fail("文件格式错误");
        }

        // Step 1: 去掉 data URL 前缀
        String base64Data = base64File.split(",")[1];

        // Step 2: 解码 Base64 数据
        byte[] decodedBytes = Base64.getDecoder().decode(base64Data);

        // Step 3: 构造 MultipartFile
        String originalFilename = "example.wgt"; // 可以从 dto 获取或动态生成
        String contentType = "application/octet-stream"; // 或根据实际类型设置

        MultipartFile file = new MockMultipartFile(
                "file", // 表单字段名，与 @RequestParam("file") 匹配
                originalFilename,
                contentType,
                decodedBytes
        );

        Result<?> result = uploadFile(file);
        if (result.getCode() == 0) {
            String appid = tbAppsDao.getFieldByUniAppid(dto.getUniAppId(), TbApps::getId).orElse("");
            if (StrUtil.isBlank(appid)) {
                throw new ServerException("小程序不存在");
            } else {
                Map<String, String> data = (Map<String, String>) result.getData();
                int status = 3;
                // 确定是否有正式版
                TbAppsVersion oldVersion = tbAppsVersionDao.get(TbAppsVersionPo.builder().whereAppId(appid).whereStatus(3).build());
                if (oldVersion != null) {
                    status = 3;
                    tbAppsVersionDao.update(TbAppsVersionPo.builder().whereId(oldVersion.getId()).status(1).build());
                } else {
                    oldVersion = tbAppsVersionDao.get(TbAppsVersionPo.builder().whereAppId(appid).whereStatus(2).build());
                    if (oldVersion != null) {
                        status = 2;
                        tbAppsVersionDao.update(TbAppsVersionPo.builder().whereId(oldVersion.getId()).status(1).build());
                    }
                }
                if (tbAppsVersionDao.add(TbAppsVersion.builder().appId(appid)
                        .versionCode(dto.getVersionCode())
                        .versionName(dto.getVersionName())
                        .desc(dto.getVersionDesc())
                        .url(data.get("url"))
                        .status(status)
                        .build()) != 1) {
                    throw new ServerException("更新失败");
                }
            }
        }

        return Result.ok("上传成功");
    }
}
