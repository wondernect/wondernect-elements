package com.wondernect.elements.common.error;

import lombok.Getter;
import lombok.Setter;

/**
 * Created on 2017/10/22.
 * wondernect.com
 * @author sunbeam
 */
public enum BusinessError {

    SUCCESS("SUCCESS", "成功"),
    AUTHORIZE_HEADER_IS_NULL("AUTHORIZE_HEADER_IS_NULL", "头部认证信息为空"),
    AUTHORIZE_TYPE_IS_INVALID("AUTHORIZE_TYPE_IS_INVALID", "认证类型有误"),
    AUTHORIZE_TOKEN_INVALID("AUTHORIZE_TOKEN_INVALID", "认证令牌有误或已过期，请重新获取"),
    AUTHORIZE_USER_ROLE_INVALID("AUTHORIZE_USER_ROLE_INVALID", "用户没有访问权限"),
    INVALID_REQUEST_HEADER("INVALID_REQUEST_HEADER", "请求头部有误"),
    INVALID_REQUEST_ARGUMENT("INVALID_REQUEST_ARGUMENT", "请求参数有误"),
    INVALID_REQUEST_URL("INVALID_REQUEST_URL", "请求url有误"),
    REQUEST_METHOD_NOT_SUPPORT("REQUEST_METHOD_NOT_SUPPORT", "请求method有误"),
    SERVER_INTERNAL_ERROR("SERVER_INTERNAL_ERROR", "服务器内部错误"),
    BUSINESS_ERROR("BUSINESS_ERROR", "服务自定义错误"),
    UNKNOWN_ERROR("UNKNOWN_ERROR", "服务器内部错误"),

    ;

    /**
     * 错误码
     */
    @Getter
    @Setter
    private String code;

    /**
     * 错误信息
     */
    @Getter
    @Setter
    private String message;

    BusinessError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "BusinessError{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
