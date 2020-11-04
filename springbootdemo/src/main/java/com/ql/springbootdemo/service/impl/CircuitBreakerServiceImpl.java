package com.ql.springbootdemo.service.impl;

import com.ql.springbootdemo.model.User;
import com.ql.springbootdemo.service.RemoteServiceConnector;
import com.ql.springbootdemo.util.CircuitBreakerUtil;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/11/4 12:00 下午
 **/
@Service
public class CircuitBreakerServiceImpl {
    @Autowired
    private RemoteServiceConnector remoteServiceConnector;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    public List<User> circuitBreakerAOP() throws Exception {
        CircuitBreakerUtil
                .getCircuitBreakerStatus("执行开始前：",circuitBreakerRegistry.circuitBreaker("backendA"));
        List<User> result = remoteServiceConnector.process();
        CircuitBreakerUtil
                .getCircuitBreakerStatus("执行结束后：", circuitBreakerRegistry.circuitBreaker("backendA"));
        return result;
    }
}
