package com.wondernect.elements.rabbitmq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionListener;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: RabbitMQConnectionListenerHolder
 * Author: chenxun
 * Date: 2018/12/11 11:04
 * Description:
 */
public class RabbitMQConnectionListenerHolder {

    /** * The logger. */
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConnectionListenerHolder.class);

    /** * The connection listener name. */
    private static String connectionListenerName;

    /** * The connection listener. */
    private static ConnectionListener connectionListener;

    static {
        initialize();
    }

    /**
     * Initialize.
     */
    private static void initialize() {
        if ((connectionListenerName == null) || "".equals(connectionListenerName)) {
            // Set default
            connectionListener = new DefaultRabbitMQConnectionListener();
            return;
        }
        // Try to load a custom consumer connection listener
        try {
            Class<?> clazz = Class.forName(connectionListenerName);
            connectionListener = (ConnectionListener) clazz.newInstance();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new IllegalStateException("Initialize RabbitMQ connection listener error: " + ex.getMessage());
        }
    }

    /**
     * Changes the preferred connection listener.
     */
    public static void setConnectionListenerName(String connectionListenerName) {
        RabbitMQConnectionListenerHolder.connectionListenerName = connectionListenerName;
        initialize();
    }

    /**
     * Obtain the current ConnectionListener
     */
    public static ConnectionListener getConnectionListener() {
        return connectionListener;
    }

    @Override
    public String toString() {
        return "RabbitMQConnectionListenerHolder[connectionListener=" + connectionListenerName + "]";
    }
}
