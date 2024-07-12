package com.telmore.pcftester.components.variables.controllers;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/variables/")
@Conditional(VariablesConditional.class)
public class VariablesController {

    @GetMapping("web")
    public String headersWeb(@RequestHeader Map<String, String> headers, HttpServletRequest request) {
        System.out.println("Testing env variables");

        StringBuilder sb = new StringBuilder();
        sb.append("<h2>Environment Variables</h2>");
        sb.append("<ul>");

        Properties properties = System.getProperties();
        Set<Map.Entry<Object, Object>> entries = properties.entrySet();

        for (Map.Entry<Object, Object> entry : entries) {
            sb.append("<li>")
                .append(entry.getKey())
                .append(": ")
                .append(entry.getValue())
                .append("</li>");
        }

        sb.append("</ul><h2>Request Variables</h2>");
        sb.append("Remote IP: ")
            .append(request.getRemoteAddr()); 

        return sb.toString();
    }

    @GetMapping("curl")
    public String headersCurl(@RequestHeader Map<String, String> headers, HttpServletRequest request) {
        System.out.println("Testing env variables - curl");

        StringBuilder sb = new StringBuilder();
        sb.append("Environment Variables\n");

        Properties properties = System.getProperties();
        Set<Map.Entry<Object, Object>> entries = properties.entrySet();

        for (Map.Entry<Object, Object> entry : entries) {
            sb.append("  * ")
                .append(entry.getKey())
                .append(": ")
                .append(entry.getValue())
                .append("\n");
        }

        sb.append("\nRequest Variables\n");
        sb.append("Remote IP: ")
            .append(request.getRemoteAddr())
            .append("\n");

        sb.append("\n");
        sb.append(request.toString());
        
        return sb.toString();
    }
}
