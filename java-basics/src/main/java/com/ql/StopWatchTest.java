package com.ql;


import com.google.common.base.Stopwatch;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/7/14 2:38 下午
 **/
public class StopWatchTest {

    public static void main(String[] args) throws InterruptedException {
//        // 创建stopwatch并开始计时
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        Thread.sleep(1980);
//        // 以秒打印从计时开始至现在的所用时间,向下取整
//        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS)); // 1
//        // 停止计时
//        stopwatch.stop();
//        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS)); // 1
//        // 再次计时
//        stopwatch.start();
//        Thread.sleep(100);
//        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS)); // 2
//        // 重置并开始
//        stopwatch.reset().start();
//        Thread.sleep(1030);
//        // 检查是否运行
//        System.out.println(stopwatch.isRunning()); // true
//        long millis = stopwatch.elapsed(TimeUnit.MILLISECONDS); // 1031
//        System.out.println(millis);
//        // 打印
//        System.out.println(stopwatch.toString()); // 1.03 s
        StopWatch first = new StopWatch("First");
        first.start("A");
        Thread.sleep(200);
        first.stop();
        first.start("B");
        Thread.sleep(200);
        first.stop();
        first.start("C");
        Thread.sleep(120);
        first.stop();
        System.out.println(first.prettyPrint());
    }
}
