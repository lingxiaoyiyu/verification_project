package com.api.common.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.api.common.exception.ServerException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URI;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FunctionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FunctionUtil.class);

    public static String uploadFileDomain;
    public static String defaultAvatar;
    private static final MessageSource messageSource;

    static {
        messageSource = SpringUtil.getBean("messageSource");
    }

    /**
     * 生成随机字符创
     *
     * @param length       长度
     * @param capitalStr   是否包含大写字母
     * @param lowercaseStr 是否包含小写字母
     * @param numberStr    是否包含数字
     * @return 随机字符串
     */

    public static String randomStr(int length, boolean capitalStr, boolean lowercaseStr, boolean numberStr) {
        if (length <= 0) {
            throw new ServerException("生成的随机字符串长度必须大于0.");
        }

        StringBuilder charSetBuilder = new StringBuilder();
        if (capitalStr) {
            charSetBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
        if (lowercaseStr) {
            charSetBuilder.append("abcdefghijklmnopqrstuvwxyz");
        }
        if (numberStr) {
            charSetBuilder.append("0123456789");
        }

        if (charSetBuilder.isEmpty()) {
            throw new IllegalArgumentException("生成的随机字符串必须包含大小写字母、数字中的一种或多种");
        }

        char[] chars = charSetBuilder.toString().toCharArray();
        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            char randomChar = chars[random.nextInt(chars.length)];
            sb.append(randomChar);
        }

        return sb.toString();
    }

    /**
     * json字符串转List<Integer>
     *
     * @param str json字符串
     * @return
     * @throws JsonProcessingException
     */
    public static List<Integer> toListIntJosn(String str) throws JsonProcessingException {
        // 创建一个ObjectMapper实例，用于处理JSON
        ObjectMapper mapper = new ObjectMapper();
        // 解析JSON字符串为List<String>
        List<String> roleIdsList = mapper.readValue(str, new TypeReference<List<String>>() {
        });

        // 将List<String>转换为List<Integer>
        return roleIdsList.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    /**
     * 处理头像
     *
     * @param imgUrl 图片地址
     * @return 处理后的图片
     */
    public static String handleAvatar(String imgUrl) {
        if (StrUtil.isBlank(imgUrl)) {
            imgUrl = defaultAvatar;
        }
        return handlerFile(imgUrl);
    }

    /**
     * 处理普通图片
     *
     * @param imgUrl 图片地址
     * @return 处理后的图片
     */
    public static String handleImg(String imgUrl) {
        if (StrUtil.isBlank(imgUrl)) {
            return "";
        }
        return handlerFile(imgUrl);
    }

    /**
     * 处理图片
     *
     * @param imgUrl 图片url
     * @return 处理后的图片
     */
    private static String handlerFile(String imgUrl) {
        if (StrUtil.isBlank(imgUrl)) {
            return "";
        }
        if (!StrUtil.startWith(imgUrl, "http")) { // 如果不是以http开头，则认为是本地图片,返回原地址
            imgUrl = uploadFileDomain + imgUrl;
        }

        if (!StrUtil.isBlank(imgUrl)) {
            // 处理imgUrl，判断是否存在uploads/tmp/。
            if (StrUtil.contains(imgUrl, "uploads/tmp/")) {
                // 如果在，则从url中uploads/tmp/后面的部分，作为path。
                URI url = URI.create(imgUrl);
                String path = StrUtil.sub(url.getPath(), 1, url.getPath().length());
                // 判断在本地环境中文件是否存在。
                if (new File(path).exists()) {
                    // 如果存在，则调用moveImg方法，将文件移动到uploads/目录中，并返回新的地址。
                    String newPath = moveImg(path);
                    // 用newPath替换imgurl中的path
                    imgUrl = StrUtil.replace(imgUrl, path, newPath);
                    imgUrl = StrUtil.replace(imgUrl, "\\", "/");
                }
            }
        }
        return imgUrl;
    }

    /**
     * 处理单个实体对象，将实体中的String类型的null值转换为空字符串
     *
     * @param entity 实体对象
     * @return 转换后的实体对象
     */
    public static <T> T convertNullToString(T entity) {
        if (entity == null) return null;
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                if (field.getType() == String.class) {
                    field.setAccessible(true); // 允许访问私有属性
                    Object value = field.get(entity);
                    if (value == null) {
                        field.set(entity, "");
                    }
                }
            } catch (IllegalAccessException e) {
                LOGGER.error("实体对象数据转换异常", e);
            }
        }
        return entity;
    }

    /**
     * 处理List<实体对象>，将实体中的String类型的null值转换为空字符串
     */
    public static <T> List<T> convertListNullToString(List<T> entities) {
        if (entities == null) return null;

        for (T entity : entities) {
            convertNullToString(entity);
        }
        return entities;
    }

    /**
     * 移动图片
     *
     * @param sourcePath 图片原始地址
     * @return 移动后的地址
     * @throws ServerException 异常对象
     */
    public static String moveImg(String sourcePath) throws ServerException {
        if (Validator.isUrl(sourcePath)) {
            return sourcePath; // Return the original URL if it's not a local file path
        }
        String dateFolder = DateUtil.format(DateUtil.date(), "yyyyMMdd");
        String destinationPath = "uploads/" + dateFolder + "/" + sourcePath.substring(sourcePath.lastIndexOf('/') + 1);

        File destDir = new File(destinationPath.substring(0, destinationPath.lastIndexOf('/')));
        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        File sourceFile = new File(sourcePath);
        File destFile = new File(destinationPath);
        if (sourceFile.renameTo(destFile)) {
            return destFile.getPath();
        } else {
            throw new ServerException("文件移动失败");
        }
    }

    /**
     * 获取当前请求
     * 
     * @return 当前请求
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return null;
        }
        return attributes.getRequest();
    }

    /**
     * 获取客户端IP地址
     *
     * @return IP地址
     */
    public static String getIpAddress() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return "0.0.0.0";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-real-ip");
        }
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 处理可能存在的多个IP，取第一个有效IP
        if (StrUtil.isNotBlank(ip)) {
            String[] ips = ip.split(",");
            for (String tmpIp : ips) {
                tmpIp = tmpIp.trim();
                if (StrUtil.isNotBlank(tmpIp) && !"unknown".equalsIgnoreCase(tmpIp)) {
                    return tmpIp;
                }
            }
        }
        return "0.0.0.0";
    }

    /**
     * 获取客户端UA
     *
     * @return UA
     */
    public static String getUserAgent() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return "";
        }
        return request.getHeader("User-Agent");
    }

    private static final int KB = 1024;
    private static final int MB = KB * 1024;
    private static final int GB = MB * 1024;

    /**
     * 将字节数转换为最合适的单位（KB、MB 或 GB）。
     *
     * @param bytes 字节数
     * @return 格式化的字符串，表示转换后的大小和单位
     */
    public static String File(long bytes) {
        if (bytes >= GB) {
            return format(bytes, GB, "GB");
        } else if (bytes >= MB) {
            return format(bytes, MB, "MB");
        } else if (bytes >= KB) {
            return format(bytes, KB, "KB");
        } else {
            return bytes + " B";
        }
    }

    private static String format(long bytes, long unit, String unitName) {
        double value = (double) bytes / unit;
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(value) + " " + unitName;
    }

    /**
     * 计算年龄
     *
     * @param birthDateStr 生日字符串
     * @return 年龄
     */
    public static int calculateAge(String birthDateStr) {
        if (birthDateStr == null || birthDateStr.trim().isEmpty()) {
            return 0;
        }

        String format = "yyyy-MM-dd"; // 日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate birthDate;
        try {
            birthDate = LocalDate.parse(birthDateStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("无效的日期格式: " + e.getMessage());
        }
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
    }

    /**
     * 将中文字符串转换为其拼音首字母的字符串，全部小写。
     *
     * @param chineseText 中文字符串
     * @return 拼音首字母的字符串
     */
    private static String convertToPinyinInitials(String chineseText) {
        StringBuilder initials = new StringBuilder();
        char[] characters = chineseText.toCharArray();
        for (char ch : characters) {
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(ch);
            if (pinyinArray != null && pinyinArray.length > 0) {
                String pinyin = pinyinArray[0];
                if (!pinyin.isEmpty()) {
                    // 提取首字母并转换为小写
                    initials.append(Character.toLowerCase(pinyin.charAt(0)));
                }
            }
        }

        return initials.toString();
    }

    /**
     * 根据key获取国际化信息
     * 
     * @param key 国际化信息key
     */
    public static String getI18nString(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    /**
     * 获取当前请求语言
     */
    public static String getLanguage() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return "zh-Hans";
        }
        return request.getHeader("Accept-Language");
    }
}
