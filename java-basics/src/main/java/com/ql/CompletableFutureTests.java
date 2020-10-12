package com.ql;

import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/8/4 9:52 上午
 **/
public class CompletableFutureTests {

    @Test
    public void testRunAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "===runAsync...");
        });
        System.out.println("future.get():" + future.get());
    }

    @Test
    public void testSupplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "===supplyAsync...");
            return "supplyAsync";
        });
        System.out.println("future.get():" + future.get());
    }

    @Test
    public void whenComplete() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            if(new Random().nextInt() % 2 >= 0) {
                int i = 12/0;
            }
            System.out.println("run end ...");
        });

        future.whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void t, Throwable action) {
                System.out.println("执行完成！");
            }

        });

        future.exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable t) {
                System.out.println("执行失败！"+t.getMessage());
                return null;
            }
        });

        TimeUnit.SECONDS.sleep(2);
    }

    @Test
    public void test_thenApply() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                //int i= 10/0;
                long result = new Random().nextInt(100);
                System.out.println(Thread.currentThread().getName() + "===result1="+result);
                return result;
            }
        }).thenApply(t -> {
            long result = t*5;
            System.out.println(Thread.currentThread().getName() + "===result2="+result);
            return result;
        });

        long result = future.get();
        System.out.println(result);
    }

    @Test
    public void test_handle() throws Exception{
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {

            @Override
            public Integer get() {
                //int i= 10/0;
                System.out.println(Thread.currentThread().getName() + "===get()");
                return new Random().nextInt(10);
            }
        }).handle(new BiFunction<Integer, Throwable, Integer>() {
            @Override
            public Integer apply(Integer param, Throwable throwable) {
                System.out.println(Thread.currentThread().getName() + "===param:" + param + " throwable:" + throwable);
                int result = -1;
                if(throwable==null){
                    result = param * 2;
                }else{
                    System.out.println(throwable.getMessage());
                }
                return result;
            }
        });
        System.out.println(future.get());
    }

    @Test
    public void test_join() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "===supplyAsync...");
            return "supplyAsync";
        }).whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                System.out.println(Thread.currentThread().getName() + "===s:" + s + "throwable:" + throwable);
            }
        }).toCompletableFuture().join();
        System.out.println("join:" + result);
    }

    @Test
    public void thenAccept() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int res = new Random().nextInt(10);
                System.out.println(Thread.currentThread().getName() + "===get() res:" + res);
                return res;
            }
        }).thenAccept(res -> {
            System.out.println(Thread.currentThread().getName() + "===thenAccept res:" + res);
        });
        future.get();
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        StopWatch stopWatch = new StopWatch("CompletableFuture");
        stopWatch.start();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {}
            System.out.println(Thread.currentThread().getName() + "==test1");
            return "test1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {}
            System.out.println(Thread.currentThread().getName() + "==test2");
            return "test2";
        });
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {}
            System.out.println(Thread.currentThread().getName() + "==test3");
            return "test3";
        });
        CompletableFuture.allOf(future1,future2,future3).get();
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    @Test
    public void test_thenCombine() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {}
            System.out.println(Thread.currentThread().getName() + ":" + "future1");
            return "hello";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {}
            System.out.println(Thread.currentThread().getName() + ":" + "future2");
            return "sdd";
        });
        CompletableFuture<String> result = future1.thenCombine(future2, (t, u) -> {
            System.out.println(Thread.currentThread().getName() + ":" + "result");
            return t+" "+u;
        });
        System.out.println(result.get());
//        TimeUnit.SECONDS.sleep(3);
    }
}
