package com.ql;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/6/2 5:04 下午
 **/
public class CacheTest2 {

    private static Map<String, String> map = new HashMap<String, String>();

    public static void main(String[] args) throws InterruptedException {
        map.put("key","value");
        map.put("key2","value2");
        LoadingCache<String, String> graphs = CacheBuilder.newBuilder()
                .refreshAfterWrite(10, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) { // no checked exception
                        return createExpensiveGraph(key);
                    }
                });
        graphs.refresh("");
    }

    private static String createExpensiveGraph(String key) {
        System.out.println("createExpensiveGraph,key="+key);
        return map.get(key);
    }
}
