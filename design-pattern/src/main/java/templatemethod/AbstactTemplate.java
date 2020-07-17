package templatemethod;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/6/15 8:42 下午
 **/
public abstract class AbstactTemplate {

    public void execute() {
        step1();
        step2();
        hookMethod();
    }

    /**
     * 钩子方法，子类可以覆盖
     */
    protected void hookMethod() {

    }

    /**
     * 子类实现
     */
    protected abstract void step2();

    private void step1() {
        System.out.println("step1 ...");
    }
}
