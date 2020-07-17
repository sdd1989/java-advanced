package templatemethod;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/6/15 9:06 下午
 **/
public class Client2 {
    public static void main(String[] args) {
        MyTemplate myTemplate = new MyTemplate();
        myTemplate.execute(new CallBackI() {
            public void call() {
                System.out.println("call ...");
            }
        });
    }
}
