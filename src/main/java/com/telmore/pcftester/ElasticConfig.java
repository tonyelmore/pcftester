package com.telmore.pcftester;

import org.apache.http.HttpHost;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.telmore.config.ElasticVars;

@Configuration
@ComponentScan("com.telmore")
public class ElasticConfig {

    // @Value("${elasticsearch.host:localhost}")
    // public String host;
    // @Value("${elasticsearch.port:9300}")
    // public int port;

    @Autowired
    private final ElasticVars elasticVars = null;

    @Bean
    public RestHighLevelClient client() {
        String host = elasticVars.getLowestNode();
        int port = elasticVars.getPort();

        System.out.println("host: " + host + "port: " + port);
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
            new HttpHost(host, 9200, "http"),
            new HttpHost(host, 9201, "http")));

        return client;
    }

}