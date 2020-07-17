package com.ql;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/7/14 5:22 下午
 **/
public class ListTest {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList();
        for (int i=0;i<1000;i++) {
            list.add(i);
        }
        list.stream().parallel().forEach(
                e->{
                    System.out.println(e + " " + Thread.currentThread().getName());
                }
        );
    }
}
