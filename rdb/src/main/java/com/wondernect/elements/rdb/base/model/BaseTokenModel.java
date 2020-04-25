package com.wondernect.elements.rdb.base.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.config.IDGenerateor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: BaseTokenModel
 * Author: chenxun
 * Date: 2018/10/18 15:37
 * Description: UUID token 基础数据模型
 */
@MappedSuperclass
public abstract class BaseTokenModel extends BaseModel {

    @Id
    @GeneratedValue(generator = "token")
    @GenericGenerator(name = "token", strategy = IDGenerateor.DISTRIBUTED_UUID_IDENTITY)
    @Column(name = "token")
    @JsonProperty("token")
    @Getter
    @Setter
    @ApiModelProperty(notes = "token对象唯一标识")
    private String token;
}
