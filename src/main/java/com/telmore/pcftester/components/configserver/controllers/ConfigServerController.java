package com.telmore.pcftester.components.configserver.controllers;

import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/configserver/")
@Conditional(ConfigServerConditional.class)
public class ConfigServerController {

    @GetMapping("test")
    public String test() {
        return "Config Server test not yet implemented";
    }

}
