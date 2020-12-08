package com.wondernect.elements.authorize.context;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: AuthorizeData
 * Author: chenxun
 * Date: 2019/4/9 15:25
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizeData implements Serializable {

    private static final long serialVersionUID = 4430953926431473988L;

    @JsonProperty("token")
    @ApiModelProperty(notes = "用户访问令牌")
    private String token;

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "用户标识")
    private String userId;

    @JsonProperty("role")
    @ApiModelProperty(notes = "角色标识")
    private String role;

    @JsonProperty("app_id")
    @ApiModelProperty(notes = "应用标识")
    private String appId;

    @JsonProperty("app_secret")
    @ApiModelProperty(notes = "应用密钥")
    private String appSecret;

    @JsonProperty("request_id")
    @ApiModelProperty(notes = "请求id")
    private String requestId;
}
