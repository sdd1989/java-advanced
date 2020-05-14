package com.ql.springbootdemo.controller;

import com.ql.springbootdemo.model.RequestContextBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/5/14 11:18 上午
 **/
@Slf4j
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @PostMapping(value = "/test")
    public Object test(@RequestParam String userId, RequestContextBean requestContextBean) {
        log.info("userId:{},requestContextBean:{}", userId, requestContextBean);
        return "";
    }

}
