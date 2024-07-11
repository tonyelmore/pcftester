package com.telmore.pcftester.components.variables.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/variables")
public class VariablesBaseController {

    @GetMapping()
    public String index() {
        StringBuilder sb = new StringBuilder();
        sb.append("Greetings from Variables Controller!\n");
        sb.append("Available Endpoints: \n");
        sb.append("GET /variables/web  - run in a browser for HTML output\n");
        sb.append("GET /variables/curl  - run as curl command for text output\n");
        return sb.toString();
    }

}
