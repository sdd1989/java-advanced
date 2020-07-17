package com.ql;

import java.math.BigDecimal;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/6/28 12:24 下午
 **/
public class FloatTest {

    public static void main(String[] args) {
        BigDecimal bd = new BigDecimal(1.25);
        System.out.println(bd.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue());
    }
}
