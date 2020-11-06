package com.wondernect.elements.jdbc.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "数据库操作返回结果")
public class JDBCResult implements Serializable {


    @JsonProperty("result")
    @ApiModelProperty(notes = "结果")
    private Boolean result;

    @JsonProperty("message")
    @ApiModelProperty(notes = "结果描述")
    private String message;

}
