package com.ql;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/4/30 6:40 下午
 **/
public abstract class AbsTemplate<T extends BaseParam> {

    public void excute(T params){
        method1();
        method2(params);
    }

    protected abstract void method2(T params);

    private void method1() {

    }
}
