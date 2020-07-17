package com.ql;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.guava.CaffeinatedGuava;
import com.google.common.cache.CacheLoader;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/7/15 7:48 下午
 **/
public class CaffeineTest {

    private static Map<String, String> map = new HashMap<String, String>();

    static {
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
    }

    private static String createExpensiveGraph(String key) {
        System.out.println("current thread:" + Thread.currentThread().getName() + " createExpensiveGraph,key=" + key);
        return map.get(key);
    }

    @Test
    public void test() throws InterruptedException {
        LoadingCache<String, String> graphs = Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .build(key -> createExpensiveGraph(key));
        graphs.get("key1");
        TimeUnit.SECONDS.sleep(1);
        graphs.get("key1");
        TimeUnit.SECONDS.sleep(4);
        graphs.get("key1");
    }

    @Test
    public void test2() throws InterruptedException {
        // Guava's LoadingCache interface
        com.google.common.cache.LoadingCache <String, String> graphs = CaffeinatedGuava.build(
                Caffeine.newBuilder().maximumSize(10000)
                        .expireAfterWrite(5, TimeUnit.SECONDS)
                        .refreshAfterWrite(1, TimeUnit.SECONDS),
                new CacheLoader<String, String>() { // Guava's CacheLoader
                    @Override public String load(String key) throws Exception {
                        return createExpensiveGraph(key);
                    }
                });
        graphs.getUnchecked("key1");
        TimeUnit.SECONDS.sleep(1);
        graphs.getUnchecked("key1");
        TimeUnit.SECONDS.sleep(4);
        graphs.getUnchecked("key1");
    }
}
