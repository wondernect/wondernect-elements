package com.wondernect.elements.rdb.base.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.config.WondernectEntityListener;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: BaseModel
 * Author: chenxun
 * Date: 2018/10/12 14:46
 * Description: 基础数据模型
 */
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class, WondernectEntityListener.class})
@FilterDef(name = "appFilter", parameters = { @ParamDef(name = "create_app", type = "java.lang.String") })
@Filters({ @Filter(name = "appFilter", condition = "create_app = :create_app") })
public abstract class BaseModel extends BaseRDBModel {

    @CreatedDate
    @JsonProperty("create_time")
    @Getter
    @Setter
    @ApiModelProperty(notes = "创建时间")
    private Long createTime;

    @LastModifiedDate
    @JsonProperty("update_time")
    @Getter
    @Setter
    @ApiModelProperty(notes = "更新时间")
    private Long updateTime;

    @CreatedBy
    @JsonProperty("create_user")
    @Getter
    @Setter
    @ApiModelProperty(notes = "创建用户")
    private String createUser;

    @LastModifiedBy
    @JsonProperty("update_user")
    @Getter
    @Setter
    @ApiModelProperty(notes = "更新用户")
    private String updateUser;

    @JsonProperty("create_app")
    @Getter
    @Setter
    @ApiModelProperty(notes = "创建应用")
    private String createApp;

    @JsonProperty("update_app")
    @Getter
    @Setter
    @ApiModelProperty(notes = "更新应用")
    private String updateApp;
}
