package com.wondernect.elements.rdb.base.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: BaseIDModel
 * Author: chenxun
 * Date: 2019/8/10 9:15
 * Description: id
 */
@MappedSuperclass
public abstract class BaseIDModel<ID extends Serializable> extends BaseModel {

    @Id
    @Column(name = "id")
    @JsonProperty("id")
    @Getter
    @Setter
    @ApiModelProperty(notes = "对象唯一标识")
    private ID id;
}
