package com.ql;

import lombok.Data;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/7/24 4:46 下午
 **/
public class LombokTest {


    public static void main(String[] args) {
        LombokDemo lombokDemo = new LombokDemo();
        lombokDemo.getName();
    }

    @Data
    private static class LombokDemo {
        private String name;
    }
}
