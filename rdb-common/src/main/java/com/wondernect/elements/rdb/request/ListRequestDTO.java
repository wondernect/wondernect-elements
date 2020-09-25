package com.wondernect.elements.rdb.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: ListRequestDTO
 * Author: chenxun
 * Date: 2020-09-25 15:23
 * Description:
 */
public abstract class ListRequestDTO {

    @Getter @Setter
    @JsonProperty("sort_data_list")
    @ApiModelProperty(notes = "列表请求参数")
    private List<SortData> sortDataList;
}
