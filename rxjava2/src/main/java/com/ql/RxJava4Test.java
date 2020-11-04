package com.ql;

import io.reactivex.*;
import io.reactivex.schedulers.Schedulers;
import lombok.Data;
import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/10/13 5:15 下午
 **/
public class RxJava4Test {

    @Test
    public void test_zip() {

        Foo foo = new Foo();
        Single<Foo> singleSource = Single.create((SingleEmitter<Foo> e) -> {
            foo.setName("sdd");
            e.onSuccess(foo);
        }).subscribeOn(Schedulers.io());

        Single<Foo> single1 = singleSource.observeOn(Schedulers.io()).map(e -> {
            e.setAge(23);
            return e;
        });

        Single<Foo> single2 = singleSource.observeOn(Schedulers.io()).map(e -> {
            e.setGender((byte) 1);
            return e;
        });
        Single.zip(single1, single2, (s1, s2) -> {
            System.out.println("s1:" + s1);
            System.out.println("s2:" + s2);
            return s1;
        }).blockingGet();

    }

    @Data
    private class Foo {
        private String name;
        private int age;
        private byte gender;
    }

    @Test
    public void test_flowable() throws InterruptedException {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 1000; i++) {
                    e.onNext(i);
                }
            }
        }, BackpressureStrategy.DROP).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("onSubscribe");
                        subscription.request(1000);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("onError: " + throwable);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
        TimeUnit.SECONDS.sleep(100);
    }
}
