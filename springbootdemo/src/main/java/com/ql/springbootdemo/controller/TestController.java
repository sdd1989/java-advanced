package com.ql.springbootdemo.controller;

import com.google.gson.Gson;
import com.ql.springbootdemo.model.RequestContextBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/5/14 11:18 上午
 **/
@Slf4j
@RestController
@RequestMapping(value = "/test")
public class TestController {

    Gson gson = new Gson();

    @PostMapping(value = "/test")
    public Object test(@RequestParam String userId, RequestContextBean requestContextBean) {
        log.info("userId:{},requestContextBean:{}", userId, requestContextBean);
        return "";
    }

    @RequestMapping(value = "/test2")
    public Object test2(HttpServletRequest request) {
        Map map = request.getParameterMap();
        log.info("map:{}", gson.toJson(map));
        Map map2 = request.getParameterMap();
        log.info("map2:{}", gson.toJson(map2));
        return "success";
    }

}
