package com.wondernect.elements.elasticsearch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created on 2017/10/20.
 * wondernect.com
 * @author sunbeam
 */
@Component
@Primary
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "wondernect.elements.elasticsearch")
public class ElasticsearchConfigProperties {

    private String host = "127.0.0.1";

    private int port = 9200;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
