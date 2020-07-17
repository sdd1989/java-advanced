package com.ql;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/7/2 4:47 下午
 **/
public class EncoderTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(URLDecoder.decode("bikan%3A%2F%2Fweb.goto%2FcommonWebView%3Furl_encode%3Dhttps%253a%252f%252ffeed.browser.miui.com%252fmobile-v2%252fact%252finvitation%26full_screen%3Dtrue", "UTF-8"));
    }
}
