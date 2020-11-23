package com.wondernect.elements.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESJSONObjectUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * Created on 2017/10/22.
 * wondernect.com
 * @author sunbeam
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "响应对象")
public class BusinessData<T> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(BusinessData.class);

    private static final long serialVersionUID = 6863694488955478047L;

    public static BusinessData SUCCESS = returnSuccess();

    @JsonProperty("code")
    @ApiModelProperty(notes = "错误码")
    private String code;

    @JsonProperty("message")
    @ApiModelProperty(notes = "错误信息")
    private String message;

    @JsonProperty("data")
    @ApiModelProperty(notes = "响应数据")
    private T data;

    /**
     * 成功
     * @param data  数据
     */
    public BusinessData(T data) {
        this(BusinessError.SUCCESS.getCode(), BusinessError.SUCCESS.getMessage(), data);
    }

    /**
     * 失败
     * @param code 错误代码
     * @param message 错误信息
     */
    public BusinessData(String code, String message) {
        this(code, message, null);
    }

    /**
     * 失败
     * @param message 错误信息
     */
    public BusinessData(String message) {
        this(BusinessError.BUSINESS_ERROR.getCode(), message, null);
    }

    /**
     * 失败
     * @param businessError 错误枚举
     */
    public BusinessData(BusinessError businessError) {
        this(businessError.getCode(), businessError.getMessage(), null);
    }

    /**
     * 异常
     * @param e 异常
     */
    public BusinessData(BusinessException e) {
        this(e.getCode(), e.getMessage(), null);
    }

    /**
     * 对象
     * @param businessData 对象
     */
    public BusinessData(BusinessData businessData) {
        this(businessData.code, businessData.message, null);
    }

    /**
     * 获取成功响应数据
     */
    public boolean success() {
        return "SUCCESS".equals(this.getCode());
    }

    /**
     * response响应错误信息
     */
    public static void error(String message, HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getOutputStream().write(ESJSONObjectUtils.jsonObjectToJsonString(new BusinessData(message)).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 构造成功响应对象
     * @return 成功响应对象
     */
    private static BusinessData returnSuccess() {
        return new BusinessData(BusinessError.SUCCESS);
    }
}
