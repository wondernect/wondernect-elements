package com.wondernect.elements.rdb.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created on 2018/2/8.
 * wondernect.com
 * @author sunbeam
 */
@Data
@ApiModel(description = "分页请求排序对象")
public class SortData implements Serializable {

    private static final long serialVersionUID = 2564910203995782030L;

    @JsonProperty("property")
    @ApiModelProperty(notes = "排序属性")
    private String property;

    @JsonProperty("direction")
    @ApiModelProperty(notes = "升序|降序(ASC|DESC)")
    private String direction;

    public SortData() {
    }

    public SortData(String property, String direction) {
        this.property = property;
        this.direction = direction;
    }
}
