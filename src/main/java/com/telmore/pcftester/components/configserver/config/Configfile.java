package com.telmore.pcftester.components.configserver.config;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@Component
@Data
@RequiredArgsConstructor
public class Configfile {
    
    /* 
     * This class holds all of the variables that come from the config server
     * 
     * The @RequiredArgsConstructor annotation is specified, but not required
     * 
     */

    @Value("${globalConfigVar:default value for globalConfigVar}")
    @Setter(AccessLevel.NONE)
    private String globalConfigVar;

    @Value("${pcftester.appSpecificVar:none}")
    @Setter(AccessLevel.NONE)
    private String appSpecificVar;

}
