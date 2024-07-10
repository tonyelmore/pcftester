package com.telmore.pcftester.components.headers.controllers;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class HeadersConditional implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return "true".equals(System.getenv("HEADERS_ENABLED"));
    }
    
}
