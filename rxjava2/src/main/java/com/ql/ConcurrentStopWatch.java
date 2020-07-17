package com.ql;

import org.springframework.util.StopWatch;

/**
 * @author qiuliang@xiaomi.com
 * @desc 支持多线程的代码执行耗时监控类，以空间换时间，多线程情况下，为每个线程提供一个StopWatch对象，支持更高的并发，相比synchronized的以时间换空间方式，不需要加锁，性能更好
 * @date 2020/7/14 3:24 下午
 **/
public class ConcurrentStopWatch {

    private ThreadLocal<StopWatch> stopWatch;

    public ConcurrentStopWatch() {
        this.stopWatch = new ThreadLocal<StopWatch>() {
            @Override
            protected StopWatch initialValue() {
                return new StopWatch();
            }
        };
    }


    public ConcurrentStopWatch(final String name) {
        this.stopWatch = new ThreadLocal<StopWatch>() {
            @Override
            protected StopWatch initialValue() {
                return new StopWatch(name);
            }
        };
    }

    public void start(String taskName) {
        try {
            if (!this.stopWatch.get().isRunning()) {
                this.stopWatch.get().start(taskName);
            } else {
                this.stopWatch.get().stop();
            }
        } catch (Exception e) {
            System.err.println(String.format("开始监控任务%s失败,原因:%s", taskName, e.getCause()));
        }
    }

    public void stop() {
        try {
            if (this.stopWatch.get().isRunning()) {
                this.stopWatch.get().stop();
            }
        } catch (Exception e) {
            System.err.println(String.format("停止监控任务失败,原因:%s", e.getCause()));
        }
    }

    public String prettyPrint() {
        try {
            return this.stopWatch.get().prettyPrint();
        } catch (Exception e) {
            System.err.println(String.format("打印监控日志失败,原因:%s", e.getCause()));
        }
        return "";
    }
}
