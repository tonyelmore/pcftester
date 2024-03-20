package com.telmore.pcftester.components.elasticsearch.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elastic")
public class ElasticBaseController {

    @GetMapping()
    public String index() {
        StringBuilder sb = new StringBuilder();
        sb.append("Greetings from Elastic Search Controller!\n");
        sb.append("Available Endpoints: \n");
        sb.append("GET /elastic/test\n");
        return sb.toString();
    }

}
