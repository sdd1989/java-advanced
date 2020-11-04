package com.ql.springbootdemo.service;

import com.ql.springbootdemo.model.User;
import com.ql.springbootdemo.util.CircuitBreakerUtil;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RemoteServiceConnector {

    @Autowired
    private RemoteService remoteService;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;


    @CircuitBreaker(name = "backendA", fallbackMethod = "fallBack")
    public List<User> process() throws Exception {
        List<User> users;
        users = remoteService.process();
        return users;
    }

    private List<User> fallBack(Throwable throwable) {
        log.info(throwable.getLocalizedMessage() + ",方法被降级了~~");
        //CircuitBreakerUtil.getCircuitBreakerStatus("降级方法中:", circuitBreakerRegistry.circuitBreaker("backendA"));
        List<User> users = new ArrayList();
        return users;
    }

    private List<User> fallBack(CallNotPermittedException e) {
        log.info("熔断器已经打开，拒绝访问被保护方法~");
        //CircuitBreakerUtil.getCircuitBreakerStatus("熔断器打开中:", circuitBreakerRegistry.circuitBreaker("backendA"));
        List<User> users = new ArrayList();
        return users;
    }
}