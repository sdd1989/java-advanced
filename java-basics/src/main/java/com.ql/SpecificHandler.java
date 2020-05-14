package com.ql;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/4/30 6:48 下午
 **/
public class SpecificHandler<T extends SubParam> extends SubTemplate<T> {

    @Override
    protected void method2(T params) {
        System.out.println("com.ql.SpecificHandler.method2");
    }
}
