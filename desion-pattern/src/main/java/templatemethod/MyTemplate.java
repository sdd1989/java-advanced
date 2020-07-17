package templatemethod;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/6/15 9:03 下午
 **/
public class MyTemplate {

    public void execute(CallBackI callBack) {
        step1();
        callBack.call();
        step3();
    }

    private void step3() {
        System.out.println("MyTemplate.step3 ...");
    }

    private void step1() {
        System.out.println("MyTemplate.step1 ...");
    }
}
