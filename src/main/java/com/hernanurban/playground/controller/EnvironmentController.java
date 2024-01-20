package com.hernanurban.playground.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

@RestController
public class EnvironmentController {
    @Value("${spring.application.name:undefined}")
    private String applicationName;


    private final ConfigurableEnvironment environment;

    public EnvironmentController(ConfigurableEnvironment environment) {
        this.environment = environment;
    }

    @GetMapping("/env")
    public Map<String, String> getEnvironmentVariables() {
        Map<String, String> environmentVariables = System.getenv();
        return new TreeMap<String, String>(environmentVariables);
    }

    @GetMapping("/properties")
    public Map<String, String> getAllProperties() {
        Map<String, String> allProperties = new HashMap<>();

        //Ø Get system properties
        Properties systemProperties = System.getProperties();
        systemProperties.forEach((key, value) -> allProperties.put(key.toString(), value.toString()));


        // Get Spring properties
        for (PropertySource<?> propertySource : environment.getPropertySources()) {
            if (propertySource instanceof MapPropertySource) {
                Map<String, Object> source = ((MapPropertySource) propertySource).getSource();
                source.forEach((key, value) -> allProperties.put(key, value.toString()));
            }
        }

        // Add application-specific properties
        allProperties.put("applicationName", applicationName);

        return new TreeMap<String, String>(allProperties);
    }
}

