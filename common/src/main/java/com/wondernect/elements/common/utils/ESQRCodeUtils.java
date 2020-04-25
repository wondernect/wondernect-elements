package com.wondernect.elements.common.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.Random;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ESQRCodeUtils
 * Author: chenxun
 * Date: 2019/6/26 13:24
 * Description:
 */
public final class ESQRCodeUtils {

    private static final Logger logger = LoggerFactory.getLogger(ESQRCodeUtils.class);

    private static final String CHARSET = "UTF-8";

    private static final String FORMAT = "JPG";

    /**
     * 二维码生成
     * @param content 内容
     * @param size 二维码宽高
     * @param logo logo文件
     * @param scale logo文件宽高为二维码图片的几分之一
     * @param needCompress 是否压缩
     */
    private static BufferedImage createImage(String content, int size, File logo, int scale,  boolean needCompress) {
        BufferedImage bufferedImage = createImage(content, size);
        return insertImage(bufferedImage, size, logo, scale, needCompress);
    }

    /**
     * 二维码生成
     * @param content 内容
     * @param size 二维码宽高
     * @param logo logo文件
     * @param scale logo文件宽高为二维码图片的几分之一
     * @param needCompress 是否压缩
     */
    private static BufferedImage createImage(String content, int size, URL logo, int scale,  boolean needCompress) {
        BufferedImage bufferedImage = createImage(content, size);
        return insertImage(bufferedImage, size, logo, scale, needCompress);
    }

    /**
     * 二维码生成
     * @param content 内容
     * @param size 二维码宽高
     */
    private static BufferedImage createImage(String content, int size) {
        BufferedImage bufferedImage;
        try {
            Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
            hints.put(EncodeHintType.MARGIN, 1);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, size, size, hints);
            int qrwidth = bitMatrix.getWidth();
            int qrheight = bitMatrix.getHeight();
            bufferedImage = new BufferedImage(qrwidth, qrheight, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < qrwidth; x++) {
                for (int y = 0; y < qrheight; y++) {
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }
        } catch (Exception e) {
            logger.error("二维码初始化参数异常:{}", e.getLocalizedMessage(), e);
            bufferedImage = null;
        }
        return bufferedImage;
    }

    /**
     * 插入LOGO
     * @param bufferedImage 二维码图片
     * @param size 二维码宽高
     * @param logo LOGO图片
     * @param scale logo文件宽高为二维码图片的几分之一
     * @param needCompress 是否压缩
     */
    private static BufferedImage insertImage(BufferedImage bufferedImage, int size, File logo, int scale, boolean needCompress) {
        Image logoImage;
        try {
            logoImage = ImageIO.read(logo);
        } catch (IOException e) {
            logger.error("logo文件读取失败");
            logoImage = null;
        }
        if (ESObjectUtils.isNull(logoImage)) {
            logger.error("logo文件读取为空,直接返回原二维码文件");
            return bufferedImage;
        }
        insertImage(bufferedImage, size, logoImage, scale, needCompress);
        return bufferedImage;
    }

    /**
     * 插入LOGO
     * @param bufferedImage 二维码图片
     * @param size 二维码宽高
     * @param logo LOGO图片
     * @param scale logo文件宽高为二维码图片的几分之一
     * @param needCompress 是否压缩
     */
    private static BufferedImage insertImage(BufferedImage bufferedImage, int size, URL logo, int scale, boolean needCompress) {
        Image logoImage;
        try {
            logoImage = ImageIO.read(logo);
        } catch (IOException e) {
            logger.error("logo链接读取失败");
            logoImage = null;
        }
        if (ESObjectUtils.isNull(logoImage)) {
            logger.error("logo链接读取为空,直接返回原二维码文件");
            return bufferedImage;
        }
        insertImage(bufferedImage, size, logoImage, scale, needCompress);
        return bufferedImage;
    }

    /**
     * 插入LOGO
     * @param bufferedImage 二维码图片
     * @param size 二维码宽高
     * @param logoImage LOGO图片
     * @param scale logo文件宽高为二维码图片的几分之一
     * @param needCompress 是否压缩
     */
    private static void insertImage(BufferedImage bufferedImage, int size, Image logoImage, int scale, boolean needCompress) {
        int logoImageWidth = bufferedImage.getWidth() / scale;
        int logoImageHeight = bufferedImage.getWidth() / scale;
        if (needCompress) {
            Image image = logoImage.getScaledInstance(logoImageWidth, logoImageHeight, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(logoImageWidth, logoImageHeight, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
            logoImage = image;
        }
        // 插入LOGO
        Graphics2D graph = bufferedImage.createGraphics();
        // graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = (size - logoImageWidth) / 2;
        int y = (size - logoImageHeight) / 2;
        graph.drawImage(logoImage, x, y, logoImageWidth, logoImageHeight, null);
        Shape shape = new RoundRectangle2D.Float(x, y, logoImageWidth, logoImageHeight, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * 给二维码下方附加说明文字
     * @param title 标题
     * @param detail 简介
     * @param qrcodeImage 二维码图片
     * @param destFilePath 最终图片文件地址
     * @param width 最终图片宽
     * @param height 最终图片高
     */
    private static void createImageText(
            String title, Color titleColor, int titleFont,
            String detail, Color detailColor, int detailFont,
            BufferedImage qrcodeImage,
            String destFilePath,
            int width, int height
    ) throws IOException {
        // 背景图片绘制
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        // 二维码图片绘制
        g.drawImage(qrcodeImage, 0, 0, null);
        // 消除锯齿
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        //计算title文字位置
        int startTitleX = (width - (titleFont * title.length()))/ 2 + titleFont*2;
        int startTitleY = height - (height - width) / 3 * 2;
        g.setColor(titleColor);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, titleFont));
        g.drawString(title, startTitleX, startTitleY);
        //计算detail文字位置
        int startDetailX = (width - (detailFont * detail.length()))/ 2 + detailFont*2;
        int startDetailY = height - (height - width) / 3;
        g.setColor(detailColor);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, detailFont));
        g.drawString(detail, startDetailX, startDetailY);
        g.dispose();
        ImageIO.write(bufferedImage, FORMAT, new File(destFilePath));
    }

    /**
     * 给二维码下方附加说明文字
     * @param title 标题
     * @param detail 简介
     * @param qrcodeImage 二维码图片
     * @param outputStream 最终图片输出流
     * @param width 最终图片宽
     * @param height 最终图片高
     */
    private static void createImageText(
            String title, Color titleColor, int titleFont,
            String detail, Color detailColor, int detailFont,
            BufferedImage qrcodeImage,
            OutputStream outputStream,
            int width, int height
    ) throws IOException {
        // 背景图片绘制
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        // 二维码图片绘制
        g.drawImage(qrcodeImage, 0, 0, null);
        // 消除锯齿
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        //计算title文字位置
        int startTitleX = (width - (titleFont * title.length()))/ 2 + titleFont*2;
        int startTitleY = height - (height - width) / 3 * 2;
        g.setColor(titleColor);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, titleFont));
        g.drawString(title, startTitleX, startTitleY);
        //计算detail文字位置
        int startDetailX = (width - (detailFont * detail.length()))/ 2 + detailFont*2;
        int startDetailY = height - (height - width) / 3;
        g.setColor(detailColor);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, detailFont));
        g.drawString(detail, startDetailX, startDetailY);
        g.dispose();
        ImageIO.write(bufferedImage, FORMAT, outputStream);
    }

    /**
     * 生成二维码(内嵌LOGO)
     * 调用者指定二维码文件名
     * @param content 内容
     * @param size 二维码宽高
     * @param logo logo文件
     * @param scale logo文件宽高为二维码图片的几分之一
     * @param destPath 存放目录
     * @param fileName 二维码文件名
     * @param needCompress 是否压缩LOGO
     */
    public static String encodeWithFileLogo(
            String content, int size, File logo, int scale, String destPath, String fileName, boolean needCompress
    ) {
        BufferedImage image = createImage(content, size, logo, scale, needCompress);
        ESFileUtils.createFilePath(destPath);
        if (ESStringUtils.isBlank(fileName)) {
            fileName = new Random().nextInt(99999999) + "." + FORMAT.toLowerCase();
        } else {
            fileName = fileName.substring(0, fileName.indexOf(".") > 0 ? fileName.indexOf(".") : fileName.length()) + "." + FORMAT.toLowerCase();
        }
        String filePath = destPath + "/" + fileName;
        try {
            ImageIO.write(image, FORMAT, new File(filePath));
        } catch (IOException e) {
            logger.error("二维码生成异常:{}", e.getLocalizedMessage(), e);
            filePath = null;
        }
        return filePath;
    }

    /**
     * 生成二维码(内嵌LOGO)
     * 调用者指定二维码文件名
     * @param content 内容
     * @param width 二维码宽
     * @param height 二维码高
     * @param logo logo文件
     * @param scale logo文件宽高为二维码图片的几分之一
     * @param destPath 存放目录
     * @param fileName 二维码文件名
     * @param needCompress 是否压缩LOGO
     */
    public static String encodeWithFileLogoAndText(
            String content, int width, int height, File logo, int scale, String destPath, String fileName, boolean needCompress,
            String title, Color titleColor, int titleFont,
            String detail, Color detailColor, int detailFont
    ) {
        BufferedImage image = createImage(content, width < height ? width : height, logo, scale, needCompress);
        ESFileUtils.createFilePath(destPath);
        if (ESStringUtils.isBlank(fileName)) {
            fileName = new Random().nextInt(99999999) + "." + FORMAT.toLowerCase();
        } else {
            fileName = fileName.substring(0, fileName.indexOf(".") > 0 ? fileName.indexOf(".") : fileName.length()) + "." + FORMAT.toLowerCase();
        }
        String filePath = destPath + "/" + fileName;
        try {
            createImageText(title, titleColor, titleFont, detail, detailColor, detailFont, image, filePath, width < height ? width : height, height > width ? height : width);
        } catch (IOException e) {
            logger.error("二维码生成异常:{}", e.getLocalizedMessage(), e);
            filePath = null;
        }
        return filePath;
    }

    /**
     * 生成二维码(内嵌LOGO) - 输出流
     * 调用者指定二维码文件名
     * @param content 内容
     * @param size 二维码宽高
     * @param logo logo文件
     * @param scale logo文件宽高为二维码图片的几分之一
     * @param needCompress 是否压缩LOGO
     */
    public static void encodeWithFileLogo(
            String content, int size, File logo, int scale, boolean needCompress, OutputStream outputStream
    ) {
        BufferedImage image = createImage(content, size, logo, scale, needCompress);
        try {
            ImageIO.write(image, FORMAT, outputStream);
        } catch (IOException e) {
            logger.error("二维码生成异常:{}", e.getLocalizedMessage(), e);
        }
    }

    /**
     * 生成二维码(内嵌LOGO)
     * 调用者指定二维码文件名
     * @param content 内容
     * @param width 二维码宽
     * @param height 二维码高
     * @param logo logo文件
     * @param scale logo文件宽高为二维码图片的几分之一
     * @param needCompress 是否压缩LOGO
     */
    public static void encodeWithFileLogoAndText(
            String content, int width, int height, File logo, int scale, boolean needCompress, OutputStream outputStream,
            String title, Color titleColor, int titleFont,
            String detail, Color detailColor, int detailFont
    ) {
        BufferedImage image = createImage(content, width < height ? width : height, logo, scale, needCompress);
        try {
            createImageText(title, titleColor, titleFont, detail, detailColor, detailFont, image, outputStream, width < height ? width : height, height > width ? height : width);
        } catch (IOException e) {
            logger.error("二维码生成异常:{}", e.getLocalizedMessage(), e);
        }
    }

    /**
     * 生成二维码(内嵌LOGO)
     * 调用者指定二维码文件名
     * @param content 内容
     * @param size 二维码宽高
     * @param logo logo链接文件
     * @param scale logo文件宽高为二维码图片的几分之一
     * @param destPath 存放目录
     * @param fileName 二维码文件名
     * @param needCompress 是否压缩LOGO
     */
    public static String encodeWithUrlLogo(
            String content, int size, URL logo, int scale, String destPath, String fileName, boolean needCompress
    ) {
        BufferedImage image = createImage(content, size, logo, scale, needCompress);
        ESFileUtils.createFilePath(destPath);
        if (ESStringUtils.isBlank(fileName)) {
            fileName = new Random().nextInt(99999999) + "." + FORMAT.toLowerCase();
        } else {
            fileName = fileName.substring(0, fileName.indexOf(".") > 0 ? fileName.indexOf(".") : fileName.length()) + "." + FORMAT.toLowerCase();
        }
        String filePath = destPath + "/" + fileName;
        try {
            ImageIO.write(image, FORMAT, new File(filePath));
        } catch (IOException e) {
            logger.error("二维码生成异常:{}", e.getLocalizedMessage(), e);
            filePath = null;
        }
        return filePath;
    }

    /**
     * 生成二维码(内嵌LOGO)
     * 调用者指定二维码文件名
     * @param content 内容
     * @param width 二维码宽
     * @param height 二维码高
     * @param logo logo文件
     * @param scale logo文件宽高为二维码图片的几分之一
     * @param destPath 存放目录
     * @param fileName 二维码文件名
     * @param needCompress 是否压缩LOGO
     */
    public static String encodeWithUrlLogoAndText(
            String content, int width, int height, URL logo, int scale, String destPath, String fileName, boolean needCompress,
            String title, Color titleColor, int titleFont,
            String detail, Color detailColor, int detailFont
    ) {
        BufferedImage image = createImage(content, width < height ? width : height, logo, scale, needCompress);
        ESFileUtils.createFilePath(destPath);
        if (ESStringUtils.isBlank(fileName)) {
            fileName = new Random().nextInt(99999999) + "." + FORMAT.toLowerCase();
        } else {
            fileName = fileName.substring(0, fileName.indexOf(".") > 0 ? fileName.indexOf(".") : fileName.length()) + "." + FORMAT.toLowerCase();
        }
        String filePath = destPath + "/" + fileName;
        try {
            createImageText(title, titleColor, titleFont, detail, detailColor, detailFont, image, filePath, width < height ? width : height, height > width ? height : width);
        } catch (IOException e) {
            logger.error("二维码生成异常:{}", e.getLocalizedMessage(), e);
            filePath = null;
        }
        return filePath;
    }

    /**
     * 生成二维码(内嵌LOGO)
     * 调用者指定二维码文件名
     * @param content 内容
     * @param size 二维码宽高
     * @param logo logo链接文件
     * @param scale logo文件宽高为二维码图片的几分之一
     * @param needCompress 是否压缩LOGO
     */
    public static void encodeWithUrlLogo(
            String content, int size, URL logo, int scale, boolean needCompress, OutputStream outputStream
    ) {
        BufferedImage image = createImage(content, size, logo, scale, needCompress);
        try {
            ImageIO.write(image, FORMAT, outputStream);
        } catch (IOException e) {
            logger.error("二维码生成异常:{}", e.getLocalizedMessage(), e);
        }
    }

    /**
     * 生成二维码(内嵌LOGO)
     * 调用者指定二维码文件名
     * @param content 内容
     * @param width 二维码宽
     * @param height 二维码高
     * @param logo logo文件
     * @param scale logo文件宽高为二维码图片的几分之一
     * @param needCompress 是否压缩LOGO
     */
    public static void encodeWithUrlLogoAndText(
            String content, int width, int height, URL logo, int scale, boolean needCompress, OutputStream outputStream,
            String title, Color titleColor, int titleFont,
            String detail, Color detailColor, int detailFont
    ) {
        BufferedImage image = createImage(content, width < height ? width : height, logo, scale, needCompress);
        try {
            createImageText(title, titleColor, titleFont, detail, detailColor, detailFont, image, outputStream, width < height ? width : height, height > width ? height : width);
        } catch (IOException e) {
            logger.error("二维码生成异常:{}", e.getLocalizedMessage(), e);
        }
    }

    /**
     * 生成二维码
     * @param content 内容
     * @param size 二维码宽高
     * @param destPath 存储地址
     */
    public static String encode(
            String content, int size, String destPath, String fileName
    ) {
        BufferedImage image = createImage(content, size);
        ESFileUtils.createFilePath(destPath);
        if (ESStringUtils.isBlank(fileName)) {
            fileName = new Random().nextInt(99999999) + "." + FORMAT.toLowerCase();
        } else {
            fileName = fileName.substring(0, fileName.indexOf(".") > 0 ? fileName.indexOf(".") : fileName.length()) + "." + FORMAT.toLowerCase();
        }
        String filePath = destPath + "/" + fileName;
        try {
            ImageIO.write(image, FORMAT, new File(filePath));
        } catch (IOException e) {
            logger.error("二维码生成异常:{}", e.getLocalizedMessage(), e);
            filePath = null;
        }
        return filePath;
    }

    /**
     * 生成二维码
     * 调用者指定二维码文件名
     * @param content 内容
     * @param width 二维码宽
     * @param height 二维码高
     * @param destPath 存放目录
     * @param fileName 二维码文件名
     */
    public static String encodeWithText(
            String content, int width, int height, String destPath, String fileName,
            String title, Color titleColor, int titleFont,
            String detail, Color detailColor, int detailFont
    ) {
        BufferedImage image = createImage(content, width < height ? width : height);
        ESFileUtils.createFilePath(destPath);
        if (ESStringUtils.isBlank(fileName)) {
            fileName = new Random().nextInt(99999999) + "." + FORMAT.toLowerCase();
        } else {
            fileName = fileName.substring(0, fileName.indexOf(".") > 0 ? fileName.indexOf(".") : fileName.length()) + "." + FORMAT.toLowerCase();
        }
        String filePath = destPath + "/" + fileName;
        try {
            createImageText(title, titleColor, titleFont, detail, detailColor, detailFont, image, filePath, width < height ? width : height, height > width ? height : width);
        } catch (IOException e) {
            logger.error("二维码生成异常:{}", e.getLocalizedMessage(), e);
            filePath = null;
        }
        return filePath;
    }

    /**
     * 生成二维码
     * @param content 内容
     * @param size 二维码宽高
     */
    public static void encode(
            String content, int size, OutputStream outputStream
    ) {
        BufferedImage image = createImage(content, size);
        try {
            ImageIO.write(image, FORMAT, outputStream);
        } catch (IOException e) {
            logger.error("二维码生成异常:{}", e.getLocalizedMessage(), e);
        }
    }

    /**
     * 生成二维码(内嵌LOGO)
     * 调用者指定二维码文件名
     * @param content 内容
     * @param width 二维码宽
     * @param height 二维码高
     */
    public static void encodeWithText(
            String content, int width, int height, OutputStream outputStream,
            String title, Color titleColor, int titleFont,
            String detail, Color detailColor, int detailFont
    ) {
        BufferedImage image = createImage(content, width < height ? width : height);
        try {
            createImageText(title, titleColor, titleFont, detail, detailColor, detailFont, image, outputStream, width < height ? width : height, height > width ? height : width);
        } catch (IOException e) {
            logger.error("二维码生成异常:{}", e.getLocalizedMessage(), e);
        }
    }

    /**
     * 解析二维码
     * @param file 二维码图片路径
     */
    public static String decodeFile(File file) {
        String qrcodeText;
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            if (ESObjectUtils.isNull(bufferedImage)) {
                logger.error("二维码文件读取结果为空");
                return null;
            }
            qrcodeText = decodeQRCode(bufferedImage);
        } catch (Exception e) {
            logger.error("二维码文件解密异常:{}", e.getLocalizedMessage(), e);
            qrcodeText = null;
        }
        return qrcodeText;
    }

    /**
     * 解析二维码
     * @param url 二维码图片链接
     */
    public static String decodeUrl(URL url) {
        String qrcodeText;
        try {
            BufferedImage bufferedImage = ImageIO.read(url);
            if (ESObjectUtils.isNull(bufferedImage)) {
                logger.error("二维码链接读取结果为空");
                return null;
            }
            qrcodeText = decodeQRCode(bufferedImage);
        } catch (Exception e) {
            logger.error("二维码链接解密异常:{}", e.getLocalizedMessage(), e);
            qrcodeText = null;
        }
        return qrcodeText;
    }

    private static String decodeQRCode(BufferedImage bufferedImage) {
        String qrcodeText;
        try {
            BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Hashtable<DecodeHintType, Object> hints = new Hashtable<>();
            hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
            Result result= new MultiFormatReader().decode(bitmap, hints);
            qrcodeText = result.getText();
        } catch (Exception e) {
            logger.error("二维码解密异常:{}", e.getLocalizedMessage(), e);
            qrcodeText = null;
        }
        return qrcodeText;
    }

    public static void main(String[] args) throws Exception {
        String destPath = "C:\\Users\\Administrator\\Desktop";
        // String destPath = "/Users/cxhome/Desktop";
        //含Logo，指定二维码图片名
        encode(
                "http://www.baidu.com",
                300,
                destPath,
                "qrcode.jpg"
        );
        encodeWithText(
                "http://www.baidu.com",
                300,
                400,
                destPath,
                "qrcode_text.jpg",
                "Teamblog",
                Color.BLACK,
                16,
                "扫一扫关注我的Teamblog",
                Color.LIGHT_GRAY,
                13
        );
        System.out.println(decodeFile(
                new File(destPath + File.separator + "qrcode_text.jpg")
        ));
        encodeWithFileLogo(
                "http://www.baidu.com",
                300,
                new File(destPath + File.separator + "1.jpg"),
                6,
                destPath,
                "qrcode_file.jpg",
                true
        );
        encodeWithFileLogoAndText(
                "http://www.baidu.com",
                300,
                400,
                new File(destPath + File.separator + "1.jpg"),
                6,
                destPath,
                "qrcode_file_text.jpg",
                true,
                "Teamblog",
                Color.BLACK,
                16,
                "扫一扫关注我的Teamblog",
                Color.LIGHT_GRAY,
                13

        );
        System.out.println(decodeFile(
                new File(destPath + File.separator + "qrcode_file_text.jpg")
        ));
        encodeWithUrlLogo(
                "http://www.baidu.com",
                300,
                new URL("http://117.34.115.23/cims_file/49841649448255488.jpg"),
                6,
                destPath,
                "qrcode_url.jpg",
                true
        );
        encodeWithUrlLogoAndText(
                "http://www.baidu.com",
                300,
                400,
                new URL("http://117.34.115.23/cims_file/49841649448255488.jpg"),
                6,
                destPath,
                "qrcode_url_text.jpg",
                true,
                "Teamblog",
                Color.BLACK,
                16,
                "扫一扫关注我的Teamblog",
                Color.LIGHT_GRAY,
                13

        );
        System.out.println(decodeFile(
                new File(destPath + File.separator + "qrcode_url_text.jpg")
        ));
    }
}
