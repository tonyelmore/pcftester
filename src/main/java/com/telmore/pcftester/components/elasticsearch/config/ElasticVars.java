package com.telmore.pcftester.components.elasticsearch.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties("vcap.services.test-elastic.credentials")
public class ElasticVars {
  private String index;
  private String password;
  private String username;
  private int defaultPort = 9200;
  List<String> nodes;

  private String getLowestNode() {
    if (CollectionUtils.isEmpty(nodes)) {
      return "";
    }
    nodes.sort((String s1, String s2) -> s1.compareTo(s2));
    return nodes.get(0);
  }

  private String getHighestNode() {
    if (CollectionUtils.isEmpty(nodes)) {
      return "";
    }
    nodes.sort((String s1, String s2) -> s2.compareTo(s1));
    return nodes.get(0);
  }

  public String getMasterNode() {
    return getLowestNode();
  }

  public String getMasterNodeAndPort() {
    return getMasterNode() + ":" + getPort();
  }
  
  public int getPort() {
    return defaultPort;
  }

}