package proxy;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/8/13 7:10 下午
 **/
public class ProxyMain {

    public static void main(String[] args) {
        // 保存生成的代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // jdk动态代理测试
        Subject subject = new JDKDynamicProxy(new RealSubject()).getProxy();
        subject.doSomething();
    }
}
