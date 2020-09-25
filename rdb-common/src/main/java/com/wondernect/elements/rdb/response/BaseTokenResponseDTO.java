package com.wondernect.elements.rdb.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: BaseCodeResponseDTO
 * Author: chenxun
 * Date: 2020-09-25 15:19
 * Description:
 */
public abstract class BaseTokenResponseDTO extends BaseResponseDTO {

    @Getter @Setter
    @JsonProperty("token")
    @ApiModelProperty(notes = "token对象唯一标识")
    private String token;
}
