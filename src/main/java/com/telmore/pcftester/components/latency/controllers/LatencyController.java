package com.telmore.pcftester.components.latency.controllers;

import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/latency/")
@Conditional(LatencyConditional.class)
public class LatencyController {

    @GetMapping("test")
    // latency is in milliseconds 1000 = 1 second
    public String latencyTest(@RequestParam(name = "size") Integer size, @RequestParam Integer latency) {
        System.out.println("Testing latency with size: " + size + " and latency: " + latency);
        try {
            Thread.sleep(latency);
        } catch (Exception ex) {
            return "Latency Test Failed: " + ex.getMessage();
        }

        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < size; x++) {
            sb.append("x");
        }

        return sb.toString();
    }

}
