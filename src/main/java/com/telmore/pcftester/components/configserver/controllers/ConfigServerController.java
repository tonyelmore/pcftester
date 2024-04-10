package com.telmore.pcftester.components.configserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telmore.pcftester.components.configserver.config.Configfile;

@RestController
@RequestMapping("/configserver/")
@Conditional(ConfigServerConditional.class)
public class ConfigServerController {

    @Autowired
    private Configfile configfile;

    @GetMapping("info")
    public String info() {
        StringBuilder sb = new StringBuilder();
        sb.append("Config server info\n");
        sb.append("The pcfTester application must be bound to a config server").append("\n");
        sb.append("There is a sample in the configServerParams.json that can be used to create the service").append("\n");
        return sb.toString();
    }

    @GetMapping("test")
    public String test() {
        StringBuilder sb = new StringBuilder();
        sb.append("Config server test\n");
        sb.append("Global config var: ").append(configfile.getGlobalConfigVar()).append("\n");
        sb.append("App specific var: ").append(configfile.getAppSpecificVar()).append("\n");
        return sb.toString();
    }

}
