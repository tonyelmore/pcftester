package com.telmore.pcftester.components.configserver.controllers;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telmore.pcftester.components.configserver.config.ConfigWithCtor;
// import com.telmore.pcftester.components.configserver.config.Configfile;

@RestController
@RequestMapping("/configserver/")
@Conditional(ConfigServerConditional.class)
public class ConfigServerController {

    // @Autowired
    // private Configfile configfile;

    private final ConfigWithCtor configWithCtor;

    public ConfigServerController(ConfigWithCtor configWithCtor) {
        this.configWithCtor = configWithCtor;
    }

    @GetMapping("test")
    public String test() {
        StringBuilder sb = new StringBuilder();
        sb.append("Config server test\n");
        sb.append("Global config var: ").append(configWithCtor.getGlobalConfigVar()).append("\n");
        sb.append("App specific var: ").append(configWithCtor.getAppSpecificVar()).append("\n");
        return sb.toString();
    }

}
