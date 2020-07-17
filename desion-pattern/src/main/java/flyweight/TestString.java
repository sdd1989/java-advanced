package flyweight;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/6/14 11:31 上午
 **/
public class TestString {
    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "abc";
        String str3 = new String("abc");
        String str4 = "ab" + "c";
        String str5 = new String("abcd");
        String str6 = new String("abcd");
        System.out.println("str1 == str2 is " + (str1 == str2));
        System.out.println("str1 == str3 is " + (str1 == str3));
        System.out.println("str1 == str4 is " + (str1 == str4));
        System.out.println("str1 == str3.intern() is " + (str1 == str3.intern()));
        System.out.println("str1 == str3 is " + (str1 == str3));
        System.out.println("str5.intern() == str6.intern() is " + (str5.intern() == str6.intern()));
        System.out.println("str5.intern() == str6 is " + (str5.intern() == str6));
    }
}
