package com.api.basic.service.common.verifyCode;

import cn.hutool.core.util.StrUtil;
import com.api.basic.data.dto.common.verifyCode.EmailCodeDto;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import com.api.common.utils.FunctionUtil;
import com.api.common.utils.RedisCache;
import com.api.common.utils.UUID;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 获取邮箱验证码
 */
@Service("VerifyCodeEmailServiceImpl")
@RequiredArgsConstructor
public class Email extends AbstractService {

    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private RedisCache redisCache; //存入redis中
    @Value("${debug:false}")
    private boolean debug;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(EmailCodeDto dto) {
        dto.setEmail(StrUtil.trim(dto.getEmail()));
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    public Result<?> service(EmailCodeDto dto) {
        Map<String, String> data = new HashMap<>();
        // 保存验证码信息
        String verifyKey = UUID.randomUUID().toString(true);

        // 保存验证码信息
        try {
            String code = FunctionUtil.randomStr(6, false, false, true);
            if (debug) {
                code = "123456"; // 测试阶段，设置默认值
            }
            redisCache.setCacheObject(verifyKey, code, 30, TimeUnit.MINUTES);

            sendEmail(dto.getEmail(), "注册验证码", "<h1>您的验证码：</h1>" + code + "<h3>有效期5分钟<h3>");

            data.put("key", verifyKey);
        } catch (Exception e) {
            throw new ServerException("发送失败", e);
        }

        return Result.ok("发送成功", data);
    }

    /**
     * 发送 HTML 邮件的方法。
     *
     * @param to          收件人的邮箱地址
     * @param subject     邮件主题
     * @param htmlContent 邮件的 HTML 内容
     * @throws ServerException 如果发送过程中出现错误
     */
    public void sendEmail(String to, String subject, String htmlContent) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true); // 设置为true表示支持多部分消息

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true); // 第二个参数设置为true表示发送HTML内容
        mailSender.send(message);
    }
}
