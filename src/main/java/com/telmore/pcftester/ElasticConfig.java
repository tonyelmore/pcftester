package com.telmore.pcftester;

import com.telmore.config.ElasticVars;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.http.HttpHeaders;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.telmore.dao")
@ComponentScan(basePackages = { "com.telmore" })
public class ElasticConfig {

    @Autowired
    private ElasticVars vars;

    // @Value("${elasticsearch.cluster.name:test-elastic}")
    // private String clusterName;

    @Bean
    RestHighLevelClient client() {
  
        String hostAndPort = vars.getMasterNodeAndPort();

        System.out.println("tonytest: hostAndPort: " + hostAndPort);
        System.out.println("tonytest: username: " + vars.getUsername());
        System.out.println("tonytest: password: " + vars.getPassword());

        HttpHeaders defaultHeaders = new HttpHeaders();
        defaultHeaders.setBasicAuth(vars.getUsername(), vars.getPassword());

        ClientConfiguration clientConfiguration = ClientConfiguration.builder() 
            .connectedTo(hostAndPort)
            .withDefaultHeaders(defaultHeaders)
            .withBasicAuth(vars.getUsername(), vars.getPassword())
            .build();
  
      return RestClients.create(clientConfiguration).rest();                  
    }
    
//     @Bean
//     public Client client() throws IOException {
//         System.out.println("tonytest: elasticsearchHome: " + elasticsearchHome);
//         System.out.println("tonytest: clusterName: " + clusterName);
//         Settings elasticsearchSettings = Settings.builder()
//           .put("client.transport.sniff", true)
// //          .put("path.home", elasticsearchHome)
//           .put("cluster.name", clusterName).build();
//         TransportClient client = new PreBuiltTransportClient(elasticsearchSettings);

//         InetAddress addr = InetAddress.getByName(vars.getMasterNode());
         
//         System.out.println("tonytest: address is: " + addr.getHostAddress());
//         if (addr.isReachable(10000)) {
//             System.out.println("tonytest: the address is reachable");
//         } else {
//             System.out.println("tonytest: the address is NOT reachable");
//         }

//         System.out.println("tonytest: inetaddr: " + addr);
//         client.addTransportAddress(new TransportAddress(addr, vars.getPort()));


//         // String[] test = vars.getMasterNode().split(".");
//         // System.out.println("tonytest: " + Integer.parseInt(test[0]));

//         // int sectiona = Integer.parseInt(test[0]);
//         // byte[] ipAddr = new byte[] {
//         //     sectiona,
//         //     Integer.parseInt(test[1]),
//         //     127, 0, 0, 1};
//       //  client.addTransportAddress(new TransportAddress(InetAddress.getByAddress(ipAddr), vars.getPort()));

//         return client;
//     }
 
    // @Bean
    // public ElasticsearchOperations elasticsearchTemplate() throws IOException {
    //     return new ElasticsearchTemplate(client());
    // }
}