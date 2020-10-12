package command;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/8/17 7:12 下午
 **/
public class Invoker {

    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void action() {
        command.execute();
    }
}
