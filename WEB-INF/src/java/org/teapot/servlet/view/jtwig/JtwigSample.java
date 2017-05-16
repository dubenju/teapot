package org.teapot.servlet.view.jtwig;

import java.io.File;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

public class JtwigSample {

    public static void main(String[] args) {
//        Path templatePath = Paths.get(Thread.currentThread().getContextClassLoader().getResource("./template/jtwig/sample.twig").toURI());
//
//        JtwigConfiguration config = new JtwigConfiguration();
//        JtwigTemplate template = new JtwigTemplate(templatePath.toFile(), config);
//        StringWriter writer = new StringWriter();
        //JtwigTemplate template = JtwigTemplate.classpathTemplate("./template/jtwig/sample.twig");
        JtwigTemplate template = JtwigTemplate.fileTemplate(new File("./template/jtwig/sample.twig"));

//        JtwigModelMap context = new JtwigModelMap();
//        context.put("word", "世界");
////        context.put("persons", Arrays.asList(new Person("磯野", "カツオ"), new Person("磯野", "ワカメ")));
//        context.put("containTag", "<script>Hello!!</script>");
        JtwigModel model = JtwigModel.newModel().with("word", "世界");
        template.render(model, System.out);
//        System.out.println(template.output(context));

    }

}
