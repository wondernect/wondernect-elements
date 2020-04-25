package com.wondernect.elements.log.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.log.em.LogLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: BaseLoggerModel
 * Author: chenxun
 * Date: 2018/11/30 15:46
 * Description:
 */
@MappedSuperclass
public class BaseLogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Getter
    @Setter
    private Long id;

    /**
     * table默认属性 log_level，INSERT示例语句如下：
     * INSERT INTO USER_LOG(LOG_LEVEL) VALUES('%X{log_level}')
     */
    @Enumerated(EnumType.STRING)
    @JsonProperty("log_level")
    @Getter
    @Setter
    private LogLevel logLevel;

    /**
     * table默认属性 message，INSERT示例语句如下：
     * INSERT INTO USER_LOG(MESSAGE) VALUES('%m')
     */
    @JsonProperty("message")
    @Getter
    @Setter
    private String message;

    /**
     * table默认属性 log_time，INSERT示例语句如下：
     * INSERT INTO USER_LOG(LOG_TIME) VALUES(NOW())
     */
    @JsonProperty("log_time")
    @Getter
    @Setter
    private Date logTime;
}
