package com.ql;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/4/30 7:04 下午
 **/
public class SpecificHandler2 extends AbsTemplate {

    @Override
    protected void method2(BaseParam params) {
        System.out.println("com.ql.SpecificHandler2.method2,params:"+params.getName());
    }
}
