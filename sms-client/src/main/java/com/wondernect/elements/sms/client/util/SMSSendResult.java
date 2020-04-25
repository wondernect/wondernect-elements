package com.wondernect.elements.sms.client.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: ESMSSendResult
 * Author: chenxun
 * Date: 2018/11/14 9:23
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "短信发送结果")
public class SMSSendResult implements Serializable {

    private static final long serialVersionUID = -7403954345156069018L;

    @JsonProperty("phone_number")
    @ApiModelProperty(notes = "短信接收号码")
    private String phoneNumber;

    @JsonProperty("content")
    @ApiModelProperty(notes = "短信内容")
    private String content;

    @JsonProperty("result")
    @ApiModelProperty(notes = "短信发送结果")
    private Boolean result;

    @JsonProperty("message")
    @ApiModelProperty(notes = "短信发送结果描述")
    private String message;
}
