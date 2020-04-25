package com.wondernect.elements.mail.client.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: EMailSendResult
 * Author: chenxun
 * Date: 2018/11/13 14:04
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "邮件发送结果")
public class MailSendResult implements Serializable {

    private static final long serialVersionUID = -5660155004596728092L;

    @JsonProperty("from_address")
    @ApiModelProperty(notes = "邮件发送者")
    private String fromAddress;

    @JsonProperty("from_address")
    @ApiModelProperty(notes = "邮件发送者名称")
    private String fromName;

    @JsonProperty("to_address")
    @ApiModelProperty(notes = "邮件接收者")
    private String toAddress;

    @JsonProperty("subject")
    @ApiModelProperty(notes = "邮件主题")
    private String subject;

    @JsonProperty("content")
    @ApiModelProperty(notes = "邮件内容")
    private String content;

    @JsonProperty("result")
    @ApiModelProperty(notes = "邮件发送结果")
    private Boolean result;

    @JsonProperty("message")
    @ApiModelProperty(notes = "邮件发送结果描述")
    private String message;
}
