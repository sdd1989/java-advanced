package command;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/8/17 7:17 下午
 **/
public class Client {

    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        ConcreteCommand concreteCommand = new ConcreteCommand(receiver);
        Invoker invoker = new Invoker(concreteCommand);
        invoker.action();
    }
}
