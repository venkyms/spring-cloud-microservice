package com.venkyms.microservices.currencyconversionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//Feign - instead of RestTemplate Fiegn rest client can be used which provides easier integration, check the proxy interface class
//EnableDiscoveryClient - enables registering with Eureka naming server
@SpringBootApplication
@EnableFeignClients("com.venkyms.microservices.currencyconversionservice")
@EnableDiscoveryClient
public class CurrencyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}

}
