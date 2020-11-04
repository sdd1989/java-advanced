package com.ql;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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

    @Test
    public void test_GroupingBy() {
        List<Student> students = Lists.newArrayList(new Student("sdd", 23), new Student("sdd", 22)
        , new Student("gml", 22));
        Map<String, List<Student>> map = students.stream().collect(Collectors.groupingBy(e -> e.getName()));
        System.out.println(map);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class Student {
        private String name;
        private Integer age;
    }


}
