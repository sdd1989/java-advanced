package com.ql;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/7/13 3:56 下午
 **/
public class RxJavaTest {

    public static void main(String[] args) throws InterruptedException {

        Single<Integer> single = Single.create((SingleEmitter<Integer> e) -> {
            //throw new RuntimeException();
            e.onSuccess(1);
            //e.onError(new RuntimeException());
        }).subscribeOn(Schedulers.io())
        .doOnSuccess(e -> {
            System.out.println("doOnSuccess:" + e + " " + Thread.currentThread().getName());
        });
                single.subscribe((integer, throwable) -> System.out.println("accept:" + integer + " " + Thread.currentThread().getName()));

        single.subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe:" + d + " " + Thread.currentThread().getName());
            }

            @SneakyThrows
            @Override
            public void onSuccess(Integer s) {
                System.out.println("onSuccess:" + s + " " + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:" + e + " " + Thread.currentThread().getName());
            }
        });
        System.out.println("main end");

        //Thread.sleep(1000);
    }

    @Test
    public void test() {
        System.out.println("currentThread:" + Thread.currentThread().getName());
        Single.create((SingleEmitter<Integer> emitter) -> {
            System.out.println("createThread:" + Thread.currentThread().getName());
            emitter.onSuccess(1);
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe((e,t)-> {
            System.out.println("mapThread:" + Thread.currentThread().getName());
        });
    }

    @Test
    public void test2() {
        Single<Integer> single = Single.create((SingleEmitter<Integer> e) -> {
            e.onSuccess(1);
            System.out.println("SingleEmitter");
        }).subscribeOn(Schedulers.io())
        .doOnSuccess(e -> {
            System.out.println("doOnSuccess:" + e + " " + Thread.currentThread().getName());
        }).cache();
        single.subscribe((integer, throwable) -> System.out.println("accept:" + integer + " " + Thread.currentThread().getName()));
        single.subscribe((integer, throwable) -> System.out.println("accept:" + integer + " " + Thread.currentThread().getName()));

    }

    @Test
    public void test3() {
        Single<Integer> single = Single.create((SingleEmitter<Integer> e) -> {
            e.onSuccess(1);
            //System.out.println("SingleEmitter:" + Thread.currentThread().getName());
        }).subscribeOn(Schedulers.io())
        .doOnSuccess(e -> {
            System.out.println(System.nanoTime() + " doOnSuccess:" + e + ":" + Thread.currentThread().getName());
        });

        Single<Integer> single2 = Single.create((SingleEmitter<Integer> e) -> {
            e.onSuccess(2);
            //System.out.println("SingleEmitter2:" + Thread.currentThread().getName());
        }).subscribeOn(Schedulers.io())
                .doOnSuccess(e -> {
                    System.out.println(System.nanoTime() + " doOnSuccess2:" + e + ":" + Thread.currentThread().getName());
                });
        Single.zip(single, single2, (integer, integer2) -> {
            return integer + integer2;
        }).doOnSuccess(e -> {
            System.out.println(System.nanoTime() + " doOnSuccess on zip:" + Thread.currentThread().getName());
        }).subscribe(e -> {
            System.out.println(System.nanoTime() + " subscribe:" + e + ":" + Thread.currentThread().getName());
        });

    }

    @Test
    public void test4() throws InterruptedException {
        ConcurrentStopWatch stopWatch = new ConcurrentStopWatch("Monitor");
        System.out.println(Thread.currentThread().getName());
        Single<Integer> single = Single.create((SingleEmitter<Integer> e) -> {
            stopWatch.start("single");
            TimeUnit.SECONDS.sleep(1);
            e.onSuccess(1);
            stopWatch.stop();
            System.out.println(stopWatch.prettyPrint() + " " + Thread.currentThread().getName());
        }).subscribeOn(Schedulers.io())
        .doOnSuccess(e -> {
            System.out.println(System.nanoTime() + " doOnSuccess:" + e + ":" + Thread.currentThread().getName());
        });

        Single<Integer> single2 = Single.create((SingleEmitter<Integer> e) -> {
            stopWatch.start("single2");
            TimeUnit.SECONDS.sleep(2);
            e.onSuccess(2);
            stopWatch.stop();
            System.out.println(stopWatch.prettyPrint() + " " + Thread.currentThread().getName());
        }).subscribeOn(Schedulers.io())
        .doOnSuccess(e -> {
            System.out.println(System.nanoTime() + " doOnSuccess2:" + e + ":" + Thread.currentThread().getName());
        });
        Single.zip(single, single2, (integer, integer2) -> integer + integer2).doOnSuccess(e -> {
            System.out.println(System.nanoTime() + " doOnSuccess on zip:" + Thread.currentThread().getName());
        }).subscribe(e -> {
            System.out.println(System.nanoTime() + " subscribe:" + e + ":" + Thread.currentThread().getName());
        });

        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    public void test5() {
        Flowable.just(1,2,3,4,5,6,7,8,9)
            .flatMap(it-> {
                System.out.println(Thread.currentThread());
                return Flowable.just(it)
                        .subscribeOn(Schedulers.io())//控制在哪些线程上生成sub-stream
                        .map(i->{
                            System.out.println(i+"  thread: "+ Thread.currentThread());
                            return  i;
                });
            })
            .subscribe(it->{
                //System.out.println("onNext:"+it+"  thread: "+ Thread.currentThread());
            });
    }

    @Test
    public void test6() {
        Flowable.range(1,1000)
                .parallel()
                .runOn(Schedulers.io())//指定在哪些线程上并发执行
                .map(it->{
                    System.out.println(it+"  thread: "+ Thread.currentThread());
                    return  it;
                })
                .sequential()
                .subscribe(it->{
                    //System.out.println("onNext:"+it+"  thread: "+ Thread.currentThread());
                });
    }

    @Test
    public void test7() {
        Single.create((SingleEmitter<List<Integer>> e) -> {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < 1000;i++) {
                list.add(i);
            }
            e.onSuccess(list);
        })
                .subscribeOn(Schedulers.io())
                .flatMap(e -> {
                    System.out.println("flatMap:" + Thread.currentThread().getName());
                    return Flowable.fromIterable(e).toList();
                })
                .subscribe();
    }


}
