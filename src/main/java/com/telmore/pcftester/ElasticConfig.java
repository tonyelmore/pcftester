package com.telmore.pcftester;

import com.telmore.config.ElasticVars;

import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.http.HttpHeaders;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.telmore.dao")
@ComponentScan(basePackages = { "com.telmore" })
public class ElasticConfig extends ElasticsearchConfiguration {

    @Autowired
    private ElasticVars vars;

    // @Value("${elasticsearch.cluster.name:test-elastic}")
    // private String clusterName;
    
    @Override
    public ClientConfiguration clientConfiguration() {
        String hostAndPort = vars.getMasterNodeAndPort();

        System.out.println("tonytest: hostAndPort: " + hostAndPort);
        System.out.println("tonytest: username: " + vars.getUsername());
        System.out.println("tonytest: password: " + vars.getPassword());

        HttpHeaders defaultHeaders = new HttpHeaders();
        defaultHeaders.setBasicAuth(vars.getUsername(), vars.getPassword());

        return ClientConfiguration.builder()           
			.connectedTo(hostAndPort)
            // .withDefaultHeaders(defaultHeaders)
            .withBasicAuth(vars.getUsername(), vars.getPassword())  
			.build();
	}

}