package flyweight;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/6/8 8:34 下午
 **/
public class TestIntegerValueOf {

    public static void main(String[] args) {
        Integer i = Integer.valueOf(10);
        System.out.println(i);
        Integer a = 6;
        Integer b = 6;
        System.out.println(a == b);
        Integer c = 128;
        Integer d = 128;
        System.out.println(c == d);
    }
}
