package com.ql.freemaker;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/5/28 3:10 下午
 **/
public class FreemarkerStringTemplateTest {
    public static void main(String[] args) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        StringTemplateLoader stringLoader = new StringTemplateLoader();
//        stringLoader.putTemplate("myTemplate", "hello,${name},your age is ${age}");
        stringLoader.putTemplate("myTemplate", "hello,sdd,your age is 23");
        cfg.setTemplateLoader(stringLoader);
        Template template = cfg.getTemplate("myTemplate");
        Map root = new HashMap();
        root.put("name","sdd");
        root.put("age",23);
        root.put("remark","xxx");
        Writer out = new StringWriter(2048);
        template.process(root, out);
        out.flush();
        System.out.println(out.toString());
    }
}
