package command;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/8/17 7:12 下午
 **/
public class ConcreteCommand implements Command {

    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }
}
