package com.ql;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/7/13 3:56 下午
 **/
public class RxJavaTest2 {

    public static void main(String[] args) {

        Single<Integer> single = Single.create((SingleEmitter<Integer> e) -> {
            e.onSuccess(1);
            //e.onError(new RuntimeException());
        }).subscribeOn(Schedulers.io())
        .doOnSuccess(e -> {
            System.out.println("doOnSuccess:" + e + " " + Thread.currentThread().getName());
        });

        Single<Integer> single2 = Single.create((SingleEmitter<Integer> e) -> {
            e.onSuccess(2);
            //e.onError(new RuntimeException());
        }).subscribeOn(Schedulers.io())
        .doOnSuccess(e -> {
                    System.out.println("doOnSuccess:" + e + " " + Thread.currentThread().getName());
        });

        Single.zip(single, single2, (a, b) -> {
            System.out.println(a + ":" + b);
            return a + b;
        })
        .doOnSuccess(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("accept:" + integer);
            }
        })
//                .subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                System.out.println("accept:" + integer);
//            }
//        });
        .subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println(Thread.currentThread().getName() + " onSubscribe");
            }

            @Override
            public void onSuccess(Integer integer) {
                System.out.println("onSuccess:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:" + e);
            }
        });
    }
}
