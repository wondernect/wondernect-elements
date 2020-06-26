package com.wondernect.elements.file.client;

import com.wondernect.elements.file.client.util.FileUploadResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Map;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: LocalFileClient
 * Author: chenxun
 * Date: 2019/3/23 13:42
 * Description: file client
 */
public interface LocalFileClient {

    /**
     * 上传文件
     * Map<String, String> metaData - 备注文件相关信息
     */
    FileUploadResult uploadFile(MultipartFile fileMedia, String subFileSavePath, Map<String, String> metaData);

    /**
     * 上传文件
     */
    FileUploadResult uploadFile(InputStream inputStream, String subFileSavePath, String fileExt, String customFileName);

    /**
     * 上传并生成缩略图图片
     * Map<String, String> metaData - 备注文件相关信息
     * 支持的图片格式包括"JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP"
     */
    FileUploadResult uploadImageAndCreateThumbImage(MultipartFile fileMedia, String subFileSavePath, Map<String, String> metaData);

    /**
     * 上传并生成缩略图图片
     * Map<String, String> metaData - 备注文件相关信息
     * 支持的图片格式包括"JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP"
     */
    FileUploadResult uploadImageAndCreateThumbImage(InputStream inputStream, String subFileSavePath, String fileExt, String customFileName);

    /**
     * 删除文件(删除原图或缩略图,需要用户依次传入地址)
     */
    void deleteByFilePath(String... filePaths);

    /**
     * 获取文件下载完整url
     */
    String getFileDownloadUrl(String filePath, String subFileSavePath);

    /**
     * 获取图片缩略图下载完整url
     */
    String getImageThumbUrl(String filePath, String subFileSavePath);
}
