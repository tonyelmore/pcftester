package com.telmore.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telmore.dao.ItemDao;
import com.telmore.domain.Item;
import com.telmore.config.ElasticVars;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Map;

@RestController
public class PcfTesterController {

    @Autowired
    public ItemDao itemDAO;

    @Autowired
    private final ElasticVars elasticVars = null;

    @Autowired
    private final RestHighLevelClient elasticClient = null;
    
    @Autowired
    private ObjectMapper objectMapper;

    private final String port;
    private final String memoryLimit;
    private final String cfInstanceIndex;
    private final String cfInstanceAddress;

    public PcfTesterController(
        // ElasticVars elasticVars,
        // Injects the PORT environment variable, defaults to NOT SET
        @Value("${port:NOT SET}") String port,
        // Injects the MEMORY_LIMIT environment variable, defaults to NOT SET
        @Value("${memory.limit:NOT SET}") String memoryLimit,
        // Injects the CF_INSTANCE_INDEX environment variable, defaults to NOT SET
        @Value("${cf.instance.index:NOT SET}") String cfInstanceIndex,
        // Injects the CF_INSTANCE_ADDR environment variable, defaults to NOT SET
        @Value("${cf.instance.addr:NOT SET}") String cfInstanceAddress
    ) {
        // this.elasticVars = elasticVars;
        this.port = port;
        this.memoryLimit = memoryLimit;
        this.cfInstanceIndex = cfInstanceIndex;
        this.cfInstanceAddress = cfInstanceAddress;
    }

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/syslog")
    public String syslog() {
        System.out.println("PcfTester: Output to stdout");
        return "Syslog successful!";
    }

    @RequestMapping("/latency")
    // latency is in milliseconds 1000 = 1 second
    public String latencyTest(@RequestParam(name = "size") Integer size, @RequestParam Integer latency) {
        System.out.println("Testing latency with size: " + size + " and latency: " + latency);
        try {
            Thread.sleep(latency);
        } catch (Exception ex) {
            return "Latency Test Failed: " + ex.getMessage();
        }

        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < size; x++) {
            sb.append("x");
        }

        return sb.toString();
    }

    @RequestMapping("/credhub")
    public String credhub() {
        System.out.println("------- Start: Get credhub value ------");
        String envVar = System.getenv("VCAP_SERVICES");

        if (envVar == null) {
            System.out.println("------- End: Get credhub value ------");
            return "Env variable 'VCAP_SERVICES' was not found";
        }

        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        //read envVar file into tree model
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(envVar);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //read credentials
        JsonNode credNode = rootNode.path("credhub").get(0).path("credentials");

        System.out.println("*** Printing only keys, not values ***");

        credNode.fieldNames().forEachRemaining(e -> System.out.println(e) );

        System.out.println("------- End: Get credhub value ------");

        return "Credhub successful - see application log";
    }

    @RequestMapping("/redis")
    public String redis() {

        System.out.println("------- Start: example of using Redis as data store -------");
        Item item1 = new Item(1, "ItemOne", 30);
        itemDAO.addItem(item1);
        Item item2 = new Item(2, "ItemTwo", 35);
        itemDAO.addItem(item2);
        System.out.println("Number of Items: " + itemDAO.getNumberOfItems());
        System.out.println(itemDAO.getItemAtIndex(1));
        itemDAO.removeItem(item2);
        System.out.println("This should be null: " + itemDAO.getItemAtIndex(1)); //It will return null, because value is deleted.
        System.out.println("This should be ItemOne:  " + itemDAO.getItemAtIndex(0).getName());
        itemDAO.removeItem(item1);
        System.out.println("------- End: example of using Redis as data store -------");

        return "Redis successful - see application log";
    }

    @RequestMapping("/elastic")
    public String elastic() throws IOException {
        Item item = new Item(5, "Tony", 30);

        String json = objectMapper.writeValueAsString(item);        
        String indexname = elasticVars.getIndex();

        IndexRequest request = new IndexRequest(indexname);
        request.source(json, XContentType.JSON);
        
        IndexResponse response = elasticClient.index(request, RequestOptions.DEFAULT);
        System.out.println("response id:" + response.getId());
        return response.getResult().toString();
    }

    @RequestMapping("/vars")
    public String vars() {
        String vcapServices = System.getenv("VCAP_SERVICES");
        System.out.println(vcapServices);

        // Print out all environment variables
        Map<String, String> env = System.getenv();
        env.forEach((k, v) -> System.out.println(k + ":" + v));

        // Common, built-in environment variables
        System.out.println("PORT: " + port);
        System.out.println("MEMORY_LIMIT: " + memoryLimit);
        System.out.println("CF_INSTANCE_INDEX: " + cfInstanceIndex);
        System.out.println("CF_INSTANCE_ADDR: " + cfInstanceAddress);

        System.out.println("_________ test ___________");
        System.out.println(elasticVars);
        System.out.println("-------- end test --------");
    
        StringBuilder sb = new StringBuilder();
        sb.append("Lowest IP Address: " + elasticVars.getLowestNode() + "\n");
        sb.append("Highest IP Address: " + elasticVars.getHighestNode());
        return sb.toString();
    }


    // What other things need to be tested?
    // 1) MySql
    // 2) Rabbit
    // 3) Redis
    // 4) any way to hit SSO?

}
