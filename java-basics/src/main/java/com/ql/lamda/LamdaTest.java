package com.ql.lamda;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/5/28 10:30 上午
 **/
@Slf4j
public class LamdaTest {

    public static void main(String[] args) {
        Map<String,String> map1 = new HashMap<String,String>();
        map1.put("name", "sdd");
        map1.put("age", "23");
        Map<String,String> map2 = new HashMap<String,String>();
        map2.put("name", "gml");
        map2.put("age", "22");
        Map<String, String> mapResult = Stream.of(map1, map2)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1 + "," + v2));
        log.info("mapResult:{}", mapResult);
//        System.out.println(mapResult);
    }
}
