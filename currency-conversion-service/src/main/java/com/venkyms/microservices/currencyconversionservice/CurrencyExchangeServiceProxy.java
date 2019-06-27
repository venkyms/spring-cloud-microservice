package com.venkyms.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//name - name of the service which needs to be integrated
//url - url of the service which needs integration (once ribbonClient is enabled url is not required)
//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
//Comment below line to stop consuming currency-exchange-service directly and change to route it via Zuul API gateway
//@FeignClient(name = "currency-exchange-service")

@FeignClient(name = "zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    //Feign - copy the signature from curency-exchange-service controller and change return type to current module bean
//	@GetMapping("/currency-exchange/from/{from}/to/{to}")

	//http://localhost:8765/{application-name}/{uri}
	//for eg. direct acces of exchange-service via gateway http://localhost:8765/currency-exchange-service/currency-exchange/from/USD/to/INR/
	//Feign - above annotation should change while changing to ZUUL API gateway, append application name aswell
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
