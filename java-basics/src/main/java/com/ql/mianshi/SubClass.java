package com.ql.mianshi;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/8/31 8:55 下午
 **/
public class SubClass extends SupperClass {

    private static int a2 = 1;
    static {
        int b2 = 2;
        System.out.println("SubClass 静态代码块 a2 = " + a2);
    }
    SubClass() {
        System.out.println("SubClass 构造器... a2 = " + a2);
    }
}
