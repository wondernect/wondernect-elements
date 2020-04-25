package com.wondernect.elements.common.utils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ESImageUtils
 * Author: chenxun
 * Date: 2019/6/24 17:34
 * Description: 图片工具类
 */
public final class ESImageUtils {

    private static final Logger logger = LoggerFactory.getLogger(ESImageUtils.class);

    private static final Color[] colors = new Color[]{Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED};

    public static Color getRandomColor() {
        Random random = new Random();
        int index = random.nextInt(11);
        return colors[index];
    }

    /**
     * 创建字符串图片 - 返回输入流
     * @param text 字符串
     * @param width 背景宽度
     * @param height 背景高度
     * @param backgroundColor 背景颜色(null=随机)
     * @param textColor 字符颜色(null=随机)
     * @return BufferedImage
     */
    public static InputStream createTextImageInputStream(String text, int width, int height, Color backgroundColor, Color textColor, String formatName) {
        return createTextImageInputStream(
                text,
                width,
                height,
                backgroundColor,
                5,
                true,
                null,
                true,
                textColor,
                Font.SANS_SERIF,
                Font.PLAIN,
                0.8f,
                0.01f,
                formatName
        );
    }

    /**
     * 创建字符串图片 - 返回图片
     * @param text 字符串
     * @param width 背景宽度
     * @param height 背景高度
     * @param backgroundColor 背景颜色(null=随机)
     * @param textColor 字符颜色(null=随机)
     * @return BufferedImage
     */
    public static BufferedImage createTextImage(String text, int width, int height, Color backgroundColor, Color textColor) {
        return createTextImage(
                text,
                width,
                height,
                backgroundColor,
                5,
                true,
                null,
                true,
                textColor,
                Font.SANS_SERIF,
                Font.PLAIN,
                0.8f,
                0.01f
        );
    }

    /**
     * 创建字符串图片 - 返回输入流
     * @param text 字符串
     * @param width 背景宽度
     * @param height 背景高度
     * @param backgroundColor 背景颜色(null=随机)
     * @param lineCount 干扰线条数
     * @param straightLine 干扰线是否直线
     * @param lineColor 干扰线颜色(null=随机)
     * @param randomLocation 每个字符高低是否随机
     * @param textColor 字符颜色(null=随机)
     * @param textFontName 字符字体
     * @param textFontStyle 字符字体风格
     * @param textFontSizeScale  字体大小为图片高度的百分比 0 - 1
     * @param yawpRate 噪声率(添加噪点)
     * @return BufferedImage
     */
    public static InputStream createTextImageInputStream(
            String text, int width, int height,
            Color backgroundColor,
            int lineCount, Boolean straightLine, Color lineColor,
            Boolean randomLocation, Color textColor, String textFontName, int textFontStyle, float textFontSizeScale,
            float yawpRate, String formatName
    ) {
        BufferedImage bufferedImage = createTextImage(text, width, height, backgroundColor, lineCount, straightLine, lineColor, randomLocation, textColor, textFontName, textFontStyle, textFontSizeScale, yawpRate);
        InputStream inputStream;
        try {
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(bs);
            ImageIO.write(bufferedImage, formatName, imageOutputStream);
            inputStream = new ByteArrayInputStream(bs.toByteArray());
        } catch (IOException e) {
            logger.error("创建text图片异常(转输入流):{}", e.getLocalizedMessage(), e);
            inputStream = null;
        }
        return inputStream;
    }

    /**
     * 创建字符串图片
     * @param text 字符串
     * @param width 背景宽度
     * @param height 背景高度
     * @param backgroundColor 背景颜色(null=随机)
     * @param lineCount 干扰线条数
     * @param straightLine 干扰线是否直线
     * @param lineColor 干扰线颜色(null=随机)
     * @param randomLocation 每个字符高低是否随机
     * @param textColor 字符颜色(null=随机)
     * @param textFontName 字符字体
     * @param textFontStyle 字符字体风格
     * @param textFontSizeScale  字体大小为图片高度的百分比 0 - 1
     * @param yawpRate 噪声率(添加噪点)
     * @return BufferedImage
     */
    public static BufferedImage createTextImage(
            String text, int width, int height,
            Color backgroundColor,
            int lineCount, Boolean straightLine, Color lineColor,
            Boolean randomLocation, Color textColor, String textFontName, int textFontStyle, float textFontSizeScale,
            float yawpRate
    ) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        Random random = new Random();
        graphics.setColor(ESObjectUtils.isNull(backgroundColor) ? getRandomColor() : backgroundColor);
        graphics.fillRect(0, 0, width, height);
        if(lineCount > 0){
            int x = random.nextInt(4), y = 0;
            int x1 = width - random.nextInt(4), y1 = 0;
            for(int i=0; i<lineCount; i++) {
                graphics.setColor(ESObjectUtils.isNull(lineColor) ? getRandomColor() : lineColor);
                y = random.nextInt(height - random.nextInt(4));
                y1 = random.nextInt(height - random.nextInt(4));
                if (straightLine) {
                    graphics.drawLine(x, y, x1, y1);
                } else {
                    graphics.drawOval(x, y, x1, y1);
                }
            }
        }
        int fsize = (int)(height * 0.8);
        int fx = 0;
        int fy = fsize;
        graphics.setFont(
                new Font(
                        ESStringUtils.isBlank(textFontName) ? Font.SANS_SERIF : textFontName,
                        textFontStyle <= 0 ? Font.PLAIN : textFontStyle,
                        textFontSizeScale <= 0 ? fsize : (int) (height * textFontSizeScale)
                )
        );
        for(int i=0; i<text.length(); i++){
            fy = randomLocation ? (int)((Math.random() * 0.3 + 0.6) * height) : fy;
            graphics.setColor(ESObjectUtils.isNull(textColor) ? getRandomColor() : textColor);
            graphics.drawString(text.charAt(i) + "", fx, fy);
            fx += (width / text.length()) * (Math.random() * 0.3 + 0.8);
        }
        int area = (int) (yawpRate * width * height);
        for (int i = 0; i < area; i++) {
            int xxx = random.nextInt(width);
            int yyy = random.nextInt(height);
            int rgb = getRandomColor().getRGB();
            image.setRGB(xxx, yyy, rgb);
        }
        graphics.dispose();
        return image;
    }

    /**
     * 图片缩略图 - 按照大小进行缩放
     * @param originImageFilePath 原始图片文件路径
     * @param thumbImageFilePath 目标缩略图文件路径
     * @param width 缩略图宽度
     * @param height 缩略图高度
     * @param keepAspectRatio 是否按照比例缩放
     * @param quality 图片的质量，值也是在0到1，越接近于1质量越好，越接近于0质量越差
     * @return 目标图片文件地址
     */
    public static String createThumbImage(String originImageFilePath, String thumbImageFilePath, int width, int height, boolean keepAspectRatio, double quality) {
        try {
            Thumbnails.of(originImageFilePath).size(width, height).keepAspectRatio(keepAspectRatio).outputQuality(quality).toFile(thumbImageFilePath);
        } catch (IOException e) {
            logger.error("创建缩略图文件(大小缩放)异常:{}", e.getLocalizedMessage(), e);
            thumbImageFilePath = null;
        }
        return thumbImageFilePath;
    }

    /**
     * 图片缩略图 - 按照比例进行缩放
     * @param originImageFilePath 原始图片文件路径
     * @param thumbImageFilePath 目标缩略图文件路径
     * @param scale 比例，值在0到1之间，1f就是原图大小
     * @param quality 图片的质量，值也是在0到1，越接近于1质量越好，越接近于0质量越差
     * @return 目标图片文件地址
     */
    public static String createThumbImage(String originImageFilePath, String thumbImageFilePath, double scale, double quality) {
        try {
            Thumbnails.of(originImageFilePath).scale(scale).outputQuality(quality).toFile(thumbImageFilePath);
        } catch (IOException e) {
            logger.error("创建缩略图文件(比例缩放)异常:{}", e.getLocalizedMessage(), e);
            thumbImageFilePath = null;
        }
        return thumbImageFilePath;
    }

    /**
     * 图片旋转图
     * @param originImageFilePath 原始图片文件路径
     * @param rotateImageFilePath 目标旋转图文件路径
     * @param rotate 0 - 360
     * @return 目标图片文件地址
     */
    public static String createRotateImage(String originImageFilePath, String rotateImageFilePath, double rotate) {
        try {
            Thumbnails.of(originImageFilePath).rotate(rotate).toFile(rotateImageFilePath);
        } catch (IOException e) {
            logger.error("创建旋转图文件异常:{}", e.getLocalizedMessage(), e);
            rotateImageFilePath = null;
        }
        return rotateImageFilePath;
    }

    /**
     * 图片水印
     * @param originImageFilePath 原始图片文件路径
     * @param watermarkImageFilePath 目标水印图片文件路径
     * @param position 水印位置
     * @param bufferedImage 水印图片(可以自定义文字等生成)
     * @param opacity 水印图片透明度
     * @return 目标图片文件地址
     */
    public static String createWatermarkImage(String originImageFilePath, String watermarkImageFilePath, Position position, BufferedImage bufferedImage, float opacity) {
        try {
            Thumbnails.of(originImageFilePath).watermark(position, bufferedImage, opacity).toFile(watermarkImageFilePath);
        } catch (IOException e) {
            logger.error("创建水印图文件异常:{}", e.getLocalizedMessage(), e);
            watermarkImageFilePath = null;
        }
        return watermarkImageFilePath;
    }

    /**
     * 图片水印
     * @param originImageFilePath 原始图片文件路径
     * @param watermarkImageFilePath 目标水印图片文件路径
     * @param position 水印位置
     * @param watermarkImageFile 水印图片路径
     * @param opacity 水印图片透明度
     * @return 目标图片文件地址
     */
    public static String createWatermarkImage(String originImageFilePath, String watermarkImageFilePath, Position position, String watermarkImageFile, float opacity) {
        try {
            Thumbnails.of(originImageFilePath).watermark(position, ImageIO.read(new File(watermarkImageFile)), opacity).toFile(watermarkImageFilePath);
        } catch (IOException e) {
            logger.error("创建水印图文件异常:{}", e.getLocalizedMessage(), e);
            watermarkImageFilePath = null;
        }
        return watermarkImageFilePath;
    }

    /**
     * 剪裁图片
     * @param originImageFilePath 原始图片文件路径
     * @param clipImageFilePath 目标剪裁图片文件路径
     * @param positions 剪裁位置
     * @param x 自定义剪裁位置x坐标
     * @param y 自定义剪裁位置y坐标
     * @param width 剪裁宽度
     * @param height 剪裁高度
     * @param keepAspectRatio 是否按照比例缩放
     * @return 目标图片文件地址
     */
    public static String createClipImage(String originImageFilePath, String clipImageFilePath, Positions positions, int x, int y, int width, int height, boolean keepAspectRatio) {
        try {
            if (ESObjectUtils.isNotNull(positions)) {
                Thumbnails.of(originImageFilePath).sourceRegion(positions, width, height).keepAspectRatio(keepAspectRatio).toFile(clipImageFilePath);
            } else {
                Thumbnails.of(originImageFilePath).sourceRegion(x, y, width, height).keepAspectRatio(keepAspectRatio).toFile(clipImageFilePath);
            }
        } catch (IOException e) {
            logger.error("创建剪裁图文件异常:{}", e.getLocalizedMessage(), e);
            clipImageFilePath = null;
        }
        return clipImageFilePath;
    }
}
