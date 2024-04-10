package com.telmore.pcftester.components.configserver.config;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@Component
public class Configfile {
    
    @Value("${globalConfigVar:none}")
    private String globalConfigVar;

    @Value("${pcftester.appSpecificVar:none}")
    private String appSpecificVar;

    public String getGlobalConfigVar() {
        return globalConfigVar;
    }

    public String getAppSpecificVar() {
        return appSpecificVar;
    }
}
