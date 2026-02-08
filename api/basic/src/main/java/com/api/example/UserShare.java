package com.api.example;

import cn.hutool.core.date.DateUtil;
import com.api.common.utils.UUID;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * 示例：生成海报
 */
public class UserShare {

    @Value("${config.upload_file_domain}")
    private String fileDomain;
    private static final String UPLOAD_DIR = "uploads/share/";

    /**
     * 生成分享海报图片
     *
     * @return 图片的访问 URL
     * @throws IOException 如果生成图片时发生错误
     * @throws WriterException 如果生成二维码时发生错误
     */
    private String generateShareImage() throws IOException, WriterException {
        String url = "http://xiangqin.sddtjg.com/login?code= ";

        // 生成二维码
        BufferedImage qrCodeImage = generateQRCodeImage(url, 500, 500);

        // 加载背景图片
        BufferedImage backgroundImage = loadBackgroundImage();

        // 创建一个 BufferedImage 对象
        int width = backgroundImage.getWidth();
        int height = backgroundImage.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // 绘制背景图片
        g2d.drawImage(backgroundImage, 0, 0, null);

        // 将二维码绘制到海报图片的居中位置
        int qrCodeX = (width - qrCodeImage.getWidth()) / 2;
        int qrCodeY = (height - qrCodeImage.getHeight()) / 2;
        g2d.drawImage(qrCodeImage, qrCodeX, qrCodeY, null);

        // 释放资源
        g2d.dispose();

        // 保存图片到文件系统
        String currentDate = DateUtil.format(DateUtil.date(), "yyyyMMdd");
        Path uploadDirPath = Paths.get(UPLOAD_DIR + currentDate + "/");
        Files.createDirectories(uploadDirPath); // 确保目录创建的原子性
        // 增强文件名的唯一性
        String uniqueFileName = UUID.randomUUID() +".Jpg" ;
        Path path = uploadDirPath.resolve(uniqueFileName);

        File outputFile = new File(path.toString());
        ImageIO.write(image, "png", outputFile);

        // 返回图片的访问 URL
        return fileDomain + "/" + uploadDirPath.toString().replace("\\", "/") + "/" + uniqueFileName;
    }

    /**
     * 生成二维码图片
     *
     * @param text 二维码内容
     * @param width 二维码宽度
     * @param height 二维码高度
     * @return 二维码图片
     * @throws WriterException 如果生成二维码时发生错误
     */
    private BufferedImage generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);

        // 保存图片到文件系统
        String currentDate = DateUtil.format(DateUtil.date(), "yyyyMMdd");
        Path uploadDirPath = Paths.get(UPLOAD_DIR + currentDate + "/");
        Files.createDirectories(uploadDirPath); // 确保目录创建的原子性

        // 增强文件名的唯一性
        String uniqueFileName = "qr_code.Jpg" ; // TODO可以拼接上用户ID作为唯一性标识
        Path path = uploadDirPath.resolve(uniqueFileName);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        return ImageIO.read(path.toFile());
    }

    /**
     * 加载背景图片
     *
     * @return 背景图片
     * @throws IOException 如果加载图片时发生错误
     */
    private BufferedImage loadBackgroundImage() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/haibao.jpg");
        return ImageIO.read(resource.getInputStream());
    }
}
