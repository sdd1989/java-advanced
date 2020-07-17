package com.ql;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Data;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/6/4 4:01 下午
 **/
public class GsonTest {

    public static void main(String[] args) {
//        JsonObject deeplinkParam = new JsonObject();
//        deeplinkParam.addProperty("teamId", "teamId");
//        deeplinkParam.addProperty("inviter", "inviter");
//        System.out.println(deeplinkParam.toString());
        Foo foo = new Gson().fromJson("{\n" +
                "\"name\":\"sdd\"\n" +
                "}", Foo.class);
        System.out.println(foo);
    }

    @Data
    public class Foo {
        private String name;
        private Integer age;
    }
}
