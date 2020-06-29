package com.wondernect.elements.rdb.common.error;

import lombok.Getter;
import lombok.Setter;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: RDBErrorEnum
 * Author: chenxun
 * Date: 2019/3/14 18:00
 * Description: rdb error
 */
public enum RDBErrorEnum {

    RDB_SAVE_FAILED("RDB_SAVE_FAILED", "保存数据失败，请稍后重试"),
    RDB_SAVE_ALL_FAILED("RDB_SAVE_ALL_FAILED", "批量保存数据失败，请稍后重试"),
    RDB_DELETE_FAILED("RDB_DELETE_FAILED", "删除数据失败，请稍后重试"),
    RDB_DELETE_ALL_FAILED("RDB_DELETE_ALL_FAILED", "批量删除数据失败，请稍后重试"),
    RDB_UPDATE_FAILED("RDB_UPDATE_FAILED", "更新数据失败，请稍后重试"),
    RDB_GET_FAILED("RDB_GET_FAILED", "获取数据失败，请稍后重试"),
    RDB_COUNT_GET_FAILED("RDB_COUNT_GET_FAILED", "获取统计计数失败，请稍后重试"),
    RDB_EXIST_GET_FAILED("RDB_EXIST_GET_FAILED", "获取是否存在失败，请稍后重试"),
    RDB_LIST_GET_FAILED("RDB_LIST_GET_FAILED", "获取数据列表失败，请稍后重试"),
    RDB_PAGE_GET_FAILED("RDB_PAGE_GET_FAILED", "获取数据分页失败，请稍后重试"),
    RDB_JPA_QUERY_COUNT_GET_FAILED("RDB_JPA_QUERY_COUNT_GET_FAILED", "获取统计计数失败，请稍后重试"),
    RDB_JPA_QUERY_LIST_GET_FAILED("RDB_JPA_QUERY_LIST_GET_FAILED", "获取数据列表失败，请稍后重试"),
    RDB_JPA_QUERY_PAGE_GET_FAILED("RDB_JPA_QUERY_PAGE_GET_FAILED", "获取数据分页失败，请稍后重试"),
    RDB_NOT_FOUND("RDB_NOT_FOUND", "数据不存在，请检查后重试"),
    RDB_HAS_EXIST("RDB_HAS_EXIST", "数据已存在，请检查后重试"),
    RDB_PAGE_REQUEST_DATA_CAN_NOT_NULL("RDB_PAGE_REQUEST_DATA_CAN_NOT_NULL", "分页请求参数不能为空"),

    ;

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String message;

    RDBErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "RDBErrorEnum{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
