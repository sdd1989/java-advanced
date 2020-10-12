package com.ql;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/9/30 3:48 下午
 **/
public class ForkAndJoinTest {

    private static ForkJoinPool forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> list = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        ForkAndJoinRequest request = new ForkAndJoinRequest(0, list.size() - 1, list);
        forkJoinPool.execute(request);
        System.out.println("sum:" + request.get());
    }

    @AllArgsConstructor
    public static class ForkAndJoinRequest extends RecursiveTask<Integer> {

        private int start;

        private int end;

        private List<Integer> list;

        @Override
        protected Integer compute() {
            int result = 0;
            if ((end - start) < 5) {
                for (int i = start; i <= end; i++) {
                    result += i;
                }
            } else {
                int mid = (start + end) / 2;
                ForkAndJoinRequest request1 = new ForkAndJoinRequest(start, mid, list);
                request1.fork();
                ForkAndJoinRequest request2 = new ForkAndJoinRequest(mid + 1, end, list);
                request2.fork();
                return request1.join() + request2.join();
            }
            return result;
        }
    }
}
