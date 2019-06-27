package com.venkyms.microservices.zuulapigatewayserver;

import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

@Component
public class ZuulLoggingFilter extends ZuulFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZuulLoggingFilter.class);
    //mention when to execute the filter , for example before , after, error
    @Override public String filterType() {
        return "pre";
    }

    //priority of the filter, 0 ,1,2
    @Override public int filterOrder() {
        return 1;
    }

    //Filter enabled/disabled
    @Override public boolean shouldFilter() {
        return true;
    }

    //Actual logic goes here, the filter logic
    @Override public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        LOGGER.info("Request: {} , Request URI:{}", request, request.getRequestURI());
        return null;
    }
}
