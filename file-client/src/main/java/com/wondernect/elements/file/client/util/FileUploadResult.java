package com.wondernect.elements.file.client.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: EFileUploadResult
 * Author: chenxun
 * Date: 2018/11/13 14:41
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "文件上传结果")
public class FileUploadResult implements Serializable {

    private static final long serialVersionUID = 251905569103006106L;

    @JsonProperty("file_name")
    @ApiModelProperty(notes = "文件名称")
    private String fileName;

    @JsonProperty("file_size")
    @ApiModelProperty(notes = "文件大小")
    private Long fileSize;

    @JsonProperty("file_ext")
    @ApiModelProperty(notes = "文件扩展名")
    private String fileExt;

    @JsonProperty("file_path")
    @ApiModelProperty(notes = "文件上传后路径")
    private String filePath;

    @JsonProperty("thumb_file_path")
    @ApiModelProperty(notes = "缩略图文件上传后路径")
    private String thumbFilePath;

    @JsonProperty("meta_data")
    @ApiModelProperty(notes = "文件相关备注信息")
    private Map<String, String> metaData;

    @JsonProperty("result")
    @ApiModelProperty(notes = "上传结果")
    private Boolean result;

    @JsonProperty("message")
    @ApiModelProperty(notes = "上传结果描述")
    private String message;
}
