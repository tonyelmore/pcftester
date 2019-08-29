package com.telmore.pcftester;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
public class PcfTesterController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/syslog")
    public String syslog() {
        System.out.println("PcfTester: Output to stdout");
        return "Syslog successful!";
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


    // What other things need to be tested?
    // 1) MySql
    // 2) Rabbit
    // 3) Redis
    // 4) any way to hit SSO?

}
