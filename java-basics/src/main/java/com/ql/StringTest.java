package com.ql;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/9/16 11:14 上午
 **/
public class StringTest {

    public static void main(String[] args) {
        String a = "ABC#$%!@#$%^&*()_+}{\\.";
        //将String对象中的每一个下标位的对象保存在数组中
        char[] b = a.toCharArray();
        //转换成响应的ASCLL
        long result = 0;
        for (char c : b) {
            //System.out.println(Integer.valueOf(c));
            result |= c;
        }
        System.out.println(result);
    }
}
