package com.ql;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

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
//        Foo foo = new Gson().fromJson("{\n" +
//                "\"name\":\"sdd\"\n" + "\"name2\":\"sdd\"\n" +
//                "}", Foo.class);
//        JsonObject jsonObject = new JsonParser().parse("{" +
//                "\"name\":\"sdd\",\"name2\":\"sdd\"" +
//                "}").getAsJsonObject();
//        JsonElement je = jsonObject.get("name2");
//        System.out.println(je.getAsString());
        String json = "{\"name\":\"sdd\", \"gender\":\"male\"}";
        Foo foo = new Gson().fromJson(json, Foo.class);
        System.out.println(foo);
    }

    @Data
    public class Foo {
        private String name;
        private Integer age;
        private Gender gender;
    }

    @ToString
    @AllArgsConstructor
    public enum Gender {
        @SerializedName("male")
        MAlE("male"),
        @SerializedName("female")
        FEMALE("female");
        private String desc;
    }
}
