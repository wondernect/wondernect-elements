package com.wondernect.elements.rdb.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.request.PageRequestData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/1/14.
 * wondernect.com
 * @author sunbeam
 */
@Data
@ApiModel(description = "分页响应对象")
public class PageResponseData<T> implements Serializable {

    private static final long serialVersionUID = -5712272915828809159L;

    @JsonProperty("page")
    @ApiModelProperty(notes = "当前页码")
    private int page;

    @JsonProperty("size")
    @ApiModelProperty(notes = "每页数量")
    private int size;

    @JsonProperty("total_pages")
    @ApiModelProperty(notes = "总页数")
    private int totalPages;

    @JsonProperty("total_elements")
    @ApiModelProperty(notes = "总数量")
    private long totalElements;

    @JsonProperty("data_list")
    @ApiModelProperty(notes = "当前页数据列表")
    private List<T> dataList;

    public PageResponseData() {
    }

    public PageResponseData(int page, int size, int totalPages, long totalElements, List<T> dataList) {
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.dataList = dataList;
    }

    public PageResponseData(PageRequestData pageRequestData) {
        if (ESObjectUtils.isNull(pageRequestData)) {
            this.page = 0;
            this.size = 10;
            this.totalPages = 0;
            this.totalElements = 0;
            this.dataList = new ArrayList<>();
        } else {
            this.page = pageRequestData.getPage();
            this.size = pageRequestData.getSize();
            this.totalPages = 0;
            this.totalElements = 0;
            this.dataList = new ArrayList<>();
        }
    }
}
