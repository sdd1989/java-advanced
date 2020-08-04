package com.ql.commonslang;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/7/3 4:15 下午
 **/
public class TestString {

    private final static Pattern LONG_TEXT_PATTERN = Pattern.compile(".*￥([D#]*[A-Z0-9]+)￥.*", Pattern.DOTALL);

    public static void main(String[] args) {
        //System.out.println(RandomStringUtils.randomAlphanumeric(32));
        //System.out.println(RandomStringUtils.randomAlphanumeric(6));
        String str = "[红包]下载【想看资讯】\n" +
                "[红包]填我的邀请码￥D#IVKZ7LH￥\n" +
                "[红包]你我都有钱拿\n" +
                "[红包]复制此消息可自动填邀请码\n" +
                "[红包]下载地址：\n" +
                "http://a.app.qq.com/o/simple.jsp?pkgname=com.xiangkan.android&ckey=CK1441826083026&android_schema=bikan%3A%2F%2Fgoto%2FinviteCode%3Fcontent%3DIVKZ7LH";
        System.out.println(screenCode(str));
    }

    private static String screenCode(String inviteCode) {
        if (StringUtils.isBlank(inviteCode)) {
            return StringUtils.EMPTY;
        }
        Matcher matcher = LONG_TEXT_PATTERN.matcher(inviteCode);
        if (matcher.matches()) {
            return matcher.group(1);
        } else {
            return inviteCode;
        }
    }
}
