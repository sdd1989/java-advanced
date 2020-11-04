package com.ql.springbootdemo;

import com.ql.springbootdemo.service.impl.CircuitBreakerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/11/4 11:59 上午
 **/
@SpringBootTest
public class CircuitBreakerServiceImplTests {

    @Autowired
    private CircuitBreakerServiceImpl circuitService;

    @Test
    public void circuitBreakerTest() throws Exception {
        for (int i=0; i<10; i++){
            circuitService.circuitBreakerAOP();
        }
        TimeUnit.SECONDS.sleep(5);
        for (int i=0; i<20; i++){
            circuitService.circuitBreakerAOP();
        }
    }
}
