package com.ql.mianshi;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/8/31 8:55 下午
 **/
public class SupperClass {

    private static int a = 1;
    static {
        int b = 2;
        System.out.println("SupperClass 静态代码块 a = " + a);
    }
    SupperClass() {
        System.out.println("SupperClass 构造器... a = " + a);
    }

}
