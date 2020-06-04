package com.ql;

import com.google.gson.JsonObject;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/6/4 4:01 下午
 **/
public class GsonTest {

    public static void main(String[] args) {
        JsonObject deeplinkParam = new JsonObject();
        deeplinkParam.addProperty("teamId", "teamId");
        deeplinkParam.addProperty("inviter", "inviter");
        System.out.println(deeplinkParam.toString());
    }
}
