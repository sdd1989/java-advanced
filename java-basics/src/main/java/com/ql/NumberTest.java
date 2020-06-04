package com.ql;

import java.math.BigDecimal;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/5/28 6:57 下午
 **/
public class NumberTest {

    public static void main(String[] args) {
//        float cashYuan = new BigDecimal(130).divide(new BigDecimal(100)).floatValue();
        float cashYuan = (float) 130 / 100;
        System.out.println(cashYuan);
        String cashStr = String.format("%.1f", cashYuan);
        System.out.println(cashStr);
    }
}
