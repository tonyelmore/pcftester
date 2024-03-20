package com.telmore.pcftester.components.redis.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisBaseController {

    @GetMapping()
    public String index() {
        StringBuilder sb = new StringBuilder();
        sb.append("Greetings from Redis Controller!\n");
        sb.append("Available Endpoints: \n");
        sb.append("GET /redis/test\n");
        return sb.toString();
    }

}
