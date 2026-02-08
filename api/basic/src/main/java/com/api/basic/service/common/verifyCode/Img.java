package com.api.basic.service.common.verifyCode;

import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import com.api.common.utils.RedisCache;
import com.api.common.utils.UUID;
import com.google.code.kaptcha.Producer;
import jakarta.annotation.Resource;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 获取图片验证码
 */
@Service("VerifyCodeImgServiceImpl")
public class Img extends AbstractService {

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCache redisCache; //存入redis中

    @Value("${config.captchaType}")
    private String captchaType;

    @Value("${debug:false}")
    private boolean debug;

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    public Result<?> service() {
        // 保存验证码信息
        String verifyKey = UUID.randomUUID().toString(true);

        String capStr, code = null;
        BufferedImage image = null;

        // 生成验证码
        if ("math".equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            if (debug) {
                capText = "12345";
            }
            if (capText.lastIndexOf("@") > -1) {
                capStr = capText.substring(0, capText.lastIndexOf("@"));
                code = capText.substring(capText.lastIndexOf("@") + 1);
            } else {
                capStr = code = capText;
            }
            image = captchaProducerMath.createImage(capStr);
        } else if ("char".equals(captchaType)) {
            capStr = code = captchaProducer.createText();
            if (debug) {
                capStr = "12345";
            }
            image = captchaProducer.createImage(capStr);
        }

        if (image != null) {
            redisCache.setCacheObject(verifyKey, code, 2, TimeUnit.MINUTES);
            // 转换流信息写出
            FastByteArrayOutputStream os = new FastByteArrayOutputStream();
            try {
                ImageIO.write(image, "png", os);
            } catch (IOException e) {
                throw new ServerException("生成验证码图片失败");
            }
            Map<String, String> data = new HashMap<>();
            data.put("img", "data:image/png;base64," + Base64.encodeBase64String(os.toByteArray()));
            data.put("key", verifyKey);
            return Result.ok(data);
        } else {
            throw new ServerException("生成验证码图片失败");
        }
    }
}
