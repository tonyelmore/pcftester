package com.telmore.pcftester.components.headers.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/headers")
public class HeadersBaseController {

    @GetMapping()
    public String index() {
        StringBuilder sb = new StringBuilder();
        sb.append("Greetings from Headers Controller!\n");
        sb.append("Available Endpoints: \n");
        sb.append("GET /headers/web  - run in a browser for HTML output\n");
        sb.append("GET /headers/curl  - run as curl command for text output\n");
        return sb.toString();
    }

}
