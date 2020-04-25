package com.wondernect.elements.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

/**
 * Created on 2017/10/11.
 * wondernect.com
 * @author sunbeam
 */
@Configuration
@EnableConfigurationProperties(ElasticsearchConfigProperties.class)
public class ElasticsearchConfig {

    @Autowired
    private ElasticsearchConfigProperties elasticsearchConfigProperties;

    @Bean
    public RestHighLevelClient highLevelClient() throws UnknownHostException {
        RestClient client = RestClient.builder(new HttpHost(elasticsearchConfigProperties.getHost(), elasticsearchConfigProperties.getPort()))
                .setFailureListener(new RestClient.FailureListener() {
                    @Override
                    public void onFailure(HttpHost host) {
                        System.out.println("wondernect elements elasticsearch rest client init failure, host=[" + host + "]");
                    }
                }).build();
        return new RestHighLevelClient(client);
    }
}
