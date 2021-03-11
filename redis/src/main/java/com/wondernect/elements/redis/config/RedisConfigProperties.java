package com.wondernect.elements.redis.config;

import com.wondernect.elements.property.source.WondernectPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created on 2017/10/20.
 * wondernect.com
 * @author sunbeam
 */
@Component
@Primary
@PropertySource(value = {"classpath:application.properties", "classpath:application.yml", "classpath:application.yaml", "classpath:bootstrap.yml", "classpath:bootstrap.yaml"}, ignoreResourceNotFound = true, factory = WondernectPropertySourceFactory.class)
@ConfigurationProperties(prefix = "wondernect.elements.redis")
public class RedisConfigProperties implements Serializable {

    private static final long serialVersionUID = 7147037463842929166L;

    private String host = "127.0.0.1";

    private int port = 6379;

    private String password = "";

    private int database = 0;

    private String serviceIdentifier = "";

    private String keySeparator = ":";

    private Long minExpires = 10L;

    private Long defaultExpires = 60L;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getServiceIdentifier() {
        return serviceIdentifier;
    }

    public void setServiceIdentifier(String serviceIdentifier) {
        this.serviceIdentifier = serviceIdentifier;
    }

    public String getKeySeparator() {
        return keySeparator;
    }

    public void setKeySeparator(String keySeparator) {
        this.keySeparator = keySeparator;
    }

    public Long getMinExpires() {
        return minExpires;
    }

    public void setMinExpires(Long minExpires) {
        this.minExpires = minExpires;
    }

    public Long getDefaultExpires() {
        return defaultExpires;
    }

    public void setDefaultExpires(Long defaultExpires) {
        this.defaultExpires = defaultExpires;
    }
}
