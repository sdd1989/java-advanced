package com.ql.springbootdemo.bean;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/8/5 9:48 上午
 **/
@Lazy
@Component("myLazyBean2")
public class MyLazyBean2 {

    private String name;

    private Integer age;

    MyLazyBean2() {
        System.out.println("MyLazyBean2.init...");
    }
}
