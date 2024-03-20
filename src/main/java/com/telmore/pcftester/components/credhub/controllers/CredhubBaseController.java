package com.telmore.pcftester.components.credhub.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credhub")
public class CredhubBaseController {

    @GetMapping()
    public String index() {
        StringBuilder sb = new StringBuilder();
        sb.append("Greetings from Credhub Controller!\n");
        sb.append("Available Endpoints: \n");
        sb.append("/test - Run Credhub test").append("\n");
        return sb.toString();
    }
}
