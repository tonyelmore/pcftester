package com.telmore.pcftester.components.configserver.config;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@Component
public class ConfigWithCtor {
    
    private final String globalConfigVar;

	private final String appSpecificVar;

	public ConfigWithCtor(@Value("${pcftester.appSpecificVar:none}") String appSpecificVar, @Value("${globalConfigVar:none}") String globalConfigVar) {
		this.appSpecificVar = appSpecificVar;
		this.globalConfigVar = globalConfigVar;
	}

	public String getAppSpecificVar() {
		return appSpecificVar;
	}

	public String getGlobalConfigVar() {
		return globalConfigVar;
	}
}
