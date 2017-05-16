package org.teapot.servlet.view.beetl;

import java.io.IOException;
import java.io.StringWriter;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

public class BeetlSample {

    public static void main(String[] args) {
        StringWriter writer = new StringWriter();
//        StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
        try {
            FileResourceLoader resourceLoader = new FileResourceLoader("./template/beetl", "utf-8");
            Configuration cfg = Configuration.defaultConfiguration();
            GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
            Template t = gt.getTemplate("hello.btl");
            t.binding("name", "beetl");
            //String str = t.render();
            t.renderTo(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("■输出结果");
        System.out.print(writer.toString());
    }
}
