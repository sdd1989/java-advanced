package com.ql;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/4/30 6:31 下午
 **/
public class InheritTest {

    public static void main(String[] args) {
//        SpecificHandler2 handler = new SpecificHandler2();

        SpecificHandler2 handler = new SpecificHandler2();
        BaseParam param = new BaseParam();
//        SpecificHandler handler = new SpecificHandler<SubParam>();
//        SubParam param = new SubParam();

        param.setName("qiuliang");
        handler.excute(param);
    }
}
