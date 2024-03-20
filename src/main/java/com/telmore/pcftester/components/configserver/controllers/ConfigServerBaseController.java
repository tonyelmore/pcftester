package com.telmore.pcftester.components.configserver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/configserver")
public class ConfigServerBaseController {

    @GetMapping()
    public String index() {
        StringBuilder sb = new StringBuilder();
        sb.append("Greetings from ConfigServer Controller!\n");
        sb.append("Available Endpoints: \n");
        sb.append("/test - Run ConfigServer test").append("\n");
        return sb.toString();
    }
}
