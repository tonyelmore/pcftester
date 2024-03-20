package com.telmore.pcftester.components.latency.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/latency")
public class LatencyBaseController {

    @GetMapping()
    public String index() {
        StringBuilder sb = new StringBuilder();
        sb.append("Greetings from Latency Controller!\n");
        sb.append("Available Endpoints: \n");
        sb.append("GET /latency/test?size=1000&latency=1000\n");
        return sb.toString();
    }

}
