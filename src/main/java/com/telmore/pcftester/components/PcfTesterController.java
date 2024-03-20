package com.telmore.pcftester.components;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telmore.pcftester.components.latency.controllers.LatencyConditional;

@RestController
@RequestMapping("/")
@Conditional(LatencyConditional.class)public class PcfTesterController {

    @Autowired
    private ApplicationContext ctx;
    
    @GetMapping()
    public String endpoints() {
        StringBuilder sb = new StringBuilder();
        sb.append("Greetings from Spring Boot!").append("\n");
        sb.append("/ - Hello World Dial Tone").append("\n");
        sb.append("/vars - Print out all environment variables").append("\n");
        sb.append("/beans - Print the beans defined in the spring context?").append("\n");
        sb.append("/syslog - Syslog tests").append("\n");
        sb.append("-------- Current Components ---------").append("\n");
        sb.append("These can be enabled / disabled in the manifest when pushing the app").append("\n");
        sb.append("/latency - Run tests with some added latency").append("\n");
        sb.append("/credhub").append("\n");
        sb.append("/redis").append("\n");
        sb.append("/elastic").append("\n");
        sb.append("-------- Future Options ---------").append("\n");
        sb.append("MySql").append("\n");
        sb.append("RabbitMQ").append("\n");
        sb.append("SCS - Config Server").append("\n");
        sb.append("Scheduler").append("\n");
        sb.append("Is there anything that can be done with SSO").append("\n");
        return sb.toString();
    }

    @GetMapping("syslog")
    public String syslog() {
        System.out.println("PcfTester: Output to stdout");
        return "Syslog successful!";
    }

    @GetMapping("vars")
    public String vars() {
        String vcapServices = System.getenv("VCAP_SERVICES");
        System.out.println(vcapServices);

        // Print out all environment variables
        Map<String, String> env = System.getenv();
        StringBuilder sb = new StringBuilder();
        sb.append("-------- Environment Variables --------\n");
        env.forEach((k, v) -> sb.append(k).append(":").append(v).append("\n"));

        System.out.println(sb.toString());
        
        return sb.toString();
    }

    @GetMapping("beans")
    public String beans() {
        StringBuilder sb = new StringBuilder();
        sb.append("Let's inspect the beans provided by Spring Boot:").append("\n");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            sb.append(beanName).append("\n");
        }

        return sb.toString();
    }

}
