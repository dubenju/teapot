package org.teapot.servlet.view.velocity;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class VelocitySample {

    public static void main(String[] args) {
        StringWriter writer = new StringWriter();
        Velocity.setProperty("file.resource.loader.path", "./template/velocity");

        Velocity.init();
        VelocityContext context = new VelocityContext();
        context.put("msgObj", "test");

        Template tmplate = Velocity.getTemplate("sample1.vm", "UTF8");
        tmplate.merge(context, writer);

        System.out.println("■输出结果");
        System.out.print(writer.toString());
    }

}
