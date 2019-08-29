package com.telmore.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telmore.dao.ItemDao;
import com.telmore.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RestController
public class PcfTesterController {

    @Autowired
    public ItemDao itemDAO;

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



    // What other things need to be tested?
    // 1) MySql
    // 2) Rabbit
    // 3) Redis
    // 4) any way to hit SSO?

}
