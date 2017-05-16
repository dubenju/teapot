package org.teapot.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Init implements IAction {

    @Override
    public void action(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
//    if (GzipUtilities.isGzipSupported(req) &&
//        !GzipUtilities.isGzipDisabled(req)) {
//
//    }
        Cookie userCookie = new Cookie("user", "user1234");
        userCookie.setMaxAge(60 * 60 * 24 * 365);
        resp.addCookie(userCookie);

        String docType = "<!DOCTYPE HTML>\n";
        out.println(docType +
            "<HTML>\n" +
            "<HEAD><TITLE>Hello</TITLE></HEAD>\n" +
            "<BODY BGCOLOR=\"#FDF5E6\">\n" +
            "<H1>Hello</H1>\n" +
            "<FORM ACTION=\"/teapot/t/AAA\">" +
            "First Parameter:<INPUT TYPE=\"TEXT\" NAME=\"param1\">\n" +
            "<INPUT TYPE=\"SUBMIT\">\n" +
            "</FORM>\n" +
            "</BODY>\n</HTML>");
    }

}
