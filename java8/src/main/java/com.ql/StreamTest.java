package com.ql;

import com.google.common.collect.Lists;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/9/28 6:25 下午
 **/
public class StreamTest {

    public static void main(String[] args) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("test-");
        executor.initialize();
        for (int i = 0; i < 100; i++) {
            executor.execute(() -> excute());
        }
        System.out.println("main end");
    }

    private static void excute() {
        StopWatch stopWatch = new StopWatch("stream");
        List<Integer> list = Lists.newArrayList();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        stopWatch.start("stream");
        //long start = System.currentTimeMillis();
        list.forEach(StreamTest::doRun);
        stopWatch.stop();
        //System.out.println("stream cost:" + (System.currentTimeMillis() - start));
        //start = System.currentTimeMillis();
        stopWatch.start("parallelStream");
        list.parallelStream().forEach(StreamTest::doRun);
        stopWatch.stop();
        //System.out.println("parallelStream cost:" + (System.currentTimeMillis() - start));
        //start = System.currentTimeMillis();
        stopWatch.start("parallelStream.forEachOrdered");
        list.parallelStream().forEachOrdered(StreamTest::doRun);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        //System.out.println("parallelStream.forEachOrdered cost:" + (System.currentTimeMillis() - start));
    }

    private static void doRun(int e) {
        try {
            //System.out.println(Thread.currentThread().getName());
            Thread.sleep(50);
            //System.out.println(e);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
