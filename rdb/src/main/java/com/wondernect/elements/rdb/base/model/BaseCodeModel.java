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
 * Description: UUID code 基础数据模型
 */
@MappedSuperclass
public abstract class BaseCodeModel extends BaseModel {

    @Id
    @GeneratedValue(generator = "code")
    @GenericGenerator(name = "code", strategy = IDGenerateor.DISTRIBUTED_UUID_IDENTITY)
    @Column(name = "code")
    @JsonProperty("code")
    @Getter
    @Setter
    @ApiModelProperty(notes = "code对象唯一标识")
    private String code;
}
