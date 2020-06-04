package com.ql.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/6/4 2:54 下午
 **/
public class ThreadTest {

    public static void main(String[] args) {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(2, 2, 2,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10000));
        executorService.allowCoreThreadTimeOut(true);

        while (true) {
            executorService.submit(() -> {
                System.out.println("task running");
                System.out.println("current thread id is " + Thread.currentThread().getId());
                try {
                    Thread.sleep(2001);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
