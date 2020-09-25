package com.wondernect.elements.rdb.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: PageRequestDTO
 * Author: chenxun
 * Date: 2020-09-25 15:24
 * Description:
 */
public abstract class PageRequestDTO {

    @Getter @Setter
    @NotNull(message = "分页请求参数不能为空")
    @JsonProperty("page_request_data")
    @ApiModelProperty(notes = "分页请求参数")
    private PageRequestData pageRequestData;
}
