package com.ql;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;

import java.util.Arrays;
import java.util.Map;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/5/12 4:07 下午
 **/
public class TestGson {

    private static Gson GSON = new Gson();

    public static void main(String[] args) {
        String json = "{\n" +
                "\"1\":{\n" +
                "\"title\":\"【2元】邀请奖励到账啦\",\n" +
                "\"content\":\"推荐更多好友，4元奖励赚不停~\",\n" +
                "\"link\":\"bikan://web.goto/commonWebView?url_encode=http%3a%2f%2ffeed.dev.browser.miui.com%2fmobile-v2%2fact%2finvitation\",\n" +
                "\"docId\":\"auto_push_invitenotice_firstday\"\n" +
                "},\n" +
                "\"2\":{\n" +
                "\"title\":\"【1元】邀请奖励到账啦\",\n" +
                "\"content\":\"推荐更多好友，4元奖励赚不停~\",\n" +
                "\"link\":\"bikan://web.goto/commonWebView?url_encode=http%3a%2f%2ffeed.dev.browser.miui.com%2fmobile-v2%2fact%2finvitation\",\n" +
                "\"docId\":\"auto_push_invitenotice_nextday\"\n" +
                "},\n" +
                "\"3\":{\n" +
                "\"title\":\"【1元】邀请奖励已全部到账\",\n" +
                "\"content\":\"好友已完成3天阅读任务，推荐新好友继续赚4元>>\",\n" +
                "\"link\":\"bikan://web.goto/commonWebView?url_encode=http%3a%2f%2ffeed.dev.browser.miui.com%2fmobile-v2%2fact%2finvitation\",\n" +
                "\"docId\":\"auto_push_invitenotice_thirdday\"\n" +
                "},\n" +
                "\"-1\":{\n" +
                "\"title\":\"【邀好友成功】快提醒Ta阅读三篇文章\",\n" +
                "\"content\":\"完成每日阅读任务，你俩都可领4元奖励>>\",\n" +
                "\"link\":\"bikan://web.goto/commonWebView?url_encode=http%3a%2f%2ffeed.dev.browser.miui.com%2fmobile-v2%2fact%2finvitation\",\n" +
                "\"docId\":\"auto_push_invitenotice_success\"\n" +
                "}\n" +
                "}";
        TypeToken<Map<String, InviteNotice>> typeToken = new TypeToken<Map<String, InviteNotice>>() {
        };
        Map<String, InviteNotice> map = GSON.fromJson(json, typeToken.getType());
        System.out.println(map);
    }

    @Data
    public class InviteNotice {
        /**
         * 主标题
         */
        private String title;
        /**
         * 内容
         */
        private String content;
        /**
         * 链接
         */
        private String link;

        /**
         * auto push docId
         */
//        private String docId;

    }
}
