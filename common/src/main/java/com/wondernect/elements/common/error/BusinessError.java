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
    AUTHORIZE_APP_DEPLOY_TYPE_INVALID("AUTHORIZE_APP_DEPLOY_TYPE_INVALID", "服务应用部署方式配置非法"),
    AUTHORIZE_APPID_IS_NULL("AUTHORIZE_APPID_IS_NULL", "头部应用id不能为空"),
    AUTHORIZE_APP_SECRET_IS_NULL("AUTHORIZE_APP_SECRET_IS_NULL", "头部应用密钥不能为空"),
    AUTHORIZE_APP_SECRET_INVALID("AUTHORIZE_APP_SECRET_INVALID", "应用不存在或密钥认证失败"),
    INVALID_APP_USER_DATA("INVALID_APP_USER_DATA", "非当前登录用户绑定应用数据"),
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
