package com.wondernect.elements.rabbitmq.listener;

import com.rabbitmq.client.ShutdownSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionListener;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: DefaultRabbitMQConsumerConnectionListener
 * Author: chenxun
 * Date: 2018/12/11 11:04
 * Description:
 */
public class DefaultRabbitMQConnectionListener implements ConnectionListener {

    private static final Logger logger = LoggerFactory.getLogger(DefaultRabbitMQConnectionListener.class);

    @Override
    public void onCreate(Connection connection) {
        logger.info("RabbitMQ connection create success: {}", connection);
    }

    @Override
    public void onClose(Connection connection) {
        logger.info("RabbitMQ connection close success: {}", connection);
    }

    @Override
    public void onShutDown(ShutdownSignalException signal) {
        logger.error("RabbitMQ connection shut down success: {}", signal);
    }
}
