package proxy;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/8/13 7:12 下午
 **/
public class RealSubject implements Subject {
    @Override
    public void doSomething() {
        System.out.println("RealSubject do something");
    }
}
