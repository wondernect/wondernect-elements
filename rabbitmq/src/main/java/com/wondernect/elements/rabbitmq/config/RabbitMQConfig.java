package com.wondernect.elements.rabbitmq.config;

import com.wondernect.elements.rabbitmq.error.RabbitMQErrorHandlerHolder;
import com.wondernect.elements.rabbitmq.listener.RabbitMQConnectionListenerHolder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (C), 2018, wondernect.com
 * FileName: RabbitMQConfig
 * Author: chenxun
 * Date: 2018-12-05 11:10
 * Description:
 */
@Configuration
@EnableConfigurationProperties(RabbitMQConfigProperties.class)
public class RabbitMQConfig {

    @Autowired
    private RabbitMQConfigProperties rabbitMQConfigProperties;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(rabbitMQConfigProperties.getHostname(), rabbitMQConfigProperties.getPort());
        cachingConnectionFactory.setUsername(rabbitMQConfigProperties.getUsername());
        cachingConnectionFactory.setPassword(rabbitMQConfigProperties.getPassword());
        cachingConnectionFactory.setVirtualHost(rabbitMQConfigProperties.getVirtualHost());
        cachingConnectionFactory.setPublisherConfirms(rabbitMQConfigProperties.isPublisherConfirms());
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        if (rabbitMQConfigProperties.getConnectionListener() != null && !"".equals(rabbitMQConfigProperties.getConnectionListener())) {
            RabbitMQConnectionListenerHolder.setConnectionListenerName(rabbitMQConfigProperties.getConnectionListener());
        }
        connectionFactory.addConnectionListener(RabbitMQConnectionListenerHolder.getConnectionListener());
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.afterPropertiesSet();
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory consumerConnectionFactory) {
        if (rabbitMQConfigProperties.getConnectionListener() != null && !"".equals(rabbitMQConfigProperties.getConnectionListener())) {
            RabbitMQConnectionListenerHolder.setConnectionListenerName(rabbitMQConfigProperties.getConnectionListener());
        }
        consumerConnectionFactory.addConnectionListener(RabbitMQConnectionListenerHolder.getConnectionListener());
        SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        rabbitListenerContainerFactory.setConnectionFactory(consumerConnectionFactory);
        if (rabbitMQConfigProperties.getErrorHandler() != null && !"".equals(rabbitMQConfigProperties.getErrorHandler())) {
            RabbitMQErrorHandlerHolder.setErrorHandlerName(rabbitMQConfigProperties.getErrorHandler());
        }
        rabbitListenerContainerFactory.setErrorHandler(RabbitMQErrorHandlerHolder.getErrorHandler());
        rabbitListenerContainerFactory.setConcurrentConsumers(rabbitMQConfigProperties.getConcurrentConsumers());
        rabbitListenerContainerFactory.setMaxConcurrentConsumers(rabbitMQConfigProperties.getMaxConcurrentConsumers());
        rabbitListenerContainerFactory.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitListenerContainerFactory;
    }
}
