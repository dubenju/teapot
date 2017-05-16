package org.teapot.servlet.view.thymeleaf;

import java.io.StringWriter;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

public class ThymeleafSample {

    public static void main(String[] args) {
        StringWriter writer = new StringWriter();
        TemplateEngine templateEngine = new TemplateEngine();
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setTemplateMode("XHTML");
        resolver.setPrefix("./template/thymeleaf/");
        resolver.setSuffix(".htm");
        templateEngine.setTemplateResolver(resolver);

        Context ctx = new Context();
//        VariablesMap<String, Object> map = ctx.getVariables();
        ctx.setVariable("args", "test");

        templateEngine.process("sample", ctx, writer);
        System.out.println("■输出结果");
        System.out.print(writer.toString());
//        writer.close();
    }

}
