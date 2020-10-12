package com.ql;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/9/21 3:31 下午
 **/
public class LamdaTest {

    @Test
    public void test_function() {
        Function<Integer, Integer> f1 = e -> e * 2;
        Function<Integer, Integer> f2 = e -> e * e;
        System.out.println(f1.andThen(f2).apply(3));
        System.out.println(f1.compose(f2).apply(3));
    }

    @Test
    public void test_consumer() {
        Consumer<Integer> c1 = e -> {
            System.out.println("c1:" + e);
        };
        Consumer<Integer> c2 = e -> {
            System.out.println("c2:" + e);
        };
        c1.andThen(c2).accept(1);
    }

    @Test
    public void test_predicate() {
        String name = "sdd";
        Predicate<String> p1 = x -> x.equals(name);
        Predicate<String> p2 = x -> x.length() < 2;
        System.out.println(p1.and(p2).test(name));
        System.out.println(p1.and(p2.negate()).test(name));
        System.out.println(p1.or(p2).test(name));
        System.out.println(Predicate.isEqual(name).test(name));
        System.out.println(Predicate.isEqual(name).test(name));
    }

    @Test
    public void test_supplier() {
        Supplier<String> s1 = () -> "hello, sdd";
        System.out.println(s1.get());
        Supplier<Student> s2 = () -> new Student();
        System.out.println(s2.get());
        Supplier<Student> s3 = Student::new;
        System.out.println(s3.get());
    }

    public class Student {

    }

}
