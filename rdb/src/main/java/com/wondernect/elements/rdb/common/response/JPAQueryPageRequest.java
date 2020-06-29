package com.wondernect.elements.rdb.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: JPAQueryPageRequest
 * Author: chenxun
 * Date: 2020-06-29 18:47
 * Description:
 */
@Data
@ApiModel(description = "JPAQUERY分页请求对象")
public class JPAQueryPageRequest implements Serializable {

    private static final long serialVersionUID = -3208768155123304533L;

    @JsonProperty("offset")
    @ApiModelProperty(notes = "页码")
    private int offset;

    @JsonProperty("limit")
    @ApiModelProperty(notes = "页数量")
    private int limit;

    public JPAQueryPageRequest() {
    }

    public JPAQueryPageRequest(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }
}
