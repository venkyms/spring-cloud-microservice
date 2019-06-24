package com.venkyms.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//name - name of the service which needs to be integrated
//url - url of the service which needs integration (once ribbonClient is enabled url is not required)
//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
@FeignClient(name = "currency-exchange-service")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    //copy the signature from curency-exchange-service controller and change return type to current module bean
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
