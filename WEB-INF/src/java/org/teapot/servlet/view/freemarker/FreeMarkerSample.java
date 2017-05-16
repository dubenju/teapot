package org.teapot.servlet.view.freemarker;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarkerSample {

    public static void main(String[] args) {
        StringWriter writer = new StringWriter();
        Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        try {
            cfg.setDirectoryForTemplateLoading(new File("./template/freemarker"));
            cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
            Map<String, String> root = new HashMap<String, String>();
            root.put("abc", "世界，你好");
            Template template = cfg.getTemplate("test.ftl");
            template.process(root, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("■输出结果");
        System.out.print(writer.toString());
    }

}
