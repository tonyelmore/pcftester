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

    @GetMapping("test")
    public String test() {
        StringBuilder sb = new StringBuilder();
        sb.append("Config server test\n");
        sb.append("Global config var: ").append(configfile.getGlobalConfigVar()).append("\n");
        sb.append("App specific var: ").append(configfile.getAppSpecificVar()).append("\n");
        return sb.toString();
    }

    // Now need to do some testing with the different strategies
    // 3.2.2 is installed
    // So far, I've created the master branch and then added the label to the service creation
    // Take off the label and it should be using the main branch


}
