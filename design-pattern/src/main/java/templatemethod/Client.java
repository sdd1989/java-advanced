package templatemethod;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/6/15 8:48 下午
 **/
public class Client {

    public static void main(String[] args) {
        ConcretClass1 concretClass1 = new ConcretClass1();
        concretClass1.execute();
        ConcretClass2 concretClass2 = new ConcretClass2();
        concretClass2.execute();
    }
}
