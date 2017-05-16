package org.teapot.servlet.view.httl;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import httl.Engine;
import httl.Template;

public class HttlSample {

    public static void main(String[] args) {
        StringWriter writer = new StringWriter();

        Engine engine = Engine.getEngine();

        Map<String, Object> context = new HashMap<>();
        context.put("word", "世界");
//        context.put("persons", Arrays.asList(new Person("磯野", "カツオ"), new Person("磯野", "ワカメ")));
        context.put("containTag", "<script>Hello!!</script>");

        try {
            Template template = engine.getTemplate(("/apache-tomcat-8.0.32/webapps/teapot/template/httl/sample.httl"), "UTF-8");
            template.render(context, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(writer);

    }

}
