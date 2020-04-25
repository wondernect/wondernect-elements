package com.wondernect.elements.file.client.impl;

import com.github.tobato.fastdfs.domain.MetaData;
import com.wondernect.elements.algorithm.snowflake.SnowFlakeAlgorithm;
import com.wondernect.elements.common.utils.*;
import com.wondernect.elements.file.client.LocalFileClient;
import com.wondernect.elements.file.client.config.FileClientConfigProperties;
import com.wondernect.elements.file.client.util.FileClientUtil;
import com.wondernect.elements.file.client.util.FileUploadResult;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: DefaultLocalFileClient
 * Author: chenxun
 * Date: 2019/3/23 13:09
 * Description: local file client
 */
@Service
public class DefaultLocalFileClient implements LocalFileClient {

    private static final Logger logger = LoggerFactory.getLogger(DefaultLocalFileClient.class);

    @Autowired
    private FileClientConfigProperties fileClientConfigProperties;

    @Autowired
    private SnowFlakeAlgorithm snowFlakeAlgorithm;

    @Override
    public FileUploadResult uploadFile(MultipartFile fileMedia, Map<String, String> metaData) {
        String localFilePath;
        if (fileClientConfigProperties.isLocalFileUserDirEnable()) {
            if (ESStringUtils.isNotRealEmpty(fileClientConfigProperties.getLocalFileSavePath())) {
                localFilePath = ESSystemUtils.getUserDir() + File.separator + fileClientConfigProperties.getLocalFileSavePath();
            } else {
                localFilePath = ESSystemUtils.getUserDir();
            }
        } else {
            localFilePath = fileClientConfigProperties.getLocalFileSavePath();
        }
        if (!ESFileUtils.exist(localFilePath)) {
            logger.info("LocalFile本地文件保存路径不存在,开始创建");
            if (!ESFileUtils.createFilePath(localFilePath)) {
                return new FileUploadResult(null, 0L, null, null, null, null, false, "文件上传目录创建失败");
            }
        }
        return uploadLocalFile(fileMedia, metaData, localFilePath, String.valueOf(snowFlakeAlgorithm.getSnowflake().nextId()), false);
    }

    @Override
    public FileUploadResult uploadFile(InputStream inputStream, String fileExt, String customFileName) {
        if (ESObjectUtils.isNull(inputStream)) {
            logger.error("LocalFile上传文件失败:{}", "上传文件为空");
            return new FileUploadResult(null, 0L, null, null, null, null, false, "上传文件为空");
        }
        String localFilePath;
        if (fileClientConfigProperties.isLocalFileUserDirEnable()) {
            if (ESStringUtils.isNotRealEmpty(fileClientConfigProperties.getLocalFileSavePath())) {
                localFilePath = ESSystemUtils.getUserDir() + File.separator + fileClientConfigProperties.getLocalFileSavePath();
            } else {
                localFilePath = ESSystemUtils.getUserDir();
            }
        } else {
            localFilePath = fileClientConfigProperties.getLocalFileSavePath();
        }
        if (!ESFileUtils.exist(localFilePath)) {
            logger.info("LocalFile本地文件保存路径不存在,开始创建");
            if (!ESFileUtils.createFilePath(localFilePath)) {
                return null;
            }
        }
        return uploadLocalFile(inputStream, null, 0, fileExt, null, localFilePath, customFileName, false);
    }

    @Override
    public FileUploadResult uploadImageAndCreateThumbImage(MultipartFile fileMedia, Map<String, String> metaData) {
        String localFilePath;
        if (fileClientConfigProperties.isLocalFileUserDirEnable()) {
            if (ESStringUtils.isNotRealEmpty(fileClientConfigProperties.getLocalFileSavePath())) {
                localFilePath = ESSystemUtils.getUserDir() + File.separator + fileClientConfigProperties.getLocalFileSavePath();
            } else {
                localFilePath = ESSystemUtils.getUserDir();
            }
        } else {
            localFilePath = fileClientConfigProperties.getLocalFileSavePath();
        }
        if (!ESFileUtils.exist(localFilePath)) {
            logger.info("LocalFile本地文件保存路径不存在,开始创建");
            if (!ESFileUtils.createFilePath(localFilePath)) {
                return new FileUploadResult(null, 0L, null, null, null, null, false, "文件上传目录创建失败");
            }
        }
        return uploadLocalFile(fileMedia, metaData, localFilePath, String.valueOf(snowFlakeAlgorithm.getSnowflake().nextId()), true);
    }

    @Override
    public FileUploadResult uploadImageAndCreateThumbImage(InputStream inputStream, String fileExt, String customFileName) {
        if (ESObjectUtils.isNull(inputStream)) {
            logger.error("LocalFile上传文件失败:{}", "上传文件为空");
            return new FileUploadResult(null, 0L, null, null, null, null, false, "上传文件为空");
        }
        if (CollectionUtils.isNotEmpty(fileClientConfigProperties.getValidImageFileExts()) && !fileClientConfigProperties.getValidImageFileExts().contains(fileExt.toLowerCase())) {
            String message = "上传图片格式" + fileExt + "不允许, 允许上传图片格式为:" + fileClientConfigProperties.getValidImageFileExts().toString();
            logger.error("LocalFile上传文件失败:{}", message);
            return null;
        }
        String localFilePath;
        if (fileClientConfigProperties.isLocalFileUserDirEnable()) {
            if (ESStringUtils.isNotRealEmpty(fileClientConfigProperties.getLocalFileSavePath())) {
                localFilePath = ESSystemUtils.getUserDir() + File.separator + fileClientConfigProperties.getLocalFileSavePath();
            } else {
                localFilePath = ESSystemUtils.getUserDir();
            }
        } else {
            localFilePath = fileClientConfigProperties.getLocalFileSavePath();
        }
        if (!ESFileUtils.exist(localFilePath)) {
            logger.info("LocalFile本地文件保存路径不存在,开始创建");
            if (!ESFileUtils.createFilePath(localFilePath)) {
                return null;
            }
        }
        return uploadLocalFile(inputStream, null, 0, fileExt, null, localFilePath, customFileName, true);
    }

    @Override
    public void deleteByFilePath(String... filePaths) {
        if (ESObjectUtils.isNotNull(filePaths)) {
            for (String filePath : filePaths) {
                if (!ESFileUtils.delete(filePath)) {
                    logger.error("LocalFile文件删除失败:{}", filePath);
                }
            }
        }
    }

    @Override
    public String getFileDownloadUrl(String filePath) {
        if (ESStringUtils.isRealEmpty(fileClientConfigProperties.getLocalFileServer())) {
            logger.error("LocalFile服务器地址尚未配置,请先配置");
            return null;
        }
        String subStr =  ESStringUtils.substringAfterLast(filePath, File.separator);
        if (ESStringUtils.isBlank(subStr)) {
            return null;
        }
        return fileClientConfigProperties.getLocalFileServer() + "/" + subStr;
    }

    @Override
    public String getImageThumbUrl(String filePath) {
        if (ESStringUtils.isRealEmpty(fileClientConfigProperties.getLocalFileServer())) {
            logger.error("LocalFile服务器地址尚未配置,请先配置");
            return null;
        }
        String subStr =  ESStringUtils.substringAfterLast(filePath, File.separator);
        if (ESStringUtils.isBlank(subStr)) {
            return null;
        }
        return fileClientConfigProperties.getLocalFileServer() + "/" + subStr;
    }

    private FileUploadResult uploadLocalFile(MultipartFile fileMedia, Map<String, String> metaData, String localFilePath, String customFileName, Boolean createThumbImage) {
        if (ESObjectUtils.isNull(fileMedia)) {
            logger.error("LocalFile上传文件失败:{}", "上传文件为空");
            return new FileUploadResult(null, 0L, null, null, null, null, false, "上传文件为空");
        }
        String fileName = ESStringUtils.subStringValueFromIndexToCharPlace(fileMedia.getOriginalFilename(), 0, ".");
        String fileExt = FilenameUtils.getExtension(fileMedia.getOriginalFilename());
        if (createThumbImage && CollectionUtils.isNotEmpty(fileClientConfigProperties.getValidImageFileExts()) && !fileClientConfigProperties.getValidImageFileExts().contains(fileExt.toLowerCase())) {
            String message = "上传图片格式" + fileExt + "不允许, 允许上传图片格式为:" + fileClientConfigProperties.getValidImageFileExts().toString();
            logger.error("LocalFile上传文件失败:{}", message);
            return new FileUploadResult(fileName, 0L, fileExt, null, null, null, false, message);
        }
        long fileSize = fileMedia.getSize();
        if (fileClientConfigProperties.getLimitUploadSize() > 0 && fileSize >= fileClientConfigProperties.getLimitUploadSize()) {
            String message = "上传文件超过" + fileClientConfigProperties.getLimitUploadSize();
            logger.error("LocalFile上传文件失败:{}", message);
            return new FileUploadResult(fileName, fileSize, fileExt, null, null, null, false, message);
        }
        FileUploadResult fileUploadResult = null;
        InputStream inputStream = null;
        try {
            inputStream = fileMedia.getInputStream();
        } catch (IOException e) {
            logger.error("LocalFile上传文件失败:{}", e.getLocalizedMessage(), e);
            fileUploadResult = new FileUploadResult(fileName, fileSize, fileExt, null, null, null, false, e.getLocalizedMessage());
        }
        if (ESObjectUtils.isNotNull(fileUploadResult)) {
            return fileUploadResult;
        }
        return uploadLocalFile(inputStream, fileName, fileSize, fileExt, metaData, localFilePath, customFileName, createThumbImage);
    }

    private FileUploadResult uploadLocalFile(InputStream inputStream, String fileName, long fileSize, String fileExt, Map<String, String> metaData, String localFilePath, String customFileName, Boolean createThumbImage) {
        Set<MetaData> metaDataSet = FileClientUtil.buildDefaultMetaData(fileName, fileSize, fileExt, metaData);
        localFilePath = localFilePath + File.separator + customFileName + "." + fileExt;
        try {
            ESFileUtils.createFile(localFilePath, inputStream);
        } catch (RuntimeException e) {
            logger.error("LocalFile上传文件失败:{}", e.getLocalizedMessage(), e);
            return new FileUploadResult(fileName, fileSize, fileExt, null, null, null, false, e.getLocalizedMessage());
        }
        if (createThumbImage) {
            String insertValue = "_" + fileClientConfigProperties.getLocalThumbImageWidth() + "x" + fileClientConfigProperties.getLocalThumbImageHeight();
            String thumbFilePath = ESStringUtils.insertStringValueIntoCharPlace(localFilePath, insertValue, ".", 0);
            ESImageUtils.createThumbImage(localFilePath, thumbFilePath, fileClientConfigProperties.getLocalThumbImageWidth(), fileClientConfigProperties.getLocalThumbImageHeight(), false, 1);
            return new FileUploadResult(fileName, fileSize, fileExt, localFilePath, thumbFilePath, FileClientUtil.getMetaData(metaDataSet), true, "LocalFile上传文件成功");
        }
        return new FileUploadResult(fileName, fileSize, fileExt, localFilePath, null, FileClientUtil.getMetaData(metaDataSet), true, "LocalFile上传文件成功");
    }
}
