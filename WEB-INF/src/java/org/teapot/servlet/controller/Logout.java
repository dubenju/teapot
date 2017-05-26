package org.teapot.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class Logout extends AbstractController {

    @Override
    public void action(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("msgObj", "test");
        context.put("link", "http://192.168.11.32:8080/teapot/t/");

        Template tmplate = Velocity.getTemplate("Logout.vm", "UTF8");

        PrintWriter writer = resp.getWriter();
        tmplate.merge(context, writer);
    }

}
