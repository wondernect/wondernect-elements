package com.wondernect.elements.rdb.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: BaseIDResponseDTO
 * Author: chenxun
 * Date: 2020-09-25 15:16
 * Description:
 */
public abstract class BaseIDResponseDTO<ID extends Serializable> extends BaseResponseDTO {

    @Getter @Setter
    @JsonProperty("id")
    @ApiModelProperty(notes = "对象唯一标识")
    private ID id;
}
