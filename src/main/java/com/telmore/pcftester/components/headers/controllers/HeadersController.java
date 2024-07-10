package com.telmore.pcftester.components.headers.controllers;

import java.util.Map;

import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/headers/")
@Conditional(HeadersConditional.class)
public class HeadersController {

    @GetMapping("web")
    public String headersWeb(@RequestHeader Map<String, String> headers) {
        System.out.println("Testing headers");

        StringBuilder sb = new StringBuilder();
        sb.append("<h2>Headers</h2>");
        sb.append("<ul>");
        headers.forEach((key, value) -> {
            System.out.printf("Header '%s' = %s%n", key, value);
            sb.append("<li>" + key + ": " + value + "</li>");
        });
        sb.append("</ul>");
        return sb.toString();
    }

    @GetMapping("curl")
    public String headersCurl(@RequestHeader Map<String, String> headers) {
        System.out.println("Testing headers");

        StringBuilder sb = new StringBuilder();
        sb.append("Headers\n");
        headers.forEach((key, value) -> {
            System.out.printf("Header '%s' = %s%n", key, value);
            sb.append("  * " + key + ": " + value + "\n");
        });
        return sb.toString();
    }
}
