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
        sb.append("/info - Get ConfigServer info").append("\n");
        return sb.toString();
    }

    @GetMapping("info")
    public String info() {
        StringBuilder sb = new StringBuilder();
        sb.append("Config server info\n");
        sb.append("The pcfTester application must be bound to a config server").append("\n");
        sb.append("There is a sample in the configServerParams.json that can be used to create the service").append("\n");
        sb.append("This functionality is enabled in the manifest by setting an env variable").append("\n");
        return sb.toString();
    }

}
