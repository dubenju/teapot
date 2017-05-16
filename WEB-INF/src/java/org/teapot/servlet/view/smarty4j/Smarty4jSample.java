package org.teapot.servlet.view.smarty4j;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.lilystudio.smarty4j.Context;
import org.lilystudio.smarty4j.Engine;
import org.lilystudio.smarty4j.Template;
import org.lilystudio.smarty4j.TemplateException;

public class Smarty4jSample {

    public static void main(String[] args) {
        StringWriter writer = new StringWriter();
        Engine engine = new Engine();
        engine.setTemplatePath("./template/smarty4j/");
        Context context = new Context();
//        Map<String, Object> map = new HashMap<>();
//        map.put("title", "世界");
//        context.putAll(map);
        context.set("title", "欢迎光临！");
        try {
            // tpl
            Template template = engine.getTemplate("a.tpl");
            template.merge(context, writer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        System.out.println("■输出结果");
        System.out.print(writer.toString());
    }

}
