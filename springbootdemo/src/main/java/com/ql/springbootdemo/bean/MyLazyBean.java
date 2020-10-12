package com.ql.springbootdemo.bean;

import org.springframework.stereotype.Component;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/8/5 9:48 上午
 **/
@Component
public class MyLazyBean {

    private String name;

    private Integer age;

    MyLazyBean() {
        System.out.println("MyLazyBean.init...");
    }
}
