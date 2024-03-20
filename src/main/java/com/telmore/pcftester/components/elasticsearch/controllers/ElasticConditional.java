package com.telmore.pcftester.components.elasticsearch.controllers;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ElasticConditional implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return false;
        // return "true".equals(System.getenv("ELASTIC_ENABLED"));
    }
    
}
