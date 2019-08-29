package com.telmore.pcftester;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RestController
public class PcfTesterController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/syslog")
    public String syslog() {
        System.out.println("PcfTester: Output to stdout");
        return "Syslog successful!";
    }

    @RequestMapping("/credhub")
    public String credhub() {
        Map<String, String> env = System.getenv();
        System.out.println("------- List of all env variables ----------");
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n",
                    envName,
                    env.get(envName));
        }
        System.out.println("------- Specific env variable for credhub ------");
        String value = System.getenv("VCAP_SERVICES");
        System.out.println(value);
        
        return "Credhub successful - see application log";
    }

}
