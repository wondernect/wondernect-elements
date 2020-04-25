package com.wondernect.elements.i18n.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.i18n.I18nMessageService;
import com.wondernect.elements.i18n.config.I18nConfigProperties;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: I18nExceptionHandler
 * Author: chenxun
 * Date: 2018/12/3 15:29
 * Description:
 */
@RestControllerAdvice
public class I18nExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(I18nExceptionHandler.class);

    private static final String I18N_VALIDATE_ERROR_CODE = "I18N_VALIDATE_ERROR";

    @Autowired
    private I18nMessageService i18nMessageService;

    @Autowired
    private I18nConfigProperties i18nConfigProperties;

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BusinessData handleException(MethodArgumentNotValidException methodArgumentNotValidException) {
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        String errorMessage = "";
        if (i18nConfigProperties.isShowErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            if (bindingResult.hasErrors()) {
                List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
                for (int index=0; index<fieldErrorList.size(); index++) {
                    FieldError fieldError = fieldErrorList.get(index);
                    if (index > 0) {
                        stringBuilder.append(",");
                    }
                    // stringBuilder.append("[");
                    // stringBuilder.append(fieldError.getField());
                    // stringBuilder.append("]");
                    stringBuilder.append(fieldError.getDefaultMessage());
                }
                errorMessage = stringBuilder.toString();
            }
        } else {
            if (bindingResult.hasErrors()) {
                List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
                if (CollectionUtils.isNotEmpty(fieldErrorList)) {
                    errorMessage = fieldErrorList.get(0).getDefaultMessage();
                }
            }
        }
        if (i18nConfigProperties.isShowException()) {
            logger.warn(errorMessage, methodArgumentNotValidException);
        } else {
            logger.warn(errorMessage);
        }
        return new BusinessData<>(I18N_VALIDATE_ERROR_CODE, errorMessage, null);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public BusinessData handleException(ConstraintViolationException constraintViolationException) {
        Set<ConstraintViolation<?>> constraintViolationSet = constraintViolationException.getConstraintViolations();
        String errorMessage = "";
        if (i18nConfigProperties.isShowErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            if (CollectionUtils.isNotEmpty(constraintViolationSet)) {
                int count = 0;
                for (ConstraintViolation<?> constraintViolation : constraintViolationSet) {
                    if (count > 0) {
                        stringBuilder.append(",");
                    }
                    // stringBuilder.append("[");
                    // stringBuilder.append(constraintViolation.getPropertyPath());
                    // stringBuilder.append("]");
                    stringBuilder.append(constraintViolation.getMessage());
                    count++;
                }
                errorMessage = stringBuilder.toString();
            }
        } else {
            if (CollectionUtils.isNotEmpty(constraintViolationSet)) {
                for (ConstraintViolation<?> constraintViolation : constraintViolationSet) {
                    errorMessage = constraintViolation.getMessage();
                    break;
                }
            }
        }
        if (i18nConfigProperties.isShowException()) {
            logger.warn(errorMessage, constraintViolationException);
        } else {
            logger.warn(errorMessage);
        }
        return new BusinessData<>(I18N_VALIDATE_ERROR_CODE, errorMessage, null);
    }

    @ExceptionHandler(value = BusinessException.class)
    public BusinessData handleException(BusinessException businessException) {
        if (i18nConfigProperties.isShowException()) {
            logger.warn(businessException.getCode() + ":" + businessException.getMessage(), businessException);
        } else {
            logger.warn(businessException.getCode() + ":" + businessException.getMessage());
        }
        return new BusinessData<>(businessException.getCode(), i18nMessageService.getMessage(businessException.getCode(), businessException.getMessage()), null);
    }

    @ExceptionHandler(value = Exception.class)
    public BusinessData handleException(Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return new BusinessData<>(BusinessError.REQUEST_METHOD_NOT_SUPPORT);
        } else if (e instanceof HttpMessageNotReadableException) {
            return new BusinessData<>(BusinessError.INVALID_REQUEST_ARGUMENT.getCode(), e.getLocalizedMessage(), null);
        } else if (e instanceof InvalidFormatException) {
            return new BusinessData<>(BusinessError.INVALID_REQUEST_ARGUMENT.getCode(), e.getLocalizedMessage(), null);
        } else if (e instanceof InternalException) {
            return new BusinessData<>(BusinessError.SERVER_INTERNAL_ERROR.getCode(), e.getLocalizedMessage(), null);
        } else {
            return new BusinessData<>(BusinessError.UNKNOWN_ERROR.getCode(), e.getLocalizedMessage(), null);
        }
    }
}
