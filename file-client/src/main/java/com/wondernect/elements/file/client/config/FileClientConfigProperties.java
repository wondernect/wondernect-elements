package com.wondernect.elements.file.client.config;

import com.wondernect.elements.property.source.WondernectPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 2017/10/25.
 * wondernect.com
 * @author sunbeam
 */
@Component
@Primary
@PropertySource(value = {"classpath:application.properties", "classpath:application.yml", "classpath:application.yaml", "classpath:bootstrap.yml", "classpath:bootstrap.yaml"}, ignoreResourceNotFound = true, factory = WondernectPropertySourceFactory.class)
@ConfigurationProperties(prefix = "wondernect.elements.file-client")
public class FileClientConfigProperties implements Serializable {

    private static final long serialVersionUID = 336742434660374111L;

    private long limitUploadSize = 1048576000; // 文件上传大小限制1000MB(1000*1024*1024b)

    private List<String> validImageFileExts = Arrays.asList("png", "jpg", "jpeg", "gif", "bmp", "wbmp"); // 合法的图片格式

    private String fastdfsFileServer; // fastdfs文件服务器地址,eg:http://example.com

    private boolean localFileUserDirEnable = true; // 本地上传文件路径是否与项目在同一级目录,false则表示用户localFileSavePath要写完整路径

    private String localFileSavePath; // 本地文件上传默认保存根路径

    private String localFileServer; // 本地文件访问服务器地址

    private int localThumbImageWidth = 150; // 本地文件默认缩略图宽度

    private int localThumbImageHeight = 150; // 本地文件默认缩略图高度

    public long getLimitUploadSize() {
        return limitUploadSize;
    }

    public void setLimitUploadSize(long limitUploadSize) {
        this.limitUploadSize = limitUploadSize;
    }

    public List<String> getValidImageFileExts() {
        return validImageFileExts;
    }

    public void setValidImageFileExts(List<String> validImageFileExts) {
        this.validImageFileExts = validImageFileExts;
    }

    public String getFastdfsFileServer() {
        return fastdfsFileServer;
    }

    public void setFastdfsFileServer(String fastdfsFileServer) {
        this.fastdfsFileServer = fastdfsFileServer;
    }

    public boolean isLocalFileUserDirEnable() {
        return localFileUserDirEnable;
    }

    public void setLocalFileUserDirEnable(boolean localFileUserDirEnable) {
        this.localFileUserDirEnable = localFileUserDirEnable;
    }

    public String getLocalFileSavePath() {
        return localFileSavePath;
    }

    public void setLocalFileSavePath(String localFileSavePath) {
        this.localFileSavePath = localFileSavePath;
    }

    public String getLocalFileServer() {
        return localFileServer;
    }

    public void setLocalFileServer(String localFileServer) {
        this.localFileServer = localFileServer;
    }

    public int getLocalThumbImageWidth() {
        return localThumbImageWidth;
    }

    public void setLocalThumbImageWidth(int localThumbImageWidth) {
        this.localThumbImageWidth = localThumbImageWidth;
    }

    public int getLocalThumbImageHeight() {
        return localThumbImageHeight;
    }

    public void setLocalThumbImageHeight(int localThumbImageHeight) {
        this.localThumbImageHeight = localThumbImageHeight;
    }
}
