package org.teapot.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Reg implements IAction {

    @Override
    public void action(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String event = req.getParameter("register");
//        log("Servlet(Http) doGet() event = " + event);
        resp.setContentType("text/html;charset=UTF-8");
        if (event == null || event.length() <= 0) {
            PrintWriter out = resp.getWriter();
            String docType = "<!DOCTYPE HTML>\n";
            out.println(docType +
                "<HTML lang='zh-CN'>\n" +
                "<HEAD>\n" +
                "<meta charset=\"utf-8\">\n" +
                "<TITLE>注册用户</TITLE>\n" +
                "</HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<FORM method=\"post\" ACTION=\"/teapot/?_a=3\">\n" +
                "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "<tr>\n<td>\n" +
                "<H1>邮箱注册</H1>\n" +
                "</td></tr><tr><td>" +
                "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n<tr><td>" +
                "电子信箱</td><td><INPUT TYPE=\"TEXT\" NAME=\"email\"></td></tr>\n<tr><td>" +
                "昵称</td><td><INPUT TYPE=\"TEXT\" NAME=\"nickname\"></td></tr>\n<tr><td>" +
                "密码</td><td><INPUT TYPE=\"password\" NAME=\"passworda\"></td></tr>\n<tr><td>" +
                "确认密码</td><td><INPUT TYPE=\"password\" NAME=\"passwordb\"></td></tr>\n<tr><td>" +
                "&nbsp;</td><td><INPUT TYPE=\"checkbox\" NAME=\"reg-code\">我已阅读并同意《使用协议》</td></tr>\n<tr><td>" +
                "&nbsp;</td><td><INPUT TYPE=\"SUBMIT\" name =\"register\" value=\"注册\"></td></tr>\n" +
                "</table>" +
                "</td></tr></table>" +
                "</FORM>\n" +
                "</BODY>\n</HTML>");
        } else {
            PrintWriter out = resp.getWriter();
            String docType = "<!DOCTYPE HTML>\n";
            out.println(docType +
                "<HTML lang='zh-CN'>\n" +
                "<HEAD>\n" +
                "<meta charset=\"utf-8\">\n" +
                "<TITLE>注册用户</TITLE>\n" +
                "</HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<FORM method=\"post\" ACTION=\"/teapot/t/?_a=3\">\n" +
                "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "<tr>\n<td>\n" +
                "<H1>邮箱注册</H1>\n" +
                "</td></tr><tr><td>" +
                "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n<tr><td>" +
                "电子信箱</td><td><INPUT TYPE=\"TEXT\" NAME=\"email\" value=\"" + req.getParameter("email") + "\"></td></tr>\n<tr><td>" +
                "昵称</td><td><INPUT TYPE=\"TEXT\" NAME=\"nickname\" value=\"" + req.getParameter("nickname") + "\"></td></tr>\n<tr><td>" +
                "密码</td><td><INPUT TYPE=\"password\" NAME=\"passworda\" value=\"" + req.getParameter("passworda") + "\"></td></tr>\n<tr><td>" +
                "确认密码</td><td><INPUT TYPE=\"password\" NAME=\"passwordb\" value=\"" + req.getParameter("passwordb") + "\"></td></tr>\n<tr><td>" +
                "&nbsp;</td><td><INPUT TYPE=\"checkbox\" NAME=\"reg-code\">我已阅读并同意《使用协议》</td></tr>\n<tr><td>" +
                "&nbsp;</td><td><INPUT TYPE=\"SUBMIT\" name =\"register\" value=\"注册\"></td></tr>\n" +
                "</table>" +
                "</td></tr></table>" +
                "</FORM>\n" +
                "</BODY>\n</HTML>");
        }
    }

}
