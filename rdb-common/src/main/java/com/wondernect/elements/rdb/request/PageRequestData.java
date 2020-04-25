package com.wondernect.elements.rdb.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/2/8.
 * wondernect.com
 * @author sunbeam
 */
@Data
@ApiModel(description = "分页请求对象")
public class PageRequestData implements Serializable {

    private static final long serialVersionUID = 467170900801435185L;

    @JsonProperty("page")
    @ApiModelProperty(notes = "页码")
    private int page;

    @JsonProperty("size")
    @ApiModelProperty(notes = "页数量")
    private int size;

    @JsonProperty("sort_data_list")
    @ApiModelProperty(notes = "排序对象列表")
    private List<SortData> sortDataList;

    public PageRequestData() {
    }

    public PageRequestData(int page, int size, List<SortData> sortDataList) {
        this.page = page;
        this.size = size;
        this.sortDataList = sortDataList;
    }

    public PageRequestData(int page, int size) {
        this.page = page;
        this.size = size;
        this.sortDataList = new ArrayList<>();
    }
}
