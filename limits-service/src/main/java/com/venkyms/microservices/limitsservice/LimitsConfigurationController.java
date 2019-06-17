package com.venkyms.microservices.limitsservice;

import com.venkyms.microservices.limitsservice.bean.LimitConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;

    @GetMapping(path = "/limits")
    public LimitConfiguration retrieveLimitsConfigurations(){
        return new LimitConfiguration(configuration.getMinimum(), configuration.getMaximum());
    }
}