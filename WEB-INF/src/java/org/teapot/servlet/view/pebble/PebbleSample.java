package org.teapot.servlet.view.pebble;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

public class PebbleSample {

    public static void main(String[] args) {
        StringWriter writer = new StringWriter();
        PebbleEngine engine = new PebbleEngine.Builder().build();
        try {
            PebbleTemplate compiledTemplate = engine.getTemplate("./template/pebble/sample.peb");
            Map<String, Object> context = new HashMap<>();
            context.put("name", "Mitchell");
            context.put("word", "世界");
//            context.put("persons", Arrays.asList(new Person("磯野", "カツオ"), new Person("磯野", "ワカメ")));
            context.put("containTag", "<script>Hello!!</script>");

            compiledTemplate.evaluate(writer, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("■输出结果");
        System.out.print(writer.toString());
    }

}
