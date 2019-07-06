package com.venkyms.microservices.limitsservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @GetMapping(path = "/fault-tolerance-example")
    @HystrixCommand(fallbackMethod="fallbackRetrieveConfigurations")
    public LimitConfiguration retrieveConfigurations(){
        //example to simulate a problem and see how fault tolerance work
        throw  new RuntimeException("Not Available");
    }

    //default on failure
    public LimitConfiguration fallbackRetrieveConfigurations(){
       return new LimitConfiguration(9, 99);
    }
}