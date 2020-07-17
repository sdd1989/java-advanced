package com.ql;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/6/8 3:11 下午
 **/
public class StringFormatTest {

    public static void main(String[] args) {
        String str = "%s,hello";
        System.out.println(String.format(str, "sdd", "blibli"));
    }
}
