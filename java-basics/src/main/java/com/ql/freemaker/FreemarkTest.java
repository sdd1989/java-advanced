package com.ql.freemaker;

import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/5/28 2:17 下午
 **/
public class FreemarkTest {

    public static void main(String[] args) throws Exception {
        generateFileByTemplate("test.ftl", new File("test.txt"), new HashMap<>());
    }

    private static void generateFileByTemplate(final String templateName, File file, Map<String,Object> dataMap) throws Exception{
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
        dataMap.put("name","sdd");
        dataMap.put("age","23");
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        template.process(dataMap,out);
    }
}
