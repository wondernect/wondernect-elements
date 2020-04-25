package com.wondernect.elements.rabbitmq.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: RabbitMQErrorHandlerHolder
 * Author: chenxun
 * Date: 2018/12/11 10:42
 * Description:
 */
public class RabbitMQErrorHandlerHolder {

    /** * The logger. */
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQErrorHandlerHolder.class);

    /** * The error handler name. */
    private static String errorHandlerName;

    /** * The error handler. */
    private static ErrorHandler errorHandler;

    static {
        initialize();
    }

    /**
     * Initialize.
     */
    private static void initialize() {
        if ((errorHandlerName == null) || "".equals(errorHandlerName)) {
            // Set default
            errorHandler = new DefaultRabbitMQErrorHandler();
            return;
        }
        // Try to load a custom error handler
        try {
            Class<?> clazz = Class.forName(errorHandlerName);
            errorHandler = (ErrorHandler) clazz.newInstance();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new IllegalStateException("Initialize RabbitMQ error handler error: " + ex.getMessage());
        }
    }

    /**
     * Changes the preferred error handler.
     */
    public static void setErrorHandlerName(String errorHandlerName) {
        RabbitMQErrorHandlerHolder.errorHandlerName = errorHandlerName;
        initialize();
    }

    /**
     * Obtain the current ErrorHandler
     */
    public static ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    @Override
    public String toString() {
        return "RabbitMQErrorHandlerHolder[errorHandler=" + errorHandlerName + "]";
    }
}
