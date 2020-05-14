package com.ql;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/5/7 5:41 下午
 **/
public class CacheTest {

    private static Map<String, String> map = new HashMap<String, String>();

    public static void main(String[] args) throws InterruptedException {
        LoadingCache<String, String> graphs = CacheBuilder.newBuilder()
//                .expireAfterAccess(1, TimeUnit.SECONDS)
                .refreshAfterWrite(10,TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                            @Override
                            public String load(String key) { // no checked exception
                                return createExpensiveGraph(key);
                            }
                        });
        map.put("key","value");
        map.put("key2","value2");
        new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map.put("key3", "value3");
            }
        }).start();
        while (true) {
            String result = graphs.getUnchecked("key");
            System.out.println(result);
            try {
                String result3 = graphs.getUnchecked("key3");
                System.out.println(result3);
            } catch (CacheLoader.InvalidCacheLoadException e) {
                e.printStackTrace();
            }
            TimeUnit.SECONDS.sleep(2);
        }
    }

    private static String createExpensiveGraph(String key) {
//        if ("key".equals(key)) {
//            return new Integer(new Random().nextInt()).toString();
//        } else {
//            return key;
//        }
        return map.get(key);
    }

}
