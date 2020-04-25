package com.wondernect.elements.rabbitmq.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.listener.exception.FatalListenerExecutionException;
import org.springframework.amqp.rabbit.listener.exception.FatalListenerStartupException;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.util.ErrorHandler;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: DefaultErrorHandler
 * Author: chenxun
 * Date: 2018/12/10 16:40
 * Description:
 */
public class DefaultRabbitMQErrorHandler implements ErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultRabbitMQErrorHandler.class);

    @Override
    public void handleError(Throwable t) {
        if (t instanceof ListenerExecutionFailedException) {
            ListenerExecutionFailedException listenerExecutionFailedException = (ListenerExecutionFailedException) t;
            logger.error("RabbitMQ error handler listener execution failed exception:{}", listenerExecutionFailedException.getFailedMessage());
        } else if (t instanceof FatalListenerStartupException) {
            FatalListenerStartupException fatalListenerStartupException = (FatalListenerStartupException) t;
            logger.error("RabbitMQ error handler fatal listener startup exception:{}", fatalListenerStartupException);
        } else if (t instanceof FatalListenerExecutionException) {
            FatalListenerExecutionException fatalListenerExecutionException = (FatalListenerExecutionException) t;
            logger.error("RabbitMQ error handler fatal listener execution exception:{}", fatalListenerExecutionException);
        } else {
            logger.error("RabbitMQ error handler other exception:{}", t);
        }
        throw new AmqpRejectAndDontRequeueException("RabbitMQ error handler exception", t);
    }
}
