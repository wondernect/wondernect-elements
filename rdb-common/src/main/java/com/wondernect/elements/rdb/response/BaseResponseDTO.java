package com.wondernect.elements.rdb.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: BaseResponseDTO
 * Author: chenxun
 * Date: 2020-09-25 15:08
 * Description:
 */
public abstract class BaseResponseDTO extends BaseRDBResponseDTO {

    @Getter @Setter
    @JsonProperty("create_time")
    @ApiModelProperty(notes = "创建时间")
    private Long createTime;

    @Getter @Setter
    @JsonProperty("update_time")
    @ApiModelProperty(notes = "更新时间")
    private Long updateTime;

    @Getter @Setter
    @JsonProperty("create_user")
    @ApiModelProperty(notes = "创建用户")
    private String createUser;

    @Getter @Setter
    @JsonProperty("update_user")
    @ApiModelProperty(notes = "更新用户")
    private String updateUser;

    @Getter @Setter
    @JsonProperty("create_app")
    @ApiModelProperty(notes = "创建应用")
    private String createApp;

    @Getter @Setter
    @JsonProperty("update_app")
    @ApiModelProperty(notes = "更新应用")
    private String updateApp;
}
