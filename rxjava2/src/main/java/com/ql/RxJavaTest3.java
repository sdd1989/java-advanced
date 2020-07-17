package com.ql;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/7/13 7:49 下午
 **/
public class RxJavaTest3 {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("the", "quick", "brown", "fox", "jumped", "over", "the", "lazy", "dog");

        //rxjava
        Observable.fromIterable(words)
                .flatMap(word -> Observable.fromArray(word.split("")))
                .distinct()
                .sorted()
                .zipWith(Observable.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string)
                ).subscribe(System.out::println);

        //java8
        words.stream()
                .flatMap((Function<String, Stream<?>>) s -> Arrays.stream(s.split("")))
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }
}
