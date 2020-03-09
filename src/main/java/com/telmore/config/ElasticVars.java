package com.telmore.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import org.elasticsearch.action.admin.cluster.repositories.cleanup.CleanupRepositoryRequest;

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
  private int defaultPort = 9300;
  List<String> nodes;

  public String getLowestNode() {
    System.out.println("tonytest: username is: " + username);
    System.out.println("tonytest: password is: " + password);
    System.out.println("tonytest: nodes is: " + nodes);

    if (CollectionUtils.isEmpty(nodes)) {
      return "";
    }
    nodes.sort((String s1, String s2) -> s1.compareTo(s2));
    return nodes.get(0);
  }

  public String getHighestNode() {
    if (CollectionUtils.isEmpty(nodes)) {
      return "";
    }
    nodes.sort((String s1, String s2) -> s2.compareTo(s1));
    return nodes.get(0);
  }

  public int getPort() {
    return defaultPort;
  }

}