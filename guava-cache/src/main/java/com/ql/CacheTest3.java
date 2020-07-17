package com.ql;

import com.google.common.cache.*;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.omg.CORBA.TIMEOUT;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/6/2 5:04 下午
 **/
public class CacheTest3 {

    private static Map<String, String> map = new HashMap<String, String>();

    static {
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
    }

    static LoadingCache<String, String> graphs = CacheBuilder.newBuilder()
            //.refreshAfterWrite(1, TimeUnit.SECONDS)
            .expireAfterWrite(1, TimeUnit.SECONDS)
            .maximumSize(2)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) { // no checked exception
                    return createExpensiveGraph(key);
                }
            });

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //graphs.refresh("key1");
        for (int i = 0; i < 3; i++) {
            System.out.println(graphs.getUnchecked("key1"));
            //System.out.println(graphs.getUnchecked("key"+(i+1)));
            TimeUnit.SECONDS.sleep(1);
        }
    }

    private static String createExpensiveGraph(String key) {
        System.out.println("current thread:" + Thread.currentThread().getName() + " createExpensiveGraph,key=" + key);
        return map.get(key);
    }

    @Test
    public void testWeight() {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .weigher(new Weigher<String, String>() {
                    @Override
                    public int weigh(String key, String value) {
                        if ("key1".equals(key)) {
                            return 2;
                        } else {
                            return 1;
                        }
                    }
                })
                .maximumWeight(3)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) { // no checked exception
                        return createExpensiveGraph(key);
                    }
                });
        for (int i = 0;i < 3;i++) {
             cache.getUnchecked("key" + (i + 1));
        }
    }

    @Test
    public void testLRU() {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) { // no checked exception
                        return createExpensiveGraph(key);
                    }
                });
        cache.getUnchecked("key1");
        cache.getUnchecked("key2");
        cache.getUnchecked("key1");
        cache.getUnchecked("key3");

    }

    @Test
    public void testReference() {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .weakKeys()
                .weakValues()
                .softValues()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) { // no checked exception
                        return loadBy(key);
                    }
                });
        for (int i = 0;i <1000000; i++)
            System.out.println(cache.getUnchecked("key"+(i+1)));
    }

    private String loadBy(String key) {
        return RandomStringUtils.randomAlphabetic(32);
    }

    @Test
    public void testRemove() {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) { // no checked exception
                        return createExpensiveGraph(key);
                    }
                });
        cache.getUnchecked("key1");
        cache.getUnchecked("key2");
        System.out.println(cache.asMap());
        //cache.invalidate("key1");
        //cache.invalidateAll(Lists.newArrayList("key1","key2"));
        cache.invalidateAll();
        System.out.println(cache.asMap());
    }

    @Test
    public void testRemovalListener() throws InterruptedException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .refreshAfterWrite(1,TimeUnit.SECONDS)
                .removalListener(new RemovalListener<String, String>() {

                    @Override
                    public void onRemoval(RemovalNotification<String, String> notification) {
                        System.out.println(notification + ":" + notification.getCause());
                    }
                })
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) { // no checked exception
                        return createExpensiveGraph(key);
                    }
                });

        cache.getUnchecked("key1");
        cache.getUnchecked("key2");
        cache.getUnchecked("key3");
        TimeUnit.SECONDS.sleep(2);
        cache.getUnchecked("key2");
    }

    @Test
    public void testStats() throws InterruptedException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .refreshAfterWrite(1,TimeUnit.SECONDS)
                .recordStats()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) { // no checked exception
                        return createExpensiveGraph(key);
                    }
                });
        cache.getUnchecked("key1");
        cache.getUnchecked("key2");
        cache.getUnchecked("key3");
        TimeUnit.SECONDS.sleep(2);
        cache.getUnchecked("key2");
        System.out.println("Stats:" + cache.stats());
    }

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Test
    public void testAsynLoad() throws InterruptedException {
        //有些键不需要刷新，并且我们希望刷新是异步完成的

        LoadingCache<String, String> graphs = CacheBuilder.newBuilder()

                .maximumSize(1000)

                //.refreshAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .build(

                        new CacheLoader<String, String>() {

                            public String load(String key) { // no checked exception

                                return createExpensiveGraph(key);

                            }


                            public ListenableFuture<String> reload(final String key, String prevGraph) {

                                if (neverNeedsRefresh(key)) {

                                    return Futures.immediateFuture(prevGraph);

                                } else {

                                    // asynchronous!
                                    ListenableFutureTask<String> task = ListenableFutureTask.create(new Callable<String>() {

                                        public String call() {

                                            return createExpensiveGraph(key);


                                        }

                                    });

                                    executor.execute(task);

                                    return task;

                                }

                            }

                        });
        graphs.getUnchecked("key1");
        TimeUnit.SECONDS.sleep(2);
        graphs.getUnchecked("key1");

    }

    private boolean neverNeedsRefresh(String key) {
        return false;
    }

    @Test
    public void testLoad() {

    }

}
