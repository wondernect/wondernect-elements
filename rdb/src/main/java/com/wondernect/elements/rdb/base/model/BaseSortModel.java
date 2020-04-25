package com.wondernect.elements.rdb.base.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: BaseSortModel
 * Author: chenxun
 * Date: 2019/4/12 18:09
 * Description:
 */
@MappedSuperclass
public abstract class BaseSortModel<ID extends Serializable> extends BaseIDModel<ID> implements Comparator<BaseSortModel> {

    @JsonProperty("weight")
    @Getter
    @Setter
    @ApiModelProperty(notes = "权重(用于排序)")
    private Integer weight = 0;

    @Override
    public int compare(BaseSortModel o1, BaseSortModel o2) {
        return Integer.compare(o1.weight, o2.weight);
    }
}
