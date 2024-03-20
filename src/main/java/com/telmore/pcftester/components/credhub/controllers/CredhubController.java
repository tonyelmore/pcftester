package com.telmore.pcftester.components.credhub.controllers;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credhub/")
@Conditional(CredhubConditional.class)
public class CredhubController {

    @GetMapping("test")
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

}
