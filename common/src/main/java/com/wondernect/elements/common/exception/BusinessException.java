package com.wondernect.elements.common.exception;

import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import lombok.Getter;
import lombok.Setter;

/**
 * Created on 2017/10/22.
 * wondernect.com
 * @author sunbeam
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1220663837769220975L;

    /**
     * 错误码
     */
    @Getter
    @Setter
    private String code;

    /**
     * 基础构造方法
     * @param message   错误信息
     * @param code      错误码
     */
    public BusinessException(String message, String code) {
        super(message);
        this.code = code;
    }

    /**
     * 错误消息构造方法
     * @param message   错误信息
     */
    public BusinessException(String message) {
        super(message);
        this.code = BusinessError.BUSINESS_ERROR.getCode();
    }

    /**
     * 错误枚举构造方法
     * @param businessError 错误
     */
    public BusinessException(BusinessError businessError) {
        super(businessError.getMessage());
        this.code = businessError.getCode();
    }

    /**
     * 响应数据异常构造
     * @param businessData 响应数据
     */
    public BusinessException(BusinessData businessData) {
        super(businessData.getMessage());
        this.code = businessData.getCode();
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "code='" + code + '\'' +
                ", message='" + this.getMessage() + '\'' +
                '}';
    }
}
