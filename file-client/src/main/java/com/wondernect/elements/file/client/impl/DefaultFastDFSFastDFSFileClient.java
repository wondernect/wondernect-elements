package com.wondernect.elements.file.client.impl;

import com.github.tobato.fastdfs.domain.MetaData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.file.client.FastDFSFileClient;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: FileClientImpl
 * Author: chenxun
 * Date: 2018/11/12 15:06
 * Description:
 */
@Service
public class DefaultFastDFSFastDFSFileClient implements FastDFSFileClient {

    private static final Logger logger = LoggerFactory.getLogger(DefaultFastDFSFastDFSFileClient.class);

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private ThumbImageConfig thumbImageConfig;

    @Autowired
    private FileClientConfigProperties fileClientConfigProperties;

    @Override
    public FileUploadResult uploadFile(MultipartFile fileMedia, Map<String, String> metaData) {
        return uploadFastDFSFile(fileMedia, metaData, false);
    }

    @Override
    public FileUploadResult uploadFile(InputStream inputStream, String fileExt) {
        if (ESObjectUtils.isNull(inputStream)) {
            logger.error("FastDFS上传文件失败:{}", "上传文件为空");
            return new FileUploadResult(null, 0L, null, null, null, null, false, "上传文件为空");
        }
        return uploadFastDFSFile(inputStream, null, 0, fileExt, null, false);
    }

    @Override
    public FileUploadResult uploadImageAndCreateThumbImage(MultipartFile fileMedia, Map<String, String> metaData) {
        return uploadFastDFSFile(fileMedia, metaData, true);
    }

    @Override
    public FileUploadResult uploadImageAndCreateThumbImage(InputStream inputStream, String fileExt) {
        if (ESObjectUtils.isNull(inputStream)) {
            logger.error("FastDFS上传文件失败:{}", "上传文件为空");
            return new FileUploadResult(null, 0L, null, null, null, null, false, "上传文件为空");
        }
        if (CollectionUtils.isNotEmpty(fileClientConfigProperties.getValidImageFileExts()) && !fileClientConfigProperties.getValidImageFileExts().contains(fileExt.toLowerCase())) {
            String message = "上传图片格式" + fileExt + "不允许, 允许上传图片格式为:" + fileClientConfigProperties.getValidImageFileExts().toString();
            logger.error("FastDFS上传文件失败:{}", message);
            return null;
        }
        return uploadFastDFSFile(inputStream, null, 0, fileExt, null, false);
    }

    @Override
    public void deleteByFilePath(String... filePaths) {
        if (ESObjectUtils.isNotNull(filePaths)) {
            for (String filePath : filePaths) {
                try {
                    fastFileStorageClient.deleteFile(filePath);
                } catch (RuntimeException e) {
                    logger.error("FastDFS删除文件失败:{}", e.getLocalizedMessage(), e);
                }
            }
        }
    }

    @Override
    public Map<String, String> getFileMetaData(String filePath) {
        StorePath storePath = null;
        try {
            storePath = StorePath.praseFromUrl(filePath);
        } catch (RuntimeException e) {
            logger.error("FastDFS获取文件扩展信息失败", e);
        }
        return storePath == null ? null : FileClientUtil.getMetaData(fastFileStorageClient.getMetadata(storePath.getGroup(), storePath.getPath()));
    }

    @Override
    public String getFileDownloadUrl(String filePath) {
        if (ESStringUtils.isRealEmpty(fileClientConfigProperties.getFastdfsFileServer())) {
            logger.error("FastDFSF服务器地址尚未配置,请先配置");
            return null;
        }
        return fileClientConfigProperties.getFastdfsFileServer() + "/" + filePath;
    }

    @Override
    public String getImageThumbUrl(String filePath) {
        if (ESStringUtils.isRealEmpty(fileClientConfigProperties.getFastdfsFileServer())) {
            logger.error("FastDFSF服务器地址尚未配置,请先配置");
            return null;
        }
        return fileClientConfigProperties.getFastdfsFileServer() + "/" + filePath;
    }

    private FileUploadResult uploadFastDFSFile(MultipartFile fileMedia, Map<String, String> metaData, Boolean createThumbImage) {
        if (ESObjectUtils.isNull(fileMedia)) {
            logger.error("FastDFS上传文件失败:{}", "上传文件为空");
            return new FileUploadResult(null, 0L, null, null, null, null, false, "上传文件为空");
        }
        String fileName = ESStringUtils.subStringValueFromIndexToCharPlace(fileMedia.getOriginalFilename(), 0, ".");
        String fileExt = FilenameUtils.getExtension(fileMedia.getOriginalFilename());
        if (createThumbImage && CollectionUtils.isNotEmpty(fileClientConfigProperties.getValidImageFileExts()) && !fileClientConfigProperties.getValidImageFileExts().contains(fileExt.toLowerCase())) {
            String message = "上传图片格式" + fileExt + "不允许, 允许上传图片格式为:" + fileClientConfigProperties.getValidImageFileExts().toString();
            logger.error("FastDFS上传文件失败:{}", message);
            return new FileUploadResult(fileName, 0L, fileExt, null, null, null, false, message);
        }
        long fileSize = fileMedia.getSize();
        if (fileClientConfigProperties.getLimitUploadSize() > 0 && fileSize >= fileClientConfigProperties.getLimitUploadSize()) {
            String message = "上传文件超过" + fileClientConfigProperties.getLimitUploadSize();
            logger.error("FastDFS上传文件失败:{}", message);
            return new FileUploadResult(fileName, fileSize, fileExt, null, null, null, false, message);
        }
        FileUploadResult fileUploadResult = null;
        InputStream inputStream = null;
        try {
            inputStream = fileMedia.getInputStream();
        } catch (IOException e) {
            logger.error("FastDFS上传文件失败:", e.getLocalizedMessage(), e);
            fileUploadResult = new FileUploadResult(fileName, fileSize, fileExt, null, null, null, false, e.getLocalizedMessage());
        }
        if (ESObjectUtils.isNotNull(fileUploadResult)) {
            return fileUploadResult;
        }
        return uploadFastDFSFile(inputStream, fileName, fileSize, fileExt, metaData, createThumbImage);
    }

    private FileUploadResult uploadFastDFSFile(InputStream inputStream, String fileName, long fileSize, String fileExt, Map<String, String> metaData, Boolean createThumbImage) {
        Set<MetaData> metaDataSet = FileClientUtil.buildDefaultMetaData(fileName, fileSize, fileExt, metaData);
        StorePath storePath;
        try {
            if (createThumbImage) {
                storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(inputStream, fileSize, fileExt, metaDataSet);
            } else {
                storePath = fastFileStorageClient.uploadFile(inputStream, fileSize, fileExt, metaDataSet);
            }
        } catch (RuntimeException e) {
            logger.error("FastDFS上传文件失败:", e.getLocalizedMessage(), e);
            return new FileUploadResult(fileName, fileSize, fileExt, null, null, null, false, e.getLocalizedMessage());
        }
        if (createThumbImage) {
            String insertValue = "_" + thumbImageConfig.getWidth() + "x" + thumbImageConfig.getHeight();
            String thumbFilePath = ESStringUtils.insertStringValueIntoCharPlace(storePath.getFullPath(), insertValue, ".", 0);
            return new FileUploadResult(fileName, fileSize, fileExt, storePath.getFullPath(), thumbFilePath, FileClientUtil.getMetaData(metaDataSet), true, "FastDFS上传文件成功");
        }
        return new FileUploadResult(fileName, fileSize, fileExt, storePath.getFullPath(), null, FileClientUtil.getMetaData(metaDataSet), true, "FastDFS上传文件成功");
    }
}
